package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0102BinaryTreeLevelOrderTraversalTest {
    private final Leet0102BinaryTreeLevelOrderTraversal solution = new Leet0102BinaryTreeLevelOrderTraversal();

    @Test
    void traversesLevelsFromTopToBottom() {
        Leet0102BinaryTreeLevelOrderTraversal.TreeNode root = node(
                3,
                node(9),
                node(20, node(15), node(7)));

        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), solution.levelOrder(root));
    }

    @Test
    void handlesSingleNodeTree() {
        assertEquals(List.of(List.of(1)), solution.levelOrder(node(1)));
    }

    @Test
    void handlesEmptyTree() {
        assertEquals(List.of(), solution.levelOrder(null));
    }

    @Test
    void handlesMissingChildrenWithinLevels() {
        Leet0102BinaryTreeLevelOrderTraversal.TreeNode root = node(
                1,
                node(2, null, node(4)),
                node(3));

        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4)), solution.levelOrder(root));
    }

    private Leet0102BinaryTreeLevelOrderTraversal.TreeNode node(int val) {
        return new Leet0102BinaryTreeLevelOrderTraversal.TreeNode(val);
    }

    private Leet0102BinaryTreeLevelOrderTraversal.TreeNode node(
            int val,
            Leet0102BinaryTreeLevelOrderTraversal.TreeNode left,
            Leet0102BinaryTreeLevelOrderTraversal.TreeNode right) {
        return new Leet0102BinaryTreeLevelOrderTraversal.TreeNode(val, left, right);
    }
}
