package com.guyang.basis.jvm.classFile.model;

/**
 * @author guyang <guyang@ebnew.com>
 * @description class文件结构
 * @date 2019-07-17 10:37
 */
public class ClassFile {

    private String magicNumber;//魔数

    public static void main(String[] args) {
        final ClassLoader classLoader = ClassFile.class.getClassLoader();
        System.out.println(1);
    }
}
