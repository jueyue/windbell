package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.oa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * dbinary: 2019-07-24 10:48:27
 *
 * @author dbinary
 */
@Data
public class LocInfos {
    /**
     *位置打卡地点纬度
     */

    private int lat;
    /**
     *位置打卡地点经度
     */
    private int lng;
    /**
     *	位置打卡地点名称
     */
    @JsonProperty("loc_title")
    private String locTitle;
    /**
     *	位置打卡地点详情
     */
    @JsonProperty("loc_detail")
    private String locDetail;
    /**
     *	允许打卡范围（米）
     */
    private int distance;


}