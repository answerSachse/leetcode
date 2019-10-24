package leetcode.problemset.dynamic;

public class Problem70 {

  public static void main(String[] args) {
    Problem70 problem = new Problem70();
    System.out.println(problem.climbStairs(70));
  }


  /**
   * 爬楼梯
   * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
   *
   * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   *
   * @param n
   * @return
   */
  public int climbStairs(int n) {
    if(n <=2 ){
      return n;
    }


    //方法1
    //    int stairNLess1 = 2;
    //    int stairNLess2 = 1;
    //    int stairN = 0;
    //    for (int i = 3; i <= n; i++) {
    //      stairN = stairNLess1 + stairNLess2;
    //      stairNLess2 = stairNLess1;
    //      stairNLess1 = stairN;
    //    }
    //    return stairN;


    /**
     * 方法2
     *
     * 动态规划  O(n)
     *
     * 自下而上分析
     *
     * 动态规划的最重要的两点：
     * a、DP状态的定义:
     *   f(n):到第n阶的总走法个数
     * b、DP方程:
     *   f(n) = f(n-1) + f(n-2)
     *
     */
    int[] dp = new int[n+1];
    dp[0] = 0;
    dp[1] = 1;
    for(int i = 2; i<= n ;i++){
      dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
  }

}
