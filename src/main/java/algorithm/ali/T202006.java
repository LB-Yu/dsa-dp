package algorithm.ali;

import java.util.Scanner;

/**
 * 输入n个数，让你从中选k个数做&运算，即位运算中的与运算，现在从所有方案中，将这k个数&一起的最小值是多少？
 *
 * 输入描述:
 * 输入第一行有一个整数T，代表接下来有T组测试数据
 * 对于每一组测试数据第一行输入两个数n和k，代表数的个数以及要选择的个数
 * 接下来n个数，代表每一个数的大小
 * 1<=n<=40
 * 1<=T<=10
 * 1<=a[i]<=2^60
 *
 * 输出描述:
 * 对于每组数据输出一行代表所能选取的最小值。
 *
 * 输入
 * 2
 * 3 2
 * 5 6 7
 * 8 2
 * 238 153 223 247 111 252 253 247
 *
 * 输出
 * 4
 * 9
 * */
public class T202006 {

  private int[] nums;
  private int k;
  private int min;

  public int solution(int[] nums, int k) {
    this.nums = nums;
    this.k = k;
    this.min = Integer.MAX_VALUE;
    dfs(0, 0, 0xffffffff);
    return min;
  }

  private void dfs(int i, int count, int value) {
    if (count == k) {
      min = Math.min(min, value);
      return;
    }
    if (i >= nums.length) return;
    dfs(i + 1, count + 1, value & nums[i]);
    dfs(i + 1, count, value);
  }

  public static void main(String[] args) {
    T202006 solution = new T202006();
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    for (int i = 0; i < n; ++i) {
      int c = scanner.nextInt(), k = scanner.nextInt();
      int[] nums = new int[c];
      for (int j = 0; j < c; ++j) {
        nums[j] = scanner.nextInt();
      }
      int min = solution.solution(nums, k);
      System.out.println(min);
    }
  }

}
