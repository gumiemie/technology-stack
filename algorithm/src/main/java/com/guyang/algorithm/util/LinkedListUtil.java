package com.guyang.algorithm.util;

import com.guyang.algorithm.dataStructure.common.SingleLinkedListBuilder;
import com.guyang.algorithm.dataStructure.common.SingleLinkedNode;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 链表工具类
 * @date 2020-07-03 10:04
 */
public class LinkedListUtil {

    /**
     * 反转单向链表
     *
     * @param head
     * @return
     */
    public static SingleLinkedNode reverseSingleLinkedList(SingleLinkedNode head) {

        SingleLinkedNode pre = null;
        SingleLinkedNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }


    public static void main(String[] args) {
        SingleLinkedNode head = SingleLinkedListBuilder.build(new Integer[]{1, 2, 3, 4, 5, 6});
        SingleLinkedNode reversed = reverseSingleLinkedList(head);
        System.out.println(reversed.print());
    }


}
