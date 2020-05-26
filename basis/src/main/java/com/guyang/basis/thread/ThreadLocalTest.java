package com.guyang.basis.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-10-11 17:18
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        UserSubContext.set(new User("2", "zhangjing"));

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //子线程1,清掉变量
        executorService.submit(() -> {
            UserSubContext.remove();
            System.out.println("subThread1-" + UserSubContext.get());
        });

        //子线程2
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("subThread2-" + UserSubContext.get());
        });

        executorService.shutdown();
        System.out.println("mainThread-" + UserSubContext.get());

    }

}
