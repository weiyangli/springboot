package com.example.springBoot.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.springBoot.bean.Checking;
import com.example.springBoot.bean.ImportChecking;
import com.example.springBoot.bean.Result;
import com.example.springBoot.dao.CheckingMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 提供考勤相关的服务
 * <p>
 * 查询字典、创建字典、修改字典、删除字典等服务。
 */
@Service
@Getter
@Setter
public class CheckingService {

    // 日志打印
    private static Logger logger = LoggerFactory.getLogger(CheckingService.class.getName());

    @Autowired
    private CheckingMapper checkingMapper;

    @Autowired
    private BaseService baseService;


    /**
     * 考勤查询
     *
     * @return
     */
    public List<Checking> findChecking() {
        List<Checking> result = checkingMapper.findAll();
        return result;
    }

    /**
     * 添加考勤
     *
     * @return
     */
    public void insertChecking(Checking checking) {
        checkingMapper.save(checking);
    }

    /**
     * 考勤导入
     *
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<String> importChecking(MultipartFile file) throws Exception {
        List<ImportChecking> importChecking = new ArrayList<>();
        byte[] buffer = IOUtils.toByteArray(file.getInputStream());

        // [1] 加载账户数据
        ImportParams userParams = new ImportParams();
        userParams.setStartRows(0);
        // 必须存在的列
        userParams.setImportFields(new String[]{"姓名", "日期"});
        userParams.setStartSheetIndex(0);
        importChecking.addAll(ExcelImportUtil.importExcel(new ByteArrayInputStream(buffer), ImportChecking.class, userParams));
        // 分析数据
        List<ImportChecking> resultList = analyzeData(importChecking);
        // 导出 excel
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String name = year + "年"+month+"月" +"打卡记录";
        Workbook book = ExcelExportUtil.exportExcel(new ExportParams(name, "考勤"), ImportChecking.class, resultList);
        // 转正式文件夹
        String finalUrl =  baseService.exportExcel(book,"考勤", 0l);
        // 插入打卡记录
        Checking checking = new Checking();
        checking.setName(name).setUrl(finalUrl);
        insertChecking(checking);
        return Result.ok();
    }

    /**
     * 考勤分析
     *
     * @param importChecking
     */
    private List<ImportChecking> analyzeData(List<ImportChecking> importChecking) throws Exception {
        List<ImportChecking> resultList = new ArrayList<>();
        // 根据名字分组
        Map<String, List<ImportChecking>> maps = importChecking.stream().collect(Collectors.groupingBy(ImportChecking::getName));
        // 获取上个月第25号和本月26号时间
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.add(Calendar.MONTH, -3);
        lastCalendar.set(Calendar.DAY_OF_MONTH, 25);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        maps.forEach((k, v) -> {
            // 上月 26 到本月 25
            // v 代表当前员工一个月的考勤记录
            int num = 1;
            String day = null;
            while (day == null || !day.equals(sdf.format(calendar.getTime()))) {
                Date date = DateUtils.addDays(lastCalendar.getTime(), num++);
                day = sdf.format(date);
                String week =  dateToWeek(day);
                ImportChecking importCheckingBean = new ImportChecking();
                importCheckingBean.setName(k);
                importCheckingBean.setExportDate(day);
                importCheckingBean.setRemark("正常");
                importCheckingBean.setWeek(week);
                List<ImportChecking> checkingStream = v.stream().filter(x ->
                    x.getNewDate() != null && sdf.format(x.getNewDate()).equals(sdf.format(date))).collect(Collectors.toList());
                List<ImportChecking> empCheck = checkingStream.stream().sorted(Comparator.comparing(ImportChecking::getNewDate)).collect(Collectors.toList());
                if (checkingStream.size() > 0) {
                    // 根据时间排序
                    // 当天有考勤记录检查其他标准
                    try {
                        long morning = sdf2.parse(sdf.format(date) + " 08:31:00").getTime();
                        long after = sdf2.parse(sdf.format(date) + " 17:30:00").getTime();
                        if (empCheck.size() == 1) {
                            importCheckingBean.setExportDate(empCheck.get(0).getExportDate());
                            if (empCheck.get(0).getNewDate().getTime() <= morning) {
                                importCheckingBean.setRemark("早上正常，下午缺卡");
                            } else if (morning < empCheck.get(0).getNewDate().getTime() && empCheck.get(0).getNewDate().getTime() < after) {
                                importCheckingBean.setRemark("缺卡，迟到或早退!");
                            } else if (empCheck.get(0).getNewDate().getTime() >= after) {
                                importCheckingBean.setRemark("下午正常，早上缺卡");
                            }
                        } else if (empCheck.size() == 2) {
                            importCheckingBean.setExportDate(empCheck.get(0).getExportDate() + "-" + empCheck.get(1).getExportDate() );
                            if (empCheck.get(0).getNewDate().getTime() > morning) {
                                importCheckingBean.setRemark("迟到");
                            } else if (morning < empCheck.get(1).getNewDate().getTime() &&  empCheck.get(1).getNewDate().getTime() < after) {
                                importCheckingBean.setRemark("早退");
                            } else if (empCheck.get(0).getNewDate().getTime() > morning && empCheck.get(1).getNewDate().getTime() < after) {
                                importCheckingBean.setRemark("迟到加早退");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    importCheckingBean.setRemark("当天没有打卡记录!");
                }
                resultList.add(importCheckingBean);
            }
        });
        return resultList;
    }
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
