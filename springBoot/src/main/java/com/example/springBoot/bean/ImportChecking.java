package com.example.springBoot.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class ImportChecking {
    @Excel(name="姓名")
    private String name;

    @Excel(name="日期", height=20, width = 50)
    private String exportDate;

    @Excel(name="星期", height=20)
    private String week;

    private Date date;

    @Excel(name="备注")
    private String remark;

    public Date getNewDate() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        if (!StringUtils.isBlank(exportDate)) {
            try {
                date = sdf2.parse(exportDate.replace("/", "-"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }
}
