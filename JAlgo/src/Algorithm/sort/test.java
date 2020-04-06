package Algorithm.sort;

import java.util.Arrays;

public class test {
    //练习用

    public static void main(String[] args) {
        int[] arr = {3, 5, 11, 7, 8, 1, 2, 5, 9};//固定

        //希尔
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                while (i - gap >= 0 && temp < arr[i - gap]) {
                    arr[i] = arr[i - gap];
                    i -= gap;
                }
                arr[i] = temp;
            }
        }

        System.out.println(Arrays.toString(arr));//固定

    }
}
