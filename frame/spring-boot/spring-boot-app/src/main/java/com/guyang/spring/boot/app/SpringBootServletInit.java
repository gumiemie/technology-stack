package com.guyang.spring.boot.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-11-13 15:18
 */
@SpringBootApplication
@ComponentScan("com.guyang.spring.boot.*")
public class SpringBootServletInit extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootServletInit.class);
    }

    /*public static void main(String[] args) {
        SpringApplication.run(SpringBootServletInit.class);
    }*/

}
