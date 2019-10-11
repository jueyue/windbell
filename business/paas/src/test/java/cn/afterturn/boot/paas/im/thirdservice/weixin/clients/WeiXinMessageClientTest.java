package cn.afterturn.boot.paas.im.thirdservice.weixin.clients;

import cn.afterturn.boot.paas.PaasApplication;
import cn.afterturn.boot.paas.common.context.ThirdServiceContext;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.*;
import cn.afterturn.boot.paas.im.thirdservice.weixin.model.result.message.WeiXinMessageResultModel;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public void sendText() { WeiXinMessageTextModel model = new WeiXinMessageTextModel();
        model.setAgentid(1000019);
        model.setMsgtype("text");
        model.setSafe(0);
        model.setTouser("48");
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
        WeiXinMessagePictureModel model = new WeiXinMessagePictureModel();
        model.setAgentid(1000019);
        model.setMsgtype("image");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("media_id","BlBybfPhlRRtm2QAmvGn_C5Rsy7WRD4K1xMjFlEnscgZhPHSs4RXYOh439KEAajf");
        model.setImage(maps);
        System.out.println("picture json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendPicture(model);
        System.out.println("sendPicture=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendVoice() {
        //沒有 mdedia-id
        WeiXinMessageVoiceModel model = new WeiXinMessageVoiceModel();
        model.setAgentid(1000019);
        model.setMsgtype("voice");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("media_id","BlBybfPhlRRtm2QAmvGn_C5Rsy7WRD4K1xMjFlEnscgZhPHSs4RXYOh439KEAajf");
        model.setVoice(maps);
        System.out.println("voice json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendVoice(model);
        System.out.println("voice=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendVedio() {
        //沒有 mdedia-id
        WeiXinMessageVedioModel model = new WeiXinMessageVedioModel();
        model.setAgentid(1000019);
        model.setMsgtype("video");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("media_id","BlBybfPhlRRtm2QAmvGn_C5Rsy7WRD4K1xMjFlEnscgZhPHSs4RXYOh439KEAajf");
        model.setVedio(maps);
        System.out.println("vedio json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendVeido(model);
        System.out.println("vedio=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }
    @Test
    public void sendFile() {
  //沒有 mdedia-id
        WeiXinMessageFileModel model = new WeiXinMessageFileModel();
        model.setAgentid(1000019);
        model.setMsgtype("file");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("media_id","BlBybfPhlRRtm2QAmvGn_C5Rsy7WRD4K1xMjFlEnscgZhPHSs4RXYOh439KEAajf");
        model.setFile(maps);
        System.out.println("vedio json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendFile(model);
        System.out.println("vedio=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());


    }
    @Test
    public void sendTextcard() {//success
        WeiXinMessageTextCardModel model = new WeiXinMessageTextCardModel();
        model.setAgentid(1000019);
        model.setMsgtype("textcard");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("title","BlBybfPhlRRtm2QAmvGn_C5Rsy7WRD4K1xMjFlEnscgZhPHSs4RXYOh439KEAajf");
        maps.put("description","BlBybfPhlRRtm2QAmvGn_C5Rsy7WRD4K1xMjFlEnscgZhPHSs4RXYOh439KEAajf");
        maps.put("url","www.baidu.com");
        maps.put("btntxt","更多");
        model.setTextcard(maps);
        System.out.println("textcard json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendTextcard(model);
        System.out.println("textcard=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendNews() {  //success
        WeiXinMessageNewsModel model = new WeiXinMessageNewsModel();
        model.setAgentid(1000019);
        model.setMsgtype("news");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();

        List<Map<String,Object>> list  = new ArrayList<Map<String,Object>>();
        Map<String,Object> map2 = new HashMap<>();
         map2.put("title","中秋节礼品领取");
         map2.put("description", "今年中秋节公司有豪礼相送");
         map2.put("url","www.baidu.com");
         map2.put("picurl","http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png");
         list.add(map2);
        map1.put("articles",list);
        model.setNews(map1);
        System.out.println("news json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendNews(model);
        System.out.println("news=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendMpNews() {
        //  queshao   "thumb_media_id": "MEDIA_ID", 缺少
        WeiXinMessageMpnewsModel model = new WeiXinMessageMpnewsModel();
        model.setAgentid(1000019);
        model.setMsgtype("mpnews");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();

        List<Map<String,Object>> list  = new ArrayList<Map<String,Object>>();
        Map<String,Object> map2 = new HashMap<>();
        map2.put("title","中秋节礼品领取");
        map2.put("thumb_media_id", "今年中秋节公司有豪礼相送");
        map2.put("author","dbinary");
        map2.put("content_source_url","www.baidu.com");
        map2.put("content","www.baidu.asdfasdfjasjdkfljasdjfasdkjf");
        map2.put("digest","歐克 qieknao");
        list.add(map2);
        map1.put("articles",list);
        model.setMpnews(map1);
        System.out.println("MpNews json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendMpNews(model);
        System.out.println("MpNews=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendMarkdown() {
        //ok
        WeiXinMessageMarkdownModel model = new WeiXinMessageMarkdownModel();
        model.setAgentid(1000019);
        model.setMsgtype("markdown");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("content", "您的会议室已经预定，稍后会同步到`邮箱"+
                "**事项详情**"+
                ">事　项：<font color=\"info\">开会</font> "+
                ">组织者：@miglioguan"+
                ">参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang"+
                "> "+
                ">会议室：<font color=\"info\">广州TIT 1楼 301</font>"+
                ">日　期：<font color=\"warning\">2018年5月18日</font>"+
                ">时　间：<font color=\"comment\">上午9:00-11:00</font>"+
               ">"+
                ">请准时参加会议。"+
                ">"+
                ">如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)");
        model.setMarkdown(maps);
        System.out.println("markdown json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendMarkdown(model);
        System.out.println("markdown=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendMniprogramNotice() {
        //API接口无权限调用
        WeiXinMessageMniprogramNoticeModel model = new WeiXinMessageMniprogramNoticeModel();
        model.setAgentid(1000019);
        model.setMsgtype("miniprogram_notice");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("appid","wx123123123123123");
        maps.put("page","pages/index?userid=zhangsan&orderid=123123123");
        maps.put("title","会议室预订成功通知");
        maps.put("description", "4月27日 16:16");
        maps.put("emphasis_first_item", true);
        List<Map<String,Object>> list  = new ArrayList<Map<String,Object>>();
        Map<String,Object> map2 = new HashMap<>();
        map2.put("key","中秋节礼品领取");
        map2.put("value", "今年中秋节公司有豪礼相送");
        list.add(map2);
        maps.put("content_item", list);

        model.setMiniprogramNotice(maps);
        System.out.println("sendMniprogramNotice json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendMniprogramNotice(model);
        System.out.println("sendMniprogramNotice=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void sendTaskcard() {//success
        WeiXinMessageTaskCardModel model = new WeiXinMessageTaskCardModel();
        model.setAgentid(1000019);
        model.setMsgtype("taskcard");
        model.setSafe(0);
        model.setTouser("48");
        Map<String,Object> maps = new HashMap<>();
        maps.put("title","会议室预订成功通知");
        maps.put("description","wx123123123123123");
        maps.put("url","http://www.baidu.com");

        maps.put("task_id", "4asdfdasfdasf");

        List<Map<String,Object>> list  = new ArrayList<Map<String,Object>>();
        Map<String,Object> map2 = new HashMap<>();
        Map<String,Object> map3 = new HashMap<>();
        map2.put("key","key111");
        map2.put("name", "今年中秋节公司有豪礼相送");
        map2.put("replace_name", "今年中秋节公司有豪礼相送");
        map2.put("color", "red");
        map2.put("is_bold", true);
        list.add(map2);
        map3.put("key","key222");
        map3.put("name", "今年中秋节公司有豪礼相送");
        map3.put("replace_name", "今年中秋节公司有豪礼相送");
        list.add(map3);
        maps.put("btn", list);

        model.setTaskcard(maps);
        System.out.println("taskcard json send =---------------- "+ JSON.toJSONString(model));
        WeiXinMessageResultModel result = weiXinMessageClient.sendTaskcard(model);
        System.out.println("taskcard=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void updateTaskcard() {
        Map<String,Object> maps = new HashMap<>();
        maps.put("agentid",1000019);
        maps.put("task_id","4asdfdasfdasf");
        maps.put("clicked_key","key222");
        List<Object> list = new ArrayList<>();
        list.add("48");
        maps.put("userids",list);
        System.out.println("updateTaskcard json send =---------------- "+ JSON.toJSONString(maps));
        WeiXinMessageResultModel result = weiXinMessageClient.updateTaskcard(maps);
        System.out.println("updateTaskcard=---------------- "+ JSON.toJSONString(result));
        Assert.assertTrue(result.isSuccess());
    }


//    @Test
//    public void sendTextcard() {
//        WeiXinMessageModel model = new WeiXinMessageModel();
//        WeiXinMessageResultModel result = weiXinMessageClient.sendText(model);
//        System.out.println("sendtext=---------------- "+ JSON.toJSONString(result));
//        Assert.assertTrue(result.isSuccess());
//    }
}
