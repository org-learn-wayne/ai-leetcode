package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0104MaximumDepthOfBinaryTreeTest {
    private final Leet0104MaximumDepthOfBinaryTree solution = new Leet0104MaximumDepthOfBinaryTree();

    @Test
    void returnsDepthForBalancedTree() {
        Leet0104MaximumDepthOfBinaryTree.TreeNode root = node(
                3,
                node(9),
                node(20, node(15), node(7)));

        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    void returnsZeroForEmptyTree() {
        assertEquals(0, solution.maxDepth(null));
    }

    @Test
    void handlesHighlySkewedTree() {
        Leet0104MaximumDepthOfBinaryTree.TreeNode root = node(
                1,
                node(2,
                        node(3,
                                node(4),
                                null),
                        null),
                null);

        assertEquals(4, solution.maxDepth(root));
    }

    private Leet0104MaximumDepthOfBinaryTree.TreeNode node(int val) {
        return new Leet0104MaximumDepthOfBinaryTree.TreeNode(val);
    }

    private Leet0104MaximumDepthOfBinaryTree.TreeNode node(
            int val,
            Leet0104MaximumDepthOfBinaryTree.TreeNode left,
            Leet0104MaximumDepthOfBinaryTree.TreeNode right) {
        return new Leet0104MaximumDepthOfBinaryTree.TreeNode(val, left, right);
    }
}
