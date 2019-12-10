package com.systop.pss.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther wang
 * @create 2019-11-30
 */
@SpringBootApplication
@MapperScan("com.systop.pss.mapper")
@RestController
public class DataApp {

    public static void main(String[] args) {

        SpringApplication.run(DataApp.class,args);
    }

    @RequestMapping("/init")
    public void tset() {
        System.out.println("aaa");
    }


}
