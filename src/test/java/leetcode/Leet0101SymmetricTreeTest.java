package leetcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Leet0101SymmetricTreeTest {
    private final Leet0101SymmetricTree solution = new Leet0101SymmetricTree();

    @Test
    void returnsTrueForSymmetricTree() {
        Leet0101SymmetricTree.TreeNode root = node(
                1,
                node(2, node(3), node(4)),
                node(2, node(4), node(3)));

        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void returnsFalseWhenShapeIsNotMirrored() {
        Leet0101SymmetricTree.TreeNode root = node(
                1,
                node(2, null, node(3)),
                node(2, null, node(3)));

        assertFalse(solution.isSymmetric(root));
    }

    @Test
    void treatsEmptyTreeAsSymmetric() {
        assertTrue(solution.isSymmetric(null));
    }

    private Leet0101SymmetricTree.TreeNode node(int val) {
        return new Leet0101SymmetricTree.TreeNode(val);
    }

    private Leet0101SymmetricTree.TreeNode node(
            int val,
            Leet0101SymmetricTree.TreeNode left,
            Leet0101SymmetricTree.TreeNode right) {
        return new Leet0101SymmetricTree.TreeNode(val, left, right);
    }
}
