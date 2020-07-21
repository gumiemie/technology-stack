package com.guyang.algorithm.util;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-06 09:49
 */
public class NumberUtil {


    /**
     * 生成随机数
     *
     * @param maxValue
     * @return
     */
    public static int createRandomInt(int maxValue) {
        return (int) (Math.random() * maxValue + 1);
    }


    /**
     * 生成10以内的随机正整数
     * @return
     */
    public static int createWithinTenRandom() {
        return createRandomInt(10);
    }


    /**
     * 生成100以内的随机正整数
     * @return
     */
    public static int createWithinHundredRandom() {
        return createRandomInt(100);
    }




}
