package DataStructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):往队列添加数据");
            System.out.println("g(get):从队列取数据");
            System.out.println("h(head):查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.ShowQueue();
                    break;
                case 'a':
                    System.out.println("请输入数据：");
                    int val = scanner.nextInt();
                    queue.AddQueue(val);
                    break;
                case 'g':
                    try {
                        int res = queue.GetQueue();
                        System.out.println("取的数据是" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.PeekQueue();
                        System.out.println("队列头的数据是" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayQueue {// 问题分析：数组不能重复使用，需要使用环形数组
    private int maxSize;
    private int front;//队列的头
    private int rear;//队列的尾
    private int[] arr;

    public ArrayQueue(int size) {
        maxSize = size;
        front = -1;//队列头部的前一个位置下标
        rear = -1;//队列尾部的下标
        arr = new int[maxSize];
    }

    // 判断队列是否为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 向队列中加数据
    public void AddQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能添加数据");
            return;
        }
        rear++;
        arr[rear] = n;// 这两行可以简写，arr[++rear]=n;
    }

    // 从队列中取数据
    public int GetQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    // 查看队列所有数据
    public void ShowQueue() {
        if (isEmpty()) {
            System.out.println("队列空，不能查看数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 查看队列头数据
    public int PeekQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front + 1];
    }
}