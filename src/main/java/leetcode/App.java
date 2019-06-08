package leetcode;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 * @author hefuhai
 */
public class App {
  public static void main(String[] args) {


    //    int[] num = new int[] {1, 1,1,1,1,2, 2, 2, 3, 4, 1, 1, 1, 1};
    //    System.out.println(removeDuplicates(num));

    //
    //    int[] num = new int[] {7, 6, 4, 3, 1};
    //    System.out.println(maxProfitOne(num));



    //    int[] num = new int[] {6, 1, 2, 1, 2};
    //    //System.out.println(maxProfitOne(num));
    //    //rotate(num, 3);
    //    System.out.println(singleNumber(num));


    /**
     * 异或交换两个数组
     */
    //    int a = 2, b = 6;
    //    a = a ^ b;
    //    b = b ^ a;
    //    a = a ^ b;
    //    System.out.println(a + "," + b);


    int[] nums1 = new int[] {1, 2, 2, 1};
    int[] nums2 = new int[] {2, 2};

  }


  /**
   * 两个数组的交集 II
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public static int[] intersect(int[] nums1, int[] nums2) {
    if (nums1 == null || nums1.length == 0) {
      return nums1;
    }
    if (nums2 == null || nums2.length == 0) {
      return nums2;
    }

  }



  /**
   * 只出现一次的数字
   *
   * @param nums
   * @return
   */
  public static int singleNumber(int[] nums) {

    //    Arrays.sort(nums);
    //    for (int i = 0; i < nums.length; i += 2) {
    //      if (i >= nums.length-1) {
    //        return nums[nums.length-1];
    //      }
    //      if (nums[i] != nums[i + 1]) {
    //        return nums[i];
    //      }
    //    }
    //    return -1;

    /**
     * 方法二
     */
    int temp = 0;
    for (int i = 0; i < nums.length; i++) {
      temp ^= nums[i];
    }
    return temp;
  }



  /**
   * 存在重复
   *
   * @param nums
   * @return
   */
  public static boolean containsDuplicate(int[] nums) {
    if (nums == null || nums.length == 0) {
      return false;
    }

    /**
     * 方法一
     */
    //    Arrays.sort(nums);
    //    for (int i = 0; i < nums.length; i++) {
    //      if (nums[i] == nums[i+1]) {
    //        return true;
    //      }
    //    }
    //    return false;

    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      set.add(nums[i]);
    }
    return nums.length != set.size();

  }

  /**
   * 旋转数组
   *
   * @param nums
   * @param k
   */
  public static void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return;
    }
    /**
     * 方法一
     */
    //    for (int i = 0; i < k % nums.length; i++) {
    //      int temp = nums[nums.length - 1];
    //      for (int j = nums.length - 2; j >= 0; j--) {
    //        nums[j + 1] = nums[j];
    //      }
    //      nums[0] = temp;
    //    }

    /**
     * 方法二  空间复杂度O(n)
     * (i+k) % length
     */
    //    int length = nums.length;
    //    k %= length;
    //    int[] result = new int[length];
    //    result = nums;
    //    for (int i = 0; i < length; i++) {
    //      nums[(i + k) % length] = result[i];
    //    }


    /**
     * 空间复杂度O(1)   和时间复杂度O(n)
     * 1、先翻转 n-k个元素 ，后翻转k个元素
     * 2、最后翻转这个数组
     */
    int length = nums.length;
    k %= length;
    reverse(nums, 0, length - k - 1);
    reverse(nums, length - k, length - 1);
    reverse(nums, 0, length - 1);

    System.out.println(nums);

  }

  private static void reverse(int[] nums, int start, int end) {
    int temp = 0;
    for (; start < end; start++, end--) {
      temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
    }

  }



  /**
   * 从排序数组中删除重复项
   *
   * @param nums
   * @return
   */
  public static int removeDuplicates(int[] nums) {
    int start = 0;
    for (int end = 1; end < nums.length; end++) {
      if (nums[start] != nums[end]) {
        nums[++start] = nums[end];
      }
    }
    return start + 1;
  }



  /**
   * 买卖股票的最佳时机 I
   *
   * @param prices
   * @return
   */
  public static int maxProfitOne(int[] prices) {
    if (prices == null) {
      return 0;
    }

    int minPrice = prices[0];
    int max = 0;
    for (int i = 0; i < prices.length; i++) {
      max = Math.max(max, prices[i] - minPrice);
      minPrice = Math.min(minPrice, prices[i]);

    }
    return max;

  }


  /**
   * 买卖股票的最佳时机 II
   *
   * @param prices
   * @return
   */
  public static int maxProfitTwo(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }

    int sum = 0;
    for (int i = 0; i < prices.length - 1; i++) {
      if (prices[i] < prices[i + 1]) {
        sum += prices[i + 1] - prices[i];
      }
    }
    return sum;

  }



}
