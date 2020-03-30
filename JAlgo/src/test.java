import java.util.Arrays;

public class test {
    //练习用

    public static void main(String[] args) {
        int[] arr = {3, 5, 11, 7, 8, 1, 2, 5, 9};//固定

        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }


        System.out.println(Arrays.toString(arr));//固定
    }
}
