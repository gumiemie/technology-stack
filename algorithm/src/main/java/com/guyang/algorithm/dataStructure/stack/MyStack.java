package com.guyang.algorithm.dataStructure.stack;

public interface MyStack<E> {

    /**
     * 元素个数
     */
    int elementCount = 0;

    /**
     * 压栈
     *
     * @param element
     */
    void push(E element);

    /**
     * 弹栈
     *
     * @return
     */
    E pop();

    /**
     * 获取元素数量
     *
     * @return
     */
    int size();

    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 看一下栈顶的元素，但不操作
     *
     * @return
     */
    E peek();

}
