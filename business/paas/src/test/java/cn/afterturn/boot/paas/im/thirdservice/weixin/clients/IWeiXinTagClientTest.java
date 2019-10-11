package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.common.context.ThirdServiceContext;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinTagModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinTagUserListModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.tag.WeiXinTagListResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.tag.WeiXinTagResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.tag.WeiXinTagUserListResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.tag.WeiXinTagUserResultModel;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by dbinary on 2019/7/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IWeiXinTagClientTest {
    @Autowired
    private IWeiXinTagClient weiXinTagClient;
    @Before
    public void init(){
        ThirdServiceContext.put(ThirdServiceContext.TENANT_ID, "TE00000001");
        ThirdServiceContext.put(ThirdServiceContext.APP_TYPE, "10");
        ThirdServiceContext.put(ThirdServiceContext.APP_CODE, "1002");
    }

    @Test
    public void create() {
        WeiXinTagModel model = new  WeiXinTagModel();
        model.setTagid("2");
        model.setTagname("分期板块");
        WeiXinTagResultModel result =   weiXinTagClient.create(model);
        System.out.println("create------------------------"+JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void update() {
        WeiXinTagModel model = new  WeiXinTagModel();
        model.setTagid("2");
        model.setTagname("分期板块1");
        WeXinBaseResultModel result =   weiXinTagClient.update(model);
        System.out.println("update------------------------"+JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void delete() {
        WeXinBaseResultModel result =   weiXinTagClient.delete("2");
        System.out.println(JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void get() {
        WeiXinTagUserResultModel result = weiXinTagClient.get("2");
        System.out.println("get tag detail------------------------"+JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void addtagusers() {
        WeiXinTagUserListModel model = new WeiXinTagUserListModel();
        model.setTagid("2");
        model.setUserlist(Arrays.asList(new String[]{"48"}));
        model.setPartylist(Arrays.asList(new Integer[]{25}));
        WeiXinTagUserListResultModel result = weiXinTagClient.addtagusers(model);
        System.out.println("add tagusres------------------------"+JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void deltagusers() {
        WeiXinTagUserListModel model = new WeiXinTagUserListModel();
        model.setTagid("2");
        model.setUserlist(Arrays.asList(new String[]{"48"}));
//        model.setPartylist(Arrays.asList(new Integer[]{25}));
        WeiXinTagUserListResultModel result = weiXinTagClient.deltagusers(model);
        System.out.println(JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void list() {
        WeiXinTagListResultModel result = weiXinTagClient.list("2");
        System.out.println("list------------------------"+JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }
}
