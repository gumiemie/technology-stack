package com.guyang.algorithm.dataStructure.queue;

import org.junit.Test;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-06-22 17:14
 */
public class QueueTest {

    public static void main(String[] args) {
        ArrayQueue<Integer> integerArrayQueue = new ArrayQueue<>(4);

        integerArrayQueue.push(1);
        integerArrayQueue.push(2);
        integerArrayQueue.push(3);
        integerArrayQueue.push(4);
        integerArrayQueue.push(7);
        integerArrayQueue.push(8);

        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue.pop());

        integerArrayQueue.push(5);
        integerArrayQueue.push(6);
        integerArrayQueue.push(7);
        integerArrayQueue.push(8);
        integerArrayQueue.push(9);
        integerArrayQueue.push(10);

        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue.pop());
        System.out.println(integerArrayQueue.pop());

    }

    @Test
    public void testLinkedQueue(){

        MyQueue myQueue = new LinkedQueue();

        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(7);
        myQueue.push(8);

        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());

    }

    @Test
    public void testTwoStackImplQueue(){
        MyQueue myQueue = new TwoStackImplQueue();

        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        myQueue.push(4);
        System.out.println(myQueue.pop());
        myQueue.push(7);
        myQueue.push(8);

    }

}
