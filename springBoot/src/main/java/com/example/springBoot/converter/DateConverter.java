package com.example.springBoot.converter;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    //这里可以自己灵活变通
    private String[] pattern = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};

    @Override
    public Date convert(String s) {
        String convertPattern = s.length() == 10 ? pattern[0] : pattern[1];
        SimpleDateFormat format = new SimpleDateFormat(convertPattern);
        try {
            return format.parse(s);
        } catch (ParseException ex) {
            logger.info(ExceptionUtils.getStackTrace(ex));
        }

        return null;
    }
}
