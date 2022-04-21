package com.quan.dataflow.dataflowweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class DataflowWebApplication {

    private final Logger logger = LoggerFactory.getLogger(DataflowWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DataflowWebApplication.class, args);
    }

}
