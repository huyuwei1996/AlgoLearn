import java.util.Arrays;

public class FibonacciSearch {
    public static int MAX = 20;

    //构建一个斐波那契数列，非递归写法
    public static int[] fibonacci() {
        int[] fib = new int[MAX];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < MAX; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    // 斐波那契数查找，非递归写法
    public static int fibonacciSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int[] f = fibonacci();
        int k = 0;

        while (high > f[k] - 1) {   //不足f[k]-1的，补到f[k]-1的长度。固定公式
            k++;
        }
        int[] temp = Arrays.copyOf(a, f[k] - 1); //copyOf，high+1开始默认补0
        for (int i = high + 1; i < temp.length; i++) {    //用a[high]，从high+1开始补到f[k]-1的长度
            temp[i] = a[high];
        }

        while (low <= high) {
            int mid = low + f[k - 1] - 1; //公式
            if (key < temp[mid]) { // 在左边继续找，k-1是左边那部分
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) { // 在右边继续找，k-2是右边那部分
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] fib=fibonacci();
//        System.out.println(Arrays.toString(fib));

        int[] arr = {1, 2, 3, 5, 5, 7, 8, 9, 11};
        int index = fibonacciSearch(arr, 15);
        System.out.println(index);
    }
}
