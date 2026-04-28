package leetcode;

import java.util.*;
// import java.util.stream.*;

public final class ShortestPaths {
    public static record Ngbr(int ngbrID, int weight) {}
    public static record Node(int id, List<Ngbr> ngbrs) {}

    public static class Graph {
        Map<Integer, List<Ngbr>> nodeToNgbrs;

        public Graph(int[][] triplets) {
            nodeToNgbrs = new HashMap<> ();

            for(var triplet : triplets) {
                int u = triplet[0];
                int v = triplet[1];
                int weight = triplet[2];

                if(!nodeToNgbrs.containsKey(u)) {
                    nodeToNgbrs.put(u, new ArrayList<>());
                }
                nodeToNgbrs.get(u).add(new Ngbr(v, weight));

                if(!nodeToNgbrs.containsKey(v)) {
                    nodeToNgbrs.put(v, new ArrayList<>());
                }
                nodeToNgbrs.get(v).add(new Ngbr(u, weight));

                // nodeToNgbrs.computeIfAbsent(u, k -> new ArrayList<>()).add(new Ngbr(v, weight));
                // nodeToNgbrs.computeIfAbsent(v, k -> new ArrayList<>()).add(new Ngbr(u, weight));
            }
        }

        // Returned list ordered from [src, dst).
        public List<Ngbr> dijkstra(int src, int dst) {
            var visited = new HashSet<Integer>();
            var toVist = new PriorityQueue<Ngbr>((l, r) -> Integer.compare(l.weight, r.weight));
            var nodeAndParent = new HashMap<Integer, Ngbr>();

            toVist.offer(new Ngbr(src, 0));
            nodeAndParent.put(src, new Ngbr(-1, 0)); // Start from the source node with no parent and zero cost

            while(!toVist.isEmpty()) {
                var temp = toVist.poll();
                var curr = temp.ngbrID;
                var currCost = temp.weight;

                visited.add(curr);

                for(var ngbr : nodeToNgbrs.get(curr)) {
                    if(!visited.contains(ngbr.ngbrID)) {
                        toVist.offer(new Ngbr(ngbr.ngbrID, currCost + ngbr.weight));
                    }

                    var parentForCurr = nodeAndParent.get(curr);
                    var newCost = ngbr.weight + parentForCurr.weight;
                    if(nodeAndParent.containsKey(ngbr.ngbrID)) {
                        var oldParent = nodeAndParent.get(ngbr.ngbrID);
                        if(oldParent.weight > newCost) {
                            nodeAndParent.put(ngbr.ngbrID, new Ngbr(curr, newCost));
                        }
                    } else {
                        nodeAndParent.put(ngbr.ngbrID, new Ngbr(curr, newCost));
                    }
                }   
            }

            return toList(nodeAndParent, src, dst);
        }
        List<Ngbr> toList(Map<Integer, Ngbr> nodeAndParent, int src, int dst) {
            var path = new ArrayList<Ngbr>();
            for(var node = dst; node!=src; ){
                var parent = nodeAndParent.get(node);
                path.add(parent);
                node = parent.ngbrID();
            }
            Collections.reverse(path);
            return path;
        }

        public void dijkstra1() {
            var visited = new HashSet<Integer>();
            var pq = new PriorityQueue<Ngbr>((a, b) -> Integer.compare(a.weight, b.weight));
            pq.offer(new Ngbr(0, 0)); // Start from node 0 with
            while(!pq.isEmpty()) {
                var curr = pq.poll();
                for(var ngbr : nodeToNgbrs.get(curr.ngbrID)) {
                    if(!visited.contains(ngbr.ngbrID)) {
                        visited.add(ngbr.ngbrID);
                        pq.offer(new Ngbr(ngbr.ngbrID, curr.weight + ngbr.weight));
                    } else {
                        // If the neighbor has already been visited, we can skip it or update its weight if necessary.
                        // In a typical Dijkstra's algorithm implementation, we would check if the new path to the neighbor is shorter than the previously known path.
                        // However, since we are using a priority queue and only adding neighbors that haven't been visited, we can safely ignore this case.
                    }   
                }
            }
        }
    }
}