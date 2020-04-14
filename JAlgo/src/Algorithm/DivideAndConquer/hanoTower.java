package Algorithm.DivideAndConquer;

public class hanoTower {

    // 分治算法
    public static void hano(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘子：" + a + " -> " + c);
        } else {
            hano(num - 1, a, c, b); // 把n-1个盘子从a借助c移动到b
            System.out.println("第" + num + "个盘子：" + a + " -> " + c); // 把最下面的盘子从a移动到c
            hano(num - 1, b, a, c); // 把n-1个盘子从b借助a移动到c，放到那一个盘子上面
        }
    }


    public static void main(String[] args) {
        hano(5, 'A', 'B', 'C');

    }
}
