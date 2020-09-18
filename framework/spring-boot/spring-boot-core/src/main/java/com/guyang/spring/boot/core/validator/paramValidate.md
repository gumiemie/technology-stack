#参数校验
##依赖
参数校验依赖JSR303，并使用hibernate的实现
```xml

<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
</dependency>

<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
</dependency>
```

##Controller使用方法

1. 类上加@Validated
2. 要校验的方法或参数加@Valid/@Validated注解,如需分组校验,只能选择@Validated
3. 参数是复杂对象时，类成员变量中加入校验规则注解；参数是java基本类型或包装类时，参数中加校验规则注解

举例
```java
@RestController
@RequestMapping("user/json")
@Validated
public class UserAction {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public List<User> getAll(@Valid @NotNull(message = "type不能为空！") Integer type) {
        return userService.getAll();
    }

    @RequestMapping("/save")
    public RespDto save(@Valid User user) {
        if (user.getId() == null) {
            userService.insert(user);
        } else {
            userService.update(user);
        }
        return new RespDto();
    }
}

```

##Service/RemoteService 使用方法
1. 接口或接口实现类上,加入@Validated 注解
2. 在接口方法中加入@Valid注解/校验规则，不能在实现类加。

举例
```java
public interface UserRemoteService {
    List<User> getAll(@Valid @NotNull  Integer type);
}

@Service(version = "1.0")
@Validated
public class UserRemoteServiceImpl implements UserRemoteService {

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAll(@Validated @NotNull(message = "type不能为空！") Integer type) {
        return null;
    }
    
    @Override
    public void add(@Validated(Groups.Add.class) User user) {
        userService.insert(user);
        return new RespDto();
    }

}

```

##自定义异常并设置全局异常拦截器


