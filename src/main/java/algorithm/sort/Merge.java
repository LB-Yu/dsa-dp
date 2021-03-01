package algorithm.sort;

import java.util.PriorityQueue;

public class Merge {
    /**
     * 循环多路归并算法
     * */
    public static int[] mergeK(int[][] arrays) {
        int n = 0;
        for (int i = 0; i < arrays.length; ++i) n += arrays[i].length;
        int[] res = new int[n];
        int t = 0;
        int[] is = new int[arrays.length];
        boolean isEmpty = false;
        while (!isEmpty) {
            int min = Integer.MAX_VALUE, minIdx = -1;
            for (int i = 0; i < arrays.length; ++i) {
                if (is[i] >= arrays[i].length) {
                    isEmpty = true;
                    continue;
                }
                isEmpty = false;
                if (arrays[i][is[i]] < min) {
                    min = arrays[i][is[i]];
                    minIdx = i;
                }
            }
            if (!isEmpty) {
                res[t++] = min;
                is[minIdx]++;
            }
        }
        return res;
    }

    /**
     * 分治算法
     * */
    public static int[] mergeKDivide(int[][] arrays) {
        return mergeKDivide(arrays, 0, arrays.length - 1);
    }

    private static int[] mergeKDivide(int[][] arrays, int start, int end) {
        if (start == end) return arrays[start];
        int mid = (start + end) >>> 1;
        return mergeTwo(mergeKDivide(arrays, start, mid), mergeKDivide(arrays, mid + 1, end));
    }

    private static int[] mergeTwo(int[] a1, int[] a2) {
        int m = a1.length, n = a2.length;
        int[] res = new int[m+n];
        int cur = 0;
        int i1 = 0, i2 = 0;
        while (i1 < m && i2 < n) {
            if (a1[i1] < a2[i2]) {
                res[cur] = a1[i1];
                ++i1;
            } else {
                res[cur] = a2[i2];
                ++i2;
            }
            ++cur;
        }
        while (i1 < m) res[cur++] = a1[i1++];
        while (i2 < n) res[cur++] = a2[i2++];
        return res;
    }

    /**
     * 最小堆多路归并
     * */
    public static int[] mergeKMinHeap(int[][] arrays) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
        for (int i = 0; i < arrays.length; ++i) {
            if (arrays[i] != null && arrays[i].length > 0) {
                pq.offer(new int[] {arrays[i][0], i, 0});
            }
        }
        int n = 0;
        for (int[] array : arrays) {
            if (array != null) n += array.length;
        }
        int[] res = new int[n];
        int cur = 0;
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            res[cur++] = node[0];
            int i = node[1], j = node[2] + 1;
            if (j < arrays[i].length) {
                pq.offer(new int[] {arrays[i][j], i, j});
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arrays = new int[][] {
                {1, 2, 3},
                {1, 6},
                {3, 4, 7}};
        int[] res = mergeKDivide(arrays);
        for (int n : res) {
            System.out.print(n + ",");
        }
    }
}
