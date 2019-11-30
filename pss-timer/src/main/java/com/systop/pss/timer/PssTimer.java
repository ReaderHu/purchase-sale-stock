package com.systop.pss.timer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther wang
 * @create 2019-11-30
 */
@Component
public class PssTimer {

    private Logger logger = LogManager.getLogger(PssTimer.class);

    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        logger.info("定时运行开始");




        logger.info("定时运行结束");


    }

}
