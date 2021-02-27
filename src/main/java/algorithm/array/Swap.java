package algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Swap {

  /**
   * 直接交换法
   * */
  public static int minSwapNums(int[] nums) {
    int[] sortNums = Arrays.copyOf(nums, nums.length);
    Arrays.sort(sortNums);
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < sortNums.length; ++i) {
      map.put(sortNums[i], i);
    }
    int count = 0;
    for (int i = 0; i < nums.length; ++i) {
      while (map.get(nums[i]) != i) {
        swap(nums, i, map.get(nums[i]));
        ++count;
      }
    }
    return count;
  }

  private static void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  /**
   * 循环节方法
   * */
  public static int minSwapNums2(int[] nums) {
    int[] sortNums = Arrays.copyOf(nums, nums.length);
    Arrays.sort(sortNums);
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < sortNums.length; ++i) {
      map.put(sortNums[i], i);
    }
    int count = 0;
    boolean[] visited = new boolean[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      if (!visited[i]) {
        int j = i;
        while (!visited[j]) {
          visited[j] = true;
          j = map.get(nums[j]);
        }
        ++count;
      }
    }
    return nums.length - count;
  }

  public static void main(String[] args) {
    int[] a = new int[] {2, 3, 4, 5, 1};
//    System.out.println(minSwapNums(a));
    System.out.println(minSwapNums2(a));
  }
}
