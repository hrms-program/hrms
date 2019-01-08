package com.paladin.hrms.service.assess.dto;

import com.paladin.framework.excel.write.ValueFormator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/12/1 12:26
 */
public class DateValueFormator implements ValueFormator {

    private ThreadLocal<SimpleDateFormat> formatLocal = new ThreadLocal<SimpleDateFormat>() {
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    @Override
    public String format(Object value) {
        if (value != null && value instanceof Date) {
            String format = formatLocal.get().format((Date) value);
            return format;
        } else {
            return "无";
        }
    }
}
