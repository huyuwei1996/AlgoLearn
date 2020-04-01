package nowcoder;

public class FindMost {
    /*
    统计排序数组中出现次数最多的元素出现的次数
    本来就是有序的，只要和前一个值相等，就+1，然后更新下当前记录的最大次数
    */
    public static void findMost(int[] arr) {
        int lastEle = arr[0];
        int maxTime = 0;
        int presentTime = 1;//当前元素的次数
        int maxEle = arr[0];//假定是第一个元素，开始循环
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == lastEle) {
                presentTime++;
            } else {
                if (presentTime > maxTime) {
                    maxTime = presentTime;
                    maxEle = lastEle;
                }
                lastEle = arr[i]; //重置上次看过到元素位置
                presentTime = 1; //重置当前次数
            }
            if (i == arr.length - 1 && presentTime > maxTime) { //到结束了，如果元素从中间一直重复到结束，这里更新下最大次数
                maxTime = presentTime;
                maxEle = lastEle;
            }
        }

        System.out.println("最多出现的次数\n" + maxTime + "\n该元素是\n" + maxEle);
    }

    public static void main(String[] args) {
//        int[] array = {1, 1, 2, 2, 2, 3, 4, 5, 6, 6, 6, 6, 6, 7, 8, 8, 9};
        int[] array = {1, 1, 2, 2, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6};
        findMost(array);
    }
}
