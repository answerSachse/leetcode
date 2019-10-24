package leetcode;

public class SearchApp {
  public static void main(String[] args) {

  }

  /**
   * 二分查找
   *
   * @param arr
   * @param target
   * @return
   */
  public static int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
      //如果left和right很大时，left + right可能会溢出
      int mid = left + (right - left) / 2;

      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }

    }
    return -1;

  }


}
