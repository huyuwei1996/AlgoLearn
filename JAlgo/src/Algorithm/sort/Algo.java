package Algorithm.sort;

// 菜鸟教程
public class Algo {

    // 冒泡排序
    // 平均时间复杂度：O(n2)
    public static void BubbleSort(int[] arr) {

        int temp;//临时变量
        for (int i = 0; i < arr.length - 1; i++) {   //表示趟数，一共arr.length-1次。
            for (int j = arr.length - 1; j > i; j--) {

                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    // 冒泡排序-优化
    // 平均时间复杂度：O(n2)
    public static void BubbleSort1(int[] arr) {

        int temp;//临时变量
        boolean flag;//是否交换的标志
        for (int i = 0; i < arr.length - 1; i++) {   //表示趟数，一共 arr.length-1 次

            // 每次遍历标志位都要先置为false，才能判断后面的元素是否发生了交换
            flag = false;

            for (int j = arr.length - 1; j > i; j--) { //选出该趟排序的最大值往后移动

                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = true;    //只要有发生了交换，flag就置为true
                }
            }
            // 判断标志位是否为false，如果为false，说明后面的元素已经有序，就直接return
            if (!flag) break;
        }
    }

    //选择排序
    //平均时间复杂度：O(n2)
    public static void select_sort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    //插入排序
    //平均时间复杂度：O(n2)
    public static void insert_sort(int[] arr) {

        int temp;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {         //不需要交换
                    break;
                }
            }
        }
    }

    //希尔排序
    public static void shell_sort(int[] arr) {

        int temp = 0;
        int incre = arr.length;

        while (true) {
            incre = incre / 2;

            for (int k = 0; k < incre; k++) {    //根据增量分为若干子序列

                for (int i = k + incre; i < arr.length; i += incre) {

                    for (int j = i; j > k; j -= incre) {
                        if (arr[j] < arr[j - incre]) {
                            temp = arr[j - incre];
                            arr[j - incre] = arr[j];
                            arr[j] = temp;
                        } else {
                            break;
                        }
                    }
                }
            }

            if (incre == 1) {
                break;
            }
        }
    }

    //快速排序
    //平均时间复杂度：O(N*logN)
    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r)
            return;

        int i = l;
        int j = r;
        int key = arr[l];//选择第一个数为key

        while (i < j) {

            while (i < j && arr[j] >= key)//从右向左找第一个小于key的值
                j--;
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            while (i < j && arr[ i] < key)//从左向右找第一个大于key的值
                i++;

            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        //i == j
        arr[i] = key;
        quickSort(arr, l, i - 1);//递归调用
        quickSort(arr, i + 1, r);//递归调用
    }

    //归并排序
    //平均时间复杂度：O(NlogN)
    public static void merge_sort(int a[],int first,int last,int temp[]){

        if(first < last){
            int middle = (first + last)/2;
            merge_sort(a,first,middle,temp);//左半部分排好序
            merge_sort(a,middle+1,last,temp);//右半部分排好序
            mergeArray(a,first,middle,last,temp); //合并左右部分
        }
    }
    //合并 ：将两个序列a[first-middle],a[middle+1-end]合并
    public static void mergeArray(int a[],int first,int middle,int end,int temp[]){
        int i = first;
        int m = middle;
        int j = middle+1;
        int n = end;
        int k = 0;
        while(i<=m && j<=n){
            if(a[i] <= a[j]){
                temp[k] = a[i];
                k++;
                i++;
            }else{
                temp[k] = a[j];
                k++;
                j++;
            }
        }
        while(i<=m){
            temp[k] = a[i];
            k++;
            i++;
        }
        while(j<=n){
            temp[k] = a[j];
            k++;
            j++;
        }

        for(int ii=0;ii<k;ii++){
            a[first + ii] = temp[ii];
        }
    }
}
