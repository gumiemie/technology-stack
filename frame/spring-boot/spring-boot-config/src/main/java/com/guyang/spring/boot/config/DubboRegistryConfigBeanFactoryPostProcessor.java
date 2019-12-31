package com.guyang.spring.boot.config;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 注册DubboRegistryConfig到spring容器
 * @date 2019-12-31 10:54
 */
public class DubboRegistryConfigBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private String providerRegistries;
    private String consumerRegistries;

    public DubboRegistryConfigBeanFactoryPostProcessor(final String providerRegistries, final String consumerRegistries) {
        this.providerRegistries = providerRegistries;
        this.consumerRegistries = consumerRegistries;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //注册消费者RegistryConfig
        if (StringUtils.isNotEmpty(consumerRegistries)) {
            Arrays.stream(consumerRegistries.split("\\s*[|]+\\s*")).filter(StringUtils::isNotEmpty).forEach(consumerRegistry -> doRegisterBeanDefinition(registry, false, consumerRegistry));
        }

        //注册生产者RegistryConfig
        if (StringUtils.isNotEmpty(providerRegistries)) {
            Arrays.stream(providerRegistries.split("\\s*[|]+\\s*")).filter(StringUtils::isNotEmpty).forEach(providerRegistry -> doRegisterBeanDefinition(registry, true, providerRegistry));
        }

    }

    private void doRegisterBeanDefinition(BeanDefinitionRegistry registry, Boolean isProvider, String beanName) {
        if (!registry.containsBeanDefinition(beanName)) {
            registry.registerBeanDefinition(beanName, generateRegistryConfigBeanDefinition(isProvider, beanName));
        } else {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            propertyValues.removePropertyValue("register");
            propertyValues.addPropertyValue("register", isProvider);
            propertyValues.addPropertyValue("check", isProvider);
        }
    }

    private BeanDefinition generateRegistryConfigBeanDefinition(Boolean isProvider, String address) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RegistryConfig.class);
        beanDefinitionBuilder.addPropertyValue("register", isProvider);
        beanDefinitionBuilder.addPropertyValue("address", address);
        beanDefinitionBuilder.addPropertyValue("subscribe", true);
        beanDefinitionBuilder.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);

        return beanDefinitionBuilder.getBeanDefinition();
    }


    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
