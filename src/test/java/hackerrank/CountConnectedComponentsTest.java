package hackerrank;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CountConnectedComponentsTest {
    @Test
    void test1() {
        testUtil(List.of(
            List.of(0, 1),
            List.of(1, 2),
            List.of(0, 2)
        ), 3, 1);
        testUtil(List.of(
            List.of(0, 2),
            List.of(0, 3),
            List.of(1, 3),
            List.of(1, 4),
            List.of(4, 2)
        ), 6, 2);
    }

    static void testUtil(List<List<Integer>> edges, int nNodes, int expect) {
        System.out.println();
        System.out.printf("nNodes   %s\n", nNodes);
        System.out.printf("edges %s\n", edges);
        System.out.printf("expect %d\n", expect);
        var actual = CountConnectedComponents.countIsolatedCommunicationGroups(edges, nNodes);
        System.out.printf("actual %d\n", actual);
        assertEquals(expect, actual);
    }
    
}
