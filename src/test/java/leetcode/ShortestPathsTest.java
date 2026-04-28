package leetcode;

import org.junit.jupiter.api.Test;

// import leetcode.ShortestPaths.Graph;

public class ShortestPathsTest {
    @Test
    void testTriangleGraph() {
        System.out.printf("*****Triangle Graph\n");
        // triangle-graph with A, B, C.
        ShortestPaths.Graph graph = new ShortestPaths.Graph(
            new int[][] {
                {1, 2, 10},
                {1, 3, 10},
                {3, 2, 10}
            }
        );
        test(graph, 1, 2);
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
        test(graph, 1, 4);
        test(graph, 1, 3);
    }
    @Test
    void testCircularGraph() {
        System.out.printf("*****Circular Graph\n");
        // circular-graph with
        // A => B, C, D, E, F
        // B,C,D,E,F are circularly connected with each other.
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

        for(var dst : new int[] {2, 3, 4, 5, 6}) {
            test(graph, 1, dst);
        }
    }

    void test(ShortestPaths.Graph graph, int src, int dst) {
        System.out.printf("graph: %s\n", graph.nodeToNgbrs);
        System.out.printf("%d => %d\n", src, dst);
        var nodeAndParent = graph.dijkstra(src, dst);
        for(var node = dst; node!=src; ){
            var parent = nodeAndParent.get(node);
            System.out.printf("node %d, parent %d, cost %d\n", node, parent.ngbrID(), parent.weight());
            node = parent.ngbrID();
        }
    }

}
