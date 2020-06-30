package com.guyang.algorithm.dataStructure.heap;

import com.guyang.algorithm.util.ArrayCreator;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-06-11 11:39
 */
public class MinHeapTest {


    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();

        int[] array = ArrayCreator.createArray(100, 20);

        for (int i = 0; i < array.length; i++) {
            heap.add(array[i]);
        }

        Integer index = heap.remove();

        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get(i));
        }
    }
}
