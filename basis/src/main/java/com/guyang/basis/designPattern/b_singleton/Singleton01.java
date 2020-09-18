package com.guyang.basis.designPattern.b_singleton;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 单例模式-饿汉式:类加载到内存后,就会初始化一个实例。
 * 优点:类只会被加载一次,线程安全的.比较实用.
 * 缺点:无论是否用到,都会创建这个对象.(实际上用的时候才会加载)
 * @date 2020-01-15 09:27
 */
public class Singleton01 {
    //构造方法私有
    private Singleton01(){}

    private final static Singleton01 singleton01 = new Singleton01();

    public static Singleton01 getInstance(){
        return singleton01;
    }

    public static void main(String[] args) {
        Singleton01 instance01 = Singleton01.getInstance();
        Singleton01 instance02 = Singleton01.getInstance();

        System.out.println(instance01 == instance02);
    }

}
