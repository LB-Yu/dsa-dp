package algorithm.ali;

/**
 * 给定N*M的地图（1或者0），如果2*2区域的点均为1，可以设置一个运营区域，运营区域不互相覆盖，问最多可以设置多少个运营区域。
 * */
public class T202003 {

  private int[][] nums;
  private int m;
  private int n;
  private int max;
  private int res;

  public int solution(int[][] nums) {
    this.nums = nums;
    this.m = nums.length;
    this.n = nums[0].length;
    dfs(0, 0);
    return max;
  }

  public void dfs(int i, int j) {
    if (j >= n - 1) {
      dfs(i + 1, 0);
      return;
    }
    if (i >= m - 1) {
      max = Math.max(max, res);
      return;
    }
    if (nums[i][j] == 1 && nums[i][j+1] == 1 && nums[i+1][j] == 1 && nums[i+1][j+1] == 1) {
      res += 1;
      nums[i][j] = 0;
      nums[i][j+1] = 0;
      nums[i+1][j] = 0;
      nums[i+1][j+1] = 0;
      dfs(i, j + 1);
      res -= 1;
      nums[i][j] = 1;
      nums[i][j+1] = 1;
      nums[i+1][j] = 1;
      nums[i+1][j+1] = 1;
    }
    dfs(i, j + 1);
  }

  public static void main(String[] args) {
    int[][] num = new int[][] {
            {1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1},
            {0, 1, 1, 0, 0}};
    System.out.println(new T202003().solution(num));
  }
}
