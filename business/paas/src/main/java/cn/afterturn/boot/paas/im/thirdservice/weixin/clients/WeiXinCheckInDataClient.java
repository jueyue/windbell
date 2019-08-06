package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.im.thirdservice.weixin.config.WeiXinConfiguration;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinCheckInDataModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinCheckInOptionModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa.WeiXinCheckInDataResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa.WeiXinCheckOptionModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dbinary on 2019/7/24
 * <p>
 * /
 * OA  打卡服务
 *
 */
@FeignClient(name = "weiXinCheckInDataClient",url = "https://qyapi.weixin.qq.com/cgi-bin/checkin",contextId = "weixin",configuration =  WeiXinConfiguration.class)
public interface WeiXinCheckInDataClient {
    /**
     * 获取打卡OA数据
     * @param model
     * @return
     */
    @RequestMapping(value = "/getcheckindata", method = RequestMethod.POST)
    public WeiXinCheckInDataResultModel getCheckInData(@RequestBody WeiXinCheckInDataModel model );
    /**
     * 获取打卡规则
     * @param model
     * @return
     *
     *
     * datetime    是	需要获取规则的日期当天0点的Unix时间戳
     * useridlist	是	需要获取打卡规则的用户列表
     */
    @RequestMapping(value = "/getcheckinoption", method = RequestMethod.POST)
    public WeiXinCheckOptionModel getCheckInOption(@RequestBody WeiXinCheckInOptionModel model );











}
