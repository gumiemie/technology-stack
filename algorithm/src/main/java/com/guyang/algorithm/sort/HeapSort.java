package com.guyang.algorithm.sort;

import com.guyang.algorithm.dataStructure.heap.MinHeap;
import com.guyang.algorithm.util.ArrayCreator;
import com.guyang.algorithm.util.ArrayValidator;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 堆排序
 * @date 2020-06-12 09:32
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = ArrayCreator.createArray(100, 30);
        int[] clone = array.clone();

        sort(array);

        System.out.println(ArrayValidator.valid(array,clone));


    }

    private static void sort(int[] array){
        MinHeap<Integer> minHeap = new MinHeap<>(array.length);

        for (int i = 0; i < array.length; i++) {
            minHeap.add(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = minHeap.remove();
        }

    }

}
