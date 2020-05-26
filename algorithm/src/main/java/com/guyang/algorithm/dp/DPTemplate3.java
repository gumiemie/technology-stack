package com.guyang.algorithm.dp;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。说明：每次只能向下或者向右移动一步。举例：
 * <p>
 * 输入:
 * arr = [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * @date 2020-05-13 15:11
 */
public class DPTemplate3 {

    public static void main(String[] args) {
        int[][] param = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(slove(param));
    }


    public static Integer slove(int[][] arr) {
        int m = arr.length;
        if (m == 0 || arr[0].length == 0) {
            return 0;
        }

        //定义数组元素，二维数组，数组元素代表到此坐标最小路径和

        int n = arr[0].length;

        //找出元素之前的关系式，由于只能向下或向右，所以records[x][y],只可能是从records[x-1][y]或records[x][y-1]过来
        //即records[x][y] = Math.min(records[x-1][y],records[x][y-1])+arr[x][y]

        //找出数组初始值,第一行不能从上面过来，第一列不能从左边过来，所以第一行和第一列的值不适用关系式

        //代码实现
        for (int x = 1; x < m; x++) {
            arr[x][0] = arr[x - 1][0] + arr[x][0];
        }
        for (int y = 1; y < n; y++) {
            arr[0][y] = arr[0][y - 1] + arr[0][y];
        }

        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {
                arr[x][y] = Math.min(arr[x - 1][y], arr[x][y - 1]) + arr[x][y];
            }
        }
        return arr[m - 1][n - 1];

        //额外空间复杂度为0,时间复杂度为O(m*n+m+n)
    }

}
