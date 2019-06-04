package com.ymt.manage.demo.algorithms;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @Author yangmingtian
 * @Date 2019/6/4
 */
public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String str) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (j < str.length()) {
            if (!set.contains(str.charAt(j))) {
                set.add(str.charAt(j++));
                max = Math.max(max, set.size());
            } else {
                set.remove(str.charAt(i++));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int max = lengthOfLongestSubstring("abcbac");
        System.out.println(max);
    }
}
