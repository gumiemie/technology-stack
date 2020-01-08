##maven内部运行原理解析
###POM project object model 项目对象模型
1. 对要构建的项目进行建模,把项目看成一个对象 PO。
2. 对象PO应该有哪些属性呢?每个项目应该有唯一坐标和依赖，如下
```java
public class Po{
    private String artifactId;
    private String groupId;
    private String version;
    private String type;
    private String scope;
    private Set<Object> dependencies;
}
```
3. 上面的类可以使用XML表示。 这就是pom.xml的作用：对项目模型进行描述。
```xml
<po>
    <artifactId></artifactId>
    <groupId></groupId>
    <version></version>
    <type></type>
    <scope></scope>
    <dependencies></dependencies>
    <dependency></dependency>
</po>
```

###项目构建生命周期 lifecycle
项目构建分为好几个阶段(phase)，这些阶段组成了项目构建的生命周期。一个标准的生命周期包含以下阶段(phase)
1. validate  用于验证项目的有效性和项目所需的东西是否具备
2. initialize 初始化操作，比如创建项目构建所需的目录
3. generate-sources 用于生成源代码，这些代码会用于compile阶段
4. process-sources 处理源代码，比如过滤一些代码
5. generate-resources 生成资源文件
6. process-resources 处理资源文件
7. compile 编译
8. process-classes 处理字节码文件
9. generate-test-sources 生成测试源码
10. process-test-sources 处理测试源码 
11. generate-test-resources 生成资源文件
12. process-test-resources 处理资源文件
13. compile-test 编译测试代码
14. process-test-classes 处理测试字节码文件
15. test 测试
16. prepare-package 打包准备操作
17. package 打包
18. pre-integration-test 集成测试前置操作
19. integration-test 集成测试
20. post-integration-test 集成测试后置操作
21. install 将打好的包安装到本地仓库
22. deploy 将打好的包安装到远程仓库

>当执行一个phase时,之前的阶段都会按顺序执行。
>maven只是定义一套规范，并没有实现如何执行。具体的执行都是由mojo实现类实现的，这些实现类就是plugin.
>plugin其实也是一个项目，也有坐标。
>pom.xml中的<build>中的配置就可以将阶段操作与plugin绑定,由goal执行具体操作
>每个plugin可能有多个goal,具体情况可在maven官网查询到:http://maven.apache.org/plugins/index.html#
````xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>compile</artifactId>
            <version>2.3</version>
            <congratution>
                <source>1.8</source>
                <target>1.8</target>
            </congratution>
            <executions>
                <execution>
                    <id>compile-g</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>compile</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
</plugins>
</build>

````

> 如果没有指定plugin,maven会使用默认的插件，默认插件的配置在:${MAVEN_HOME}/lib/maven-core-3.3.9.jar!/META-INF/plexus/default-bindings.xml
> 如果没有指定的配置，会使用默认的默认，默认配置存在 ${MAVEN_HOME}/lib/maven-model-builder-3.0.3.jar:org/apache/maven/model/pom-4.0.0.xml中

###常用插件介绍
####maven-resources-plugin
#####1.插件介绍
1. 用于资源文件的处理,最新版本是 3.1.0
2. 有4个goal ,分别是:resources,testResources,copyResources,help
#####2.常用配置解析
```xml

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <configuration>
        <!--配置资源文件编码,如果属性有配置,默认会使用此配置
        <properties><project.build.sourceEncoding>UTF-8</project.build.sourceEncoding></properties>
        -->
        <encoding>UTF-8</encoding>
        
        <!--使用默认占位符${ },否则配置文件yml或properties文件中要使用@**@-->
        <useDefaultDelimiters>true</useDefaultDelimiters>

        <!--非resources目录的文件或java目录内的非class文件需要打包,则需要在此处配置或在<build><resources>内-->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <!--排除文件的列表-->
                <excludes>
                    <exclude>application*.yml</exclude>
                </excludes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <!--filtering配置的作用是：把application配置文件中的占位符@**@或${}  替换成正确的值-->
                <filtering>true</filtering>
                <!--包含的文件列表-->
                <includes>
                    <include>application.yml</include>
                    <include>application-${profiles-active}.yml</include>
                </includes>
            </resource>
        </resources>
    </configuration>
</plugin>
```

####maven-compiler-plugin
#####1.插件介绍
1. 用于编译源码.the default compiler is javax.tools.JavaCompiler. 插件最新版本是3.8.1
2. 有两个goal:compile,testCompile. 用于compile和test-compile阶段

