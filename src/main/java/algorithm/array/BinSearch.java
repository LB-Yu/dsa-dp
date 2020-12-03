package algorithm.array;

public class BinSearch {

    int binSearchLeftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) right = mid;
            else if (nums[mid] < target) left = mid + 1;
        }
        return left;
    }

    int binSearchRightBound(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) right = mid;
            else if (nums[mid] <= target) left = mid + 1;
        }
        return nums[right-1] == target ? right - 1 : right;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {};
        BinSearch binSearch = new BinSearch();
        int res = binSearch.binSearchRightBound(nums, 7);
        System.out.println(res);
    }
}
