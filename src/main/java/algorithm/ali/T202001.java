package algorithm.ali;

import java.util.Scanner;

/**
 * 有一叠扑克牌，每张牌介于1和10之间
 *
 * 有四种出牌方法：
 *
 * 单出一张
 * 出两张相同的牌(对子)
 * 出五张顺子(如12345)
 * 出三连对子(如112233)
 * 给10个数，表示1-10每种牌有几张，问最少要多少次能出完？
 * */
public class T202001 {

  private int[] nums;

  public int solution(int[] nums) {
    this.nums = nums;
    return getNum(0);
  }

  public int getNum(int i) {
    int res = Integer.MAX_VALUE;
    if (i >= nums.length) return 0;
    if (nums[i] == 0) return getNum(i + 1);
    if (i + 2 < nums.length && nums[i] >= 2 && nums[i+1] >= 2 && nums[i+2] >= 2) {
      nums[i] -= 2;
      nums[i+1] -= 2;
      nums[i+2] -= 2;
      res = Math.min(res, 1 + getNum(i));
      nums[i] += 2;
      nums[i+1] += 2;
      nums[i+2] += 2;
    }
    if (i + 4 < nums.length && nums[i] >= 1 && nums[i+1] >= 1 && nums[i+2] >= 1 && nums[i+3] >= 1 && nums[i+4] >= 1) {
      nums[i] -= 1;
      nums[i+1] -= 1;
      nums[i+2] -= 1;
      nums[i+3] -= 1;
      nums[i+4] -= 1;
      res = Math.min(res, 1 + getNum(i));
      nums[i] += 1;
      nums[i+1] += 1;
      nums[i+2] += 1;
      nums[i+3] += 1;
      nums[i+4] += 1;
    }
    if (nums[i] >= 2) {
      nums[i] -= 2;
      res = Math.min(res, 1 + getNum(i));
      nums[i] += 2;
    }
    if (nums[i] >= 1) {
      nums[i] -= 1;
      res = Math.min(res, 1 + getNum(i));
      nums[i] += 1;
    }
    return res;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] cards = new int[10];
    for (int i = 0; i < 10; ++i) {
      cards[i] = scanner.nextInt();
    }
    System.out.println(new T202001().solution(cards));
  }
}
