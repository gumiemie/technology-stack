package com.guyang.algorithm.dataStructure.common;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 单向链表节点
 * @date 2020-07-01 17:36
 */
public class SingleLinkedNode<E> {

    public E value;

    public SingleLinkedNode<E> next;

    public SingleLinkedNode(final E value) {
        this.value = value;
    }

    public SingleLinkedNode(final E value, final SingleLinkedNode<E> next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return "SingleLinkedNode{" +
                "value=" + value +
                '}';
    }

    public String print() {
        StringBuilder sb = new StringBuilder("SingleLinkedList-->").append(value.toString()).append("-->");
        SingleLinkedNode index = next;
        while (index != null) {
            sb.append(index.value).append("-->");
            index = index.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
