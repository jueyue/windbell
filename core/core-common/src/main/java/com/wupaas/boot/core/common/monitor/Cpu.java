package com.wupaas.boot.core.common.monitor;

import com.wupaas.boot.core.common.math.Arith;
import lombok.Data;

/**
 * @author jueyue on 20-6-21.
 */
@Data
public class Cpu {
    /**
     * 核心数
     */
    private String name;
    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;
    /**
     * 使用率(%)
     */
    private double usage;

    public double getTotal() {
        return Arith.mul(total, 100, 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSys() {
        return Arith.mul(sys / total, 100, 2);
    }


    public double getUsed() {
        return Arith.mul(used / total, 100, 2);
    }


    public double getWait() {
        return Arith.mul(wait / total, 100, 2);
    }


    public double getFree() {
        return Arith.mul(free / total, 100, 2);
    }

    public double getUsage() {
        return Arith.mul(used / total, 100, 2);
    }
}
