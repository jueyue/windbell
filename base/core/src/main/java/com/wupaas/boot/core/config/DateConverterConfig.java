package com.wupaas.boot.core.config;

import com.wupaas.boot.core.util.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JueYue
 * 统一日期转换
 */
@Component
public class DateConverterConfig implements Converter<String, Date> {

    private static final List<String> FORMART_LIST = new ArrayList<>(4);

    static {
        FORMART_LIST.add("yyyy-MM");
        FORMART_LIST.add("yyyy-MM-dd");
        FORMART_LIST.add("yyyy-MM-dd hh:mm");
        FORMART_LIST.add("yyyy-MM-dd hh:mm:ss");
    }


    @Override
    public Date convert(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        try {
            String formatter = "";
            if (s.matches("^\\d{4}-\\d{1,2}$")) {
                formatter = FORMART_LIST.get(0);
            } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                formatter = FORMART_LIST.get(1);
            } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
                formatter = FORMART_LIST.get(2);
            } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
                formatter = FORMART_LIST.get(3);
            } else {
                throw new IllegalArgumentException("Invalid boolean value '" + s + "'");
            }
            return DateUtil.parse(s, formatter);
        } catch (Exception e) {
            return null;
        }
    }
}
