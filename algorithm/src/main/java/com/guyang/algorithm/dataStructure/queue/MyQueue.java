package com.guyang.algorithm.dataStructure.queue;

/**
 * 队列
 */
public interface MyQueue<E>  {

    int size();

    E pop();

    void push(E element);

    boolean isEmpty();

    E peek();


}
