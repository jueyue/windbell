package com.wupaas.boot.paas.im.thirdservice.weixin.model.result.oa;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * dbinary: 2019-07-24 10:48:27
 *
 * @author dbinary
 */
@Data
public class Checkindate {
    /**
     * 工作日。若为固定时间上下班或自由上下班，则1到6分别表示星期一到星期六，0表示星期日；若为按班次上下班，则表示拉取班次的日期。
     */
    private List<Integer> workdays;
    /**
     *s上下班时间提醒时间汇总类
     */
    private List<Checkintime> checkintime;
    /**
     * 弹性时间（毫秒）
     */
    @JsonProperty("flex_time")
    private int flexTime;
    /**
     * 	下班不需要打卡
     */

    @JsonProperty("noneed_offwork")
    private boolean noneedOffwork;
    /**
     * 打卡时间限制（毫秒）
     */
    @JsonProperty("limit_aheadtime")
    private int limitAheadtime;

}