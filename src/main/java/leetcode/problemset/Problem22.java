package leetcode.problemset;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hefuhai
 */
public class Problem22 {

  /***
   * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
   * 例如，给出 n = 3，生成结果为：
   *
   * [
   *   "((()))",
   *   "(()())",
   *   "(())()",
   *   "()(())",
   *   "()()()"
   * ]
   *
   */





  public static void main(String[] args) {

    Problem22 problem = new Problem22();
    System.out.println(problem.generateParenthesis(3));

  }



  /**
   *分析问题:
   * n对括号，对应着2n字符，也就是长度为2n的数组。
   *
   * 暴力穷举2n, 满二叉树  O(4^n)
   *
   * 在递归搜索2n的数组上进行"剪枝"
   * 1、局部不合法，就不再递归
   *   eg: 第一个字符是")",那后面的字符都是不合法的
   * 2、记录"(" 用的次数，和")"用的次数。
   *   因为："("出现的次数必须是n，")"出现的次数也必须是n。
   *
   * 进行剪枝之后，O(2^n)
   */
  public List<String> generateParenthesis(int n) {
    List result = new ArrayList();
    if(n <=0){
      return result;
    }
    //this.generatePlus(result,0,0,n,"");
    this.generateMinus(result,n,n,"");

    return result;
  }

  /**
   * 使用次数加
   * @param result
   * @param left  "("使用的次数
   * @param right ")"使用的次数
   * @param n
   * @param str 拼接的括号字符串
   */
  private void generatePlus(List result, int left, int right, int n,String str) {
    //剪枝
    if(left == n && right == n){
      result.add(str);
      return;
    }
    //剪枝
    if(left < n){
      generatePlus(result,left+1,right,n,str+"(");
    }
    //剪枝
    if(left > right && right < n){
      generatePlus(result,left,right+1,n,str+")");
    }
  }



  /**
   * 使用次数减
   * @param result
   * @param left
   * @param right
   * @param str
   */
  private void generateMinus(List result, int left, int right,String str) {
    //剪枝
    if(left == 0 && right == 0){
      result.add(str);
      return;
    }
    //剪枝
    if(left > 0){
      generateMinus(result,left-1,right,str+"(");
    }
    //剪枝
    if(right > left){
      generateMinus(result,left,right-1,str+")");
    }
  }








}
