package com.guyang.algorithm.dataStructure.queue;

import com.guyang.algorithm.dataStructure.common.DoublyLinkedNode;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 基于双向链表实现队列
 * @date 2020-06-23 10:05
 */
public class LinkedQueue<E> extends AbstractQueue<E> {

    private DoublyLinkedNode head;

    private DoublyLinkedNode end;

    @Override
    public E pop() {
        if (head != null) {
            E value = (E) head.value;

            head = head.next;
            if (head != null) {
                head.pre = null;
            }

            elementCount--;
            return value;
        }
        throw new RuntimeException("this queue has no element");
    }

    @Override
    public void push(final E element) {
        DoublyLinkedNode<E> node = new DoublyLinkedNode<>(end, null, element);
        if (end == null) {
            head = node;
        } else {
            end.next = node;
        }
        end = node;
        elementCount++;
    }

    @Override
    public E peek() {
        if (head == null) {
            throw new RuntimeException("this queue has no element");
        }
        return (E) head.value;
    }
}
