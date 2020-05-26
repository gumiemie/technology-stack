package com.guyang.basis.designPattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 单例:懒汉式。当需要使用的时候再new对象。
 * 缺点：这种写法是线程不安全的。
 * @date 2020-01-15 10:04
 */
public class Singleton02 {

    private static Singleton02 singleton02;

    private Singleton02() {
    }

    public static Singleton02 getInstance() {

        if (singleton02 == null) {
            //判断singleton02为空后,人为增加可能被其它线程打断的机率
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            singleton02 = new Singleton02();
        }

        return singleton02;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> System.out.println(getInstance().hashCode()));
        }
    }

}
