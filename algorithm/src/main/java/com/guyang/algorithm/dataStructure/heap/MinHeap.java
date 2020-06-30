package com.guyang.algorithm.dataStructure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 最小堆
 * @date 2020-06-11 09:33
 */
public class MinHeap<T extends Comparable<T>> {

    private List<T> arrayList;

    public MinHeap(int capacity) {
        arrayList = new ArrayList<T>(capacity);
    }

    public MinHeap() {
        arrayList = new ArrayList<T>();
    }

    public MinHeap(T[] array) {
        arrayList = Arrays.asList(array);
        for (T temp : array) {
            add(temp);
        }
    }

    /**
     * 获取父元素索引
     *
     * @param index
     * @return
     */
    public int parent(int index) {
        return (index - 1) >> 1;
    }

    /**
     * 获取左侧子元素索引
     *
     * @param index
     * @return
     */
    public int leftChild(int index) {
        return (index << 1) + 1;
    }

    /**
     * 获取右侧子元素索引
     *
     * @param index
     * @return
     */
    public int rightChild(int index) {
        return (index << 1) + 2;
    }


    /**
     * 添加元素
     *
     * @param e
     */
    public void add(T e) {
        arrayList.add(e);
        siftUp(arrayList.size() - 1);
    }

    /**
     * 堆元素数量
     *
     * @return
     */
    public int size() {
        return arrayList.size();
    }

    /**
     * 获取元素
     *
     * @param index
     * @return
     */
    public T get(int index) {
        return arrayList.get(index);
    }

    /**
     * 删除元素
     */
    public T remove() {
        T result = arrayList.get(0);
        int lastIndex = arrayList.size() - 1;
        arrayList.set(0, arrayList.get(lastIndex));
        arrayList.remove(lastIndex);
        siftDown(0);

        return result;
    }

    /**
     * 互换位置
     *
     * @param x
     * @param y
     */
    private void swap(int x, int y) {
        T temp = arrayList.get(x);
        arrayList.set(x, arrayList.get(y));
        arrayList.set(y, temp);
    }

    /**
     * 位置上浮，以保证符合堆的规则
     */
    private void siftUp(int childIndex) {
        //基线条件
        if (childIndex == 0) {
            return;
        }

        //递归条件
        int parentIndex = parent(childIndex);
        if (arrayList.get(childIndex).compareTo(arrayList.get(parentIndex)) < 0) {
            swap(childIndex, parentIndex);
            siftUp(parentIndex);
        }
    }

    /**
     * 位置下浮
     *
     * @param parentIndex
     */
    private void siftDown(int parentIndex) {
        //基线条件
        if (parentIndex >= arrayList.size() - 1) {
            return;
        }
        int leftChild = leftChild(parentIndex);
        if (leftChild > arrayList.size() - 1) {
            return;
        }

        //递归条件
        int rightChild = rightChild(parentIndex);
        T parent = arrayList.get(parentIndex);
        if (rightChild <= arrayList.size() - 1 && arrayList.get(leftChild).compareTo(arrayList.get(rightChild)) > 0) {
            if (parent.compareTo(arrayList.get(rightChild)) > 0) {
                swap(rightChild, parentIndex);
                siftDown(rightChild);
            }
        } else {
            if (parent.compareTo(arrayList.get(leftChild)) > 0) {
                swap(leftChild, parentIndex);
                siftDown(leftChild);
            }
        }

    }

}
