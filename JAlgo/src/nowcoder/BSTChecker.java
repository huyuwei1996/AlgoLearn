package nowcoder;


public class BSTChecker {
    private static int lastVisit = Integer.MIN_VALUE;

    public static boolean isBST(Node root) {
        if (root == null) {
            return true;
        }

        boolean judgeLeft = isBST(root.left);

        if (lastVisit < root.data && judgeLeft) {
            lastVisit = root.data;
        } else {
            return false;
        }

        boolean judgeRight = isBST(root.right);

        return judgeRight;
    }

    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.add(new Node(0));
        System.out.println(isBST(binarySortTree.root));
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.data < this.data) {
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
}

class BinarySortTree {
    public Node root;

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
}