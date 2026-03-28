package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndexes = new HashMap<>();
        for (int index = 0; index < inorder.length; index++) {
            inorderIndexes.put(inorder[index], index);
        }

        return build(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inorderIndexes);
    }

    private TreeNode build(int[] preorder, int preorderStart, int preorderEnd,
                           int inorderStart, int inorderEnd,
                           Map<Integer, Integer> inorderIndexes) {
        if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }

        int rootValue = preorder[preorderStart];
        int inorderRootIndex = inorderIndexes.get(rootValue);
        int leftTreeSize = inorderRootIndex - inorderStart;

        TreeNode root = new TreeNode(rootValue);
        root.left = build(
                preorder,
                preorderStart + 1,
                preorderStart + leftTreeSize,
                inorderStart,
                inorderRootIndex - 1,
                inorderIndexes);
        root.right = build(
                preorder,
                preorderStart + leftTreeSize + 1,
                preorderEnd,
                inorderRootIndex + 1,
                inorderEnd,
                inorderIndexes);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
