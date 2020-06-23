package com.wupaas.boot.core.monitor;

import cn.hutool.core.util.NetUtil;
import com.alibaba.fastjson.JSON;
import com.wupaas.boot.core.math.Arith;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.util.*;

/**
 * 服务器基础监控
 *
 * @author jueyue on 20-5-31.
 */
public class ServerMonitor {

    private static final int OSHI_WAIT_SECOND = 1000;

    private static Date START_DATE = new Date();

    public static Map getCurrentServer() {
        ServerMonitor            serverMonitor = new ServerMonitor();
        SystemInfo               si            = new SystemInfo();
        HardwareAbstractionLayer hal           = si.getHardware();
        Map<String, Object>      map           = new HashMap<>();
        map.put("cpu", serverMonitor.getCpuInfo(hal.getProcessor()));
        map.put("memory", serverMonitor.getMemInfo(hal.getMemory()));
        map.put("disk", serverMonitor.getDisks(si.getOperatingSystem()));
        map.put("jvm", serverMonitor.getJvmInfo());

        map.put("ip", JSON.toJSON(NetUtil.localIpv4s()));
        map.put("localhost", NetUtil.getLocalhostStr());
        map.put("osName", System.getProperties().getProperty("os.name"));
        map.put("oaArch", System.getProperties().getProperty("os.arch"));
        map.put("dir", System.getProperties().getProperty("user.dir"));
        return map;
    }

    /**
     * 设置Java虚拟机
     */
    private Jvm getJvmInfo() {
        Properties props = System.getProperties();
        Jvm        jvm   = new Jvm();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
        return jvm;
    }

    /**
     * 设置CPU信息
     */
    private Cpu getCpuInfo(CentralProcessor processor) {
        Cpu    cpu       = new Cpu();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks    = processor.getSystemCpuLoadTicks();
        long   nice     = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long   irq      = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long   softirq  = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long   steal    = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long   cSys     = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long   user     = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long   iowait   = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long   idle     = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long   totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setName(processor.getName());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setWait(iowait);
        cpu.setFree(idle);
        return cpu;
    }

    /**
     * 设置内存信息
     */
    private Memory getMemInfo(GlobalMemory globalMemory) {
        Memory memory = new Memory();
        memory.setTotal(Arith.div(globalMemory.getTotal(), (1024 * 1024 * 1024), 2));
        memory.setUsed(Arith.div(globalMemory.getTotal() - globalMemory.getAvailable(), (1024 * 1024 * 1024), 2));
        memory.setFree(Arith.div(globalMemory.getAvailable(), (1024 * 1024 * 1024), 2));
        return memory;
    }

    /**
     * 设置磁盘信息
     */
    private List<Disk> getDisks(OperatingSystem os) {
        FileSystem    fileSystem = os.getFileSystem();
        OSFileStore[] fsArray    = fileSystem.getFileStores();
        List<Disk>    list       = new ArrayList<>(fsArray.length);
        for (OSFileStore fs : fsArray) {
            long free  = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used  = total - free;
            Disk disk  = new Disk();
            disk.setDirName(fs.getMount());
            disk.setSysTypeName(fs.getType());
            disk.setTypeName(fs.getName());
            disk.setTotal(convertFileSize(total));
            disk.setFree(convertFileSize(free));
            disk.setUsed(convertFileSize(used));
            disk.setUsage(Arith.mul(Arith.div(used, total, 4), 100));
            list.add(disk);
        }
        return list;
    }

    public String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }
}
