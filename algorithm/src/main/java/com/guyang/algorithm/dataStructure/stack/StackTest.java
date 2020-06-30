package com.guyang.algorithm.dataStructure.stack;

import org.junit.Test;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-06-22 11:54
 */
public class StackTest {
    public static void main(String[] args) {
        ArrayStack<Integer> integerArrayStack = new ArrayStack<>();
        integerArrayStack.push(1);
        integerArrayStack.push(2);
        System.out.println(integerArrayStack.size());
        System.out.println(integerArrayStack.peek());
        System.out.println(integerArrayStack.pop());
        System.out.println(integerArrayStack.size());
        for (int i = 0; i <20; i++) {
            integerArrayStack.push(i);
        }
        System.out.println(integerArrayStack.size());
        System.out.println(integerArrayStack.peek());
        int size = integerArrayStack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(integerArrayStack.pop());
        }
    }

    @Test
    public void testLinkedStack(){
        LinkedStack<Integer> integerLinkedStack = new LinkedStack<>();
        integerLinkedStack.push(1);
        integerLinkedStack.push(2);
        System.out.println(integerLinkedStack.size());
        System.out.println(integerLinkedStack.peek());
        System.out.println(integerLinkedStack.pop());
        System.out.println(integerLinkedStack.size());
        for (int i = 0; i <20; i++) {
            integerLinkedStack.push(i);
        }
        System.out.println(integerLinkedStack.size());
        System.out.println(integerLinkedStack.peek());
        int size = integerLinkedStack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(integerLinkedStack.pop());
        }


    }
}
