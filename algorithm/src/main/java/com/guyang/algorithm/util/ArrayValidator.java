package com.guyang.algorithm.util;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-05-20 17:40
 */
public class ArrayValidator {
    public static boolean valid(int[] sortedArray, int[] sourceArray) {

        Arrays.sort(sourceArray);
        if (sortedArray.length != sourceArray.length) {
            return false;
        }

        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i] != sourceArray[i]) {
                return false;
            }
        }

        return true;
    }

}
