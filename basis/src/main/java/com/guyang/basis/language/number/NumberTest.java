package com.guyang.basis.language.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
        Double aDouble = Double.valueOf("11111111111111.123456");
        Double d1 = 11111111111111.1d;
        String format1 = String.format("%s!", d1);
        System.out.println(format1);
        System.out.println(Long.MAX_VALUE);
        BigDecimal b1 = new BigDecimal(aDouble);
        BigDecimal b2 = new BigDecimal(0.5);
        System.out.println("b1="+b1+"\nb2="+b2);

        String s2 = d1.toString();
        System.out.println(s2);

        BigInteger bigInteger = new BigInteger(new byte[]{1,10});
        String s = bigInteger.toString();
        Double d2 = 11111111111111.123456d;
        String s1 = String.valueOf(d2);
        BigDecimal bigDecimal = new BigDecimal(s1);
        BigDecimal bigDecimal1 = bigDecimal.setScale(6, BigDecimal.ROUND_FLOOR);
        DecimalFormat decimalFormat = new DecimalFormat("#.######");
        String format = decimalFormat.format(bigDecimal);
        System.out.println(format);
        System.out.println(bigDecimal1.toString());

    }

    @Test
    public void testDecimal(){
        String s = "11111111111111.123456";
        System.out.println(s);
        BigDecimal bigDecimal = new BigDecimal(s);
        System.out.println(bigDecimal.toString());
        BigDecimal bigDecimal1 = bigDecimal.setScale(5, BigDecimal.ROUND_FLOOR);
        DecimalFormat decimalFormat = new DecimalFormat("#.######");
        String format = decimalFormat.format(bigDecimal);
        System.out.println(format);
        System.out.println(bigDecimal1.toString());

        BigDecimal bigDecimal2 = new BigDecimal(new BigInteger("444445"), 3, new MathContext(5, RoundingMode.DOWN));
        System.out.println(bigDecimal2.toString());



    }

    @Test
    public void testDouble(){
        Double d1 = 2233322222.22222333d;
        Double d2 = 222222.222223333344d;
        System.out.println(d1);
        System.out.println(d2);
        BigDecimal bigDecimal = new BigDecimal(d1);
        System.out.println(bigDecimal.toString());
        System.out.println(new Double(Math.pow(2,51)).longValue());

        boolean naN = Double.isNaN(0.0/0.0);


        int i = Double.valueOf("1").hashCode();
        System.out.println(Double.doubleToLongBits(1));
        System.out.println(i);



    }

}
