package DataStructure.avltree;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};// 测试左旋转
//        int[] arr = {10, 12, 8, 9, 7, 6};// 测试右旋转
        int[] arr = {10, 11, 7, 6, 8, 9};// 测试双旋转

        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();

        System.out.println("树的高度 = " + avlTree.getRoot().getHeight());
        System.out.println("左子树的高度 = " + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度 = " + avlTree.getRoot().rightHeight());
        System.out.println("根节点 = " + avlTree.getRoot());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * @param node 以传入的节点为根节点，当做一个二叉排序树
     * @return 返回最小值，并删除该节点
     */
    public int delRightTreeMin(Node node) {
        // 用了一个辅助节点
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    // 删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 先找到要删除的节点
            Node target = search(value);
            if (target == null) { // 如果找不到，就直接返回
                return;
            }
            // 如果没有直接返回，但是二叉树只有一个根节点，说明就是要找的值，把根节点置空
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);
            // 如果要删的是叶子节点
            if (target.left == null && target.right == null) {
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (target.left != null && target.right != null) { // 如果要删除的节点有两颗子树
                int minVal = delRightTreeMin(target.right);
                target.value = minVal;
            } else { // 如果要删除的节点只有一颗子树
                if (target.left != null) {
                    if (parent != null) { // 如果不判断，会有空指针异常
                        if (parent.left == target) {
                            parent.left = target.left;
                        } else {
                            parent.right = target.left;
                        }
                    } else {
                        root = target.left;
                    }
                } else {
                    if (parent != null) { // 如果不判断，会有空指针异常
                        if (parent.left == target) {
                            parent.left = target.right;
                        } else {
                            parent.right = target.right;
                        }
                    } else {
                        root = target.right;
                    }
                }
            }
        }
    }

    // 添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("当前二叉排序树为空，不能遍历");
        }
    }

}

// 节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 获取左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.getHeight();
        }
    }

    // 获取右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.getHeight();
        }
    }

    // 获取以当前节点为根节点的树的高度
    public int getHeight() {
        return Math.max((left == null ? 0 : left.getHeight()), (right == null ? 0 : right.getHeight())) + 1;
    }

    // 左旋转
    public void leftRotate() {
        // 以当前的值复制一个新的节点
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        right = right.right;
        left = newNode;
    }

    // 右旋转
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    // 添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                // 如果左子节点不为空，那就递归的添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归添加
                this.right.add(node);
            }
        }

        // 当添加节点完后，判断是否需要旋转
        if ((rightHeight() - leftHeight()) > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
        } else if ((leftHeight() - rightHeight()) > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
        }
    }

    // 节点的中序遍历方法
    public void infixOrder() {
        if (this == null) {
            return;
        }
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value 要查找的值
     * @return 返回该节点
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.search(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 查找要删除的节点的父节点
     *
     * @param value 要查找的值
     * @return 返回该值的父节点
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }
}
