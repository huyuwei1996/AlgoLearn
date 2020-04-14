package Algorithm.Dynamic;

// 这里写的0-1背包问题
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};// 对应物品的重量
        int[] val = {1500, 3000, 2000};// 对应物品的价值
        int m = 4;// 背包的容量
        int n = val.length;// 物品的个数

        int[][] v = new int[n + 1][m + 1];// 填表，用二维数组存放价值
        int[][] path = new int[n + 1][m + 1];// 填表，用二维数组存放商品情况

        // 初始化第一行和第一列，其实不处理也是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; // 第一列
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; // 第一行
        }
        // 动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) { // 物品重量，w[i-1]才是第一个物品
                    v[i][j] = v[i - 1][j];
                } else {
                    // 把表格上一行，和当前物品加上背包剩余容量的最大可放价值，比较哪个大
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < (val[i - 1] + v[i - 1][j - w[i - 1]])) { // 为了记录物品放入到背包的情况
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // 打印
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        // 输出放入的哪些商品 ///看不懂
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) { // 从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("将第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
