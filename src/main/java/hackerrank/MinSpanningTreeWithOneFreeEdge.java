package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

// https://www.hackerrank.com/contests/software-engineer-prep-kit/challenges/min-spanning-tree-with-one-free-edge/problem?isFullScreen=true
public class MinSpanningTreeWithOneFreeEdge {
    /**
     * Doesn't see we could...
     * - find MST
     * - set max-cost edge to 0 and be done
     * Example#1:
     * - A-B-1
     * - A-C-2
     * - B-C-5
     * - C-D-6
     * MST is A-B-1, A-C-2, C-D-6, total 9.
     * - If we set max-cost edge to 0, we get 3.
     * - But if we set B-C-5 to 0, we get 1+5+6=12.
     * Example#2:
     * - A-B-1
     * - A-C-2
     * - B-C-5
     * - C-D-4
     * MST is A-B-1, A-C-2, C-D-4, total 7.
     * - If we set max-cost edge to 0, we get 3.
     * - But if we set B-C-5 to 0, we get 1+0+4=5.
     * @param n
     * @param m
     * @param edges
     * @return
     */
    static boolean DEBUG = false;

    public static long calculateMinimumSpanningTreeWeightWithFreeEdge(int nNodes, int mEdges, List<List<Integer>> edges) {
        var freeEdgeAndTotalCost = calcMSTWithFreeEdge(nNodes, mEdges, edges, null);
        if(true) return freeEdgeAndTotalCost.get(3);

        var freeEdgeAndTotalCost2 = calcMSTWithFreeEdge(nNodes, mEdges, edges, freeEdgeAndTotalCost);
        return freeEdgeAndTotalCost2.get(3);
    }
    /**
     * 
     * @param nNodes
     * @param mEdges
     * @param edges
     * @param freeEdgeAndTotalCost:
     * If null, will pick (highest-cost) edge in MST as free edge.
     * => the picked edge is returned, along with total-cost with the edge set to 0.
     * 
     * If not null, it provides an edge - will set it to 0-cost instead.
     * => the "free" edge is returned, along with total-cost.
     * @return
     */
     static List<List<Integer>>  setFreeEdge(List<List<Integer>> edges, List<Long> freeEdgeAndTotalCost) {
        if(freeEdgeAndTotalCost==null) {
            return edges;
        }

        var freeEdgeFrom = freeEdgeAndTotalCost.get(0).intValue();
        var freeEdgeTo = freeEdgeAndTotalCost.get(1).intValue();
        return edges.stream().map(e -> {
            var eFrom =e.get(0);
            var eTo  =e.get(1);
            var isFreeEdge =
                (eFrom == freeEdgeFrom && eTo == freeEdgeTo) ||
                (eFrom == freeEdgeTo && eTo == freeEdgeFrom);
            return (isFreeEdge ? List.of(eFrom, eTo, 0) : e);
        }).collect(Collectors.toList());
    }

    public static List<Long> calcMSTWithFreeEdge(int nNodes, int mEdges, List<List<Integer>> edges,
        List<Long> freeEdgeAndTotalCost
    ) {
        edges = setFreeEdge(edges, freeEdgeAndTotalCost);

        var edgeList = new ArrayList<List<Integer>>(edges.size()*2);
        edgeList.addAll(edges);
        
        for(var i=0; i<edges.size(); i++) {
            var edge = edges.get(i);
            edgeList.add(List.of(edge.get(1), edge.get(0), edge.get(2)));
        }
        if(DEBUG) System.out.printf("edgeList %s\n", edgeList);

        var pq = new PriorityQueue<List<Integer>>((l, r) -> Integer.compare(l.get(2), r.get(2)));
        pq.addAll(edgeList);

        var nodeToParent = new int[nNodes];
        for(int i=0; i<nNodes; i++) nodeToParent[i] = i;

        long totalCost = 0;
        List<Integer> lastEdgeCost = List.of(0, 0, 0); // sentinel
        while(!pq.isEmpty()){
            var edge = pq.poll();
            var from = edge.get(0);
            var to = edge.get(1);
            var cost = edge.get(2);

            if(isConnected(nodeToParent, from, to)) {
                continue;
            } else {
                union(nodeToParent, from, to);
                if(DEBUG) System.out.printf("add edge %s => %s\n", edge, Arrays.toString(nodeToParent));
                assert lastEdgeCost.get(2) <= cost : "edges should be polled in increasing order of cost";
                lastEdgeCost = edge;
                totalCost += cost;
            }
        }

        totalCost =(freeEdgeAndTotalCost == null) ? totalCost - (long)lastEdgeCost.get(2) : totalCost;

        return List.of(
            (long)lastEdgeCost.get(0),
            (long)lastEdgeCost.get(1),
            (long)lastEdgeCost.get(2),
            totalCost
        );
    }

    static void union(int[] nodeToParent, int from, int to) {
        if(nodeToParent[from] == from) nodeToParent[from] = to;
        else if(nodeToParent[to] == to) nodeToParent[to] = from;
        else
            // TODO: this isn't optimal, but should be good enough for the test cases.
            nodeToParent[from] = to;
    }
    static boolean isConnected(int[] nodeToParent, int from, int to) {
        var fRoot = getRoot(nodeToParent, from);
        var tRoot = getRoot(nodeToParent, to);
        return (fRoot == tRoot);
    }
    static int getRoot(int[] nodeToParent, int node) {
        while(nodeToParent[node] != node) node = nodeToParent[node];
        return node;
    }

}
