package DataStructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(8, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        System.out.println("打印当前链表");
        singleLinkedList.list();

//        singleLinkedList.update(new HeroNode(2, "卢俊义~~", "玉麒麟~~"));
//        System.out.println("更新后的链表：");
//        singleLinkedList.list();

//        singleLinkedList.del(1);
//        singleLinkedList.del(2);
//        singleLinkedList.del(3);
//        singleLinkedList.del(4);
//        System.out.println("删除后的链表：");
//        singleLinkedList.list();

//        System.out.println(getLength(singleLinkedList.getHead()));

//        System.out.println(findLastIndexNode(singleLinkedList.getHead(), 1));

//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

//        reversePrint(singleLinkedList.getHead());

//        HeroNode h1 = new HeroNode(2, "2", "2");
        HeroNode h2 = new HeroNode(5, "5", "5");
        HeroNode h3 = new HeroNode(6, "6", "6");
        HeroNode h4 = new HeroNode(7, "7", "7");

        SingleLinkedList sL2 = new SingleLinkedList();
//        sL2.addByOrder(h1);
        sL2.addByOrder(h2);
        sL2.addByOrder(h3);
        sL2.addByOrder(h4);
        System.out.println("打印当前链表");
        sL2.list();

        System.out.println("合并两个有序链表");
        HeroNode h = mergeList(singleLinkedList.getHead(), sL2.getHead());
        while (h.next != null) {
            System.out.println(h.next);
            h = h.next;
        }
    }

    // question5：合并两个有序的单链表
    public static HeroNode mergeList(HeroNode h1, HeroNode h2) {
        HeroNode head = new HeroNode(0, "", "");
        HeroNode temp = head;
        h1 = h1.next;
        h2 = h2.next;
        while (h1 != null && h2 != null) {
            if (h1.no <= h2.no) {
                temp.next = h1;
                h1 = h1.next;
            } else {
                temp.next = h2;
                h2 = h2.next;
            }
            temp = temp.next;
        }
        if (h1 == null) { //  这段可以简写这样temp.next = h1 == null ? h2 : h1;
            temp.next = h2;
        } else {
            temp.next = h1;
        }
        return head;
    }


    // question4：从尾到头打印单链表
    // 利用栈，将各个节点压入到栈中，利用栈的先进后出的特点
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }


    // question3：单链表的反转
    public static void reverseList(HeroNode head) {
        //从头一边遍历，一遍摘下节点，插入到一个reverseHead的后一个位置。最后把head.next指向新的head.next的位置
        if (head.next == null || head.next.next == null) {
            return;
        }
        //使用一个辅助指针，用于遍历
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //开始遍历
        while (cur != null) {
            next = cur.next;   //暂时保存当前节点的下一个节点
            cur.next = reverseHead.next; //当前位置的next，指向reverseHead后面的next
            reverseHead.next = cur; //然后把reverseHead的next。指向cur
            cur = next; //让cur继续往后走
        }
        // 将head.next指向reverseHead.next
        head.next = reverseHead.next;
    }

    // question2：查找单链表中的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义一个辅助指针
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {//往后移动size-index步
            cur = cur.next;
        }
        return cur;
    }


    // question1：获取单链表的节点的个数。如果是带头节点的，不统计头节点
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int lengh = 0;
        // 用一个辅助指针
        HeroNode cur = head;
        while (cur.next != null) {
            lengh++;
            cur = cur.next;
        }
        return lengh;

    }
}

// 写单链表类，来管理节点
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public void setHead(HeroNode head) {
        this.head = head;
    }

    public HeroNode getHead() {
        return head;
    }

    //不考虑编号顺序时，直接往最后位置加
    public void add(HeroNode heroNode) {
        //添加一个辅助指针，指向头指针
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;   //只要temp.next不为空，就一直往后找
        }
        temp.next = heroNode;// 循环结束时就找到最后一个节点，最后一个节点的next，指向新添加的节点
    }

    // 按编号添加
    public void addByOrder(HeroNode heroNode) {
        // 添加一个辅助指针，找到添加的位置，添加位置的前一个位置
        HeroNode temp = head;
        boolean flag = false;//标示是否存在
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到了，插入到temp和temp.next之间
                break;
            } else if (temp.next.no == heroNode.no) { //说明已经存在了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("准备插入的节点，编号已存在");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据编号来修改节点信息
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        HeroNode temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no == newHeroNode.no) {
                flag = true;
                temp.next.name = newHeroNode.name;
                temp.next.nickname = newHeroNode.nickname;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到要修改的节点");
        }
    }

    //删除节点
    public void del(int no) {
        //添加辅助指针，找到待删除节点的前一个位置
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;//标识是否找到
        while (temp.next != null) {
            if (temp.next.no == no) {
                flag = true;
                temp.next = temp.next.next;//删除节点
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到要删除的节点");
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 否则，添加一个辅助指针，用来遍历
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next; //一定要后移
        }
    }
}

// node节点，写水浒传英雄类
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//next域

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name=" + name +
                ", nickname=" + nickname +
                '}';
    }
}