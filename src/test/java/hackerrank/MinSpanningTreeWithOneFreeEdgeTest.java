package hackerrank;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MinSpanningTreeWithOneFreeEdgeTest {
    @Test
    void test1() {
        testUtil(List.of(
            List.of(1, 0, 1),
            List.of(2, 1, 7),
            List.of(2, 0, 5)
        ), 3, 1);
        testUtil(List.of(
            List.of(1, 0, 9),
            List.of(2, 1, 8),
            List.of(3, 2, 9)
        ), 4, 17);
        testUtil(List.of(
            List.of(1, 0, 4),
            List.of(2, 1, 6),
            List.of(3, 1, 7),
            List.of(4, 2, 9),
            List.of(1, 4, 2)
        ), 5, 12);
    }

    static void testUtil(List<List<Integer>> edges, int nNodes, int expect) {
        System.out.println();
        System.out.printf("nNodes   %s\n", nNodes);
        System.out.printf("edges %s\n", edges);
        System.out.printf("expect %d\n", expect);
        var actual = MinSpanningTreeWithOneFreeEdge.calculateMinimumSpanningTreeWeightWithFreeEdge(nNodes, edges.size(), edges);
        System.out.printf("actual %d\n", actual);
        assertEquals(expect, actual);
    }
    
}
