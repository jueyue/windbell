package cn.afterturn.boot.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SpringBoot方式启动类
 *
 * @author stylefeng
 * @Date 2017/5/21 12:06
 */
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class TradeApplication {

    protected final static Logger logger = LoggerFactory.getLogger(TradeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
        logger.info("AccountApplication is success!");
    }
}
