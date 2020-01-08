##dubbo整合springboot
###方案1:dubbo+springboot(不使用starter)
####1. 加入依赖
```xml
<dependencies>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>dubbo</artifactId>
        <version>2.6.6</version>
    </dependency>
    <dependency>
        <groupId>com.101tec</groupId>
        <artifactId>zkclient</artifactId>
        <version>0.7</version>
        <exclusions>
            <exclusion>
                <artifactId>slf4j-log4j12</artifactId>
                <groupId>org.slf4j</groupId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>io.netty</groupId>
        <artifactId>netty-all</artifactId>
        <version>4.1.32.Final</version>
    </dependency>
    <dependency>
        <groupId>org.apache.curator</groupId>
        <artifactId>curator-framework</artifactId>
        <version>4.0.1</version>
    </dependency>
</dependencies>
```

####2. 加入dubbo启动注解
> 1. 方法1：在springboot启动类 加入@EnableDubbo(scanBasePackages = "com.guyang.spring.boot.remote.service.impl") 
  理论上可以加在Configuration类，但是加上之后scanBasePackages无法被正确拿到（原因待解）。
>
> 2. 方法2：可以用@EnableDubboConfig @DubboComponentScan(basePackages = {"${spring.dubbo.scanPackages}"})替代。
  scanBasePackages 指明哪些包中的@Service要发布，可以使用通配符，但是通配符只支持一个路径。多个不同路径无法使用通配符。
  
####3. 配置dubbo相关参数
>1. 第一种方式：配置在application.properties文件中
>> 注意: 如果properties文件中的属性配置如下,使用@EnableDubbo 或 @EnableDubboConfig注解可使dubbo自动生成配置。
        那么这种情况下，不能再使用javaConfig方式配置。
```properties
#详细的参数可参见 dubbo.jar中xsd约束文件
#下面两个是必须配置的，否则会报错。
dubbo.application.name=guyang
dubbo.registry.address=zookeeper://10.4.0.163:2181?backup=10.4.0.164:2181,10.4.0.165:2181
#下面是可选参数,xx代表具体属性字段，字段名参见xsd约束文件或 DubboConfigConfiguration.Single
dubbo.application.xx
dubbo.module.xx
dubbo.registry.xx
dubbo.protocol.xx
dubbo.monitor.xx
dubbo.provider.xx
dubbo.consumer.xx
```
>2. 使用javaConfig配置. 
>> 1. 注意去掉@EnableDubbo和@EnableDubboConfig。
>> 2. ServiceBean 和 ReferenceBean参数设置判断逻辑问题，如果ConsumerConfig中配置了RegistryConfig,那么RegistryConfig会失效且RegistryConfig无法被设置，会报错。
>> 3. 由于多个网卡导致发布到zk上的host错乱问题,可在providerConfig设置host

示例如下：

```java
@Configuration
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

    //手动注册RegistryConfigBeanDefinition到spring容器
    @Bean
    public DubboRegistryConfigBeanFactoryPostProcessor dubboRegistryConfigBeanFactoryPostProcessor(){
        return new DubboRegistryConfigBeanFactoryPostProcessor(environment.getProperty("dubbo.provider.registries"),environment.getProperty("dubbo.consumer.registries"));
    }

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }
}

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


```

####4. 定义接口并实现

在实现类加入注解@com.alibaba.dubbo.config.annotation.Service
```java
@Service(version = "1.0")
public class UserRemoteServiceImpl implements UserRemoteService {

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }
}

```

####5. 服务暴露流程

1. 加载并绑定dubbo配置
```java
//@EnableDubbo 引用了 @EnableDubboConfig
//@EnableDubboConfig import class: DubboConfigConfigurationRegistrar
//DubboConfigConfigurationRegistrar注册参数绑定类到spring容器

public class DubboConfigConfigurationRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //获取EnableDubboConfig属性
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableDubboConfig.class.getName()));
        //拿到multiple属性值
        boolean multiple = attributes.getBoolean("multiple");

        // Single Config Bindings
        registerBeans(registry, DubboConfigConfiguration.Single.class);
        
        //如果多个注册配置,注册多个。
        if (multiple) { // Since 2.6.6 https://github.com/apache/incubator-dubbo/issues/3193
            registerBeans(registry, DubboConfigConfiguration.Multiple.class);
        }
    }
}

//绑定配置
public class DubboConfigConfiguration {
    /**
     * Single Dubbo {@link AbstractConfig Config} Bean Binding
     */
    @EnableDubboConfigBindings({
            @EnableDubboConfigBinding(prefix = "dubbo.application", type = ApplicationConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.module", type = ModuleConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.registry", type = RegistryConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.protocol", type = ProtocolConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.monitor", type = MonitorConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.provider", type = ProviderConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.consumer", type = ConsumerConfig.class)
    })
    public static class Single {}
    
    /**
     * Multiple Dubbo {@link AbstractConfig Config} Bean Binding
     */
    @EnableDubboConfigBindings({
            @EnableDubboConfigBinding(prefix = "dubbo.applications", type = ApplicationConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.modules", type = ModuleConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.registries", type = RegistryConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.protocols", type = ProtocolConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.monitors", type = MonitorConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.providers", type = ProviderConfig.class, multiple = true),
            @EnableDubboConfigBinding(prefix = "dubbo.consumers", type = ConsumerConfig.class, multiple = true)
    })
    public static class Multiple {}
}
``` 

