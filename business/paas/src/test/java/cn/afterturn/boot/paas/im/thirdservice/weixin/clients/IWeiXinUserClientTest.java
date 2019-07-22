package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.im.thirdservice.ThirdServiceContext;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinUserInviteModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinUserListModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinUserModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.user.*;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by Administrator on 2019/7/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IWeiXinUserClientTest {
    @Autowired
    private IWeiXinUserClient weiXinUserClient;
    @Autowired
    private IWeiXinTokenClient weiXinTokenClient;
    @Autowired
    private IWeiXinInviteClient weiXinInviteClient;
    @Autowired
    private IWeiXinJoinQrcodeClient weiXinJoinQrcodeClient;
    @Before
    public void init(){
        ThirdServiceContext.put(ThirdServiceContext.TENANT_ID, "TE00000001");
        ThirdServiceContext.put(ThirdServiceContext.APP_TYPE, "10");
        ThirdServiceContext.put(ThirdServiceContext.APP_CODE, "1002");
    }
    @Test
    public void testCreateUser(){
        WeiXinUserModel user = new WeiXinUserModel();
        user.setUserid("1000000000009");
        user.setName("测");
        user.setDepartment(Arrays.asList(new int[]{1}));
        user.setMobile("13112345678");
        WeXinBaseResultModel userResultModel = weiXinUserClient.create(user);
        Assert.assertTrue(userResultModel.isSuccess());
    }
    @Test
    public void testUpdateUser(){
        WeiXinUserModel user = new WeiXinUserModel();
        user.setUserid("1000000000001");
        user.setName("测试");
        user.setDepartment(Arrays.asList(new int[]{2}));
        user.setMobile("13112345663");
        user.setTellephone("0537-6215879");
        user.setEmail("doublebinary@sina.com");
        WeXinBaseResultModel userResultModel = weiXinUserClient.update(user);
        Assert.assertTrue(userResultModel.isSuccess());
    }
    @Test
    public void testDeleteUser(){
        WeXinBaseResultModel userResultModel = weiXinUserClient.delete("1000000000001");
        Assert.assertTrue(userResultModel.isSuccess());
    }
    @Test
    public void testBatchdelete(){
        WeXinBaseResultModel userResultModel = weiXinUserClient.batchdelete(new WeiXinUserListModel(Arrays.asList("1000000000009")));
        Assert.assertTrue(userResultModel.isSuccess());
    }
    @Test
    public void testGetUser(){
        WeiXinUserResultModel userResultModel = weiXinUserClient.get("1000000000009");
        System.out.println(JSON.toJSONString(userResultModel));
        Assert.assertTrue(userResultModel.isSuccess());
    }
    @Test
    public void testSimplelist(){
        WeiXinUserListResultModel userResultModel = weiXinUserClient.simplelist("25","1");
        System.out.println(JSON.toJSONString(userResultModel));
        Assert.assertTrue(userResultModel.isSuccess());
    }
    @Test
    public void testList(){
        WeiXinUserListResultModel userResultModel = weiXinUserClient.list("25","1");
        System.out.println(JSON.toJSONString(userResultModel));
        Assert.assertTrue(userResultModel.isSuccess());
    }
    @Test
    public void convert2openid(){
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("userid","342");
        WeiXinUserOpenIdUserIdResultModel resutlt = weiXinUserClient.convert2openid(map);
        System.out.println(JSON.toJSONString(resutlt));
        Assert.assertTrue(resutlt.isSuccess());
    }
    @Test
    public void convert2userid(){
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("openid","oCuJ50VojHaVn9Gdvm2iVnCRE9cU");
        WeiXinUserOpenIdUserIdResultModel resutlt = weiXinUserClient.convert2userid(map);
        System.out.println(JSON.toJSONString(resutlt));
        Assert.assertTrue(resutlt.isSuccess());
    }
    @Test
    public void authsucc(){
        WeXinBaseResultModel resutlt = weiXinUserClient.authsucc("1000000000009");
        System.out.println(JSON.toJSONString(resutlt));
        Assert.assertTrue(resutlt.isSuccess());
    }
    @Test
    public void invite(){
        WeiXinUserInviteModel model = new WeiXinUserInviteModel();
        model.setUser(Arrays.asList(new String []{"48"}));
        model.setParty(Arrays.asList(new Integer[]{25}));
        WeiXinUserInviteResultModel resutlt = weiXinInviteClient.invite(model);
        System.out.println(JSON.toJSONString(resutlt));
        Assert.assertTrue(resutlt.isSuccess());
    }
    @Test
    public void getJoinQrcode(){

        WeiXinJoinQrcodeResultModel resutlt = weiXinJoinQrcodeClient.getJoinQrcode("30");
        System.out.println(JSON.toJSONString(resutlt));
        Assert.assertTrue(resutlt.isSuccess());
    }
}
