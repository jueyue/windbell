package cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa;

import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by dbinary on 2019/7/24
 * <p>
 * /
 * /**
 *
 * @author by Administrator on 2019/7/24.
 */
@Data
public class WeiXinCheckInDataResultModel extends WeXinBaseResultModel {

    private List<WeiXinCheckInDataDetailResultModel> checkindata;

}
