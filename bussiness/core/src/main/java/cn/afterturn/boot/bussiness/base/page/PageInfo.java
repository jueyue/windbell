package cn.afterturn.boot.bussiness.base.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页结果的封装(for Bootstrap Table)
 *
 * @author fengshuonan
 * @Date 2017年1月22日 下午11:06:41
 */
@Data
@ApiModel(value = "分页数据")
public class PageInfo<T> {

    @ApiModelProperty(value = "数据", dataType = "array")
    private List<T> rows;

    @ApiModelProperty("总数")
    private long total;

    public PageInfo(Page<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }

}
