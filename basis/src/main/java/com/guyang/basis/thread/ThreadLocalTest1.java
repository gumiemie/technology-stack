package com.guyang.basis.thread;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-03-08 10:17
 */
public class ThreadLocalTest1 {

    public static void main(String[] args) {
        User user = UserContext.get();
        System.out.println(user);
        User user1 = UserSubContext.get();
        System.out.println(user1);
    }
}
