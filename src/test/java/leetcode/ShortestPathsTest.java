package leetcode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import org.junit.jupiter.api.Test;

// import leetcode.ShortestPaths.Graph;

public class ShortestPathsTest {
    
    @Test
    void testCircularGraph() {
        System.out.printf("*****Circular Graph\n");
        // Circular-graph with A, B, C.
        ShortestPaths.Graph graph = new ShortestPaths.Graph(
            new int[][] {
                {1, 2, 10},
                {2, 3, 15},
                {3, 4, 30},
                {4, 5, 60},
                {5, 1, 150}
            }
        );
        // testAllPairs(graph);
        test(graph, 1, 2, List.of(
            new ShortestPaths.Ngbr(1, 10)
        ));
        test(graph, 1, 3, List.of(
            new ShortestPaths.Ngbr(1, 10),
            new ShortestPaths.Ngbr(2, 25)
        ));
        test(graph, 1, 4, List.of(
            new ShortestPaths.Ngbr(1, 10),
            new ShortestPaths.Ngbr(2, 25),
            new ShortestPaths.Ngbr(3, 55)
        ));
        test(graph, 1, 5, List.of(
            new ShortestPaths.Ngbr(1, 10),
            new ShortestPaths.Ngbr(2, 25),
            new ShortestPaths.Ngbr(3, 55),
            new ShortestPaths.Ngbr(4, 115)
        ));
    }
    @Test
    void testLinearGraph() {
        System.out.printf("*****Linear Graph\n");
        // linear-graph with A, B, C.
        ShortestPaths.Graph graph = new ShortestPaths.Graph(
            new int[][] {
                {1, 2, 10},
                {2, 3, 20},
                {3, 4, 30},
                {4, 5, 40}
            }
        );

        // testAllPairs(graph);
        test(graph, 1, 2, List.of(
            new ShortestPaths.Ngbr(1, 10)
        ));
        test(graph, 1, 3, List.of(
            new ShortestPaths.Ngbr(1, 10),
            new ShortestPaths.Ngbr(2, 30)
        ));
        test(graph, 1, 4, List.of(
            new ShortestPaths.Ngbr(1, 10),
            new ShortestPaths.Ngbr(2, 30),
            new ShortestPaths.Ngbr(3, 60)
        ));
        test(graph, 1, 5, List.of(
            new ShortestPaths.Ngbr(1, 10),
            new ShortestPaths.Ngbr(2, 30),
            new ShortestPaths.Ngbr(3, 60),
            new ShortestPaths.Ngbr(4, 100)
        ));
    }
    @Test
    void testRippleGraph() {
        System.out.printf("*****Ripple Graph\n");
        // Ripple-graph with
        // A => B, C, D, E, F
        // B,C,D,E,F are Ripple connected with each other.
        ShortestPaths.Graph graph = new ShortestPaths.Graph(
            new int[][] {
                {1, 2, 100},
                {1, 3, 200},
                {1, 4, 300},
                {1, 5, 400},
                {1, 6, 500},

                {2, 3, 20},
                {3, 4, 30},
                {4, 5, 40},
                {5, 6, 50},
                {6, 2, 60}
            }
        );

        // testAllPairs(graph);
        test(graph, 1, 2, List.of(
            new ShortestPaths.Ngbr(1, 100)
        ));
        test(graph, 1, 3, List.of(
            new ShortestPaths.Ngbr(1, 100),
            new ShortestPaths.Ngbr(2, 120)
        ));
        test(graph, 1, 4, List.of(
            new ShortestPaths.Ngbr(1, 100),
            new ShortestPaths.Ngbr(2, 120),
            new ShortestPaths.Ngbr(3, 150)
        ));
        test(graph, 1, 5, List.of(
            new ShortestPaths.Ngbr(1, 100),
            new ShortestPaths.Ngbr(2, 120),
            new ShortestPaths.Ngbr(3, 150),
            new ShortestPaths.Ngbr(4, 190)
        ));
        test(graph, 1, 6, List.of(
            new ShortestPaths.Ngbr(1, 100),
            new ShortestPaths.Ngbr(2, 160)
        ));
    }

    // void testAllPairs(ShortestPaths.Graph graph) {
    //     System.out.printf("graph: %s\n", graph.nodeToNgbrs);
    //     for(var src : graph.nodeToNgbrs.keySet()) {
    //         for(var dst : graph.nodeToNgbrs.keySet()) {
    //             int s = src, d = dst;
    //             if(s != d) {
    //                 test(graph, src, dst);
    //             }
    //         }
    //     }
    //     System.out.println();
    //     System.out.println();
    // }
    void test(ShortestPaths.Graph graph, int src, int dst,
        List<ShortestPaths.Ngbr> expectedSrcToDstExclusive
    ) {
        System.out.printf("%d => %d\n", src, dst);
        var srcToDstExclusive = graph.dijkstra(src, dst);
        assertIterableEquals(expectedSrcToDstExclusive, srcToDstExclusive);
        for(var ngbr:srcToDstExclusive){
            System.out.printf("=> %d, cost %d\n", ngbr.ngbrID(), ngbr.weight());
        }
        System.out.printf("=> %d\n", dst);
        System.out.println();
    }

}
