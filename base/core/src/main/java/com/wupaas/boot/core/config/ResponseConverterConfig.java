package com.wupaas.boot.core.config;

import com.wupaas.boot.bussiness.response.Response;
import com.wupaas.boot.bussiness.response.SuccessResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author JueYue
 * 统一日期转换
 */
@Component
public class ResponseConverterConfig implements Converter<String, Response> {


    @Override
    public Response convert(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return new SuccessResponse();
    }
}
