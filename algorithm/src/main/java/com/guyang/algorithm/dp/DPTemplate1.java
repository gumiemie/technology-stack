package com.guyang.algorithm.dp;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 动态规划示例1 问题描述：一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * @date 2020-04-24 17:35
 */
public class DPTemplate1 {

    public static void main(String[] args) {
        System.out.println(optmizeSolve(4));
    }

    private static int solve(int n) {
        //1.定义数组含义
        //定义保存历史计算结果的数组
        //跳到第n级台阶有temp[n]种跳法
        int[] temp = new int[n + 1];

        //2.找出数组元素之前的关系式
        //跳到第n级台阶可以从n-1,也可以从n-2。所以有 temp[n] = temp[n-1] + temp[n-2]

        //3.找出初始条件
        // n=1时,n-2 = -1,数组下标不能为负，我们要给出初始值，所以temp[1] = 1。n=2时 temp[2] = temp[1]+temp[0]
        if (n <= 2) return n;

        for (int i = 1; i <= n; i++) {
            if (i <= 2) {
                temp[i] = i;
            } else {
                temp[i] = temp[i - 1] + temp[i - 2];
            }
        }

        return temp[n];
    }


    //额外空间复杂度优化版
    //额外空间复杂度为O(1),时间复杂度O(n)
    private static Integer optmizeSolve(Integer n) {

        int[] temp = new int[2];

        if (n <= 2) return n;

        temp[0] = 1;
        temp[1] = 1;

        int tNum;

        for (int i = 2; i < n; i++) {
            tNum = temp[1];
            temp[1] = temp[0] + temp[1];
            temp[0] = tNum;
        }
        return temp[0] + temp[1];
    }

}
