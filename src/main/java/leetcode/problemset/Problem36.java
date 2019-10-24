package leetcode.problemset;

import com.sun.tools.javac.util.StringUtils;

/**
 * 数独
 * @author hefuhai
 */
public class Problem36 {


  private static int count = 0;


  /**
   * 编写一个程序，通过已填充的空格来解决数独问题。
   *
   * 一个数独的解法需遵循如下规则：
   *
   * 数字 1-9 在每一行只能出现一次。
   * 数字 1-9 在每一列只能出现一次。
   * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
   * 空白格用 '.' 表示。
   *
   */


  public static void main(String[] args) {
    Problem36 problem = new Problem36();

    char[][] board = new char[9][9];

    //String str = "5,3,.,.,7,.,.,.,.;6,.,.,1,9,5,.,.,.;.,9,8,.,.,.,.,6,.;8,.,.,.,6,.,.,.,3;4,.,.,8,.,3,.,.,1;7,.,.,.,2,.,.,.,6;.,6,.,.,.,.,2,8,.;.,.,.,4,1,9,.,.,5;.,.,.,.,8,.,.,7,9";
    String str = "8,.,.,.,.,.,.,.,.;.,.,3,6,.,.,.,.,.;.,7,.,.,9,.,2,.,.;.,5,.,.,.,7,.,.,.;.,.,.,.,4,5,7,.,.;.,.,.,1,.,.,.,3,.;.,.,1,.,.,.,.,6,8;.,.,8,5,.,.,.,1,.;.,9,.,.,.,.,4,.,.";
    int i = 0, j = 0;
    for (String row : str.split(";")) {
      for (String col : row.split(",")) {
        board[i][j++] = col.charAt(0);
      }
      ++i;
      j = 0;
    }

    problem.solveSudokuDFS(board);
    for (i = 0; i < 9; i++) {
      for (j = 0; j < 9; j++) {
        String end = j < 8 ? "," : "\n";
        System.out.print(board[i][j]+end);
      }
    }



  }


  /**
   * 一、 DFS 普通方法
   *
   *  dfs(row,col)
   *    在每一层遍历 col中：
   *      1、枚举1--9的数
   *      2、检查行、列和宫不存在重复
   *
   *
   *
   * @param board
   */
  public void solveSudokuDFS(char[][] board) {
    if(board == null || board.length == 0){
      return;
    }
    solve(board,0,0);
  }

  private boolean solve(char[][] board,int row,int col) {
    /**
     * row++,col = 0
     * col+1
     * 此设计  是不需要重复执行数据
     */

    for (; row < board.length; row++,col = 0) {
      for (; col < board[0].length; col++) {
        if (board[row][col] == '.') {
          //枚举1-9的数
          for (char c = '1'; c <= '9'; c++) {
            //检查行、列和宫不存在重复
            if (isValid(board, row, col, c)) {
              board[row][col] = c;
              if (solve(board,row,col+1)) {
                return true;
              } else {
                board[row][col] = '.';
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }





  /**
   * 判断数字是否有效
   * @param board
   * @param row
   * @param col
   * @param c
   * @return
   */
  private boolean isValid(char[][] board, int row, int col, char c) {
    //9*9 的棋盘
    for (int i = 0; i < 9; i++) {
      //行
      if (board[i][col] != '.' && board[i][col] == c) {
        return false;
      }

      //列
      if (board[row][i] != '.' && board[row][i] == c) {
        return false;
      }

      //宫
      int blockRow = 3 * (row / 3) + i / 3;
      int blockCol = 3 * (col / 3) + i % 3;
      if (board[blockRow][blockCol] != '.' && board[blockRow][blockCol] == c) {
        return false;
      }
    }
    return true;
  }






  /**
   * 二、 DFS 加速方法
   *
   *  dfs(row,col)
   *    剪枝：
   *       1、选项少的开始(从存在数字多的行或列开始)，搜索空间大大的减少
   *       2、预处理：先用N*N的循环搜索棋盘，把每有个空格能填的数预处理(可选数)。
   *
   *
   * 处理流程：
   * 1、根据可选数进行从小到大排序
   * 2、在dfs时，从可选数最少的开始循环
   *    2.1 循环选数最
   *    2.2 检查行、列和宫不存在重复
   *
   *
   *
   *
   * @param board
   */
  public void solveSudokuDFSQuick(char[][] board) {

  }



  /**
   * 三、 DancingLinks 算法实现
   * 详细见 dlx 目录
   *
   * @param board
   */
  public void solveSudokuDancingLinks(char[][] board) {

  }



}


