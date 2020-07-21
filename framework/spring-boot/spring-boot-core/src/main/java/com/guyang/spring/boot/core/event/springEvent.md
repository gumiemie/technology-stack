#spring事件机制
##applicationEventPublisherAware事件发布
通过事件来达到解藕的目的
事件发布者发布事件，事件监听者监听此事件，当监听到对应事件时执行,触发调用相关的方法。

##开发示例：
1. 事件类 
```java
public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    public UserRegisterEvent(final Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

}
```

2. 发布事件
实现applicationEventPublisherAware接口
applicationEventPublisher.publishEvent方法发布事件
```java
@Service
public class UserServiceImpl implements UserService, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void insert(final User user) {
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, user));
    }

    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
```

3. 监听事件
事件监听器实现ApplicationListener接口。
监听到对应事件时,执行onApplicationEvent方法
```java
@Component
public class UserRegisterEventListener implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(final UserRegisterEvent event) {
        User user = event.getUser();
        System.out.printf("用户:%s注册成功;\n", user.getLoginName());
        System.out.print("用户:%s注册成功;\n");
    }
}
```


