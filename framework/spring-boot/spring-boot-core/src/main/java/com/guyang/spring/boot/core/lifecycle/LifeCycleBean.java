package com.guyang.spring.boot.core.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 验证spring bean的生命周期
 * @date 2020-07-22 15:25
 */
public class LifeCycleBean implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean, EnvironmentAware {

    public LifeCycleBean() {
        System.out.println("实例化LifeCycleBean");
    }

    @Override
    public void setBeanName(final String name) {
        System.out.printf("--调用 BeanNameAware 的 setBeanName 方法--参数是：%s", name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--调用 InitializingBean 的 afterPropertiesSet 方法--");
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        System.out.println("-- 调用 ApplicationContextAware 的 setApplicationContext 方法--");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("-- 调用 DisposableBean 的 destroy 方法--");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("执行钩子函数")));
        Runtime.getRuntime().addShutdownHook(new Thread("thread2"){
            @Override
            public void run() {
                System.out.println("执行钩子函数2");
            }
        });
    }

    @Override
    public void setEnvironment(final Environment environment) {
        System.out.println("-- 调用 EnvironmentAware 的 setEnvironment 方法--");
    }

    @PostConstruct
    private void beforeInit() {
        System.out.println("-- 调用 @PostConstruct 的 beforeInit 方法--");
    }

    private void myInit() {
        System.out.println("-- 调用 @Bean init-method 中的 myInit 方法--");
    }

    private void myDestroy() {
        System.out.println("-- 调用 @Bean destroy-method 中的 myDestroy 方法");
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("-- 调用 @PreDestroy 的 preDestroy 方法--");
    }


}
