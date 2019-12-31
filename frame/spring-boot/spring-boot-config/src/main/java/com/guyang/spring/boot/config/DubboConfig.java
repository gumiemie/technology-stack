package com.guyang.spring.boot.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-12-30 10:41
 */
@Configuration
//@EnableDubboConfig
@DubboComponentScan(basePackages = {"${dubbo.scanPackages}"})
public class DubboConfig implements EnvironmentAware {

    private Environment environment;

    @Bean
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig(environment.getProperty("dubbo.application.name"));
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(30000);

        return providerConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(30000);

        return consumerConfig;
    }

    @Bean
    public DubboRegistryConfigBeanFactoryPostProcessor dubboRegistryConfigBeanFactoryPostProcessor(){
        return new DubboRegistryConfigBeanFactoryPostProcessor(environment.getProperty("dubbo.provider.registries"),environment.getProperty("dubbo.consumer.registries"));
    }

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }
}
