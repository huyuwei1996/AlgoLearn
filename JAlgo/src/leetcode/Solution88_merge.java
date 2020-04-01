package leetcode;

import java.util.Arrays;

//88. 合并两个有序数组
public class Solution88_merge {
    //从头扫描
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        int p1 = 0;
        int p2 = 0;
        int p = 0;

        while ((p1 < m) && (p2 < n)) {
            if (nums1_copy[p1] < nums2[p2]) {
                nums1[p++] = nums1_copy[p1++];
            } else {
                nums1[p++] = nums2[p2++];
            }
        }
        while (p1 < m) {
            nums1[p++] = nums1_copy[p1++];
        }
        while (p2 < n) {
            nums1[p++] = nums2[p2++];
        }
    }

    //从后往前扫描
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;//nums1.length - 1
        while ((p1 >= 0) && (p2 >= 0)) {
            //下面if-else简写：
            //nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
            if (nums1[p1] < nums2[p2]) {
                nums1[p--] = nums2[p2--];
            } else {
                nums1[p--] = nums1[p1--];
            }
        }
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge1(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
