package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa;
import lombok.Data;

import java.util.List;

/**
 * dbinary: 2019-07-24 10:48:27
 *
 * @author dbinary
 */
@Data
public class SpeOffdays {
    /**
     * 	特殊日期具体时间
     */

    private int timestamp;
    /**
     * 特殊日期备注
     */
    private String notes;
    private List<String> checkintime;


}