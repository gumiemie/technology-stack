package com.guyang.spring.boot.core.lifecycle;

import org.springframework.context.annotation.Bean;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-23 10:33
 */
//@Configuration
public class LifeCycleConfig {
    
    @Bean(initMethod = "myInit", destroyMethod = "myDestroy")
    public LifeCycleBean lifeCycleBean() {
        return new LifeCycleBean();
    }

    @Bean
    public LifeCycleBeanFactoryPostProcessor lifeCycleBeanFactoryPostProcessor() {
        return new LifeCycleBeanFactoryPostProcessor();
    }

    @Bean
    public LifeCycleBeanPostProcessor lifeCycleBeanPostProcessor() {
        return new LifeCycleBeanPostProcessor();
    }

    @Bean
    public LifeCycleInstantiationAwareBeanPostProcessorAdapter lifeCycleInstantiationAwareBeanPostProcessorAdapter() {
        return new LifeCycleInstantiationAwareBeanPostProcessorAdapter();
    }

}
