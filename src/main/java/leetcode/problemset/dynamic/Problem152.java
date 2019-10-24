package leetcode.problemset.dynamic;

/**
 * @author hefuhai
 */
public class Problem152 {


  /**
   * 乘积最大子序列
   * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
   *
   * 示例 1:
   *
   * 输入: [2,3,-2,4]
   * 输出: 6
   * 解释: 子数组 [2,3] 有最大乘积 6。
   * 示例 2:
   *
   * 输入: [-2,0,-1]
   * 输出: 0
   * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
   *
   */

  public static void main(String[] args) {

    Problem152 problem = new Problem152();
    //int[] arr = {2, 3, -2, 4};
    int[] arr = {-2, 0,-1,-8};
    System.out.println(problem.maxProduct(arr));

  }



  /**
   * 动态规划
   *
   * 1、状态的定义
   *   DP[i]   0-->i 乘积最大子序列，
   *      可能包含的子序列(
   *       num[i],
   *       {num[i],num[i-1]},
   *       ...
   *       ,{num[i],...,num[0]}
   *     )
   *
   *
   * 2、状态转移方程
   *     DP[i] = DP[i-1] * num[i];
   *
   *     但是 num[i]可能为正，也可能是负；
   *     如果num[i]是负数，DP[i]是最小值；如果num[i]是正数，DP[i]是最大值。
   *
   *    状态DP[i]变成 dp[i][2]  [2]表示0：max,1:min 负数的最小值(绝对值最大值)。
   *
   *    状态转移方程变成：
   *      dp[i,0] = if num[i] >=0 dp[i-1,0] * num[i]
   *                  else  dp[i-1,1] * num[i]  :负数的最小值 * 负数，就是最大值
   *
   *      dp[i,1] = if num[i] >=0 dp[i-1,1] * num[i]
   *                      else  dp[i-1,0] * num[i]
   *
   *
   *
   *
   *
   * @param nums
   * @return
   */
  public int maxProduct(int[] nums) {
    if (nums == null) {
      return 0;
    }

    /**
     * 第一维表示数组的长度，由于不需要保存整个数组，所以长度只需要取2即可，
     * 第二维 用0表示是正的最大值，用1表示为负的最大值
     */
    int dp[][] = new int[2][2];
    // 最终的结果
    int res = nums[0];
    //起始状态值
    dp[0][0] = nums[0];
    dp[0][1] = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int x = i % 2;
      //滚动数组，取值 0 or 1
      int y = (i - 1) % 2;

//      if (nums[i] >= 0) {
//        // 正的最大值
//        dp[x][0] = Math.max(dp[y][0] * nums[i], nums[i]);
//        // 负的最小值(绝对值最大值)
//        dp[x][1] = Math.min(dp[y][1] * nums[i], nums[i]);
//      } else {
//        // 正的最大值，负负得正
//        dp[x][0] = Math.max(dp[y][1] * nums[i], nums[i]);
//        // 负的最小值(绝对值最大值)
//        dp[x][1] = Math.min(dp[y][0] * nums[i], nums[i]);
//      }

      /**
       * 可以写成
       */
      dp[x][0] = Math.max(Math.max(dp[y][0] * nums[i],dp[y][1] * nums[i]),nums[i]);
      dp[x][1] = Math.min(Math.min(dp[y][1] * nums[i],dp[y][0] * nums[i]),nums[i]);

      //最后的结果取决于正的最大值
      res = Math.max(res,dp[x][0]);
    }

    return res;
  }






}
