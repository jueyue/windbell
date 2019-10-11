package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.common.context.ThirdServiceContext;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.WeiXinDeptModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.WeXinBaseResultModel;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.dept.WeiXinDeptResultModel;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IWeiXinDeptClientTest {

    @Autowired
    private IWeiXinDeptClient weiXinDeptClient;
    @Before
    public void init(){
        ThirdServiceContext.put(ThirdServiceContext.TENANT_ID, "TE00000001");
        ThirdServiceContext.put(ThirdServiceContext.APP_TYPE, "10");
        ThirdServiceContext.put(ThirdServiceContext.APP_CODE, "1002");
    }
    @Test
    public void create() {
        WeiXinDeptModel model = new WeiXinDeptModel();
        model.setId(1000);
        model.setName("测试大部门");
        model.setOrder(1000);
        model.setParentid(2);
        WeiXinDeptResultModel result  =  weiXinDeptClient.create(model);
        System.out.println("------------------------------------create");
        System.out.println(JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void update() {
        WeiXinDeptModel model = new WeiXinDeptModel();
        model.setId(1000);
        model.setName("测试小部门");
        model.setParentid(2);
        WeXinBaseResultModel result  =    weiXinDeptClient.update(model);
        System.out.println("------------------------------------update");
        System.out.println(JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }



    @Test
    public void list() {
        WeXinBaseResultModel result  =   weiXinDeptClient.list("1000");
        System.out.println("------------------------------------list");
        System.out.println(JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }
   @Test
    public void delete() {
        WeXinBaseResultModel result  =  weiXinDeptClient.delete("1000");
       System.out.println("------------------------------------delete");
        System.out.println(JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }
}