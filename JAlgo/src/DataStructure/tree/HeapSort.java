package DataStructure.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 堆排序 - 核心：无序数组 => 大顶堆/小顶堆
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序:");

        // 从最后一个非叶子节点，往前开始循环，构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 把堆顶的元素，和最后交换，然后要调整的length-1
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }


    }

    // 将一个无序数组，调整成大顶堆

    /**
     * @param arr    需要调整的数组
     * @param i      最后一个非叶子节点的下标 arr.length / 2 - 1
     * @param length 需要调整的长度 => 是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // 先保存到临时变量
        // 从i的左子节点开始，下次继续向它的左子节点，循环
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { // 左子节点小于右子节点
                k++; // k指向右子节点
            }
            if (arr[k] > temp) {
                arr[i] = arr[k]; // 把大的值上移
                i = k; // 让i指向k，继续循环比较
            } else {
                break;
            }
        }
        // 当for循环结束后，已经将以i为父节点的最大值，放在了最顶上(局部的)
        arr[i] = temp; // 将temp值放到调整后的位置
    }
}
