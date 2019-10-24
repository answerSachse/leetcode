package leetcode.problemset.dynamic.stock;

public class Problem188 {

  public static void main(String[] args) {

    Problem188 problem = new Problem188();
    int k =2;
    int[] prices = {2,4,1};
    System.out.println(problem.maxProfit(k,prices));


  }



  /**
   *
   * 买卖股票的最佳时机 IV
   *
   *
   * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
   *
   * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
   *
   * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
   *
   * 示例 1:
   *
   * 输入: [2,4,1], k = 2
   * 输出: 2
   * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
   *
   *示例 2:
   *
   * 输入: [3,2,6,5,0,3], k = 2
   * 输出: 7
   * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
   *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
   *
   *
   *
   *
   *
   *
   * 一、状态的定义 和  状态转移方程
   *
   *  1、  DP[i]  到i天最大的利润。
   *
   *      DP[i] = DP[i-1] + (-a[i])  买股票
   *            DP[i-1] + a[i]  卖股票
   *
   *
   *   但是：维度： 第i天，k笔交易，不能同时参与多笔交易；所以状态的定义要定义成3维
   *              注：简单的说，有几个限制条件，就有多少维度
   *              分别是 DP[i][k][j],j=0代表没有股票，j=1代表有股票。
   *
   * 2、加入j(是否有股票)变成：
   *      DP[i][0] = max (  dp[i-1][0]  //不动
   *                         dp[i-1][1] + a[i]  //卖股票
   *                     )
   *
   *      DP[i][1] = max (  dp[i-1][1]  //不动
   *                        dp[i-1][0] - a[i]  //买股票
   *                     )
   *
   *
   * 3、加入k(k笔交易)变成：
   *  for i--> n
   *     for 0-->k
   *           DP[i][k][0] = max (  dp[i-1][k][0]  //不动
   *                         dp[i-1][k-1][1] + a[i]  //卖股票 本次交易1次，所以上一次交易总次数是k-1
   *                         )
   *
   *          DP[i][k][1] = max (  dp[i-1][k][1]  //不动
   *                            dp[i-1][k][0] - a[i]  //买股票
   *                         )
   *
   *
   *  max( dp[n-1][0...k][0] )
   *
   *
   *
   *  扩展：
   *    股票可以累加(可以有多只股票 j)
   *    [j] 变成0-->j
   *     for i--> n
   *       for 0-->k
   *         for 0-->j
   *            DP[i][k][j] = max (  dp[i-1][k][j]  //不动
   *                                 dp[i-1][k-1][j+1] + a[i]  //卖股票 本次交易1次，所以上一次交易总次数是k-1
   *                                 dp[i-1][k][j-1] - a[i]  //买股票
   *                         )
   *
   *
   *  注： 状态的定义，可以想成盗梦空间，回溯到每一天的状态，并且记录到数组中去，
   *      发现不止要记录第i天，
   *      还要记录记录交易k次，
   *      是否有股票j
   *
   *
   * @param k
   * @param prices
   * @return
   */

  public int maxProfit(int k, int[] prices) {
    if (k == 0 || prices == null) {
      return 0;
    }

    //int[][][] 分别是i天，k 笔交易，是否有股票
    int[][][] dp = new int[prices.length][k + 1][2];
    int res = Integer.MIN_VALUE;

    for (int i = 0; i < prices.length; i++) {
      //初始化数据
      dp[i][0][1] = -prices[0];
    }

    //第i天
    for (int i = 1; i < prices.length; i++) {

      //交易次数
      for (int k1 = 1; k1 <= k; k1++) {

        //没有股票
        dp[i][k1][0] = Math.max(dp[i - 1][k1][0], dp[i - 1][k1][1] + prices[i]);

        //有股票
        dp[i][k1][1] = Math.max(dp[i - 1][k1][1], dp[i - 1][k1-1][0] - prices[i]);

        System.out.println(dp[i][k1][0] + ";" + dp[i][k1][1]);

        res = Math.max(res, dp[i][k1][0]);

      }

    }

    return res;

  }

}
