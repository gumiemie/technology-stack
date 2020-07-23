package com.guyang.spring.boot.core.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-22 15:32
 */
public class LifeCycleInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {
    public LifeCycleInstantiationAwareBeanPostProcessorAdapter() {
        System.out.println("实例化LifeCycleInstantiationAwareBeanPostProcessorAdapter");
    }

    @Override
    public Object postProcessBeforeInstantiation(final Class<?> beanClass, final String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            System.out.println("--调用 InstantiationAwareBeanPostProcessorAdapter 的 postProcessBeforeInstantiation 方法--");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            System.out.println("--调用 InstantiationAwareBeanPostProcessorAdapter 的 postProcessAfterInitialization 方法--");
        return super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(final PropertyValues pvs, final Object bean, final String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            System.out.println("--调用 InstantiationAwareBeanPostProcessorAdapter 的 postProcessProperties 方法--");
        return super.postProcessProperties(pvs, bean, beanName);
    }
}
