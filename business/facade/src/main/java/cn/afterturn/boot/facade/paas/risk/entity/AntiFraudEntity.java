package cn.afterturn.boot.facade.paas.risk.entity;

import lombok.Data;

/**
 * @author jueyue on 19-11-16.
 */
@Data
public class AntiFraudEntity {
    /**
     * 撞库结果 1找到 -1 没找到 99异常
     */
    private String found;
    /**
     * 反欺诈分数
     */
    private String score;
    /**
     * 反欺诈具体信息
     */
    private String info;
}
