package com.wupaas.boot.web.iview;

import lombok.Data;

import java.util.List;

/**
 * 树形空间
 *
 * @author by jueyue on 19-6-2.
 * @href https://www.iviewui.com/components/tree
 */
@Data
public class IViewTree {

    /**
     * 数据ID
     */
    private String          id;
    /**
     * 标题
     */
    private String          title;
    /**
     * 是否展开直子节点
     */
    private boolean         expand;
    /**
     * 禁掉响应
     */
    private boolean         disabled;
    /**
     * 禁掉 checkbox
     */
    private boolean         disableCheckbox;
    /**
     * 是否选中子节点
     */
    private boolean         selected;
    /**
     * 是否勾选(如果勾选，子节点也会全部勾选)
     */
    private boolean         checked;
    /**
     * 子节点属性数组
     */
    private List<IViewTree> children;
}
