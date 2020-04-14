package DataStructure.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试中序线索二叉树
        ThreadedHeroNode root = new ThreadedHeroNode(1, "tom");
        ThreadedHeroNode node2 = new ThreadedHeroNode(3, "jack");
        ThreadedHeroNode node3 = new ThreadedHeroNode(6, "smith");
        ThreadedHeroNode node4 = new ThreadedHeroNode(8, "mary");
        ThreadedHeroNode node5 = new ThreadedHeroNode(10, "king");
        ThreadedHeroNode node6 = new ThreadedHeroNode(14, "kim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

//        ThreadedHeroNode leftNode = node5.getLeft();
//        ThreadedHeroNode rightNode = node5.getRight();
//        System.out.println(leftNode);
//        System.out.println(rightNode);

        System.out.println("中序线索化二叉树的中序遍历:");
        threadedBinaryTree.infixThreadedList();

    }
}

// 线索化二叉树 = 二叉树 + 线索化的方法
class ThreadedBinaryTree {
    private ThreadedHeroNode root;
    // 用于线索化时，保存前一个节点
    private ThreadedHeroNode pre = null;

    public void setRoot(ThreadedHeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        threadedNodes(this.root);
    }

    // 编写二叉树 中序线索化的方法
    public void threadedNodes(ThreadedHeroNode node) {
        // 如果节点为空，不能线索化
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft()); // 先线索化左子树

        // 线索化当前节点，先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre); // 为空就指向前驱节点
            node.setLeftType(1); // 修改左指针类型
        }
        // 再处理后继节点(其实是下一次处理)
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node); // 修改前驱节点的右指针，指向当前节点
            pre.setRightType(1);
        }
        pre = node; // 每处理一个节点后，让当前节点是下一个节点的前驱节点

        threadedNodes(node.getRight()); // 再线索化右子树
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public ThreadedHeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    // 中序线索化二叉树的中序遍历，需要用新的方法，无需使用递归
    public void infixThreadedList() {
        // 定义一个变量，存储当前遍历的节点，从root开始
        ThreadedHeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) { // 一直循环，直到找到leftType == 1的节点
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) { // 如果右指针指向后继节点，就一直输出
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight(); // 替换遍历的节点(如果右指针不是后继节点，就往右子树走)
        }

    }

    public ThreadedHeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public ThreadedHeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delNode(int no) {
        if (this.root != null) {
            // 需要立即判断，root是不是要删除的节点
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("二叉树为空，无法删除");
        }
    }
}

// 增加一点改造
class ThreadedHeroNode {
    private int no;
    private String name;
    private ThreadedHeroNode left;
    private ThreadedHeroNode right;

    private int leftType; // 约定：如果leftType=0为左子树，leftType=1为前驱节点
    private int rightType; // 约定：如果rightType=0为右子树，rightType=1为后继节点

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public ThreadedHeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadedHeroNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedHeroNode left) {
        this.left = left;
    }

    public ThreadedHeroNode getRight() {
        return right;
    }

    public void setRight(ThreadedHeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "ThreadedHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前中后序遍历，对应父节点的输出顺序
    // 前序遍历方法
    public void preOrder() {
        System.out.println(this); // 输出父节点
        if (this.left != null) { // 继续向左子树遍历
            this.left.preOrder();
        }
        if (this.right != null) { // 继续向右子树遍历
            this.right.preOrder();
        }
    }

    // 中序遍历方法
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历方法
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 递归删除节点，约定：如果是叶子节点就删除该节点，如果是非叶子节点，就删除该子树
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) { //判断左节点是不是要删除的节点
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) { // 判断右节点是不是要删除的节点
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no); // 尝试向左子树递归删除
        }
        if (this.right != null) {
            this.right.delNode(no); // 尝试向右子树递归删除
        }
    }

    // 前序遍历查找
    public ThreadedHeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");
        // 首先先比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        ThreadedHeroNode resNode = null; // 用一个变量来接收
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode; // 有可能没找到，没找到自动返回null
    }

    // 中序遍历查找
    public ThreadedHeroNode infixOrderSearch(int no) {
        ThreadedHeroNode resNode = null; // 用一个变量来接收
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历查找");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序遍历查找
    public ThreadedHeroNode postOrderSearch(int no) {
        ThreadedHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历查找");
        if (this.no == no) {
            return this;
        } else {
            return null;
        }
    }
}