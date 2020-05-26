package com.guyang.algorithm.util;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-05-20 16:54
 */
public class ArrayCreator {

    public static int[] createArray(int maxValue, int maxLength) {
        int[] result = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            result[i] = (int) (Math.random() * maxValue) + 1;
        }

        return result;
    }

}