#####2.常用配置解析
```xml
<plugin>                                                                                                                                      
    <!-- since maven3.0 the default jdk is 1.6 -->                                                                           
    <groupId>org.apache.maven.plugins</groupId>                                                                                               
    <artifactId>maven-compiler-plugin</artifactId>                                                                                            
    <version>3.1</version>   
                                                                                                                     
    <configuration>                                                                                                                           
        <!-- in general,target is same to source,but 
        Sometimes when you may need to compile a certain project to a different version than what you are currently using.  -->                    
        <source>1.8</source> <!-- source file jdk version-->                                                                                             
        <target>1.8</target> <!-- target class file version -->   
        
        <encoding>UTF-8</encoding><!-- charset -->
        <skipTests>true</skipTests>                                                                             
        <verbose>true</verbose><!--print execute logs-->
        <showWarnings>true</showWarnings>                                                                                                               
        <fork>true</fork><!--if fork is true，this means some parameter is effective.such as: compilerVersion,meminitial,maxmem-->                                                        
        <executable><!-- path-to-javac --></executable><!-- ex. <executable>${JAVA_HOME}/bin/javac</executable> -->           
        <compilerVersion>1.3</compilerVersion><!-- compiler version -->                                                                      
        <meminitial>128m</meminitial><!-- compiler initialize memory-->                                                                                      
        <maxmem>512m</maxmem><!-- compiler use max memory -->                                                                                              
        
        <!--command line arguments-->  
        <compilerArgument>-verbose -bootclasspath ${java.home}\lib\rt.jar</compilerArgument>             
        <compilerArgs>
            <arg>-verbose</arg>
            <arg>-Xlint:all,-options,-path</arg>
        </compilerArgs>
        
    </configuration>                                                                                                                          
</plugin>
```
####maven-assembly-plugin
#####1.作用: 制作项目分发包，该分发包可能包含了项目的可执行文件、源代码、readme、平台脚本等等。
maven-assembly-plugin支持各种主流的格式如zip、tar.gz、jar和war等，具体打包哪些文件是高度可控的，
例如用户可以按文件级别的粒度、文件集级别的粒度、模块级别的粒度、以及依赖级别的粒度控制打包，此外，包含和排除配置也是支持的。
使用 descriptorRefs(官方提供的定制化打包方式)，官方提供的 descriptorRef 有 bin, jar-with-dependencies, src, project。

