package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0103BinaryTreeZigzagLevelOrderTraversalTest {
    private final Leet0103BinaryTreeZigzagLevelOrderTraversal solution =
            new Leet0103BinaryTreeZigzagLevelOrderTraversal();

    @Test
    void alternatesTraversalDirectionByLevel() {
        Leet0103BinaryTreeZigzagLevelOrderTraversal.TreeNode root = node(
                3,
                node(9),
                node(20, node(15), node(7)));

        assertEquals(List.of(List.of(3), List.of(20, 9), List.of(15, 7)), solution.zigzagLevelOrder(root));
    }

    @Test
    void handlesSingleNodeTree() {
        assertEquals(List.of(List.of(1)), solution.zigzagLevelOrder(node(1)));
    }

    @Test
    void handlesEmptyTree() {
        assertEquals(List.of(), solution.zigzagLevelOrder(null));
    }

    @Test
    void keepsAlternatingThroughDeeperLevels() {
        Leet0103BinaryTreeZigzagLevelOrderTraversal.TreeNode root = node(
                1,
                node(2, node(4), node(5)),
                node(3, null, node(6)));

        assertEquals(
                List.of(List.of(1), List.of(3, 2), List.of(4, 5, 6)),
                solution.zigzagLevelOrder(root));
    }

    private Leet0103BinaryTreeZigzagLevelOrderTraversal.TreeNode node(int val) {
        return new Leet0103BinaryTreeZigzagLevelOrderTraversal.TreeNode(val);
    }

    private Leet0103BinaryTreeZigzagLevelOrderTraversal.TreeNode node(
            int val,
            Leet0103BinaryTreeZigzagLevelOrderTraversal.TreeNode left,
            Leet0103BinaryTreeZigzagLevelOrderTraversal.TreeNode right) {
        return new Leet0103BinaryTreeZigzagLevelOrderTraversal.TreeNode(val, left, right);
    }
}
