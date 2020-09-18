package com.guyang.basis.designPattern.b_singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 懒汉式-线程安全的写法
 * 缺点:会给当前class加重量级锁,导致效率降低
 * @date 2020-01-15 10:24
 */
public class Singleton03 {
    private Singleton03() {
    }

    private static Singleton03 singleton03;

    private synchronized static Singleton03 getInstance() {
        if (singleton03 == null) {
            try {
                Thread.sleep(1);
            }catch(Exception e){
                e.printStackTrace();
            }
            singleton03 = new Singleton03();
        }
        return singleton03;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(()-> System.out.println(getInstance().hashCode()));
        }
    }

}
