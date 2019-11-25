package com.algo.lec04.kmp;

import java.util.Arrays;

/**
 * KMP是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置的经典算法;
 * Knuth-Morris-Pratt 字符串查找算法，简称为 “KMP算法”，常用于在一个文本串S内查找一个模式串P 的出现位置，
 * 这个算法由Donald Knuth、Vaughan Pratt、James H. Morris三人于1977年联合发表，故取这3人的姓氏命名此算法;
 * KMP方法算法就利用之前判断过信息，通过一个next数组，保存模式串中前后最长公共子序列的长度，
 * 每次回溯时，通过next数组找到，前面匹配过的位置，省去了大量的计算时间。
 */
public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        //int[] next = kmpNext("ABCDABD");
        //System.out.println(Arrays.toString(next));
        int index = kmpSearch(str1, str2);
        System.out.println("index = " + index);

    }

    /**
     * @param source
     * @param pattern
     * @return -1表示没有匹配，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String source, String pattern) {
        int[] next = kmpNext(pattern);
        System.out.println("next[]=" + Arrays.toString(next));
        for (int i = 0, j = 0; i < source.length(); i++) {
            //需要处理 source.charAt(i) ！= pattern.charAt(j), 去调整j的大小
            //KMP算法核心点, 可以验证...
            while (j > 0 && source.charAt(i) != pattern.charAt(j))
                j = next[j - 1];
            if (source.charAt(i) == pattern.charAt(j))
                j++;
            if (j == pattern.length())
                return i - j + 1;
        }
        return -1;

    }

    /**
     * 获取部分匹配值表
     *
     * @param pattern
     * @return
     */
    public static int[] kmpNext(String pattern) {
        int length = pattern.length();
        int[] next = new int[length];
        next[0] = 0; //第一个部分匹配值永远是0
        for (int i = 1, j = 0; i < length; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
                j = next[j - 1];
            //当pattern.charAt(i) == pattern.charAt(j)满足时，部分匹配值+1
            if (pattern.charAt(i) == pattern.charAt(j))
                j++;
            next[i] = j;
        }
        return next;
    }

}
