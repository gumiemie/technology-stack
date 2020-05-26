package com.guyang.algorithm.sort;

import com.guyang.algorithm.util.ArrayCreator;
import com.guyang.algorithm.util.ArrayValidator;
import com.guyang.algorithm.util.SortUtil;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 冒泡排序：比较相邻两个元素，如果是降序，两个元素交换。时间复杂度：O(n*n),额外空间：O(1)
 * @date 2020-05-21 16:10
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = ArrayCreator.createArray(1000, 100);
        int[] clone = array.clone();
        Arrays.sort(clone);
        sort(array);
        System.out.println(ArrayValidator.valid(array, clone));
    }


    private static void sort(int[] array) {

        if (array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    SortUtil.exchange(array, j - 1, j);
                }
            }
        }

    }


}
