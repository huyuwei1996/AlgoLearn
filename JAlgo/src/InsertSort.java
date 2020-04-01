import java.util.Arrays;

public class InsertSort {
    // 时间复杂度O(n^2)
    public static int[] insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;

            System.out.println("第" + i + "轮排序后：");
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
        int[] arr = {1, 3, 5, 7, 7, 3, 9, 5, 3};

//        insertSort(arr);
        insertSortUp(arr);
    }

    public static int[] insertSortUp(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {  //如果倒序排列，insertVal > arr[insertIndex]
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) { //优化：
                arr[insertIndex + 1] = insertVal;
            }
            System.out.println("第" + i + "轮排序后：");
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }
}
