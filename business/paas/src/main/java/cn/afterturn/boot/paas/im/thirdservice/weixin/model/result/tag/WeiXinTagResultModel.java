package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.tag;

import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import lombok.Data;

/**
 * @author by dbinary on 2019/7/20.
 */
@Data
public class WeiXinTagResultModel extends WeXinBaseResultModel {
    /**
     * 是	标签名称，长度限制为32个字以内（汉字或英文字母），标签名不可与其他标签重名。
     */
    private String tagname;
    /**
     * 否	标签id，非负整型，指定此参数时新增的标签会生成对应的标签id，不指定时则以目前最大的id自增。
     */

    private String   tagid;

}
