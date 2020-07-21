package com.guyang.algorithm.dataStructure.linkedlist;

import com.guyang.algorithm.dataStructure.common.SingleLinkedListBuilder;
import com.guyang.algorithm.dataStructure.common.SingleLinkedNode;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 给定两个可能有环也可能无环的单向链表的头，实现一个函数，如果两个链表相交，返回第一个相交的结点，否则返回null
 * [要求] 如果两个链表长度之和为N,时间复杂度要求达到O(N),额外空间复杂度为O(1)
 * @date 2020-07-05 21:45
 */
public class LinkedListIntersect {

    public static void main(String[] args) {
        SingleLinkedNode build = SingleLinkedListBuilder.build(20, 5, 6);
        System.out.println(getFirstRingNode(build));
    }

    /**
     * 判断链表是否有环，如果有环，快指针和慢指针肯定会在环中某个点相遇
     *  相遇之后，将快指针回到头节点，每次走一步，快慢指针会再次在首个入环节点相遇。
     *  返回首个入环节点
     * @param head
     * @return
     */
    private static SingleLinkedNode getFirstRingNode(SingleLinkedNode<Integer> head) {
        if (head == null || head.next == null || head.next.next == null) return null;

        SingleLinkedNode<Integer> fast = head.next.next;
        SingleLinkedNode<Integer> slow = head.next;

        while (fast.next != null && fast.next.next != null) {
            if (fast == slow) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }


}
