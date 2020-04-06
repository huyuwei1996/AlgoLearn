package Algorithm.recursion;

public class EightQueen {
    private int max = 8;
    //这里只用一维数组表示皇后的位置，下标index代表第index+1行，array[index]代表放在第array[index]+1列
    private int[] array = new int[max];
    private static int count;

    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        queen.chess(0);
        System.out.println(count);
    }

    // 编写摆放第n个皇后的方法
    private void chess(int n) {
        if (n == max) {
            print();
            return;
        }
        // 依次递增列，尝试摆放
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) { // 如果不冲突，就可以继续摆放下一个
                chess(n + 1);
            }
            // 否则，i会递增，摆放到下一列去尝试
        }
    }

    // 判断第n个皇后，是否和之前的冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 如果在同一列，或者，行间距等于列间距（在同一条斜线上）
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 打印这个一维数组
    private void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
