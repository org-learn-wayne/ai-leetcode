package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class KruskalMST {
    /*
     * Complete the 'kruskals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */
    public static class Foo<T  extends Number & Comparable<T>> {
        public List<T> gWeight;
    }
    public static class Bar {
        // Purpose of "super" lower-bound constraint:
        // - to allow *adding* Integer to given list
        // param can be... (list of "anything Integer derives from")
        // -  List<Integer>
        // -  List<Number>
        // -  List<Object>
        public static void locks() {
            {
                var lock = new Object();
                synchronized(lock) {
                    System.out.printf("synchronized-locked on %s\n", lock);
                }
            }
            {
                var lock = new ReentrantLock();
                try{
                    lock.lock();
                    System.out.printf("ReentrantLock-locked on %s\n", lock);
                } finally {
                    lock.unlock();
                    System.out.printf("UNlocked on %s\n", lock);
                }

                boolean isLocked = false;
                try{
                    isLocked = lock.tryLock(100, TimeUnit.MILLISECONDS);
                    System.out.printf("ReentrantLock-tryLock on %s\n", lock);
                } catch(InterruptedException e) {
                    System.out.printf("got exception %s\n", e);
                } finally {
                    if(isLocked) {
                        lock.unlock();
                        System.out.printf("UNlocked on %s\n", lock);
                    }
                }
            }
            {
                var lock = new ReentrantReadWriteLock();
                try{
                    lock.writeLock().lock();
                    System.out.printf("ReentrantReadWriteLock-writeLock on %s\n", lock);
                } finally {
                    lock.writeLock().unlock();
                    System.out.printf("UN-writeLock on %s\n", lock);
                }
                try{
                    lock.readLock().lock();
                    System.out.printf("ReentrantReadWriteLock-readLock on %s\n", lock);
                } finally {
                    lock.readLock().unlock();
                    System.out.printf("UN-readLock on %s\n", lock);
                }
            }
        }
        public static void writeToList(List<? super Integer>  list) {
            System.out.printf("writeToList %s\n", list);
            list.add(123);

            var ts = new TreeSet<>();
            for(var i: list) {
                ts.add(i);
            }
            System.out.printf("TreeSet %s\n", ts);

            { // Won't compile:
                List<Object> objList = new ArrayList<>();
                // readFromList(objList);
            }
            {
                    List<Number> numList = new ArrayList<>();
                    numList.add(123);
                    readFromList(numList);    
            }            
            {
                    List<Integer> intList = new ArrayList<>();
                    intList.add(123);
                    readFromList(intList);    
            }            
        }
        // param can be... (list of "anything derived from Number")
        // -  List<Integer>
        // -  List<Number>
        public static void readFromList(List<? extends Number>  list) {
            var num = list.get(0);
            System.out.printf("readFromList %s: %d\n", list, num);

            {
                List<Object> objList = new ArrayList<>();
                // writeToList(objList); // avoid infinite recursion in this example path
            }
        }
    }

    public static void runAndWaitForThread() {
        var pool = Executors.newFixedThreadPool(2);

        Callable<String> task =            () -> "foo";
        try {
            Future<String> future = pool.submit(task);
            var result = future.get();
            System.out.printf("got result %d\n", result);
        } catch(InterruptedException|ExecutionException e) {
            System.out.printf("got exception %s\n", e);
        }
    }

    public static void runAndWaitForThreads() {
        var pool = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> tasks = Arrays.asList(
            () -> 10,
            () -> 20,
            () -> 30
        );
        try {
            List<Future<Integer>> futures = pool.invokeAll(tasks);
            for(var future : futures) {
                var result = future.get();
                System.out.printf("got result %d\n", result);
            }
        } catch(InterruptedException|ExecutionException e) {
            System.out.printf("got exception %s\n", e);
        }
    }

    public static void asyncAwait() {
        var nThreads = 10;
        var pool = Executors.newFixedThreadPool(nThreads);
        CompletableFuture.supplyAsync(() -> "http://example.com")
            .thenAccept(url -> doUrl(url));

        // pool.submit((String url) -> doUrl(url)); lambda cannot take param?
        for(int i=0; i<nThreads+5; i++) {
            var future = pool.submit(new MyTask("http://example.com/"+i));
            try {
                future.get();
            } catch(ExecutionException|InterruptedException e) {
                System.out.printf("got exception %s\n", e);
            }
        }
        pool.shutdown();
    }
    static void doUrl(String url) {
        System.out.printf("doUrl %s\n", url);
    }
    static class MyTask implements Runnable {
        String url;
        public MyTask(String url) {
            this.url = url;
        }
        @Override
        public void run() {
            doUrl(url);
        }
    }

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        var edges = new ArrayList<List<Integer>>(gFrom.size());
        for(var i=0; i<gFrom.size(); i++) {
            if(gFrom.get(i) < gTo.get(i)) {
                edges.add(List.of(gFrom.get(i), gTo.get(i), gWeight.get(i)));
            } else {
                edges.add(List.of(gTo.get(i), gFrom.get(i), gWeight.get(i)));
            }
        }
        edges.sort((l, r) -> Integer.compare(l.get(2), r.get(2)));
        
        System.out.printf("sorted edges: %s\n", edges);
        
        var result  = new ArrayList<List<Integer>>();
        var nodeToRoot = new HashMap<Integer, Integer>();
        for(var edge : edges) {
            if(union(nodeToRoot, edge)) {
                result.add(edge);
            }
        }
        
        return result.stream().mapToInt(e -> e.get(2)).sum();
    }
    static boolean union(Map<Integer, Integer> nodeToRoot, List<Integer> edge) {
        System.out.printf("union edge %s to map %s...\n", edge, nodeToRoot);
        var a = edge.get(0);
        var b = edge.get(1);
        var rootA = getRoot(nodeToRoot, a);
        var rootB = getRoot(nodeToRoot, b);
        if (rootA == rootB) {
            return false;
        }
        nodeToRoot.put(rootB, rootA);
        return true;
    }

    public static class ClassicKruskals {
        public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
            var edges = new ArrayList<List<Integer>>(gFrom.size());
            for(var i=0; i<gFrom.size(); i++) {
                if(gFrom.get(i) < gTo.get(i)) {
                    edges.add(List.of(gFrom.get(i), gTo.get(i), gWeight.get(i)));
                } else {
                    edges.add(List.of(gTo.get(i), gFrom.get(i), gWeight.get(i)));
                }
            }
            edges.sort((l, r) -> Integer.compare(l.get(2), r.get(2)));

            var maxNode = gNodes;
            for(var edge : edges) {
                maxNode = Math.max(maxNode, Math.max(edge.get(0), edge.get(1)));
            }

            var parent = new int[maxNode + 1];
            var rank = new int[maxNode + 1];
            for(int i = 1; i <= maxNode; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            var totalWeight = 0;
            for(var edge : edges) {
                int a = edge.get(0);
                int b = edge.get(1);
                int rootA = find(parent, a);
                int rootB = find(parent, b);
                if(rootA == rootB) continue;
                union(parent, rank, rootA, rootB);
                totalWeight += edge.get(2);
            }

            return totalWeight;
        }

        static int find(int[] parent, int node) {
            if(parent[node] == node) {
                return node;
            }
            return parent[node] = find(parent, parent[node]);
        }

        static void union(int[] parent, int[] rank, int rootA, int rootB) {
            if(rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if(rank[rootB] < rank[rootA]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }

    static boolean formCycle(Map<Integer, Integer> nodeToRoot, List<Integer> edge) {
        var nodeA = edge.get(0);
        var nodeB = edge.get(1);
        if(!nodeToRoot.containsKey(nodeA)) return false;
        if(!nodeToRoot.containsKey(nodeB)) return false;

        var aParent = getRoot(nodeToRoot, nodeA);
        var bParent = getRoot(nodeToRoot, nodeB);
        return aParent == bParent;
    }
    static int getRoot(Map<Integer, Integer> nodeToRoot, Integer node) {
        while(node != null) {
            var parent = nodeToRoot.getOrDefault(node, null);
            if(parent==null) {
                return node;
            } else {
                node = parent;
            }
        }

        assert false;
        return -9;
    }    
}
