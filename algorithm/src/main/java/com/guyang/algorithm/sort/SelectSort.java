package com.guyang.algorithm.sort;

import com.guyang.algorithm.util.ArrayCreator;
import com.guyang.algorithm.util.ArrayValidator;
import com.guyang.algorithm.util.SortUtil;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 选择排序：每次选择一个最小的元素，排列到前面。时间复杂度O(n*n) 额外空间复杂度O(1)
 * @date 2020-05-21 10:37
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = ArrayCreator.createArray(1000, 100);
        int[] clone = array.clone();
        sort(array);
        System.out.println(ArrayValidator.valid(array, clone));
    }

    private static void sort(int[] array) {
        if (array.length <= 1) return;

        for (int i = 0; i < array.length; i++) {
            int temp = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[temp] > array[j]) {
                    temp = j;
                }
            }

            if (temp != i) {
                SortUtil.exchange(array, temp, i);
            }
        }
    }

}
