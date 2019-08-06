package cn.afterturn.boot.paas.im.thirdservice.weixin.model;

import lombok.Data;

import java.util.List;

/**
 * Created by dbinary on 2019/7/23
 * <p>
 * /
 *  oA 数据entity
 *
 l-eVO2kTq2WFqjegffFQE6IwPLLW8crj8Pm1246AjxM
 */
@Data
public class WeiXinCheckInDataModel {
    /**
     * 打卡类型。1：上下班打卡；2：外出打卡；3：全部打卡
     */
    private String opencheckindatatype;
    /**
     * 获取打卡记录的开始时间。Unix时间戳
     */
    private Long starttime ;
    /**
     *
     * 获取打卡记录的结束时间。Unix时间戳
     */
    private Long endtime;
    /**
     * 需要获取打卡记录的用户列表
     */
    private List<String> useridlist;
}
