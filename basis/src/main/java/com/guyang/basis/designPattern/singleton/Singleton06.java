package com.guyang.basis.designPattern.singleton;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 枚举对象都是static final的
 * @date 2020-01-15 10:59
 */
public enum Singleton06 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Singleton06.INSTANCE.hashCode())).start();
        }
    }
}
