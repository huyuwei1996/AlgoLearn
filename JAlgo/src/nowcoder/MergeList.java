package nowcoder;

import java.util.Arrays;

public class MergeList {
    /*
    合并两个排序数组
    使用一个新数组
     */
    public static int[] mergeList(int[] a, int[] b) {
        int result[] = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;    //a数组下标，b数组下标，新数组下标
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }
        return result;
    }


    public static void main(String[] args) {
        int[] a={1,4,5,6,7,8};
        int[] b={2,3,7,9};

        int[] r=mergeList(a,b);
        System.out.println(Arrays.toString(r));
    }
}
