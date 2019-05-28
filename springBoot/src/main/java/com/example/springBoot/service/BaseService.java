package com.example.springBoot.service;

import com.example.springBoot.controller.Urls;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Getter
@Setter
@Accessors(chain = true)
public class BaseService {

    @Value("${tempUploadDirectory}")
    private String tempUploadDirectory; // 上传文件的临时文件夹

    /**
     * 根据参数导出 Excel 文件
     *
     * @param book Workbook 对象
     * @param fileName 文件名
     * @param userId 用户 ID
     *
     * @return String
     */
    public String exportExcel(Workbook book, String fileName, Long userId) throws IOException {
        // 1) 定义变量
        // 2) 校验参数
        // 3) 写入文件流
        // 4) 输出对象
        // 1.1、定义返回对象
        // 1.2、定义文件类型
        // 1.3、定义临时文件名
        // 1.4、定义临时访问路径

        // [1.1]
        // [1.2]
        String url = "";
        String       extension    = "xls";
        // [1.3]
        String         fileId       = UUID.randomUUID().toString().substring(0, 33).replace("-", "");
        String       tempFilename = fileId + (StringUtils.isBlank(extension) ? "" : "." + extension); // 临时文件名
        // [1.4]
        String       tempUrl      = Urls.URL_TEMPORARY_FILE_PREFIX + tempFilename;


        // [2]

        if(book == null){
            return url;
        }
        if(StringUtils.isBlank(fileName)){
            return url;
        }
        if(userId == null){
            return url;
        }
        // [3]
        File tempFile = new File(tempUploadDirectory, tempFilename);   // 临时文件
        book.write(new FileOutputStream(tempFile));
        return tempUrl;

    }
}
