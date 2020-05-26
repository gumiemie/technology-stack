package com.guyang.algorithm.dp;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * @date 2020-05-11 10:01
 */
public class DPTemplate2 {

    public static void main(String[] args) {
        System.out.println(solve(1, 3));
    }

    private static Integer solve(Integer m, Integer n) {
        if (m<=0||n<=0){
            return 0;
        }

        //定义数组元素的含义：当走到坐标为m,n时，int[m-1][n-1]元素存放的是有多少种路径
        int[][] records = new int[m][n];

        //找出数组元素之间的关系,由于只能向下或向右移动。
        //records[m][n] = records[m - 1][n] + records[m][n - 1];

        //找出初始值,第一行和第一列的数值要进行初始化
        for (int i = 0; i < m; i++) {
            records[i][0] = i;
        }
        for (int i = 0; i < n; i++) {
            records[0][i] = i;
        }

        //代码实现
        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {
                records[x][y] = records[x - 1][y] + records[x][y - 1];
            }
        }

        return records[m - 1][n - 1];
    }

}
