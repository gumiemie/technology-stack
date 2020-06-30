package com.guyang.algorithm.util;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2020-05-20 16:54
 */
public class ArrayCreator {

    /**
     * 生成数组
     * @param maxValue
     * @param maxLength
     * @return
     */
    public static int[] createArray(int maxValue, int maxLength) {
        int[] result = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            result[i] = (int) (Math.random() * maxValue) + 1;
        }

        return result;
    }

    /**
     * 生成二维数组
     * @param rowLength
     * @param maxValue
     * @param maxLength
     * @return
     */
    public static int[][] createTwoDimensionalArray(int rowLength, int maxValue, int maxLength) {
        int[][] result = new int[rowLength][maxLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < maxLength; j++) {
                result[i][j] = (int) (Math.random() * maxValue) + 1;
            }
        }

        return result;
    }


}
