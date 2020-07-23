package com.guyang.spring.boot.core.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-22 15:29
 */
public class LifeCycleBeanPostProcessor implements BeanPostProcessor {
    public LifeCycleBeanPostProcessor() {
        System.out.println("实例化LifeCycleBeanPostProcessor");
    }

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            System.out.println("--调用LifeCycleBeanPostProcessor的 postProcessBeforeInitialization 方法--");

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            System.out.println("--调用LifeCycleBeanPostProcessor的 postProcessAfterInitialization 方法--");
        return bean;
    }
}
