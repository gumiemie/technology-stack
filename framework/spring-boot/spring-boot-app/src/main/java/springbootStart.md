#spring 启动方式配置

##不依赖外部容器的方式启动,均需依赖内置容器
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
```


###1.SpringApplication.run

###2.maven打war包或jar包 并使用 java -jar **.jar命令运行

1. 依赖 
```xml
    <!--生成maven相关文件-->
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
```


###3.maven命令运行 ：在有启动类的module目录下，运行 mvn spring-boot:run 

1. 依赖 
```xml
    <!--生成maven相关文件-->
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
```


##部署到外部容器

###使用maven构建war包
1.启动类继承 extends SpringBootServletInitializer 并重写 configure方法
2.pom 中设置打包方式为war
3.pom 中排除内置tomcat容器
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
```

