package algorithm.array;

import java.util.*;

public class BinSearch {

    static int binSearchLeftBound(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target <= nums[mid]) right = mid - 1;
            else if (target > nums[mid]) left = mid + 1;
        }
        if (left >= nums.length || nums[left] != target)
            return -left - 1;
        return left;
    }

    static int binSearchRightBound(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target < nums[mid]) right = mid - 1;
            else if (target >= nums[mid]) left = mid + 1;
        }
        if (right < 0 || nums[right] != target)
            return -left - 1;
        return right;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1};

        int idx;

        idx = binSearchRightBound(nums, 2);
        System.out.println("right bound result: " + idx);

        idx = binSearchLeftBound(nums, 2);
        System.out.println("left bound result: " + idx);

        idx = Arrays.binarySearch(nums, 2);
        System.out.println("JDK result: " + idx);
    }
}
