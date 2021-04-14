package com.wupaas.boot.bussiness.monitor;

import cn.hutool.db.Db;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jueyue on 21-2-15.
 */
@Component
public class MonitorService {

    /**
     * 每分钟 监控下服务入库一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void monitor() {
        //Db.use();
    }

}
