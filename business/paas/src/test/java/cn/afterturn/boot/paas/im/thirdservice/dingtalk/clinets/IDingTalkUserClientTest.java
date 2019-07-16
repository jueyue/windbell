package cn.afterturn.boot.paas.im.thirdservice.dingtalk.clinets;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.im.thirdservice.ThirdServiceContext;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.DingTalkUserModel;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.user.DingTalkUserResultModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IDingTalkUserClientTest {

    @Autowired
    private IDingTalkUserClient dingTalkUserClient;

    @Before
    public void init() {
        ThirdServiceContext.put(ThirdServiceContext.TENANT_ID, "TE00000001");
        ThirdServiceContext.put(ThirdServiceContext.APP_TYPE, "20");
        ThirdServiceContext.put(ThirdServiceContext.APP_CODE, "1001");
    }

    @Test
    public void create() {
        DingTalkUserModel user = new DingTalkUserModel();
        user.setUserid("1000000000001");
        user.setName("测试");
        user.setDepartment(Arrays.asList(new int[]{1}));
        user.setMobile("13112345678");
        DingTalkUserResultModel userResultModel = dingTalkUserClient.create(user);
        Assert.assertTrue(userResultModel.isSuccess());
    }
}