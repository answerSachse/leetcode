package leetcode.problemset.bit;

/**
 *
 * @author hefuhai
 */
public class Problem191 {

  /**
   * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
   *
   */

  public static void main(String[] args) {
    Problem191 problem = new Problem191();
    System.out.println(problem.hammingWeightOne(1294967293));
    System.out.println(problem.hammingWeightTwo(1294967293));


  }


  public int hammingWeightOne(int n) {
    int count = 0;
    for(int i = 0;i< 32;i++){
      if((n & 1) != 0 ){
        count++;
      }
      n >>= 1;
    }

    return count;
  }


  /**
   * n = n & (n-1) 清零最最低位的1
   * @param n
   * @return
   */
  public int hammingWeightTwo(int n) {
    int count = 0;
    while(n != 0){
      count++;
      n = n &(n-1);
    }
    return count;
  }

}
