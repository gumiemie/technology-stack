package com.guyang.algorithm.dataStructure.linkedlist;

import com.guyang.algorithm.dataStructure.common.SingleLinkedListBuilder;
import com.guyang.algorithm.dataStructure.common.SingleLinkedNode;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 将单向链表按某值划分成左边小于，中间等于，右边大于的形式
 * @date 2020-07-03 17:05
 */
public class LinkedListPartition {

    public static void main(String[] args) {
        SingleLinkedNode head = SingleLinkedListBuilder.build(20, 8);
        System.out.println(head.print());
        System.out.println(solveByLinkedJoin(head, 10).print());
    }

    /**
     * 将所有节点放入数组中，放入数组时分区，然后再把数组串成链表
     * 不稳定
     *
     * @param head
     * @return
     */
    public static SingleLinkedNode solveByArray(SingleLinkedNode<Integer> head, int targetValue) {
        SingleLinkedNode index = head;
        int length = 0;
        while (index != null) {
            index = index.next;
            length++;
        }
        SingleLinkedNode[] singleLinkedNodes = new SingleLinkedNode[length];
        int left = 0, right = length - 1;
        while (head != null) {
            if (head.value > targetValue) {
                singleLinkedNodes[right--] = head;
            } else {
                singleLinkedNodes[left++] = head;
            }
            head = head.next;
        }

        head = singleLinkedNodes[0];
        SingleLinkedNode result = head;
        for (int i = 1; i < length; i++) {
            head.next = singleLinkedNodes[i];
            head = head.next;
        }
        head.next = null;

        return result;
    }


    /**
     * 把三个部分分别用三个链表串联起来，最后再把这个三链表连接起来
     *
     * @param head
     * @return
     */
    public static SingleLinkedNode solveByLinkedJoin(SingleLinkedNode<Integer> head, int targetValue) {

        SingleLinkedNode lessHead = null;
        SingleLinkedNode lessTail = null;
        SingleLinkedNode equalHead = null;
        SingleLinkedNode equalTail = null;
        SingleLinkedNode bigHead = null;
        SingleLinkedNode bigTail = null;


        //拆分成三个链表
        while (head != null) {
            SingleLinkedNode temp = head.next;
            if (head.value < targetValue) {
                if (lessHead == null) {
                    lessHead = head;
                    lessHead.next = null;
                    lessTail = head;
                    lessTail.next = null;
                } else {
                    lessTail.next = head;
                    lessTail = head;
                    lessTail.next = null;
                }
            } else if (head.value > targetValue) {
                if (bigHead == null) {
                    bigHead = head;
                    bigHead.next = null;
                    bigTail = head;
                    bigTail.next = null;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                    bigTail.next = null;
                }
            } else {
                if (equalHead == null) {
                    equalHead = head;
                    equalHead.next = null;
                    equalTail = head;
                    equalTail.next = null;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                    equalTail.next = null;
                }
            }
            head = temp;
        }

        //合并成一个链表
        if (equalHead != null) {
            equalTail.next = bigHead != null ? bigHead : null;
        }
        if (lessHead != null) {
            lessTail.next = equalHead != null ? equalHead : bigHead != null ? bigHead : null;
        }


        return lessHead != null ? lessHead : equalHead != null ? equalHead : bigHead;
    }


}
