#springboot单元测试

##需要配置的注解

在测试类中加入注解
```java
@RunWith(SpringRunner.class) //调用运行junit的类
@SpringBootTest //核心注解
@WebAppConfiguration //模拟ServletContext
```

##Service测试

使用@AutoWired注入就可以测，使用示例：UserServiceImplTest

##Controller测试
使用MockMvc来模拟，使用示例：UserControllerTest

