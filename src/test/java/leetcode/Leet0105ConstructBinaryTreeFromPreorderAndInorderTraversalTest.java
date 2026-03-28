package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversalTest {
    private final Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal solution =
            new Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal();

    @Test
    void buildsTreeFromTypicalTraversalPair() {
        Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.TreeNode root =
                solution.buildTree(
                        new int[] {3, 9, 20, 15, 7},
                        new int[] {9, 3, 15, 20, 7});

        assertArrayEquals(new Integer[] {3, 9, 20, null, null, 15, 7}, toLevelOrder(root));
    }

    @Test
    void handlesSingleNodeTree() {
        Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.TreeNode root =
                solution.buildTree(new int[] {-1}, new int[] {-1});

        assertArrayEquals(new Integer[] {-1}, toLevelOrder(root));
    }

    @Test
    void handlesEmptyInput() {
        Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.TreeNode root =
                solution.buildTree(new int[] {}, new int[] {});

        assertArrayEquals(new Integer[] {}, toLevelOrder(root));
    }

    @Test
    void buildsLeftHeavyTree() {
        Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.TreeNode root =
                solution.buildTree(
                        new int[] {4, 3, 2, 1},
                        new int[] {1, 2, 3, 4});

        assertArrayEquals(new Integer[] {4, 3, null, 2, null, 1}, toLevelOrder(root));
    }

    private Integer[] toLevelOrder(Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.TreeNode root) {
        if (root == null) {
            return new Integer[] {};
        }

        List<Integer> values = new ArrayList<>();
        List<Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.TreeNode> queue = new ArrayList<>();
        queue.add(root);

        for (int index = 0; index < queue.size(); index++) {
            Leet0105ConstructBinaryTreeFromPreorderAndInorderTraversal.TreeNode node = queue.get(index);
            if (node == null) {
                values.add(null);
                continue;
            }

            values.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
        }

        int lastNonNull = values.size() - 1;
        while (lastNonNull >= 0 && values.get(lastNonNull) == null) {
            lastNonNull--;
        }

        return values.subList(0, lastNonNull + 1).toArray(Integer[]::new);
    }
}
