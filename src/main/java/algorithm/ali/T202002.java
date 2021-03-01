package algorithm.ali;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 每行表示一个非降序排列的字符串
 * 求各行拼接后的最长非降序字符串长度
 *
 * 测试:
 * aaa
 * bcd
 * zzz
 * bcdef
 * */
public class T202002 {

  public int solution(String[] lines) {
    return getLongest(lines);
  }

  public int getLongest(String[] lines) {
    Arrays.sort(lines);
    int[] dp = new int[lines.length];
    for (int i = 0; i < lines.length; ++i) dp[i] = lines[i].length();
    for (int i = 0; i < lines.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (lines[i].charAt(0) >= lines[j].charAt(lines[j].length() - 1)) {
          dp[i] = Math.max(dp[i], dp[j] + lines[i].length());
        }
      }
    }
    return dp[lines.length-1];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    String[] lines = new String[n];
    for (int i = 0; i < n; ++i) {
      lines[i] = scanner.nextLine();
    }
    System.out.println(new T202002().solution(lines));
  }
}
