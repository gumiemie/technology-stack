package com.guyang.spring.boot.core.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Iterator;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-22 15:28
 */
public class LifeCycleBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public LifeCycleBeanFactoryPostProcessor() {
        System.out.println("实例化LifeCycleBeanFactoryPostProcessor");
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        System.out.println("--调用 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法");
    }
}
