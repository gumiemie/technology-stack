package com.guyang.algorithm.dataStructure.queue;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 基于列表实现简单的队列，列表循环使用，会自动扩容为原来容量的1.5倍
 * @date 2020-06-22 15:28
 */
public class ArrayQueue<E> extends AbstractQueue<E> {

    private int ARRAY_DEFAULT_CAPACITY = 16;

    private Object[] elements;

    private int pushIndex = 0;

    private int popIndex = 0;

    public ArrayQueue() {
        elements = new Object[ARRAY_DEFAULT_CAPACITY];
    }

    public ArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("capacity must lager than zero!");
        }
        elements = new Object[capacity];
    }

    @Override
    public E pop() {
        if (elementCount > 0) {
            E element = (E) elements[popIndex];
            //如果指针到了数组结尾，重置到0
            if (popIndex == elements.length - 1) {
                popIndex = 0;
            } else {
                popIndex++;
            }
            elementCount--;
            return element;
        }
        throw new RuntimeException("this queue has no elements");
    }

    @Override
    public void push(final E element) {
        ensureCapacity();
        if (pushIndex == elements.length) {
            pushIndex = 0;
        }
        elements[pushIndex] = element;
        pushIndex++;
        elementCount++;
    }

    @Override
    public E peek() {
        return (E) elements[popIndex];
    }

    //确保容量,有必要扩容
    private void ensureCapacity() {

        if (elementCount == elements.length) {
            Object[] objects = new Object[elementCount + Math.max(elementCount >> 1, 1)];
            if (popIndex == 0) {
                System.arraycopy(elements, 0, objects, 0, elementCount);
            } else {
                System.arraycopy(elements, popIndex, objects, 0, elementCount - popIndex);
                System.arraycopy(elements, 0, objects, elementCount - popIndex, popIndex);
                popIndex = 0;
                pushIndex = elementCount;
            }

            elements = objects;

        }

    }
}
