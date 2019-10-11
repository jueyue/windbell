package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.common.context.ThirdServiceContext;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinCheckInDataModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinCheckInOptionModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa.WeiXinCheckInDataResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.oa.WeiXinCheckOptionModel;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by dbinary on 2019/7/24
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeiXinCheckInDataClientTest {
    @Autowired
    private WeiXinCheckInDataClient weiXinCheckInDataClient;
    @Before
    public void init(){
        ThirdServiceContext.put(ThirdServiceContext.TENANT_ID, "TE00000001");
        ThirdServiceContext.put(ThirdServiceContext.APP_TYPE, "10");
        ThirdServiceContext.put(ThirdServiceContext.APP_CODE, "1004");
    }
    @Test
    public void getcheckindata() {
        WeiXinCheckInDataModel model = new WeiXinCheckInDataModel();
        model.setEndtime(System.currentTimeMillis());
        model.setOpencheckindatatype("3");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY,-4);

        model.setStarttime(c.getTimeInMillis());
        model.setUseridlist(Arrays.asList("48"));
        WeiXinCheckInDataResultModel result =  weiXinCheckInDataClient.getCheckInData(model);
        System.out.println(JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());

    }

    @Test
    public void getcheckinoption() {
        WeiXinCheckInOptionModel model  = new WeiXinCheckInOptionModel();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);

        model.setDatetime(c.getTimeInMillis());
        model.setUseridlist(Arrays.asList("48"));
        System.out.println(JSON.toJSONString(model));
        WeiXinCheckOptionModel result =  weiXinCheckInDataClient.getCheckInOption(model);
        System.out.println(JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }
}
