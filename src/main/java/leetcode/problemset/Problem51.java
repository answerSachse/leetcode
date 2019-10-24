package leetcode.problemset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hefuhai
 */
public class Problem51 {

  private  static  int count = 0;



  public static void main(String[] args) {
    Problem51 problem = new Problem51();
    //System.out.println(problem.solveNQueens(20));
    //System.out.println(problem.totalNQueensOne(20));
    System.out.println(problem.totalNQueensTwo(20));
  }


  /***************************N皇后 I ***************************************/

  /**
   * N皇后
   * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
   *
   * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
   *
   * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
   *
   * 示例:
   *
   * 输入: 4
   * 输出: [
   *  [".Q..",  // 解法 1
   *   "...Q",
   *   "Q...",
   *   "..Q."],
   *
   *  ["..Q.",  // 解法 2
   *   "Q...",
   *   "...Q",
   *   ".Q.."]
   * ]
   * 解释: 4 皇后问题存在两个不同的解法。
   *
   */


  /**
   * DFS 深度优先算法
   * N > 20 性能不佳
   *
   * 问题分析：
   * 1、每一行就代表深度，到第N层就结束。
   *    每一层里做的事：
   *    A、枚举每一列(每一层只能放一个皇后),列用j表示
   *    B、判断格子能否放皇后
   *      B.1 暴力
   *      B.2 剪枝(用数据缓存已经存的位置)；被占=1,否则=0;
   *          列---col[j] = 1;
   *          撇--- y=-x+b ==>  x+y = b  pie[x+y] = 1;
   *          捺--- y=x-b  ==> x-y = b   na[x-y] =1;
   *    C、当前层做完，要恢复现场。
   *
   * @param n
   * @return
   */
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    List<String> currLevel = new ArrayList<>();
    Set<Integer> cols = new HashSet<>();
    Set<Integer> pies = new HashSet<>();
    Set<Integer> nas = new HashSet<>();

    this.dfsQueensOne(result,cols,pies,nas,0,n,currLevel);


    return result;

  }

  /**
   *  解法 1
   * @param result  结果集
   * @param cols  列
   * @param pies  撇
   * @param nas   捺
   * @param row 当前层（行）
   * @param n
   * @param solutionList 每一种方案的list
   */
  private void dfsQueensOne(List<List<String>> result, Set<Integer> cols, Set<Integer> pies, Set<Integer> nas, int row, int n,List<String> solutionList) {


    //剪枝
    if(row >= n){
      result.add(new ArrayList<>(solutionList));
      return;
    }

    //遍历列
    for(int col = 0;col< n;col++){

      //剪枝
      if(cols.contains(col) || pies.contains(row + col) || nas.contains(row - col)){
        continue;
      }

      cols.add(col);
      pies.add(row + col);
      nas.add(row - col);

      String colStr = tranferStr(col, n);
      solutionList.add(colStr);
      dfsQueensOne(result,cols,pies,nas,row+1,n,solutionList);

      cols.remove(col);
      pies.remove(row + col);
      nas.remove(row-col);
      solutionList.remove(colStr);

    }

  }


  /**
   * 转换成字符串
   * @param col
   * @param n
   * @return
   */
  public String tranferStr(int col,int n){
    String str = "";
    for(int i = 0;i< n ;i++){
      if(i == col){
        str += "Q";
        continue;
      }
      str += ".";
    }
    return str;
  }










    /***************************N皇后 II ***************************************/
  /**
   * 方法一
   *
   * 输出解决方案数量
   * @param n
   * @return
   */
  public int totalNQueensOne(int n) {
    Set<Integer> cols = new HashSet<>();
    Set<Integer> pies = new HashSet<>();
    Set<Integer> nas = new HashSet<>();
    return this.dfsQueensTotal(0,cols,pies,nas,0,n);

  }

  private int dfsQueensTotal(int total,Set<Integer> cols, Set<Integer> pies, Set<Integer> nas, int row, int n) {
    //剪枝
    if(row >= n){
      return ++total;
    }

    //遍历列
    for(int col = 0;col< n;col++){

      //剪枝
      if(cols.contains(col) || pies.contains(row + col) || nas.contains(row - col)){
        continue;
      }

      cols.add(col);
      pies.add(row + col);
      nas.add(row - col);

      total = dfsQueensTotal(total,cols,pies,nas,row+1,n);

      cols.remove(col);
      pies.remove(row + col);
      nas.remove(row-col);

    }
    return total;
  }



  /**
   *
   * 方法二
   * 位运算
   *
   *
   *
   * @param n
   * @return
   */
  public int totalNQueensTwo(int n) {
    if (n < 1) {
      return count;
    }
    this.dfsQueensTotalBit(n, 0, 0, 0, 0);
    return count;
  }

  /**
   * cols,pie,na位的值 1是代表被占；0代表未被占
   *
   * 1、(cols | pie | na) 作用是，得到在cols,pie,na中哪些被占，哪些未被占
   * 2、~(cols | pie | na)作用是，得到位值1代表未被占。但是高位变成1。所以进行3步骤mask。
   * 3、(1 << n) - 1 作用是，都变成1，与步骤2的值&,把高位变成0。
   * 4、(~(cols | pie | na)) & ((1 << n) - 1) 得到当前所有的空位(可以让皇后的位置)，位值为1的是空位
   * 5、枚举4结果中的所有二进制：
   *    5.1、获取最后一位的1( p = bits & -bits),也就是放Q的位置。
   *    5.2、cols,pie,na都要更新，把p中1的位置，加入到cols,pie,na中。
   *         cols | p 添加5.1中的位置
   *         (pie | p) << 1 左移一位
   *         (na | p) >> 1 右移一位
   * 6、处理过最后1位之后，把最后一位1去掉(bits & (bits - 1));
   *
   *
   *
   * @param n
   * @param row
   * @param cols
   * @param pie
   * @param na
   */
  public void dfsQueensTotalBit(int n, int row, int cols, int pie, int na) {
    if (row >= n) {
      count++;
      return;
    }

    //得到当前所有的空位(可以让皇后的位置)，位值为1的是空位
    int bits = (~(cols | pie | na)) & ((1 << n) - 1);
    while (bits > 0) {
      //获取最低位的1
      int p = bits & -bits;
      dfsQueensTotalBit(n, row + 1, cols | p, (pie | p) << 1, (na | p) >> 1);
      //去掉最低位的1
      bits = bits & (bits - 1);
    }
  }






}
