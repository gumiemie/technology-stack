package com.guyang.algorithm.sort;

import com.guyang.algorithm.util.ArrayCreator;
import com.guyang.algorithm.util.ArrayValidator;
import com.guyang.algorithm.util.SortUtil;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 插入排序，从第二个元素开始依次取出元素向有序的数组中插入，当前元素前面部分相当于一个有序数组。
 * 时间复杂度最好O(n)，最坏是O(n*n)，平均O(n*n)
 * @date 2020-05-28 11:02
 */
public class InsertionSort {


    public static void main(String[] args) {
        int[] array = ArrayCreator.createArray(100, 50);
        int[] clone = array.clone();
        sort(array);
        Arrays.sort(clone);

        System.out.println(ArrayValidator.valid(array, clone));
    }

    private static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j - 1] > array[j]) {
                    SortUtil.exchange(array, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

}
