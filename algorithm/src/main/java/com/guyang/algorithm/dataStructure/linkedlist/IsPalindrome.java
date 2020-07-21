package com.guyang.algorithm.dataStructure.linkedlist;

import com.guyang.algorithm.dataStructure.common.SingleLinkedListBuilder;
import com.guyang.algorithm.dataStructure.common.SingleLinkedNode;

import java.util.Stack;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 给定一个单链表的头节点head, 请判断该链表是否回文结构。
 * @date 2020-07-02 17:44
 */
public class IsPalindrome {

    public static void main(String[] args) {
        SingleLinkedNode head = SingleLinkedListBuilder.build(new Integer[]{1, 3, 4, 8, 4, 3, 1});
//        System.out.println(solveByStack1(head));
//        System.out.println(solveByReverse1(head));
        System.out.println(solveByReverseHalf(head));
    }

    /**
     * 判断是否为回文
     * 使用栈来实现,
     * 1.把链表节点依次压入栈中，出栈顺序与链表顺序反转
     * 2.把遍历链表并弹栈，依次对比两处元素。
     *
     * @param head
     * @return
     */
    private static boolean solveByStack1(SingleLinkedNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        SingleLinkedNode index = head;
        Stack<Object> stack = new Stack<>();

        while (index != null) {
            Object value = index.value;
            stack.push(value);
            index = index.next;
        }

        while (head != null) {
            Object value = head.value;
            if (!value.equals(stack.pop())) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    /**
     * 基本于栈实现的方法改进版，栈容器容量减半
     *
     * @param head
     * @return
     */
    private static boolean solveByStack2(SingleLinkedNode head) {
        if (head == null || head.next == null) return true;

        SingleLinkedNode fast = head;
        SingleLinkedNode slow = head;

        //找到奇数中点，偶数上中点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //把中点之后的节点压栈
        Stack stack = new Stack();
        while (slow.next != null) {
            stack.push(slow.next.value);
            slow = slow.next;
        }

        //弹栈并依次对比
        while (!stack.isEmpty()) {
            if (!stack.pop().equals(head.value)) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    /**
     * 链表全部反转后与原链表对比
     *
     * @return
     */
    private static boolean solveByReverse1(SingleLinkedNode head) {
        if (head == null || head.next == null) return true;
        SingleLinkedNode originally = head;

        SingleLinkedNode pre = null;
        SingleLinkedNode index = head;

        while (index != null) {
            index = index.next;
            head.next = pre;
            pre = head;
            head = index;
        }

        while (originally != null) {
            if (!originally.value.equals(pre.value)) {
                return false;
            }
            pre = pre.next;
            originally = originally.next;
        }

        return true;
    }


    /**
     * 反转后半部分，与前面部分对比
     *
     * @param head
     * @return
     */
    private static boolean solveByReverseHalf(SingleLinkedNode head) {
        if (head == null || head.next == null) return true;

        //找到中点
        SingleLinkedNode fast = head;
        SingleLinkedNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        SingleLinkedNode rightHalfHead = slow.next;
        slow.next = null;
        //反转后半部分
        SingleLinkedNode pre = null;
        SingleLinkedNode index = rightHalfHead;
        while (index != null) {
            index = rightHalfHead.next;
            rightHalfHead.next = pre;
            pre = rightHalfHead;
            rightHalfHead = index;
        }

        //将前后两半部分对比
        while (head != null && pre != null) {
            if (!head.value.equals(pre.value)) {
                return false;
            }
            head = head.next;
            pre = pre.next;
        }

        return true;
    }


}
