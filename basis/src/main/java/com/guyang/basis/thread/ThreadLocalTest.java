package com.guyang.basis.thread;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-10-11 17:18
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("kkkkkkk");
        System.out.println(threadLocal.get());
    }

}
