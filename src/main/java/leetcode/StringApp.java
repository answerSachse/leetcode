package leetcode;

import java.util.Arrays;

/**
 * 字符串
 *
 * @author hefuhai
 */
public class StringApp {

  public static void main(String[] args) {

    char[] arr = {'1','3','4'};
    //reverseString(arr);
    //recursiveString(arr);

    xorString(arr);

    System.out.println(arr);

  }



  /**
   * 反转
   * @param s
   */
  public static void reverseString(char[] s) {
    if (!(s != null && s.length > 0)) {
      return;
    }
    int start = 0, end = s.length - 1;
    char temp;
    for (; start < end; start++, end--) {
      temp = s[start];
      s[start] = s[end];
      s[end] = temp;
    }
  }



  /**
   * 递归
   */
  public static void recursiveString(char[] s){
    swap(s,0,s.length-1);
  }



  public static void swap(char[] s,int start,int end){
    if(start >= end){
      return;
    }
    char temp = s[start];
    s[start] = s[end];
    s[end] = temp;
    swap(s,++start,--end);
  }



  /**
   *异或 不使用额外空间
   */
  public static void xorString(char[] s){
    int start = 0, end = s.length - 1;
    for (; start < end; start++, end--) {
      s[start] = (char) (s[start] ^ s[end]);
      s[end] = (char) (s[start] ^ s[end]);
      s[start] = (char) (s[start] ^ s[end]);
    }

  }




}
