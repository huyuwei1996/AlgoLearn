package DataStructure.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

//        System.out.println("前序遍历:");
//        binaryTree.preOrder();
//        System.out.println("中序遍历:");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历:");
//        binaryTree.postOrder();

//        System.out.println("遍历查找:");
//        System.out.println(binaryTree.postOrderSearch(5));

        System.out.println("删除前-前序遍历:");
        binaryTree.preOrder();
        binaryTree.delNode(5);
//        binaryTree.delNode(3);
        System.out.println("删除后-前序遍历");
        binaryTree.preOrder();
    }
}

// 定义二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
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

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");
        // 首先先比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null; // 用一个变量来接收
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
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null; // 用一个变量来接收
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
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
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
