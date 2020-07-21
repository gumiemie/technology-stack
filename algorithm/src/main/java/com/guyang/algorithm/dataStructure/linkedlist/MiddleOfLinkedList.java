package com.guyang.algorithm.dataStructure.linkedlist;

import com.guyang.algorithm.dataStructure.common.SingleLinkedListBuilder;
import com.guyang.algorithm.dataStructure.common.SingleLinkedNode;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 使用快慢指针查询链表中间节点
 * @date 2020-07-02 14:24
 */
public class MiddleOfLinkedList {

    public static void main(String[] args) {
        SingleLinkedNode head1 = SingleLinkedListBuilder.build(new Integer[]{1, 2, 3, 4, 5});
        SingleLinkedNode head2 = SingleLinkedListBuilder.build(new Integer[]{1, 2, 3, 4, 5, 6});

        System.out.println(findMidOrPreMid(head1));
        System.out.println(findMidOrPreMid(head2));
        System.out.println(findMidOrAfterMid(head1));
        System.out.println(findMidOrAfterMid(head2));
        System.out.println(findPreMidOrPreUpMid(head1));
        System.out.println(findPreMidOrPreUpMid(head2));
        System.out.println(findPreMidOrPreDownMid(head1));
        System.out.println(findPreMidOrPreDownMid(head2));
    }


    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     *
     * @param head
     * @return
     */
    private static SingleLinkedNode findMidOrPreMid(SingleLinkedNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        SingleLinkedNode fast = head.next.next;
        SingleLinkedNode slow = head.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     *
     * @param head
     * @return
     */
    private static SingleLinkedNode findMidOrAfterMid(SingleLinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        SingleLinkedNode fast = head.next;
        SingleLinkedNode slow = head.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    /**
     * 输入链表头节点，奇数长度返回中点前一结点，偶数长度返回上中点前一节点
     *
     * @param head
     * @return
     */
    private static SingleLinkedNode findPreMidOrPreUpMid(SingleLinkedNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        SingleLinkedNode slow = head;
        SingleLinkedNode fast = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    /**
     * 输入链表头节点，奇数长度返回中点前一结点，偶数长度返回下中点前一节点
     *
     * @param head
     * @return
     */
    private static SingleLinkedNode findPreMidOrPreDownMid(SingleLinkedNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        SingleLinkedNode slow = head;
        SingleLinkedNode fast = head.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


}
