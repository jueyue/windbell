package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeiXinTokenResultModel;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IWeiXinTokenClientTest {

    @Autowired
    private IWeiXinTokenClient weiXinTokenClient;

    @Test
    public void gettoken() {
        WeiXinTokenResultModel model = weiXinTokenClient.getToken("ww45033b600504283b", "U_fE5u6YF5-JIm6BUZhtV3RqzA1S_ds4S-016b7DPqM");
        System.out.println(JSON.toJSONString(model));
        Assert.assertTrue(model.getErrcode() == 0);
    }


}