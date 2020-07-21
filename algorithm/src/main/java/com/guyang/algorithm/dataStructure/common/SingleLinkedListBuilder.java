package com.guyang.algorithm.dataStructure.common;

import com.guyang.algorithm.util.NumberUtil;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 单向链表构建器
 * @date 2020-07-02 17:02
 */
public class SingleLinkedListBuilder {

    /**
     * 创建无环单向链表
     *
     * @param array
     * @return
     */
    public static SingleLinkedNode build(Object[] array) {
        if (array.length == 0) return null;
        SingleLinkedNode head = new SingleLinkedNode(array[0]);
        SingleLinkedNode index = head;
        for (int i = 1; i < array.length; i++) {
            index.next = new SingleLinkedNode(array[i]);
            index = index.next;
        }
        return head;
    }


    /**
     * 创建无环单向链表
     *
     * @param maxValue
     * @param maxLength
     * @return
     */
    public static SingleLinkedNode build(int maxValue, int maxLength) {
        SingleLinkedNode head = new SingleLinkedNode(NumberUtil.createRandomInt(maxValue));
        SingleLinkedNode index = head;
        for (int i = 1; i < maxLength; i++) {
            index.next = new SingleLinkedNode(NumberUtil.createRandomInt(maxValue));
            index = index.next;
        }

        return head;
    }


    /**
     * 创建有环链表
     *
     * @param maxValue
     * @param leftLength
     * @param ringLength
     * @return
     */
    public static SingleLinkedNode build(int maxValue, int leftLength, int ringLength) {
        if (leftLength + ringLength == 0) return null;
        SingleLinkedNode head = new SingleLinkedNode(NumberUtil.createRandomInt(maxValue));
        SingleLinkedNode leftTail = head;

        for (int i = 1; i < leftLength; i++) {
            leftTail.next = new SingleLinkedNode(NumberUtil.createRandomInt(maxValue));
            leftTail = leftTail.next;
        }

        SingleLinkedNode ringHead = new SingleLinkedNode(NumberUtil.createRandomInt(maxValue));
        SingleLinkedNode ringTail = ringHead;

        for (int i = 1; i < ringLength; i++) {
            ringTail.next = new SingleLinkedNode(NumberUtil.createRandomInt(maxValue));
            ringTail = ringTail.next;
        }

        leftTail.next = ringHead;
        ringTail.next = ringHead;

        return head;
    }


}
