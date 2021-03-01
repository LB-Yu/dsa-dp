package algorithm.ali;

/**
 * 有两张满M可用的优惠券，现在给你N件商品，让你用最少的钱花出去两张优惠券，输出花费的最少钱。如果无法使用就输出‘-1.0’
 * */
public class T202004 {

  private int[] nums;
  private int target;
  private int min = Integer.MAX_VALUE;

  public int solution(int[] nums, int target) {
    this.nums = nums;
    this.target = target;
    dfs(0, 0);
    return min;
  }

  public void dfs(int cur, int i) {
    if (i >= nums.length) return;
    if (cur >= target) {
      min = Math.min(min, cur);
      return;
    }
    dfs(cur + nums[i], i + 1);
    dfs(cur, i + 1);
  }

  public static void main(String[] args) {
    int[] nums = new int[] {200, 55, 50, 251, 251};
    System.out.println(new T202004().solution(nums, 300));
  }
}
