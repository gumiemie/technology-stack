###DataSource相关

####DataSource
1. 如果没有使用javaConfig显式的生成DataSource,系统会读取application.properties文件中的配置生成DataSource, 需要的配置有：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

####mybatis:SqlSessionFactory和SqlSessionTemplate
1. SqlSessionFactory和SqlSessionTemplate 互斥,SqlSessionTemplate 拥有更高的优先级
2. 如果显示定义SqlSessionTemplate 配置文件中与SqlSessionFactory相关的属性将不会被自动使用

```properties
#设置mapper.xml文件的路径。类型是String[]
mybatis.mapper-locations=classpath:mapper/*.xml

#设置别名的包扫描路径.使用",; \t\n"分隔。
#如果包内的类没有加@Alias,则使用默认。否则取@Alias的value值。
#别名可用于mapper文件的任意地方如：resultType
mybatis.type-aliases-package=com.guyang.spring.boot.model
```

####MapperScannerConfigurer
1. 用于配置@MapperScan 设置的包路径。使用逗号或分号分隔
2. 显式配置此bean, MapperScan的注解会失效
