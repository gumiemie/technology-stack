package com.guyang.basis.designPattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 单例-懒汉式。线程安全的改进写法:双重检查
 * @date 2020-01-15 10:36
 */
public class Singleton04 {
    private Singleton04() {
    }

    private static volatile Singleton04 singleton04;

    private static Singleton04 getInstance() {
        if (singleton04 == null) {
            synchronized (Singleton04.class) {
                if (singleton04 == null) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    singleton04 = new Singleton04();
                }
            }
        }
        return singleton04;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> System.out.println(getInstance().hashCode()));
        }
    }


}
