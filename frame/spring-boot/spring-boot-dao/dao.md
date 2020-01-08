###Dao相关的注意事项 
#### Druid/mybatis/mysql (mapper可基于annotation或xml配置),本次以xml配置为主

1. dataSource 可配置在application.properties 或 单独以javaConfig方式配置
2. Dao的pojo类扫描需要在configuration或启动文件加入MapperScan注解
3. Mapper.xml文件中nameSpace需与dao文件的全限定名一致,select/delete/update/insert语句的id需与dao类中的方法名一致
4. 需要在application.properties中配置mybatis.mapper-locations，用于指定mapper.xml文件的路径
5. mapper.xml中返回结果使用resultType(别名)时，需指定扫描别名的路径：mybatis.type-aliases-package

