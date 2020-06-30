package com.guyang.algorithm.dataStructure.stack;

import com.guyang.algorithm.dataStructure.common.DoublyLinkedNode;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-06-22 14:19
 */
public class LinkedStack<E> implements MyStack<E> {

    private int elementCount = 0;

    private DoublyLinkedNode head;
    private DoublyLinkedNode last;

    @Override
    public void push(final E element) {
        DoublyLinkedNode temp = new DoublyLinkedNode<>(last, null, element);
        if (head != null) {
            last.next = temp;
            last = temp;
        } else {
            head = temp;
            last = temp;
        }
        elementCount++;
    }

    @Override
    public E pop() {
        if (elementCount > 0) {
            //取值
            E value = (E) last.value;

            //变更最后节点
            last = last.pre;
            if (last != null) {
                last.next = null;
            }

            elementCount--;

            return value;
        } else {
            throw new RuntimeException("栈为空！");
        }

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
        return last != null ? (E) (last.value) : null;
    }
}
