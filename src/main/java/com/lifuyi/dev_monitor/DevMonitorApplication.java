package com.lifuyi.dev_monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lifuyi.dev_monitor.dao")
public class DevMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevMonitorApplication.class, args);
    }

}
