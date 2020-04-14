package Algorithm.search;

public class BinarySearchNoRecur {
    public static int BinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 5, 7, 8, 9, 11};
        int index = BinarySearch(arr, 5);
        System.out.println(index);
    }
}
