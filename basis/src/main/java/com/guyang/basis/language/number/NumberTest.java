package com.guyang.basis.language.number;

import org.junit.Test;

import java.math.BigInteger;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-09-06 11:18
 */
public class NumberTest {


    public static void main(String[] args) {

        Byte aByte = 1;
        byte b = Byte.parseByte("11", 2);
        System.out.format("my is %d", b);

        System.out.println("---------");

        int i = Byte.toUnsignedInt((byte) -12);
        System.out.println(i);
        String s1 = Integer.toBinaryString(i);
        String s2 = Integer.toBinaryString(-12);
        System.out.println(s1);
        System.out.println(s2);

        System.out.println(Integer.toBinaryString(11));
        System.out.println(Integer.toBinaryString(Integer.rotateLeft(11,2)));
        System.out.println(Integer.toBinaryString(Integer.rotateRight(11,2)));
        System.out.println(Integer.toBinaryString(Integer.reverse(11)));

        int i2 = Integer.rotateLeft(10,2);
        System.out.println(i2);
    }

    @Test
    public void execute(){
        BigInteger bigInteger = new BigInteger(new byte[]{-2,10});
        String s = bigInteger.toString();
        System.out.println(s);

    }

}
