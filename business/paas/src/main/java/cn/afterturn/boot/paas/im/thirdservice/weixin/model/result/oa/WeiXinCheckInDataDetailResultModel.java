package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by dbinary on 2019/7/24
 *
 *
 */
@Data
public class WeiXinCheckInDataDetailResultModel {
    /**
     * 用户id
     *
     */
    private String userid;
    /**
     * groupname	打卡规则名称
     *
     */
    private String groupname;
    /**
     *  * checkin_type	打卡类型。字符串，目前有：上班打卡，下班打卡，外出打卡
     *
     */
    @JsonProperty("checkin_type")
    private String checkinType;
    /**
     * exception_type	异常类型，字符串，包括：时间异常，地点异常，未打卡，wifi异常，非常用设备。如果有多个异常，以分号间隔
     *
     */
    @JsonProperty("exception_type")
    private String exceptionType;
    /**
     *  checkin_time	打卡时间。Unix时间戳

     */
    @JsonProperty("checkin_time")
    private int checkinTime;
    /**
     *  location_title	打卡地点title

     */
    @JsonProperty("location_title")
    private String locationTitle;
    /**
     * *      *      *      *      *      * location_detail	打卡地点详情

     */
    @JsonProperty("location_detail")
    private String locationDetail;
    /**
     *  wifiname	打卡wifi名称

     */
    private String wifiname;
    /**
     *   notes	打卡备注

     */
    private String notes;
    /**
     *  wifimac	打卡的MAC地址/bssid

     */
    private String wifimac;
    /**
     * mediaids	打卡的附件media_id，可使用media/get获取附件
     */
    private List<String> mediaids;
}
