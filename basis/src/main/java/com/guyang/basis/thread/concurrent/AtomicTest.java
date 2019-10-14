package com.guyang.basis.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guyang <guyang@ebnew.com>
 * @description atomic test
 * @date 2019-10-12 14:11
 */
public class AtomicTest {
    private static int count = 0;

    public static void increase() {
        count++;
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(100);
        for (int i = 0; i < 50; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.incrementAndGet());
            });
        }
        try {
            //关闭线程池，线程池内所有线程执行完之后会执行此操作
            executorService.shutdown();
            //等待线程池内所有线程完成
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(atomicInteger.get());

    }


}
