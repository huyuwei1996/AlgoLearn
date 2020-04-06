package DataStructure.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("a(add):  添加雇员");
            System.out.println("l(list): 显示雇员");
            System.out.println("f(ById): 查找雇员");
            System.out.println("e(exit): 退出系统");

            key = scanner.next();
            switch (key) {
                case "a":
                    System.out.println("请输入id:");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字：");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "l":
                    hashTab.list();
                    break;
                case "f":
                    System.out.println("请输入要查找的id:");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "e":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

// 编写哈希表，管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedLisArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLisArray = new EmpLinkedList[size]; // 记得初始化每条链表，不然会空指针异常
        for (int i = 0; i < size; i++) {
            empLinkedLisArray[i] = new EmpLinkedList();
        }
    }

    // 根据id，添加到对应的链表中
    public void add(Emp emp) {
        int empLinkedListNO = hashFunc(emp.id);
        empLinkedLisArray[empLinkedListNO].add(emp);
    }

    // 遍历hash表，遍历打印多条链表即可
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLisArray[i].list(i + 1);
        }
    }

    // 根据输入id查找雇员
    public void findEmpById(int id) {
        // 首先使用散列函数，确认在哪条链表
        int empLinkedListNO = hashFunc(id);
        Emp emp = empLinkedLisArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", empLinkedListNO + 1, id);
        } else {
            System.out.println("在哈希表中没有找到该雇员!");
        }
    }

    // 散列函数，这里用取模法
    public int hashFunc(int id) {
        return id % size;
    }

}

// TODO 链表&哈希表中增加删除雇员功能
class EmpLinkedList {
    private Emp head;

    // 链表尾部添加雇员
    public void add(Emp emp) {
        if (head == null) {
            head = emp; //添加第一个，直接把head指向新的雇员就行，这个是不带头节点的链表，记住
            return;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    // 遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "链表为空");
            return;
        }
        Emp curEmp = head;
        System.out.print("第" + no + "链表的信息为：");
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    // 根据id查找雇员
    public Emp findEmpById(int id) {
        // 判断当前链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null; // 置空，如果找不到
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}