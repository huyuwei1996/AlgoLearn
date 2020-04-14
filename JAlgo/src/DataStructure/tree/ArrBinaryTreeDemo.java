package DataStructure.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        arrBinaryTree.infixOrder();
        arrBinaryTree.postOrder();
    }
}

// 顺序存储二叉树，下标0开始的完全二叉树
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() { // 重载方法
        this.preOrder(0);
        System.out.println();
    }

    // 顺序存储二叉树的前序遍历
    public void preOrder(int index) { // 从根节点开始，下标为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法前序遍历");
        }
        System.out.print(arr[index] + " "); // 打印当前节点
        if ((index * 2 + 1) < arr.length) { // 向左递归，左节点下标，防止越界
            preOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) { // 向右递归，右节点下标，防止越界
            preOrder(index * 2 + 2);
        }
    }

    public void infixOrder() { // 重载方法
        this.infixOrder(0);
        System.out.println();
    }

    // 顺序存储二叉树的中序遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法中序遍历");
        }
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        System.out.print(arr[index] + " ");
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    public void postOrder() { // 重载方法
        this.postOrder(0);
        System.out.println();
    }

    // 顺序存储二叉树的后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法后序遍历");
        }
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.print(arr[index] + " ");
    }
}