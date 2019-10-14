###ThreadLocal是什么
ThreadLocal提供了本地线程的局部变量,每个线程都可以使用set、get、remove方法对变量进行操作。
此变量与其它线程是隔离的。

###实现原理
每个线程都会维护一个ThreadLocal.ThreadLocalMap。用来处理局部变量
ThreadLocalMap有个内部类 Entry 用于引用变量。
Entry会存放在ThreadLocalMap的数组里面，Entry[] table;

```java
        static class Entry extends WeakReference<ThreadLocal<?>> {
            Object value;
            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
```

ThreadLocalMap由当前线程维护
```java
    ThreadLocal.ThreadLocalMap threadLocals = null;

    void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }

```

###应用场景
1. 存放cookie,session,一些链接等。

