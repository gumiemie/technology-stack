package com.guyang.algorithm.dataStructure.queue;

import java.util.Stack;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 使用两个stack实现队列
 * @date 2020-06-23 10:57
 */
public class TwoStackImplQueue<E> extends AbstractQueue<E> {

    private Stack pushStack;

    private Stack popStack;

    public TwoStackImplQueue() {
        pushStack = new Stack();
        popStack = new Stack();
    }

    @Override
    public E pop() {
        if (popStack.isEmpty()) {
            transfer();
        }

        if (!popStack.isEmpty()) {
            return (E) popStack.pop();
        }

        throw new RuntimeException("this queue has no element");
    }

    @Override
    public void push(final E element) {
        pushStack.push(element);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("this queue has no element");
        }
        return (E) popStack.peek();
    }

    private void transfer() {
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }

}
