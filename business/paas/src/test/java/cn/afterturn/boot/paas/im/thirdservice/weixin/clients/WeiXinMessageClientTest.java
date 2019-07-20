package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.im.thirdservice.ThirdServiceContext;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinMessageModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.message.WeiXinMessageResultModel;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dbinary on 2019/7/20
 * 发送应用消息
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeiXinMessageClientTest {
    @Autowired
    private WeiXinMessageClient weiXinMessageClient;
    @Before
    public void init(){
        ThirdServiceContext.put(ThirdServiceContext.TENANT_ID, "TE00000001");
        ThirdServiceContext.put(ThirdServiceContext.APP_TYPE, "10");
        ThirdServiceContext.put(ThirdServiceContext.APP_CODE, "1003");
    }
    @Test
    public void sendText() {
        WeiXinMessageModel model = new WeiXinMessageModel();
        model.setAgentid(1000019);
        model.setMsgtype("text");
        model.setSafe(0);
        model.setTouser("48|48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("content","i'm work in shandong dazheng company one years ");
        model.setText(maps);
        System.out.println("sendtext=---------------- "+ JSON.toJSONString(model));
//        model.setText("i'm work in shandong dazheng company one years ");
        WeiXinMessageResultModel result = weiXinMessageClient.sendText(model);
        System.out.println("sendtext=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendPicture() {
        WeiXinMessageModel model = new WeiXinMessageModel();
        WeiXinMessageResultModel result = weiXinMessageClient.sendPicture(model);
        System.out.println("sendPicture=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendVoice() {
        WeiXinMessageModel model = new WeiXinMessageModel();
        WeiXinMessageResultModel result = weiXinMessageClient.sendText(model);
        System.out.println("sendVoice=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendVeido() {
        WeiXinMessageModel model = new WeiXinMessageModel();
        WeiXinMessageResultModel result = weiXinMessageClient.sendText(model);
        System.out.println("sendVeido=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendTextcard() {
        WeiXinMessageModel model = new WeiXinMessageModel();
        WeiXinMessageResultModel result = weiXinMessageClient.sendText(model);
        System.out.println("sendtext=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }
}
