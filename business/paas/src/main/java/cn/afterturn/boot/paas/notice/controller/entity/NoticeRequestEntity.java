package cn.afterturn.boot.paas.notice.controller.entity;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("类型")
    private Integer             type;
    @ApiModelProperty("租户")
    private String              tenantId;
    @ApiModelProperty("模板")
    private String              templateCode;
    @ApiModelProperty("定时发送时间,空立刻发送")
    private Date                sendTime;
    @ApiModelProperty("角色")
    private String              rolesIds;
    @ApiModelProperty("用户")
    private String              userIds;
    @ApiModelProperty("部门")
    private String              deptIds;
    @ApiModelProperty("地址")
    private String              address;
    @ApiModelProperty("数据")
    private Map<String, Object> data;

}
