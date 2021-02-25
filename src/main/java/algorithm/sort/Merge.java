package algorithm.sort;

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
     * 最小堆多路归并
     * */
    public static int[] mergeKMinHeap(int[][] arrays) {
        return null;
    }

    public static void main(String[] args) {
        int[][] arrays = new int[][] {
                {1, 2, 3},
                {1, 6},
                {3, 4, 7}};
        int[] res = mergeK(arrays);
        for (int n : res) {
            System.out.print(n + ",");
        }
    }
}
