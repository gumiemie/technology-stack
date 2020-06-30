package com.guyang.algorithm.dataStructure.common;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 链表：双向节点
 * @date 2020-06-22 13:47
 */
public class DoublyLinkedNode<E> {

    //上个节点
    public DoublyLinkedNode pre;
    //下个节点
    public DoublyLinkedNode next;

    //节点值
    public E value;

    public DoublyLinkedNode() {
    }

    public DoublyLinkedNode(final E value) {
        this.pre = null;
        this.next = null;
        this.value = value;
    }

    public DoublyLinkedNode(final DoublyLinkedNode pre, final DoublyLinkedNode next, final E value) {
        this.pre = pre;
        this.next = next;
        this.value = value;
    }

}
