package com.guyang.algorithm.dataStructure.common;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-07-03 11:44
 */
public class DoublyLinkedListBuilder {

    public static class InDoublyLinkedNode<E> {
        //上个节点
        public InDoublyLinkedNode pre;
        //下个节点
        public InDoublyLinkedNode next;

        //节点值
        public E value;

        public InDoublyLinkedNode() {
        }

        public InDoublyLinkedNode(final E value) {
            this.value = value;
        }

        /*
        public String print() {
            StringBuilder append = new StringBuilder("DoublyLinkedNode {").append(value.toString()).append("-->");
            while (next != null) {
                append.append(next.value).append("-->");
            }
            return append.toString();
        }*/

    }


    public static InDoublyLinkedNode build(Object[] objects) {
        if (objects.length == 0) return null;
        InDoublyLinkedNode head = new InDoublyLinkedNode(objects[0]);
        InDoublyLinkedNode end = head;

        for (int i = 1; i < objects.length; i++) {
            InDoublyLinkedNode temp = new InDoublyLinkedNode(objects[i]);
            end.next = temp;
            temp.pre = end;

            end = temp;
        }

        return head;
    }

    public static InDoublyLinkedNode randomBuild(int maxValue, int length) {
        InDoublyLinkedNode head = new InDoublyLinkedNode(new Integer ((int)Math.random() * maxValue + 1));
        InDoublyLinkedNode end = head;

        for (int i = 0; i < length; i++) {
            InDoublyLinkedNode temp = new InDoublyLinkedNode(new Integer ((int) (Math.random() * maxValue + 1)));
            end.next = temp;
            temp.pre = end;

            end = temp;
        }

        return head;
    }

    public static InDoublyLinkedNode reverseDoublyLinkedList(InDoublyLinkedNode head) {
        InDoublyLinkedNode pre = null;
        InDoublyLinkedNode next = null;

        while (head != null) {
            next = head.next;
            pre = head.pre;

            head.next = pre;
            head.pre = next;

            pre = head;
            head = next;
        }

        return pre;
    }


    public static void main(String[] args) {
        System.out.println(reverseDoublyLinkedList(build(new Integer[]{1,3,4,5,6})));
        System.out.println(reverseDoublyLinkedList(randomBuild(10,10)));
    }

}
