package DataStructure.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        // 构建环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(10,20,125);
    }
}

// 创建一个环形的单项链表
class CircleSingleLinkedList {
    private Boy first = null;// 创建第一个节点

    // 添加boy节点，构成一个环形的链表
    public void addBoy(int nums) {
        // 对nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        // 创建一个辅助指针
        Boy curBoy = null;
        // 用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建boy节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy; // 把第一个直接赋给first
                first.setNext(first); // 组成一个环
                curBoy = first; //指向第一个位置（其实就是最后一个节点，用于后面遍历）
            } else {
                curBoy.setNext(boy); // 往最后一个位置的后面加节点
                boy.setNext(first); // 指向first组成环
                curBoy = boy; // (指向最后一个位置)往后移一个位置
            }
        }
    }

    // 遍历环形链表
    public void showBoy() {
        // 如果为空，直接返回
        if (first == null) {
            System.out.println("没有孩子节点");
            return;
        }
        // 创建一个辅助指针，用于遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("孩子的编号为 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) { // 如果next指向first，就已经遍历完了
                break;
            }
            curBoy = curBoy.getNext(); // 否则的话，辅助指针往后移
        }
    }

    /**
     * 小孩出圈 - 约瑟夫问题
     *
     * @param startNo  从第几个小孩开始数
     * @param countNum 数几次然后出圈
     * @param nums     共有几个个小孩
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据做校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误");
            return;
        }
        // 先用一个辅助指针，应该指向first节点的前一个
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext(); //指向最后一个位置(first节点的前一个)
        }
        // 然后helper和first移动startNo - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }
        // 每数countNum - 1 次，就让first出圈
        while (helper != first) { // 退出循环条件，只有最后一个元素
            for (int i = 0; i < countNum - 1; i++) {
                helper=helper.getNext();
                first=first.getNext();
            }
            System.out.printf("孩子 %d 出圈\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后还有孩子 %d 在圈中\n",first.getNo());
    }
}

// 表示节点
class Boy {
    private int no;
    private Boy next;//指向下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}