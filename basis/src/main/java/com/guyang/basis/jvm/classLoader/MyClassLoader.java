package com.guyang.basis.jvm.classLoader;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-10-14 13:50
 */
public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) {
        final ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            final Class<?> aClass = Class.forName("[I");
            final Class<?> aClass1 = systemClassLoader.loadClass("[I");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
