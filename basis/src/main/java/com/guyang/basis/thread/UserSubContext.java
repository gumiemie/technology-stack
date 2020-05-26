package com.guyang.basis.thread;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-03-08 10:11
 */
public class UserSubContext {

    private static InheritableThreadLocal<User> userInheritableThreadLocal = new InheritableThreadLocal<>();

    public static void set(User user){
        userInheritableThreadLocal.set(user);
    }

    public static User get(){
        return userInheritableThreadLocal.get();
    }

    public static void remove(){
        userInheritableThreadLocal.remove();
    }

}
