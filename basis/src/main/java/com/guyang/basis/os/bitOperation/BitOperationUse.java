package com.guyang.basis.os.bitOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 位运算的一些应用
 * @date 2020-06-15 10:15
 */
public class BitOperationUse {

    public static void main(String[] args) {
        //bitUpdate(5, 3, 2, 5);

        //System.out.println(aPlusB(3, 7));

        //System.out.println(checkPowerOf2(8));

        //System.out.println(countOnes(-7));

        //List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        //List list = subSets(integers);
        //System.out.println(Arrays.toString(list.toArray()));

        //int[] ints = {1, 1, 2, 2, 3, 4, 5, 4, 3};
        //System.out.println(singleNum(ints));

        //int[] ints = {2, 2, 2, 3, 3, 3, 4, 5, 5, 5};
        //System.out.println(singleNum1(ints));

        int[] ints ={1,2,3,3,4,4,5,5};
        System.out.println(Arrays.toString(twoSingleNums(ints)));
    }


    /**
     * 给出两个32位的整数N和M，以及两个二进制位的位置i和j。写一个方法来使得N中的第i到j位等于M（M会是N中从第i为开始到第j位的子串）。
     * 解题思路:将n的i-j位都变成0，然后按位或(m<<i)即可
     * 怎么把n的i-j位都变成0?将值按位与0就可以，生成一个其它位置是1，i-j位置是0的数字，按位与n
     * 将-1<<(32 - j + i) 之后再>>>i 再取反即可
     */
    private static void bitUpdate(Integer n, int m, int i, int j) {
        System.out.println(Integer.toBinaryString(-1));

        int i1 = -1 << (31 - j + i);
        System.out.println(Integer.toBinaryString(i1));

        int i2 = i1 >>> i;
        System.out.println(Integer.toBinaryString(i2));

        int i3 = ~i2;
        System.out.println(Integer.toBinaryString(i3));

        int i4 = i3 & n;
        System.out.println(Integer.toBinaryString(i4));

        System.out.println(Integer.toBinaryString(i4 | m << i));


    }

    /**
     * 给出两个整数a和b, 求他们的和, 但不能使用 + 等数学运算符。
     * 主要利用异或运算来完成，异或运算有个别名叫：不进位加法。
     * 那么a^b就是a和b相加后，该进位的地方不进位，
     * 然后下面就是要考虑哪些地方需要进位，自然是a和b都是1的地方。
     * a&b就是a和b里都是1的那些位置，a&b<<1就是进位之后的结果。
     * 所以，a+b = (a^b) + (a&b<<1).令_a = a^b , _b = a&b<<1
     * 来模拟加法
     *
     * @param a
     * @param b
     * @return
     */
    private static int aPlusB(int a, int b) {

        while (b != 0) {
            //a = 3, b = 7
            //1: a = 00000000 00000000 00000000 00000011
            //1: b = 00000000 00000000 00000000 00000111

            //1:a^b =00000000 00000000 00000000 00000100
            //1: (a & b) << 1 = 00000000 00000000 00000000 00000110

            //2:a^b =00000000 00000000 00000000 00000010
            //2:(a & b) << 1 =00000000 00000000 00000000 00001000

            //3:a^b = 00000000 00000000 00000000 00001010
            //3:(a & b) << 1 =00000000 00000000 00000000 00000000
            //运算结束
            int _a = a ^ b;
            int _b = (a & b) << 1;
            a = _a;
            b = _b;
        }

        return a;
    }

    /**
     * 用O(1) 时间检测整数 n 是否是 2 的幂次。
     * 思路：2 的幂次需满足 n中二进制位只有一个位置是1。
     * n&(n-1) 可用来消除n中的最后一个1.如果n&(n-1) ==0 则n满足
     *
     * @param n
     * @return
     */
    private static boolean checkPowerOf2(int n) {

        return n > 0 && (n & (n - 1)) == 0;

    }

    /**
     * 计算一个32位的整数的二进制位有多少个1.
     * 思路：n&(n-1) 可用来消除n中的最后一个1
     *
     * @return
     */
    private static int countOnes(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 计算把a变成b需要改变多少个bit位
     * 思路：看a和b不同的位有多少，异或，相同为0不同为1.a^b结果中1的个数，就是要改变的位数
     *
     * @param a
     * @param b
     * @return
     */
    private static int bitSwapRequired(int a, int b) {
        return countOnes(a ^ b);
    }


    /**
     * 给定一个含不同整数的集合，返回其所有的子集。
     * <p>
     * 思路：使用一个正整数的二进制表示第i位是1还是0，代表集合的第i个数取或不取
     * 0-2^n-1 正好对应2^n个子集
     *
     * @param nums
     * @return
     */
    public static List subSets(List<Integer> nums) {
        int size = nums.size();
        ArrayList<List<Integer>> result = new ArrayList<>(1 << size);
        for (int i = 0; i < (1 << size); i++) {
            ArrayList<Integer> subList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if ((i & (1 << j)) != 0) {
                    subList.add(nums.get(j));
                }
            }
            result.add(subList);
        }
        return result;
    }

    /**
     * 数组中，只有一个数出现一次，剩下都出现两次，找出出现一次的数
     * 思路：使用位异或操作，相同的数位异或=0，a^0 = a，就可找出这一个数
     *
     * @param numbers
     * @return
     */
    public static int singleNum(int[] numbers) {
        if (numbers.length == 0) {
            return -1;
        }
        int result = 0;

        for (int i = 0; i < numbers.length; i++) {
            result ^= numbers[i];
        }
        return result;
    }


    /**
     * 数组中，只有一个数出现一次，剩下都出现三次，找出出现一次的数
     * 思路：统计二进制位出现的次数，
     * 用3个数字分别表示出现1次，2次，3次的位表示的整数，次数到3次后重置为0次，遍历之后代表出现1次的整数就是要找的出现一次的数。
     *
     * @param numbers
     * @return
     */
    public static int singleNum1(int[] numbers) {

        int ones = 0, twos = 0, threes = 0;

        for (int i = 0; i < numbers.length; i++) {
            //位数出现两次
            twos |= (ones & numbers[i]);
            //出现1次的位数
            ones ^= numbers[i];
            //出现3次的位数
            threes = ones & twos;
            //如果出现了3次，将出现1次和2次的置为0
            twos &= ~threes;
            ones &= ~threes;
        }
        return ones;
    }


    /**
     * 数组中，只有两个数出现一次，剩下都出现两次，找出出现一次的数.
     * 思路：假设这两个数是a和b, c=a^b. c肯定不为0.找到c中最后一个二进制位1的位置d
     * 数组中的元素，a和b在d位置的值肯定不同。根据d位置的值可以将元素分成两组，a和肯定不在一个组中。
     * 将分组中所有元素与c异或，可得出a和b的值。
     *
     * @param numbers
     * @return
     */
    public static int[] twoSingleNums(int[] numbers) {
        int[] result = {0, 0};
        int c = 0;
        for (int i = 0; i < numbers.length; i++) {
            c ^= numbers[i];
        }

        //找到c的最后一位1
        c &= (-c);

        for (int i = 0; i < numbers.length; i++) {
            if ((numbers[i] & c) == 0) {
                result[0] ^= numbers[i];
            } else {
                result[1] ^= numbers[i];
            }
        }

        return result;
    }


}
