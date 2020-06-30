package com.guyang.algorithm.sort;

import com.guyang.algorithm.util.ArrayCreator;
import com.guyang.algorithm.util.ArrayValidator;
import com.guyang.algorithm.util.SortUtil;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 快速排序。分治法,在原数组上操作
 * @date 2020-06-09 10:11
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] array = ArrayCreator.createArray(100, 16);
        int[] clone = array.clone();
        System.out.println(Arrays.toString(array));
        doSort(array, 0, clone.length - 1);
        System.out.println(Arrays.toString(array));
        System.out.println(ArrayValidator.valid(array, clone));

    }


    private static void doSort(int[] array, int left, int right) {
        //基线条件
        if (right - left == 1) {
            if (array[left] > array[right]) {
                SortUtil.exchange(array, left, right);
            }
            return;
        }
        if (right <= left) {
            return;
        }

        //递归,基数取中间元素
        int middle = left + ((right - left) >> 1), baseValue = array[middle], x = left, y = right;

        while (x <= y) {
            //左侧元素比基数小，右侧元素比基数大，不用变位置
            if (array[x]<=baseValue&&array[y]>baseValue){
                x++;
                y--;
            }else {
                //左侧元素不小于基数，右侧元素不大于基数，位置互换
                if (array[x] >= baseValue && array[y] <= baseValue) {
                    SortUtil.exchange(array, x, y);
                    x++;
                    y--;
                    //左侧元素比基数小，左侧元素不动，遍历继续
                } else if (array[x] <= baseValue) {
                    x++;
                    //右侧元素比基数大，右侧元素不动，遍历继续
                } else if (array[y] >= baseValue) {
                    y--;
                }
            }
        }

        doSort(array, left, x - 1);
        doSort(array, x, right);
    }


}
