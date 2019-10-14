#ClassLoader
##ClassLoader的作用
>1. 故名思义,是用来加载class的。他负责将class文件中的字节码形式加载到jvm中（jdk1.7或之前的版本，存放在永久代;1.8或之后,存放在metaspace）
>2. class文件可以是目录内，也可以是jar包中的，也可以是远程字节码流。
>3. 每个class类都有一个classLoader 。 Class.getClassLoader();
>4. ClassLoader类似于一个容器,容器内存放着加载过的class类

##延迟加载
>1. JVM运行时并不是一次性加载所有的class文件，而是按需加载,也就是延迟加载。
>2. 程序在运行过程中，会遇到一些新类（或没有加载过的类），此时就会调用ClassLoader来加载这些类,加载成功之后会保存在ClassLoader中。
>3. 类加载的时候,会先加载类的父类。 会加载静态属性类，非静态属性类在实例化的时候才会加载。

##各司其职
>1. JVM中运行着多个不同的ClassLoader,从不同的地方加载不同的class文件。JVM中内置了三个重要的ClassLoader,分别是：BootStrapClassLoader,ExtClassLoader,AppClassLoader
>2. BootStrapClassLoader 是 jvm 中用C语言实现的根加载器，负责加载jvm运行时核心类，比如：这些类位于$JAVA_HOME/lib/rt.jar中。
    例如，java.util、java.lang、java.io等等。
>3. ExtClassLoader 负责jvm扩展类，如swing系列，jmx，xml相关等。这些库名通常以javax开头,它们的 jar 包位于 $JAVA_HOME/lib/ext/*.jar 中，有很多 jar 包。
>4. AppClassLoader 是面向开发者的加载器，负责加载classPath内的class文件。我们自己编写的代码以及使用的第三方 jar 包通常都是由它来加载的。
>5. URLClassLoader 可以用来加载远程class文件或jar包。URLClassLoader不但可以加载远程，也可以加载本地，取决于构造方法的URL。
    ExtClassLoader 和 AppClassLoader 都是URLClassLoader的子类。
>6. URLClassLoader加反射可以实现不同版本的类，同时使用。

##双亲委派
>1. 程序运行中,遇到未知(未被加载)类,由哪个classLoader来加载呢？Jvm默认的是由当前调用类的类加载器加载。
    class中有classLoader字段来记录是哪个类加载器加载的，一般都是由AppClassLoader加载的。
>2. AppClassLoader在加载类时，不会立刻扫描classpath,而是交给他的父类ExtClassLoader来加载；而ExtClassLoader在加载一个未知类时，不会立刻搜索ext路径
    而是交给BootStrapClassLoader来加载。这就是双亲委派。
    
##Class.forName vs ClassLoader.loadClass
>1. public static Class<?> forName(String className)throws ClassNotFoundException 使用内置类加载器加载任意类，包含原生类。
>2. public static Class<?> forName(String name, boolean initialize,ClassLoader loader)throws ClassNotFoundException 加载任意类，可以指定类加载器。
>3. Class.forName 可以获取原生类型的 Class，而 ClassLoader.loadClass() 则会报错

##Thread.contextClassLoader
>1. contextClassLoader 是那种需要显示使用的类加载器，如果你没有显示使用它，也就永远不会在任何地方用到它。
>2. 其次线程的 contextClassLoader 默认是从父线程那里继承过来的，所谓父线程就是创建了当前线程的线程。
>3. 它可以做到跨线程共享类，只要它们共享同一个 contextClassLoader。父子线程之间会自动传递 contextClassLoader，所以共享起来将是自动化的。
    如果不同的线程使用不同的 contextClassLoader，那么不同的线程使用的类就可以隔离开来。
