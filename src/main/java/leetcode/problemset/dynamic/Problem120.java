package leetcode.problemset.dynamic;

import java.util.ArrayList;
import java.util.List;

public class Problem120 {

  /**
   * 三角形最小路径和
   *
   *
   * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
   *
   * 例如，给定三角形：
   *
   * [
   *      [2],
   *     [3,4],
   *    [6,5,7],
   *   [4,1,8,3]
   * ]
   * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
   *
   *
   */


  public static void main(String[] args) {
    Problem120 problem = new Problem120();

    List<List<Integer>> arrys = new ArrayList<>();

    List<Integer> list0 = new ArrayList<>();
    list0.add(2);
    arrys.add(list0);

    List<Integer> list1 = new ArrayList<>();
    list1.add(3);
    list1.add(4);
    arrys.add(list1);

    List<Integer> list2 = new ArrayList<>();
    list2.add(6);
    list2.add(5);
    list2.add(7);
    arrys.add(list2);

    List<Integer> list3 = new ArrayList<>();
    list3.add(4);
    list3.add(1);
    list3.add(8);
    list3.add(3);
    arrys.add(list3);


    System.out.println(problem.minimumTotal(arrys));
    System.out.println(problem.minimumTotalRecursive(arrys));

  }



  

  /**
   * 递归 O(2^n)
   * @param triangle
   * @return
   */
  public int minimumTotalRecursive(List<List<Integer>> triangle) {
    if (triangle == null) {
      return 0;
    }
    int deep = triangle.size();
    return dfs(deep, 0, 0, triangle);
  }

  private int dfs(int deep, int row, int col, List<List<Integer>> triangle) {
    if (row >= deep) {
      return 0;
    }
    return triangle.get(row).get(col) + Math
      .min(dfs(deep, row + 1, col, triangle), dfs(deep, row + 1, col + 1, triangle));
  }



  /**
   * 动态规划 O(m*n)
   *
   * 自下而上分析
   *
   * 动态规划的最重要的两点：
   * a、DP状态的定义:
   *  DP[i,j] : 从底-->(i,j)节点走过路径和的最小值。
   *
   * b、DP方程:
   *   DP[i,j] = min (DP[i+1,j],DP[i+1,j+1]) +  triangle[i,j];
   *   DP[i,j]当前节点的路径总值 = 下一层中，左、右节点最小总值，加上当前节点的值。
   *
   *
   * @param triangle
   * @return
   */
  public int minimumTotal(List<List<Integer>> triangle) {
    if (triangle == null) {
      return 0;
    }

    int row = triangle.size() - 1;

    int[] dp = new int[triangle.get(row).size()+1];

    //遍历行
    for (int i = row; i >= 0; i--) {
      //遍历列
      for (int j = 0; j < triangle.get(i).size(); j++) {
        dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
      }
    }

    return dp[0];
  }

}
