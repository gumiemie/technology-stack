###切面相关
####引入依赖
```xml
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.4</version>
    </dependency>
```
####定义切点
```java
    @Pointcut("execution(* com.guyang.spring.boot.service.impl..*.*(..))")
    public void pointCut() {}
```

####execution表达式
>1. execution(* com.guyang.spring.boot..*.*(..))
>2. 第一个”*“符号 表示返回值的类型任意；
>3. com.guyang.spring.boot	AOP所切的包
>4. 包名后面的.. 表示当前包及子包
>5. 第二个”*“ 表示类名，*即所有类。
>6. .*(..) 表示任何方法名，括号表示参数，两个点表示任何参数类型

####通知类型
1. before,after,afterReturning,afterThrowing。
```java
    @Before("pointCut()")//引入切点
    public void before(JoinPoint joinPoint) {
        //参数JoinPoint封装了目标连接点和参数信息
        System.out.println("before........");
    }
```
2.around 环绕通知
```java
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        System.out.println("around start .....");
        Object result = null;
        try {
            //ProceedingJoinPoint 可继续执行目标方法
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around end .....");

        return result;
    }
```

####使用场景
>1. 日志,鉴权,拦截等