package com.guyang.algorithm.dataStructure.stack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 两个列表实现stack
 * @date 2020-06-23 11:56
 */
public class TwoQueueImplStack<E> implements MyStack<E> {

    private Queue data;
    private Queue help;

    private int elementCount;

    public TwoQueueImplStack() {
        data = new ArrayDeque();
        help = new ArrayDeque();
    }


    @Override
    public void push(final E element) {
        data.add(element);
    }

    @Override
    public E pop() {
        if (data.size() > 1) {
            while (data.size() > 1) {
                help.add(data.poll());
            }
            E last = (E) data.poll();

            Queue temp = data;
            data = help;
            help = temp;

            return last;
        }
        throw new RuntimeException("this stack has no element");
    }

    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == 0;
    }

    @Override
    public E peek() {
        return null;
    }
}
