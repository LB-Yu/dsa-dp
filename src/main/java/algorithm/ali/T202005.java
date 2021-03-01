package algorithm.ali;

import java.util.Arrays;
import java.util.Scanner;

/**
 * n个人排成一排，每个人都拥有自己的能量值，越靠前的能量值越高。每个人可以崇拜唯一的能量值比他高的人，也可以没有崇拜对象。
 * 现在n个人进行投票，每个人可以投自己或者是跟着崇拜对象进行投票，求出每个人可能得到的最多票数。
 *
 * 输入：第一行为人数n，是一个正整数；第二行Ai(1<=i<=n)为第n个人的崇拜对象，是一个整数，之间用空格分隔。
 * 输出：n行整数，第i行为第i个人能得到的最多票数。
 *
 * 示例：
 * 输入：
 * 4
 * 0 1 1 1
 * 输出：
 * 4
 * 1
 * 1
 * 1
 * */
public class T202005 {

  public int[] solution(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    Arrays.fill(res, 1);
    for (int i = n - 1; i >= 0; --i) {
      if (nums[i] != 0) {
        res[nums[i]-1] += res[i];
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    int[] nums = new int[n];
    for (int i = 0; i < n; ++i) {
      nums[i] = scanner.nextInt();
    }
    int[] res = new T202005().solution(nums);
    for (int r : res) {
      System.out.println(r);
    }
  }
}
