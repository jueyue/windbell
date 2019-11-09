package cn.afterturn.boot.facade.paas.msg.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 消息发送实体
 *
 * @author by jueyue on 19-8-29.
 */
@Data
public class NoticeRequestEntity {

    private Integer type;

    private String templateCode;

    private Date sendTime;

    private String rolesIds;

    private String userIds;

    private String deptIds;

    private String address;

    private Map<String, String> data;

}
