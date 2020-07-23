##spring中bean的生命周期
###涉及到的类，注解，接口
1. BeanFactoryPostProcessor 用于向beanFactory中注册bean
2. BeanPostProcessor 可用于修改bean定义，对所有bean生效
3. InstantiationAwareBeanPostProcessorAdapter BeanPostProcessor的实现类，可修改bean定义及修改属性
4. LifeCycleBean 测试类
5. BeanNameAware 获取bean名称
6. EnvironmentAware 获取环境变量
7. ApplicationContextAware 获取applicationContext
8. @PostConstruct servlet注解，实例化时会调用该方法，通过反射调用
9. InitializingBean 实例化之后调用
10. @Bean 声明bean,可设置init-method(生成bean时会调用)，destroy-method(销毁时会调用)，都是通过反射调用
11. @PreDestroy  销毁前调用
12. DisposableBean 销毁时调用

###调用顺序
1. 实例化BeanFactoryPostProcessor
2. 调用 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法
3. 实例化LifeCycleBeanPostProcessor
4. 实例化LifeCycleInstantiationAwareBeanPostProcessorAdapter
5. 调用 InstantiationAwareBeanPostProcessorAdapter 的 postProcessBeforeInstantiation 方法
6. --调用 InstantiationAwareBeanPostProcessorAdapter 的 postProcessBeforeInstantiation 方法--
7. 实例化LifeCycleBean
8. --调用 InstantiationAwareBeanPostProcessorAdapter 的 postProcessProperties 方法--
9. --调用 BeanNameAware 的 setBeanName 方法--参数是：lifeCycleBean-- 调用 EnvironmentAware 的 setEnvironment 方法--
10. -- 调用 ApplicationContextAware 的 setApplicationContext 方法--
11. --调用LifeCycleBeanPostProcessor的 postProcessBeforeInitialization 方法--
12. -- 调用 @PostConstruct 的 beforeInit 方法--
13. --调用 InitializingBean 的 afterPropertiesSet 方法--
14. -- 调用 @Bean init-method 中的 myInit 方法--
15. --调用LifeCycleBeanPostProcessor的 postProcessAfterInitialization 方法--
16. --调用 InstantiationAwareBeanPostProcessorAdapter 的 postProcessAfterInitialization 方法--
17. 调用 @PreDestroy 的 preDestroy 方法
18. -- 调用 @Bean destroy-method 中的 myDestroy 方法
19. -- 调用 DisposableBean 的 destroy 方法--