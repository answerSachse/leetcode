package leetcode;

/**
 * 递归
 *
 * @author hefuhai
 */
public class RecursiveApp {

  public static void main(String[] args) {
    //System.out.println(factorial(3));


    System.out.println(myPowEntrance(1, -2147483648));
  }


  /**
   * 阶乘
   *
   * @param n
   * @return
   */
  public static int factorial(int n) {
    return n <= 1 ? n : n * factorial(n - 1);
  }



  /**
   * pow(x,n)
   *
   * @param x
   * @param n
   * @return
   */
  public static double myPowEntrance(double x, long n) {

    double result = 0;
    //1、挨个计算
    //result = n < 0 ? 1 / powRecursive(x, -n) : powRecursive(x, n);

    //2、分治计算
    result = n < 0 ? 1 / powDivide(x, -n) : powDivide(x, n);


    return result;
  }



  /**
   * 递归 O(n)
   *
   * @param x
   * @param n
   * @return
   */
  public static double powRecursive(double x, long n) {
    if (n == 0) {
      return 1;
    }
    return x * powRecursive(x, n - 1);
  }



  /**
   * 分治 O(log n)
   *
   * @param x
   * @param n
   * @return
   */
  public static double powDivide(double x, long n) {
    if (n == 0) {
      return 1;
    }

    boolean even = n % 2 == 0;

    double subResult = even ? powDivide(x, n >> 1) : powDivide(x, (n - 1) >> 1);

    return even ? subResult * subResult : subResult * subResult * x;
  }




}
