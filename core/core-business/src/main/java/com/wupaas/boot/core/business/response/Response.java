package com.wupaas.boot.core.business.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午11:58:00
 */
@Data
public class Response<T> {

    public Response() {

    }

    @ApiModelProperty("数据")
    protected T       data;
    @ApiModelProperty("返回值")
    protected int     code;
    @ApiModelProperty("是否成功")
    protected boolean success;
    @ApiModelProperty("描述")
    protected String  msg;
}
