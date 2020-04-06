package Algorithm.sort;

import java.util.Arrays;

public class SelectSort {
    // 时间复杂度O(n^2)
    public static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { //如果倒序排列，min < arr[j]
                    minIndex = j;
                    min = arr[j];
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;

            System.out.println("第" + (i + 1) + "轮排序后：");
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 11, 7, 8, 1, 2, 5, 9};
        selectSort(arr);
    }

    public static int[] selectSortUp(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { //如果倒序排列，这里修改判断符号即可
                    minIndex = j;
                    min = arr[j];
                }
            }
            if (minIndex != i) {    //优化：
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第" + (i + 1) + "轮排序后：");
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }
}
