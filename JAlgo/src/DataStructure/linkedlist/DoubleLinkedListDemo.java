package DataStructure.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(8, "林冲", "豹子头");

        DoubleListedList doubleListedList = new DoubleListedList();
        doubleListedList.add(hero1);
        doubleListedList.add(hero2);
        doubleListedList.add(hero3);
        doubleListedList.add(hero4);
        System.out.println("打印当前链表");
        doubleListedList.list();

        // 修改
        HeroNode2 newhero = new HeroNode2(8, "林冲～～", "豹子头～");
        doubleListedList.update(newhero);
        System.out.println("修改后的链表");
        doubleListedList.list();

        //删除
        doubleListedList.del(3);
        System.out.println("删除后的链表");
        doubleListedList.list();

        //按顺序添加
        HeroNode2 heroNode2 = new HeroNode2(9, "xx", "xx");
        doubleListedList.addByOrder(heroNode2);
        System.out.println("顺序添加后的链表");
        doubleListedList.list();

    }
}

class DoubleListedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    // 按编号添加 //按照单链表的顺序添加，稍微修改
    public void addByOrder(HeroNode2 heroNode) {
        // 添加一个辅助指针
        HeroNode2 temp = head;
        boolean flag = false;
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
            if (temp.next != null) {
                temp.next.pre = heroNode;
            }
            heroNode.pre = temp;
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 添加节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //添加一个辅助指针，指向头指针
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;   //只要temp.next不为空，就一直往后找
        }
        temp.next = heroNode;// 循环结束时就找到最后一个节点，最后一个节点的next，指向新添加的节点
        heroNode.pre = temp;// 形成双向
    }

    // 修改双向链表的节点// 和单向链表一样，只是节点的类型不一样
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        HeroNode2 temp = head;
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

    // 删除双向链表的节点
    public void del(int no) {
        // 添加辅助指针，直接找到要删除的这个节点，找到后，自我删除
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                temp.pre.next = temp.next;// 直接自我删除
                if (temp.next != null) { //所以我们加一下判断，否则会出现空指针异常
                    temp.next.pre = temp.pre;// 这句有风险，如果要删除的就是最后一个元素，这句不用执行
                }
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到要删除的节点");
        }
    }

    // 遍历双向链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 否则，添加一个辅助指针，用来遍历
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next; //一定要后移
        }
    }


}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//next域
    public HeroNode2 pre;//prev域

    public HeroNode2(int no, String name, String nickname) {
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