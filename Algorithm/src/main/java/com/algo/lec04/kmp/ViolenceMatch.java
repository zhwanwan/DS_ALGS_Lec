package com.algo.lec04.kmp;

/**
 * 暴力匹配算法
 * <p>
 * 如果用暴力匹配的思路，并假设现在str1匹配到 i 位置，子串str2匹配到 j 位置，则有:
 * 1.如果当前字符匹配成功（即str1[i] == str2[j]），则i++，j++，继续匹配下一个字符;
 * 2.如果不匹配（即str1[i]! = str2[j]），令i = i - (j - 1)，j = 0。相当于每次匹配失败时，i回溯，j被重置为0。
 * <p>
 * 用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量的时间。
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "abcdefgabecde";
        String str2 = "defga";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    public static int violenceMatch(String s1, String s2) {

        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        int len1 = char1.length;
        int len2 = char2.length;
        int i = 0, j = 0;

        while (i < len1 && j < len2) {
            if (char1[i] == char2[j]) { //matched
                i++;
                j++;
            } else { //unmatched
                i = i - (j - 1);
                j = 0;
            }
        }
        //matched or not?
        if (j == len2)
            return i - j;
        else
            return -1;
    }
}
