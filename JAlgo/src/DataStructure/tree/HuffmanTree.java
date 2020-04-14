package DataStructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node root = createHuffmanTree(arr);

        preOrder(root);
    }

    // 创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        // 遍历arr数组，将每个元素构建成Node，然后放入到ArrayList里调用sort方法
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            // 排序，从小到大
            Collections.sort(nodes);
            // 取最小的两个值，构建一个新的二叉树。父节点值为两者之和
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            // 把处理过的值从ArrayList中删除
            nodes.remove(left);
            nodes.remove(right);
            // 把parent加入进去，继续排序
            nodes.add(parent);
        }
        // 最终返回赫夫树的root节点
        return nodes.get(0);
    }

    // 写一个前序遍历，遍历赫夫曼树
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树，不能遍历");
        }
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    // 写一个前序遍历，遍历赫夫曼树
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大。   如果要从大到小，取负
        return this.value - o.value;
    }
}