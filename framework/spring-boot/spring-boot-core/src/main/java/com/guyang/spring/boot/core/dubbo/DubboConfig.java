package com.guyang.spring.boot.core.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Value;
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
    public DubboConfig() {
        System.out.println("DubboConfig 构造方法");
    }

    @Value("dubbo.application.name")
    private String applicationName;

    private Environment environment;

    @Bean
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig(applicationName);
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
