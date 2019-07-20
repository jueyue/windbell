package cn.afterturn.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

/**
 * @author by dbinry on 2019/7/19.
 */
@Data
public class WeiXinDeptModel {
    /**
     *部门id，32位整型，指定时必须大于1。若不填该参数，将自动生成id
     */
    private int id;
    /**
     *部门名称。长度限制为1~32个字符，字符不能包括\:?”<>｜
     */
    private String name;
    /**
     *父部门id，32位整型
     */
    private int parentid;
    /**
     *在父部门中的次序值。order值大的排序靠前。有效的值范围是[0, 2^32)
     */
    private int order;

}
