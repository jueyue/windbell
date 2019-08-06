package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * Auto-generated: 2019-07-24 10:48:27
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
@Data
public class Group {
    /**
     * 打卡规则类型。1：固定时间上下班；2：按班次上下班；3：自由上下班 。
     */
    private int grouptype;
    /**
     *打卡规则id
     */
    private int groupid;
    /**
     *打卡时间
     */
    private List<Checkindate> checkindate;
    /**
     *特殊日期
     */
    @JsonProperty("spe_workdays")
    private List<SpeWorkdays> speWorkdays;
    /**
     *特殊
     */
    @JsonProperty("spe_offdays")
    private List<SpeOffdays> speOffdays;
    /**
     *是否同步法定节假日
     */
    @JsonProperty("sync_holidays")
    private boolean syncHolidays;
    /**
     *	打卡规则名称
     */
    private String groupname;
    /**
     *	是否打卡必须拍照
     */
    @JsonProperty("need_photo")
    private boolean needPhoto;
    /**
     *	WiFi打卡地点信息
     */
    @JsonProperty("wifimac_infos")
    private List<WifimacInfo> wifimacInfos;
    /**
     *	是否备注时允许上传本地图片
     */
    @JsonProperty("note_can_use_local_pic")
    private boolean noteCanUseLocalPic;
    /**
     *是否允许异常打卡时提交申请
     */
    @JsonProperty("allow_checkin_offworkday")
    private boolean allowCheckinOffworkday;
    /**
     *
     */
    @JsonProperty("allow_apply_offworkday")
    private boolean allowApplyOffworkday;
    /**
     *位置打卡地点信息
     */
    @JsonProperty("loc_infos")
    private List<LocInfos> locInfos;


}