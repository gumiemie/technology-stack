package com.guyang.algorithm.sort;

import com.guyang.algorithm.util.ArrayCreator;
import com.guyang.algorithm.util.ArrayValidator;
import com.guyang.algorithm.util.SortUtil;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 希尔排序：也称为递减增量排序算法，是插入排序的一种更高效的改进版本，是非稳定的排序算法
 * 希尔排序的关键在于选择步长，目前已知较好是步长是2^k-1.时间复杂度最好是O(N*5/4),最差是O(N*3/2)
 * @date 2020-05-29 11:35
 */
public class ShellSort {


    public static void main(String[] args) {

        int[] array = ArrayCreator.createArray(100, 50);
        int[] clone = array.clone();

        sort(array);
        Arrays.sort(clone);

        System.out.println(ArrayValidator.valid(array, clone));

    }


    private static void sort(int[] array) {

        //计算步长需要的幂
        int length = array.length, k = (int) (Math.log(length) / Math.log(2));

        for (; k >= 1; k--) {
            //计算步长
            int step = (int) Math.pow(2, k) - 1;

            for (int j = step; j < array.length; j += step) {
                for (int i = j; i >= step; i -= step) {
                    if (array[i] < array[i - step]) {
                        SortUtil.exchange(array, i, i - step);
                    } else {
                        break;
                    }
                }
            }
        }

    }

}
