package com.wupaas.boot.core.common.monitor;

import com.wupaas.boot.core.common.math.Arith;
import lombok.Data;

/**
 * @author jueyue on 20-6-21.
 */
@Data
public class Memory {

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    /**
     * 使用率(%)
     */
    private double usage;

    public double getUsage() {
        return Arith.mul(used / total, 100, 2);
    }
}
