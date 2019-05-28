package com.example.springBoot.controller;

import com.example.springBoot.bean.Checking;
import com.example.springBoot.bean.Result;
import com.example.springBoot.service.CheckingService;
import com.example.springBoot.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * 考勤相关控制器
 *
 * 提供字典的增加、修改、删除、查询的相关接口
 */
@Controller
public class CheckingController {

    private static Logger logger = LoggerFactory.getLogger(CheckingController.class.getName());

    @Autowired
    private CheckingService checkingService;
    /**
     * 考勤查询
     *
     * @return
     */
    @GetMapping(Urls.API_CHECKING)
    @ResponseBody
    public Result<List<Checking>> findChecking() {
        return Result.ok(checkingService.findChecking());
    }

    /**
     * 考勤查询
     *
     * @return
     */
    @GetMapping("/api/page/checking")
    @ResponseBody
    public Result<List<Checking>> findCheckingToPage(@RequestParam(defaultValue = "1", required = false) int currentPage,
                                                     @RequestParam(defaultValue = "2", required = false) int pageSize) {
        // 计算偏移量
        int offset = PageUtils.offset(currentPage, pageSize);
        return Result.ok(checkingService.findCheckingToPage(offset, pageSize));
    }


    /**
     * 添加考勤
     *
     * @return
     */
    @PostMapping(Urls.API_CHECKING)
    @ResponseBody
    public void insertChecking(@RequestBody Checking checking) {
        checkingService.insertChecking(checking);
    }

    /**
     * 导入学员成绩(不含证书)
     * URL: http://localhost:8080/api/checking/import
     *
     * @param file 导入用户的 excel 文件流
     * @return 返回导入结果
     * @throws Exception
     */
    @PostMapping(Urls.API_CHECKING_IMPORT)
    @ResponseBody
    public Result<String> importChecking(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info("考勤导入)");
        return checkingService.importChecking(file);
    }

}