2. 扫描并注册@Service类到spring容器
```java
//@EnableDubbo 引用 @DubboComponentScan
//@DubboComponentScan import class:DubboComponentScanRegistrar
//DubboComponentScanRegistrar 注册ServiceAnnotationBeanPostProcessor类，用于注册@Service,注册ReferenceAnnotationBeanPostProcessor类,用于注册@Reference

public class DubboComponentScanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //解析basePackages
        Set<String> packagesToScan = getPackagesToScan(importingClassMetadata);
        //注册ServiceAnnotationBeanPostProcessor类，用于注册@Service
        registerServiceAnnotationBeanPostProcessor(packagesToScan, registry);
        //注册ReferenceAnnotationBeanPostProcessor类,用于注册@Reference
        registerReferenceAnnotationBeanPostProcessor(registry);
    }

    private void registerServiceAnnotationBeanPostProcessor(Set<String> packagesToScan, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = rootBeanDefinition(ServiceAnnotationBeanPostProcessor.class);
        builder.addConstructorArgValue(packagesToScan);
        builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);

    }

    private void registerReferenceAnnotationBeanPostProcessor(BeanDefinitionRegistry registry) {

        // Register @Reference Annotation Bean Processor
        BeanRegistrar.registerInfrastructureBean(registry,
                ReferenceAnnotationBeanPostProcessor.BEAN_NAME, ReferenceAnnotationBeanPostProcessor.class);

    }

    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(DubboComponentScan.class.getName()));
        String[] basePackages = attributes.getStringArray("basePackages");
        Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
        String[] value = attributes.getStringArray("value");
        // Appends value array attributes
        Set<String> packagesToScan = new LinkedHashSet<String>(Arrays.asList(value));
        packagesToScan.addAll(Arrays.asList(basePackages));
        for (Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }
        if (packagesToScan.isEmpty()) {
            return Collections.singleton(ClassUtils.getPackageName(metadata.getClassName()));
        }
        return packagesToScan;
    }

}

//ServiceAnnotationBeanPostProcessor类registerServiceBean方法将ServiceBean<T>注册到spring容器中
private void registerServiceBean(BeanDefinitionHolder beanDefinitionHolder, BeanDefinitionRegistry registry,
                                     DubboClassPathBeanDefinitionScanner scanner) {
        Class<?> beanClass = resolveClass(beanDefinitionHolder);
        Service service = findAnnotation(beanClass, Service.class);
        Class<?> interfaceClass = resolveServiceInterfaceClass(beanClass, service);
        String annotatedServiceBeanName = beanDefinitionHolder.getBeanName();
        AbstractBeanDefinition serviceBeanDefinition =
                buildServiceBeanDefinition(service, interfaceClass, annotatedServiceBeanName);
                
        // ServiceBean Bean name
        String beanName = generateServiceBeanName(service, interfaceClass, annotatedServiceBeanName);
        if (scanner.checkCandidate(beanName, serviceBeanDefinition)) { // check duplicated candidate bean
            //执行注册操作
            registry.registerBeanDefinition(beanName, serviceBeanDefinition);
            if (logger.isInfoEnabled()) {
                logger.info("The BeanDefinition[" + serviceBeanDefinition +
                        "] of ServiceBean has been registered with name : " + beanName);
            }
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("The Duplicated BeanDefinition[" + serviceBeanDefinition +
                        "] of ServiceBean[ bean name : " + beanName +
                        "] was be found , Did @DubboComponentScan scan to same package in many times?");
            }
        }
    }

```

3. 服务暴露
ServiceBean<T>实现了 InitializingBean, DisposableBean,ApplicationContextAware, ApplicationListener<ContextRefreshedEvent>,
 BeanNameAware,ApplicationEventPublisherAware 接口。
 
InitializingBean中的方法 afterPropertiesSet() 设置配置,且延迟发布为否，则发布
ApplicationListener中的方法 onApplicationEvent 监听ContextRefreshEvent,如果设置延迟发布且还没有发布，就发布。
```java
    //ServiceBean的export方法
    
    public void export() {
        super.export();
        // Publish ServiceBeanExportedEvent
        publishExportEvent();
    }
    
    //ServiceConfig方法doExport,检查配置并调用doExportUrls()发布。如果没有设置Config相关bean，则会从配置文件中按默认名称为key取值
    //doExportUrls()方法获取协议列表，按不同注册中心和协议循环导出
    //doExportUrlsFor1Protocol 执行导出操作
```

###方案2 使用alibaba官方starter (没有启动成功,由于时间问题暂时先不管这个套路)
####1. 加入依赖
```xml
    <dependency>
        <groupId>com.alibaba.spring.boot</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <version>2.0.0</version>
    </dependency>
```

####2. 加入启动注解
>1. @EnableDubboConfiguration 
    此注解关联两个类：DubboProviderAutoConfiguration 和 DubboConsumerAutoConfiguration 来配置相关参数 


####3. 在properties文件中加入配置
```properties
spring.dubbo.application.name=guyang
spring.dubbo.server=true
spring.dubbo.registry=zookeeper://10.4.0.163:2181?backup=10.4.0.164:2181,10.4.0.165:2181
```

####4. 定义service接口并实现
>1. 注意：实现类须加入@Component 注解，否则无法被扫描到
```java
@Service(version = "1.0")
@Component
public class UserRemoteServiceImpl implements UserRemoteService {

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }
}

```


