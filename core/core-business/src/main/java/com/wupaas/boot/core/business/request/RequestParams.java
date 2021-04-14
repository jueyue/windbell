package com.wupaas.boot.core.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 时间查询条件
 *
 * @author by jueyue on 18-6-15.
 */
@Data
@EqualsAndHashCode
@ApiModel("参数(包含扩展)")
public class RequestParams<T> extends PageParams<T> {

    public RequestParams() {

    }

    public RequestParams(T model) {
        super.setModel(model);
    }

    @ApiModelProperty("扩展参数")
    private Map<String, String> map;


}
