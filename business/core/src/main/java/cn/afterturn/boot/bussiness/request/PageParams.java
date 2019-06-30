package cn.afterturn.boot.bussiness.request;

import cn.afterturn.boot.bussiness.constant.state.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by jueyue on 18-6-15.
 */
@Data
@ApiModel("分页参数")
public class PageParams<T> {

    @ApiModelProperty("查询参数")
    private T model;

    @ApiModelProperty("页面大小")
    private Integer pageSize = 10;

    @ApiModelProperty("当前页")
    private Integer page = 1;

    @ApiModelProperty("排序,默认CRT_TIME")
    private String sort = "CRT_TIME";

    @ApiModelProperty("排序规则,asc||desc ,默认desc")
    private String order = Order.DESC.getDes();
}
