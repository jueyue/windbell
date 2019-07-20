package cn.afterturn.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by dbinary on 2019/7/20
 * <p>
 */
@Data
public class WeiXinMessageModel {
    /**
     *成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向该企业应用的全部成员发送
     */
    private String touser;
    /**
     *部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
     */
    private String toparty;
    /**
     *标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
     */
    private String totag;
    /**
     *消息类型，w文本发送此时固定为：text
     */
    private String msgtype;
    /**
     *企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
     */
    private int agentid;
    /**
     *消息内容，最长不超过2048个字节，超过将截断---文字
     */
    private  Map<String,Object> text;
    /**
     *消息内容，最长不超过2048个字节，超过将截断 --图片
     */
    private  Map<String,Object> image;
    /**
     *表示是否是保密消息，0表示否，1表示是，默认0
     */
    private int safe;

}
