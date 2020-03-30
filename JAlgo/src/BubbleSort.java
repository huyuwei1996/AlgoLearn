import java.util.Arrays;

public class BubbleSort {
    public void BubbleSort(int arr[]) {
        int temp = 0;
        // if i:0～lenth-1，j:0～length-1-i
        // if i:1～lenth，  j:0～length-i
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
//                System.out.println(Arrays.toString(arr));
            }
            System.out.println(Arrays.toString(arr));
            System.out.println("第" + (i + 1) + "趟👆");
        }


        public static void main (String[]args){
//        int arr[] = {3, 9, -1, 10, -2};
            int arr[] = {1, 3, 5, 7, 7, 3, 9, 5, 3};

            BubbleSort(arr);
        }
    }
}
