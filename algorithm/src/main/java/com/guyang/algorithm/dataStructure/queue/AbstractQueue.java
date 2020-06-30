package com.guyang.algorithm.dataStructure.queue;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-06-23 10:11
 */
public class AbstractQueue<E> implements MyQueue<E> {

    protected int elementCount = 0;

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public void push(final E element) {

    }

    @Override
    public boolean isEmpty() {
        return elementCount > 0;
    }

    @Override
    public E peek() {
        return null;
    }
}
