package Algorithm.sort;

import java.util.Arrays;

public class BubbleSort {
    // 时间复杂度O(n^2)
    public static int[] bubbleSort(int[] arr) {
        int temp = 0;
//        i =[0, len - 1)
//        j =[0, len - 1 - i)

//        i =[1, len)
//        j =[0, len - i)
        // 外层循环控制比较轮数
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层循环控制每轮比较次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面数比后面数大，则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "轮排序后：");
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
        int[] arr = {1, 3, 5, 7, 7, 3, 9, 5, 3};

//        bubbleSort(arr);
        bubbleSortUp(arr);
    }

    public static int[] bubbleSortUp(int[] arr) {   //优化后的冒泡排序
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {    //优化：如果没有发生过交换，就退出内循环
                break;
            }
            System.out.println("第" + i + "轮排序后：");
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

}

