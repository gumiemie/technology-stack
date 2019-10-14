package com.guyang.basis.language.charSequence;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description Character 测试
 * @date 2019-08-26 10:21
 */
public class CharacterTest {

    public static void main(String[] args) {

        System.out.println((int) Character.MAX_VALUE);
        char c1 = (char) 1265533;
        char c2 = (char) 20349;
        System.out.println(c1);
        System.out.println(c2);

        char[] chars = Character.toChars(20349);
        System.out.println(Arrays.toString(chars));

        char a = Character.toUpperCase('a');
        System.out.println(a);

        char b1 = Character.reverseBytes('b');
        char b2 = Character.reverseBytes('戀');
        System.out.println(b2);

        Character a1 = new Character('a');
        String s = a1.toString();
        System.out.println(s);

    }

}
