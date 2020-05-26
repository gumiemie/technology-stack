package com.guyang.algorithm.util;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-05-21 16:41
 */
public class SortUtil {


    /**
     * 将数组中的两个元素交换
     * @param array
     * @param x
     * @param y
     */
    public static void exchange(int[] array, int x, int y) {
        if (x == y || x + y == 0) {
            return;
        }
        array[x] = array[x] ^ array[y];
        array[y] = array[x] ^ array[y];
        array[x] = array[x] ^ array[y];
    }

}
