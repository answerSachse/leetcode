package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeApp {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    TreeNode leftRoot = new TreeNode(9);
    TreeNode rightRoot = new TreeNode(20);
    root.left = leftRoot;

    root.right = rightRoot;
    TreeNode right2 = new TreeNode(15);
    TreeNode right3 = new TreeNode(7);
    rightRoot.left = right2;
    rightRoot.right = right3;

    TreeApp treeApp = new TreeApp();
    //    List<List<Integer>> lists = treeApp.levelOrderBFS(root);
    //    System.out.println(lists);

    //    List<List<Integer>> lists = treeApp.levelOrderDFS(root);
    //    System.out.println(lists);

    System.out.println(treeApp.maxDepthBFS(root));
    System.out.println(treeApp.minDepthBFS(root));

    System.out.println(treeApp.maxDepthDFS(root));
    System.out.println(treeApp.minDepthDFS(root));



  }


  /************************************层次遍历  start***************************************************/

  /**
   * 层次遍历(广度优先遍历)
   *
   * @param root
   * @return
   */
  public List<List<Integer>> levelOrderBFS(TreeNode root) {

    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    //if used

    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      List<Integer> currLevel = new ArrayList<>();

      for (int i = 1; i <= queueSize; i++) {
        TreeNode node = queue.poll();
        currLevel.add(node.val);
        //把孩子节点加入到queue
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      result.add(currLevel);
    }
    return result;
  }


  /**
   * 层次遍历(深度优先遍历)
   *
   * @param root
   * @return
   */
  public List<List<Integer>> levelOrderDFS(TreeNode root) {

    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    dfsOrder(result, root, 0);
    return result;
  }


  private void dfsOrder(List<List<Integer>> result, TreeNode root, int level) {
    if (root == null) {
      return;
    }
    if (result.size() < level + 1) {
      result.add(new ArrayList<>());
    }
    result.get(level).add(root.val);

    dfsOrder(result, root.left, level + 1);
    dfsOrder(result, root.right, level + 1);

  }


  /************************************最大深度  start***************************************************/


  /**
   * 二叉树的最大深度(广度优先)
   *
   * @param root
   * @return
   */
  public int maxDepthBFS(TreeNode root) {
    int depth = 0;
    if (root == null) {
      return depth;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int i = 1; i <= queueSize; i++) {
        TreeNode node = queue.poll();
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      depth++;
    }
    return depth;
  }


  /**
   * 二叉树的最大深度(深度优先)
   *
   * @param root
   * @return
   */
  public int maxDepthDFS(TreeNode root) {
    return root == null ? 0 : 1 + Math.max(maxDepthDFS(root.left), maxDepthDFS(root.right));
  }



  /************************************最小深度  start***************************************************/


  /**
   * 二叉树的最小深度(广度优先)
   *
   * @param root
   * @return
   */
  public int minDepthBFS(TreeNode root) {
    int depth = 0;
    if (root == null) {
      return depth;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      ++depth;
      for (int i = 1; i <= queueSize; i++) {
        TreeNode node = queue.poll();

        //叶子节点
        if (node.left == null && node.right == null) {
          return depth;
        }

        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }

    }
    return depth;
  }



  /**
   * 二叉树的最小深度(深度优先)
   *
   * @param root
   * @return
   */
  public int minDepthDFS(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null) {
      return 1 + minDepthDFS(root.right);
    }
    if (root.right == null) {
      return 1 + minDepthDFS(root.left);
    }

    //divide and  conqure
    int leftMinDepth = minDepthDFS(root.left);
    int rightMinDepth = minDepthDFS(root.right);

    //process subproblems's result
    int result = 1 + Math.min(leftMinDepth, rightMinDepth);

    return result;
  }



  /**
   * 简化版一
   *
   * @param root
   * @return
   */
  public int minDepthDFSOne(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null) {
      return 1 + minDepthDFSOne(root.right);
    }
    if (root.right == null) {
      return 1 + minDepthDFSOne(root.left);
    }
    return 1 + Math.min(minDepthDFSOne(root.left), minDepthDFSOne(root.right));
  }


  /**
   * 简化版二
   *
   * @param root
   * @return
   */
  public int minDepthDFSTwo(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = minDepthDFSTwo(root.left);
    int right = minDepthDFSTwo(root.right);

    return (left == 0 || right == 0) ? (left + right + 1) : 1+Math.min(left, right);
  }



}



class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}
