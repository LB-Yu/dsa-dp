package algorithm.sort;

import java.util.*;

/**
 * 常用排序算法模板
 * */
public class Sort {

    /**
    * 1. 冒泡排序
    * */
    public static int[] bubbleSort(int[] array) {
        Objects.requireNonNull(array);
        for (int i = 0; i < array.length - 1; ++i) {
            for (int j = 0; j < array.length - i - 1; ++j) {
                if (array[j] > array[j + 1]) swap(array, j, j + 1);
            }
        }
        return array;
    }

    /**
     * 2. 插入排序
     * */
    public static int[] insertionSort(int[] array) {
        Objects.requireNonNull(array);
        for (int i = 1; i < array.length; ++i) {
            int value = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > value) {
                array[j + 1] = array[j];
                --j;
            }
            array[j + 1] = value;
        }
        return array;
    }

    /**
     * 3. 选择排序
     * */
    public static int[] selectSort(int[] array) {
        Objects.requireNonNull(array);
        for (int i = 0; i < array.length - 1; ++i) {
            int maxPos = -1;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < array.length - i; ++j) {
                if (array[j] > max) {
                    maxPos = j;
                    max = array[j];
                }
            }
            swap(array, maxPos, array.length - i - 1);
        }
        return array;
    }

    /**
     * 4. 堆排序
     * */
    public static int[] heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; --i) {
            heapify(array, i, array.length);
        }
        for (int i = array.length - 1; i >= 0; --i) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;

            heapify(array, 0, i);
        }
        return array;
    }

    /**
     * 5. 快速排序
     * */
    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) return;
        int pivotIndex = left;
        pivotIndex = partition(array, left, right, pivotIndex);
        quickSort(array, left, pivotIndex);
        quickSort(array, pivotIndex + 1, right);
    }

    /**
     * 6. 归并排序
     * */
    public static void mergeSort(int[] array, int left, int right, int[] tmpArray) {
        if (right - left >= 2) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid, tmpArray);
            mergeSort(array, mid, right, tmpArray);
            merge(array, left, mid, right, tmpArray);
        }
    }

    private static void merge(int[] array, int left, int mid, int right, int[] tmpArray) {
        int i = left;
        int j = mid;
        int t = 0;
        while (i < mid && j < right) {
            if (array[i] < array[j]) tmpArray[t++] = array[i++];
            else tmpArray[t++] = array[j++];
        }
        while (i < mid) tmpArray[t++] = array[i++];
        while (j < mid) tmpArray[t++] = array[j++];
        t = 0;
        while (left < right) array[left++] = tmpArray[t++];
    }

    private static int partition(int[] array, int left, int right, int pivotIndex) {
        int pivot = array[pivotIndex];
        // swap the value in pivotIndex to last
        swap(array, pivotIndex, right - 1);
        // partition
        int p = left;
        for (int i = left; i < right - 1; ++i) {
            if (array[i] <= pivot) {
                if (i != p) swap(array, i, p);
                ++p;
            }
        }
        swap(array, p, right - 1);
        return p;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void heapify(int[] array, int i, int n) {
        int max = array[i];
        int maxPos = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left >= n) return;
        if (array[left] > max) {
            maxPos = left;
            max = array[left];
        }
        if (right < n && array[right] > max) {
            maxPos = right;
            max = array[right];
        }
        if (maxPos != i) {
            int tmp = array[i];
            array[i] = array[maxPos];
            array[maxPos] = tmp;

            heapify(array, maxPos, n);
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[] {};
        int[] array2 = new int[] {9};
        int[] array3 = new int[] {1, 0};
        int[] array4 = new int[] {9, 8, 8, 7, 6, 5, 4};

        quickSort(array1, 0, array1.length);
        quickSort(array2, 0, array2.length);
        quickSort(array3, 0, array3.length);
        quickSort(array4, 0, array4.length);

//        mergeSort(array4, 0, array4.length, new int[array4.length]);

        System.out.print("array1: ");
        for (int i = 0; i < array1.length; ++i) {
            System.out.print(array1[i] + " ");
        }
        System.out.println();

        System.out.print("array2: ");
        for (int i = 0; i < array2.length; ++i) {
            System.out.print(array2[i] + " ");
        }
        System.out.println();

        System.out.print("array3: ");
        for (int i = 0; i < array3.length; ++i) {
            System.out.print(array3[i] + " ");
        }
        System.out.println();

        System.out.print("array4: ");
        for (int i = 0; i < array4.length; ++i) {
            System.out.print(array4[i] + " ");
        }

        List<Integer> arr = new ArrayList<>();
        int[] a = arr.stream().mapToInt(r -> r).toArray();
    }
}
