package com.guyang.basis.designPattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 单例-懒汉：静态内部类方式
 * 类加载时,不会加载内部类,所以是懒加载。
 * 每个类都只会加载一次,所以是线程安全的。
 * @date 2020-01-15 10:47
 */
public class Singleton05 {

    private Singleton05() {
    }

    private static class Singleton05Holder {
        private static final Singleton05 SINGLETON_05 = new Singleton05();
    }

    public static Singleton05 getInstance() {
        return Singleton05Holder.SINGLETON_05;
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(()-> System.out.println(getInstance().hashCode()));
        }
    }


}
