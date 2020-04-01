import java.util.Arrays;

public class QuickSort {
    // 快速排序，对冒泡排序的一种改进

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];//中位数
        int temp = 0;
        //目的让比pivot值小的放左边，比pivot值大的放右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot的值，才退出
            while ((arr[l]) < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot的值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l >= r) {//说明pivot左右两边的值，已经按照左边小于等于pivot值，右边大于pivot值
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值，r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值，l++，右移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果l == r，必须l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        System.out.println("第" + "x" + "轮排序后：");
        System.out.println("Pivot=" + pivot);
        System.out.println(Arrays.toString(arr));

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 11, 7, 8, 1, 2, 5, 9};
        quickSort(arr, 0, arr.length - 1);
    }
}
