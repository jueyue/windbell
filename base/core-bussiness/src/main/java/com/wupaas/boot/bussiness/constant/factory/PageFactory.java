package com.wupaas.boot.bussiness.constant.factory;

import com.wupaas.boot.bussiness.constant.Order;
import com.wupaas.boot.bussiness.request.PageParams;
import com.wupaas.boot.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;


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
            if (StringUtils.isNotEmpty(params.getSort())) {
                if (Order.ASC.getDes().equals(params.getOrder())) {
                    page.setAsc(params.getSort());
                } else {
                    page.setDesc(params.getSort());
                }
            }
            return page;
        }
    }
}
