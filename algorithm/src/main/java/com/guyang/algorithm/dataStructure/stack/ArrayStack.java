package com.guyang.algorithm.dataStructure.stack;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 基于数组实现的栈
 * @date 2020-06-22 11:27
 */
public class ArrayStack<E> implements MyStack<E> {

    private Object[] elements;

    private int DEFAULT_CAPACITY = 16;

    private Object[] DEFAULT_CAPACITY_ARRAY = new Object[DEFAULT_CAPACITY];

    private int index = -1;

    private int elementCount = 0;

    ArrayStack() {
        elements = DEFAULT_CAPACITY_ARRAY;
    }

    ArrayStack(int initCapacity) {
        if (initCapacity > 0) {
            elements = new Object[initCapacity];
        } else {
            elements = DEFAULT_CAPACITY_ARRAY;
        }
    }


    @Override
    public void push(final E element) {
        ensureCapacity();
        elements[++index] = element;
        elementCount++;
    }

    @Override
    public E pop() {
        if (elementCount > 0) {
            elementCount--;
            return (E) elements[index--];
        }
        return null;
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public boolean isEmpty() {
        return elementCount > 0;
    }

    @Override
    public E peek() {
        if (elementCount > 0) {
            return (E) elements[index];
        }
        return null;
    }

    private void ensureCapacity() {
        //容量满了，扩容0.5倍
        int length = elements.length;
        if (index == length - 1) {
            Object[] objects = new Object[length + Math.max(length >> 1, 1)];
            System.arraycopy(elements, 0, objects, 0, length);
            elements = objects;
        }
    }

}
