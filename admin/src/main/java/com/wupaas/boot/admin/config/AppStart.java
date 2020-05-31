package com.wupaas.boot.admin.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author JueYue
 * @date 2020年5月31日
 */
@Component
public class AppStart implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("-------------悟耘科技PAAS基础平台 启动成功............");
    }


}

