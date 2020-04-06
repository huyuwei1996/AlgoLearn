package Algorithm.recursion;

public class MiGong {
    public static void main(String[] args) {
        // 定义一个二维数据来表示迷宫
        int[][] map = new int[8][7];
        // 用1填充墙的部分
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 设置障碍物
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("打印地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map,1,1);
        System.out.println("打印走过后的地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * [1]从1,1做起点，6,5为终点
     * [2]当map[i][j]为0表示没走过，2表示可以走，3表示已走过，但是走不通
     *
     * @param map 传入一个地图
     * @param i,j 从什么位置开始走
     * @return 可以走的通返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2; // 先假定这个点是通的，然后递归尝试四个方向，下 -> 右 -> 上 -> 左
                if (setWay(map, i + 1, j)) {
                    return true; // 如果向下能走通，就返回true
                }else if (setWay(map, i,j+1)){
                    return true; // 如果向右能走通，就返回true
                }else if (setWay(map,i-1,j)){
                    return true; // 如果向上能走通，就返回true
                }else if (setWay(map, i,j-1)){
                    return true; // 如果向左能走通，就返回true
                }else{
                    map[i][j] = 3; // 都走不通，标记为3，返回false
                    return false;
                }
            }else { // 如果map[i][j]不等于0，可能就是1，2，3
                return false; // 墙不能走，走过了不能走，走不通的也不要走
            }


        }
    }
}
