package Algorithm.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    // 二分查找，要求是有序数组（下面是升序） - 递归写法
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }

    // 二分查找，返回查到元素的所有下标
    public static ArrayList<Integer> binarySearchs(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal < midVal) {
            return binarySearchs(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {
            return binarySearchs(arr, mid + 1, right, findVal);
        } else {
            int temp = mid - 1;
            ArrayList<Integer> results = new ArrayList<Integer>();
            while (temp >= 0 && arr[temp] == midVal) {
                results.add(temp);
                temp--;
            }
            results.add(mid);
            temp = mid + 1;
            while (temp < arr.length && arr[temp] == midVal) {
                results.add(temp);
                temp++;
            }
            return results;
        }
    }

    // 插值查找
    public static int insertValueSearch(int[] arr, int low, int high, int findVal) {
        if (low > high || findVal < arr[low] || findVal > arr[high]) {  // 防止mid越界
            return -1;
        }
        int mid = low + (high - low) * (findVal - arr[low]) / (arr[high] - findVal); // 公式，自适应mid
        if (findVal < arr[mid]) {
            return insertValueSearch(arr, low, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            return insertValueSearch(arr, mid + 1, high, findVal);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 5, 7, 8, 9, 11};
//        int index = binarySearch(arr, 0, arr.length - 1, 5);
//        System.out.println(index);

//        List<Integer> indexs = binarySearchs(arr, 0, arr.length - 1, 5);
//        System.out.println(indexs);


//        int[] array = new int[100];
//        for (int i = 0; i < 100; i++) {
//            array[i] = i + 1;
//        }
//        int index = insertValueSearch(array, 0, array.length - 1, 5);
//        System.out.println(index);
    }

}
