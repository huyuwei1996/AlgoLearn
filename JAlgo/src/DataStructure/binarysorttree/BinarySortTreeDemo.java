package DataStructure.binarysorttree;


public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        System.out.println("删除节点");
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(12);
//        binarySortTree.delNode(7);
//        binarySortTree.delNode(3);
//        binarySortTree.delNode(10);
//        binarySortTree.delNode(1);

//        System.out.println("root" + binarySortTree.getRoot());
        binarySortTree.infixOrder();

    }
}

// 二叉排序树
class BinarySortTree {
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
