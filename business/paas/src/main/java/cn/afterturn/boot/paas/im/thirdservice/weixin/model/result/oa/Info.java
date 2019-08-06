package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa;

import lombok.Data;

/**
 * dbinary: 2019-07-24 10:48:27
 *
 * @author dbinary
 */
@Data
public class Info {
    /**
     * 	用户id
     */
    private String userid;
    /**
     * 打卡类型种类划分
     */
    private Group group;

}