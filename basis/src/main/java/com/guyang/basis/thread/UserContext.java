package com.guyang.basis.thread;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-03-08 10:07
 */
public class UserContext {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

    public static void set(User user){
        userThreadLocal.set(user);
    }

    public static User get(){
        return userThreadLocal.get();
    }

    public static void remove(){
        userThreadLocal.remove();
    }

}
