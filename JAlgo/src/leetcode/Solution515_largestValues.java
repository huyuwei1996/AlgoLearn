package leetcode;


import java.util.ArrayList;
import java.util.List;

public class Solution515_largestValues {
    public static void main(String[] args) {

    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        findLevelLargest(root, res, 0);
        return res;
    }

    // 递归写法
    public void findLevelLargest(TreeNode node, List<Integer> res, int level) {
        if (node == null) return;
        if (res.size() < (level + 1)) {
            res.add(node.val);
        } else {
            if (node.val > res.get(level)) {
                res.remove(level);
                res.add(node.val);
            }
        }
        findLevelLargest(node.left, res, level + 1);
        findLevelLargest(node.right, res, level + 1);
    }
}
