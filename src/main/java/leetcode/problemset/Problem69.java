package leetcode.problemset;

/**
 * @author hefuhai
 */
public class Problem69 {



  /**
   * 实现 int sqrt(int x) 函数。
   *
   * 计算并返回 x 的平方根，其中 x 是非负整数。
   *
   * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
   *
   * 示例 1:
   *
   * 输入: 4
   * 输出: 2
   * 示例 2:
   *
   * 输入: 8
   * 输出: 2
   * 说明: 8 的平方根是 2.82842...,
   *      由于返回类型是整数，小数部分将被舍去。
   *
   */


  public static void main(String[] args) {

    Problem69 problem = new Problem69();
    System.out.println(problem.mySqrt(8));
    System.out.println(problem.mySqrtOne(8));
    System.out.println(problem.mySqrtTwo(8,8));


  }



  /**
   * 二分查找法
   * y = x^2  是单调递增的函数  所以可以采用二分查找。
   *
   *
   *
   * @param x
   * @return
   */
  public int mySqrt(int x) {
    if(x == 0  || x == 1){
      return x;
    }
    int left = 1;
    int right = x;
    int result = -1;
    while(left <= right){
      int mid = left + (right - left) / 2;
      //不能采用 mid * mid == x 比较； mid * mid 可能超过int最大值
      if(mid == x / mid){
        return mid;
      }else if(mid > x / mid){
        right = mid -1;
      }else {
        left = mid +1;
        result = mid;
      }
    }
    return result;

  }



  /**
   * 牛顿迭代法
   * 地址：https://blog.csdn.net/qq_24133491/article/details/80918247
   *
   * @param x
   * @return
   */
  public int mySqrtOne(int x) {
    if(x == 0  || x == 1){
      return x;
    }
    double result = x;
    while(result * result > x){
      result = (result +  x /result  ) / 2;
    }

    return (int) result;

  }


  public double mySqrtTwo(int x, int precision) {
    if (x == 0 || x == 1) {
      return x;
    }
    double result = x;
    while (result * result > x) {
      result = (result + x / result) / 2;
    }
    return Double.valueOf(String.format("%."+precision+"f",result));
  }


}
