package com.wupaas.boot.core.web.iview;

import lombok.Data;

import java.util.List;

/**
 * iview 的菜单对象
 *
 * @author by jueyue on 19-6-28.
 */
@Data
public class IViewMenu {

    /**
     * 数据ID
     */
    private String          id;
    /**
     * 前端路径
     */
    private String        path;
    /**
     * 名称
     */
    private String        name;
    private String        open;
    private String        menu;
    private String        num;
    private String        access;
    private String        icon;
    private String        redirect;
    private String        component;
    private IViewMenuMeta meta;

    private List<IViewMenu> children;

    @Data
    static private class IViewMenuMeta {
        /**
         * 访问权限
         */
        private String  access;
        /**
         * 图标
         */
        private String  icon;
        /**
         * 分组标题
         */
        private String  title;
        /**
         * 是否缓存
         */
        private boolean notCache = false;
        /**
         * 关闭tab回调函数
         */
        private String  beforeCloseName;

    }
}
