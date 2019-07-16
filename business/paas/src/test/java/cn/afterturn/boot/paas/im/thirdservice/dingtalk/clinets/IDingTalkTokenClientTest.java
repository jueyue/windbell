package cn.afterturn.boot.paas.im.thirdservice.dingtalk.clinets;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkTokenResultModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IDingTalkTokenClientTest {

    @Autowired
    private IDingTalkTokenClient dingTalkTokenClient;

    @Test
    public void getToken() {
        DingTalkTokenResultModel resultModel = dingTalkTokenClient.getToken("ding92wmevtj2qdzvytm", "Gg3NRVA_bqmf73lFwUHqE7X-6Re_zujXZ1EBv8yZRg6GH_EqAiGLhvr8gsViAxdE");
        Assert.assertTrue(resultModel.isSuccess());
    }

}