package com.guyang.algorithm.sort;

import com.guyang.algorithm.util.ArrayCreator;
import com.guyang.algorithm.util.ArrayValidator;

import java.util.Arrays;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 归并排序：把数组分成n/2个子数组，分别对子数组排序，最后再把所有子数组合并。
 * 时间复杂度：O(n*logn)  空间复杂度O(n)
 * @date 2020-05-31 11:17
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = ArrayCreator.createArray(100, 50);
        int[] clone = array.clone();

        Arrays.sort(clone);
        int[] sorted = sort(array);

        System.out.println(ArrayValidator.valid(sorted, clone));
    }


    private static int[] sort(int[] array) {

        //基线条件
        if (array.length <= 1) return array;

        //拆分
        int half = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, half);
        int[] right = Arrays.copyOfRange(array, half, array.length);

        //合并
        return merge(sort(left), sort(right));

    }

    //将两个已排序数组合并,递归实现
    private static int[] merge(int[] left, int[] right) {


        int leftLength = left.length;
        int rightLength = right.length;

        int[] result = new int[leftLength + rightLength];

        //两个数组分别有两个指针
        int i = 0, li = 0, ri = 0;
        while (li < leftLength && ri < rightLength) {
            if (left[li] < right[ri]) {
                result[i] = left[li];
                li++;
                i++;
            } else {
                result[i] = right[ri];
                ri++;
                i++;
            }
        }

        //左侧数组指针到终点,右侧没到
        if (li == leftLength && ri <= rightLength - 1) {
            System.arraycopy(right, ri, result, i, rightLength - ri);
        }

        //右侧数组指针到终点,左侧没到
        if (ri == rightLength && li <= leftLength - 1) {
            System.arraycopy(left, li, result, i, leftLength - li);
        }

        return result;
    }



}
