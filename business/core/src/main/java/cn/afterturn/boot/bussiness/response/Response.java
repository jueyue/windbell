package cn.afterturn.boot.bussiness.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午11:58:00
 */
@Data
public abstract class Response<T> {

    @ApiModelProperty("数据")
    protected T data;
    @ApiModelProperty("返回值")
    protected int code;
    @ApiModelProperty("描述")
    protected String msg;
}
