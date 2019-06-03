package cn.afterturn.boot.bussiness.constant.factory;

import cn.afterturn.boot.bussiness.constant.state.Order;
import cn.afterturn.boot.bussiness.model.PageParams;
import cn.afterturn.boot.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * 默认的分页参数创建
 *
 * @author JueYue
 * @date 2017-04-05 22:25
 */
public class PageFactory {


    public static Page init(PageParams params) {
        if (ToolUtil.isEmpty(params.getSort())) {
            Page page = new Page(params.getPage(), params.getPageSize());
            return page;
        } else {
            Page page = new Page(params.getPage(), params.getPageSize());
            if (Order.ASC.getDes().equals(params.getOrder())) {
                page.setAsc(params.getSort());
            } else {
                page.setDesc(params.getSort());
            }
            return page;
        }
    }
}
