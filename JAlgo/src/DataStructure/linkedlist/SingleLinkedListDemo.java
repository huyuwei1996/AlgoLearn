package DataStructure.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.list();
    }
}

// 写单链表类，来管理节点
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    //不考虑编号顺序时，直接往最后位置加
    public void add(HeroNode heroNode) {
        //添加一个辅助指针，指向头指针
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;   //只要temp.next不为空，就一直往后找
        }
        temp.next = heroNode;// 循环结束时就找到最后一个节点，最后一个节点的next，指向新添加的节点
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