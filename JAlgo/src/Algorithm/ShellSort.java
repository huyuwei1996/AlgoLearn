package Algorithm;

import java.util.Arrays;

public class ShellSort {
    // 希尔排序：减小增量排序，对简单插入排序的优化
    // 时间复杂度为O(n^1.5)
    public static int[] shellSort(int[] arr) {
        // 逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组，进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];  //把下标为gap的值存一份
                while (i - gap >= 0 && temp < arr[i - gap]) {
                    arr[i] = arr[i - gap];  // 把i-gap位置的数，向后移动到i位置
                    i -= gap;   // 找所在组的前一个位置
                }
                arr[i] = temp;  // 退出while后，把temp插入对应位置
            }

            System.out.println("第" + "x" + "轮排序后：");
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 11, 7, 8, 1, 2, 5, 9};
        shellSort(arr);
    }
}