#####2.配置解析
```xml
<!-- 1.2.3的配置,是互斥的-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>3.1.0</version>
    <configuration>
        <!--1.descriptorRefs用于插件预定义的descriptor,有四个:jar-with-dependencies,bin,src,project -->
        <descriptorRefs>
            <!--jar-with-dependencies 只会原样打包,将各种依赖库打包在一起,不会解决冲突 -->
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        
        <!--2.如果这儿不配置,会加载默认的描述文件-->
        <descriptors>
            <!--自定义打包配置-->
            <descriptor>src/assembly/bin.xml</descriptor>
        </descriptors>
        
        <!--3.可执行jar配置-->
        <archive>
            <manifest>
                <mainClass>org.sample.App</mainClass>
            </manifest>
        </archive>
    </configuration>
    <executions>
        <execution>
            <!--打包的文件名会使用${artifactId}-${id} -->
            <id>assembly</id>
            <!--绑定到package这个phase,这是此插件常用的方式-->
            <phase>package</phase>
            <goals>
                <!--执行的goal,有两个goal:single,help-->
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
```xml
<assembly xmlns="http://maven.apache.org/ASSEMBLY/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/ASSEMBLY/2.0.0 http://maven.apache.org/xsd/assembly-2.0.0.xsd">
  <!--id 标识符，添加到生成文件名称的后缀符。
  如果指定 id 的话，目标文件则是 ${artifactId}-${id}.tar.gz-->
  <id>bin</id>
  
  <!--maven-assembly-plugin 支持的打包格式有zip、tar、tar.gz (or tgz)、tar.bz2 (or tbz2)、jar、dir、war，可以同时指定多个打包格式-->
    <formats>
      <format>tar.gz</format>
      <format>dir</format>
    </formats>
    
  <!-- 用来定制工程依赖 jar 包的打包方式，核心元素如下表所示。-->
  <!--元素	类型	作用-->
  <!--outputDirectory	String	指定包依赖目录，该目录是相对于根目录-->
  <!--includes/include*	List<String>	包含依赖-->
  <!--excludes/exclude*	List<String>	排除依赖-->
    <dependencySets>
      <dependencySet>
        <outputDirectory>/lib</outputDirectory>
      </dependencySet>
    </dependencySets>
  
  <!--管理一组文件的存放位置，核心元素如下表所示。-->
  <!--元素	类型	作用-->
  <!--outputDirectory	String	指定文件集合的输出目录，该目录是相对于根目录-->
  <!--includes/include*	List<String>	包含文件-->
  <!--excludes/exclude*	List<String>	排除文件-->
  <!--fileMode	String	指定文件属性，使用八进制表达，分别为(User)(Group)(Other)所属属性，默认为 0644-->
    <fileSets>
      <fileSet>
        <includes>
          <include>bin/**</include>
        </includes>
        <fileMode>0755</fileMode>
      </fileSet>
      <fileSet>
        <includes>
          <include>/conf/**</include>
          <include>logs</include>
        </includes>
      </fileSet>
    </fileSets>
    
    <!--可以指定目的文件名到指定目录，其他和 fileSets 相同，核心元素如下表所示。-->
    <!--元素	类型	作用-->
    <!--source	String	源文件，相对路径或绝对路径-->
    <!--outputDirectory	String	输出目录-->
    <!--destName	String	目标文件名-->
    <!--fileMode	String	设置文件 UNIX 属性-->
    <files>
      <file>
        <source>README.txt</source>
        <outputDirectory>/</outputDirectory>
      </file>
    </files>
</assembly>
```
####maven-jar-plugin
#####1.插件介绍
1. 用于打包jar文件。比如生成可执行的jar包。默认绑定到package阶段。
2. 有两个goal：jar,test-jar。

#####2.常用配置解析
```xml
<plugins>
      <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>2.6</version>
            <!-- The configuration of the plugin -->
            <configuration>
                <!-- Configuration of the archiver -->
                <archive>

                    <!--
                        生成的jar中，不要包含pom.xml和pom.properties这两个文件
                    -->
                    <addMavenDescriptor>false</addMavenDescriptor>

                    <!-- Manifest specific configuration -->
                    <manifest>
                        <!--
                            是否要把第三方jar放到manifest的classpath中
                        -->
                        <addClasspath>true</addClasspath>
                        <!--
                           生成的manifest中classpath的前缀，因为要把第三方jar放到lib目录下，所以classpath的前缀是lib/
                       -->
                        <classpathPrefix>lib/</classpathPrefix>
                        <!--
                            应用的main class
                        -->
                        <mainClass>com.xxx.demo.App</mainClass>
                        
                    </manifest>
                    
                    <manifestEntries>
                        <Class-Path>config/</Class-Path>
                    </manifestEntries>
                    
                </archive>
                <!--排队文件-->
                <excludes>
                    <exclude>${project.basedir}/xml/*</exclude>
                </excludes>
            </configuration>
        </plugin>
</plugins>        
```

####maven-war-plugin
#####1.插件介绍
1. 用来打包war文件。可用于合并多个war包。
2. 有三个goal:war,exploded,inplace。

#####2.常用配置解析
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
        <configuration>
   
            <!--该配置的值为true|false，默认是false。表示在发布war包的时候是否同时发布一个jar包（只有classes，不包含页面相关文件）。
                正常情况下war类型的工程，当我们执行install或者deploy的时候build出一个war包，安装到本地或者发布到远程。
                当该参数配置为true时，除了war包外还会多出一个jar包，不过该jar包的classifier默认为classes。了-->
            <attachClasses>true</attachClasses>

            <!--该配置的值为true|false，默认是false。表示是否将class进行打包。
                正常情况下war类型的工程，java代码编译后的类文件会放到WEB-INF/classes目录下面，散装。
                 当该参数配置为true时，会将所有的class打包为一个jar，jar的名字与war的名字一致（除了后缀）。
                 然后把这个jar放到WEB-INF/lib目录下，此时WEB-INF/classes目录下是空的。-->
            <archiveClasses>true</archiveClasses>

            <!--设置jar包名字，如果A中没有定义该名字，那么名字为classes,如下图：-->
            <classesClassifier>api</classesClassifier>

                <!--相同点：packagingExcludes与webResources 均可以排除某些文件。
                    不同点：packagingExcludes针对拷贝到target后的资源进行处理。
                            webResources针对编译前的资源进行处理。-->
        <packagingExcludes>WEB-INF/classes/**/**</packagingExcludes>
        <webResources>
                    <resource>
                              <directory>target/${project.artifactId}/WEB-INF/classes</directory>
                              <excludes>
                                  <exclude>**/**</exclude>
                              </excludes>
                     </resource>
        </webResources>
      </configuration>                
</plugin>
```

