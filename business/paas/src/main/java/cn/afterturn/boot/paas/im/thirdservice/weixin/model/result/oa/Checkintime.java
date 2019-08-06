package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * dbinary: 2019-07-24 10:48:27
 *
 * @author dbinary
 */
@Data
public class Checkintime {
    /**
     * 上班时间，表示为距离当天0点的秒数。
     */
    @JsonProperty("work_sec")
    private int workSec;
    /**
     * 下班时间，表示为距离当天0点的秒数。
     */
    @JsonProperty("off_work_sec")
    private int offWorkSec;
    /**
     * 上班提醒时间，表示为距离当天0点的秒数。
     */
    @JsonProperty("remind_work_sec")
    private int remindWorkSec;
    /**
     * 下班提醒时间，表示为距离当天0点的秒数。
     */
    @JsonProperty("remind_off_work_sec")
    private int remindOffWorkSec;
}