package leetcode;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数组
 * Hello world!
 *
 * @author hefuhai
 */
public class ArraysApp {
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
    //    b = a ^ b;  b = a ^ b ^ b
    //    a = a ^ b; a = a ^ b ^ a ^ b ^ b
    //    System.out.println(a + "," + b);


    //    int[] nums1 = new int[] {1, 2, 2, 1};
    //    int[] nums2 = new int[] {2, 2};
    //    intersect(nums1, nums2);
    //
    //    int[] nums = new int[] {9, 9, 9};
    //    plusOne(nums);


//    int[] nums = new int[] {1, 0, 1};
//    moveZeroes(nums);


    int[] nums = new int[] {3,2,4};

    int[] array = twoSum(nums,6);

    System.out.println(array);



  }



  public static int[] twoSum(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return null;
    }

    Map<Integer,Integer> map = new HashMap<>();

    for(int i = 0 ; i < nums.length;i++){
      int reduce = target - nums[i];
      if(map.containsKey(reduce) ){
        return new int[]{map.get(reduce),i};
      }

      map.put(nums[i],i);
    }

    return null;

  }



  /**
   * 移动零
   *
   * @param nums
   */
  public static void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) {
      return;
    }
    //    int temp = 0;
    //    for (int i = 0; i < nums.length - 1; i++) {
    //      for (int j = i + 1; j < nums.length; j++) {
    //        if (nums[i] == 0 && nums[j] != 0) {
    //          temp = nums[i];
    //          nums[i] = nums[j];
    //          nums[j] = temp;
    //          break;
    //        }
    //      }
    //    }



    int lastZeroIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        int temp = nums[i];
        nums[i] = nums[lastZeroIndex];
        nums[lastZeroIndex++] = temp;
      }
    }



  }


  /**
   * @param digits
   * @return
   */
  public static int[] plusOne(int[] digits) {
    if (digits == null || digits.length == 0) {
      return digits;
    }

    /**
     * 方法一
     */
    //    boolean carry = true;
    //    for (int i = digits.length - 1; i >= 0; i--) {
    //      if ((digits[i] + 1) == 10 && carry) {
    //        digits[i] = 0;
    //      } else {
    //        digits[i] = digits[i] + 1;
    //        carry = false;
    //        break;
    //      }
    //    }
    //
    //    if (!carry) {
    //      return digits;
    //    }
    //    int[] result = new int[digits.length + 1];
    //    result[0] = 1;
    //    return result;


    /**
     * 方法二
     */
    int carry = 1;
    for (int i = digits.length - 1; i >= 0; i--) {
      int tmp = digits[i] + carry;
      digits[i] = tmp % 10;
      carry = tmp / 10;
    }
    if (carry == 1) {
      int[] result = new int[digits.length + 1];
      result[0] = 1;
      return result;
    }

    return digits;
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
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    int i = 0, j = 0;
    List<Integer> temp = new ArrayList<>();
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        temp.add(nums1[i]);
        i++;
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else {
        j++;
      }
    }

    int[] result = new int[temp.size()];
    for (int k = 0; k < result.length; k++) {
      result[k] = temp.get(k);
    }


    return result;

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
