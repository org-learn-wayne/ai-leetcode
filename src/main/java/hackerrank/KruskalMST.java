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

        // Use map-based DSU to support sparse node ids
        var parent = new HashMap<Integer, Integer>();
        var rank = new HashMap<Integer, Integer>();

        for(var edge : edges) {
            int a = edge.get(0);
            int b = edge.get(1);
            parent.putIfAbsent(a, a);
            parent.putIfAbsent(b, b);
            rank.putIfAbsent(a, 0);
            rank.putIfAbsent(b, 0);
        }

        var totalWeight = 0;
        for(var edge : edges) {
            int a = edge.get(0);
            int b = edge.get(1);
            int rootA = findAndCompress(parent, a);
            int rootB = findAndCompress(parent, b);
            if(rootA == rootB) continue;
            union(parent, rank, rootA, rootB);
            totalWeight += edge.get(2);
        }

        return totalWeight;
    }

    /**
     * Find the representative (root) of the set containing {@code node}.
     * This method performs path compression and will modify the {@code parent}
     * map so that nodes visited on the way to the root point directly to the root.
     *
     * @param parent map from node to parent (may be updated)
     * @param node node to find
     * @return root representative of the node's set
     */
    static int findAndCompress(Map<Integer, Integer> parent, int node) {
        parent.putIfAbsent(node, node);
        int p = parent.get(node);
        if(p == node) return node;
        int r = findAndCompress(parent, p);
        parent.put(node, r);
        return r;
    }

    /**
     * Union two sets identified by their roots {@code ra} and {@code rb}.
     * Uses union-by-rank heuristic and updates both {@code parent} and
     * {@code rank} maps accordingly.
     *
     * @param parent map from node to parent (will be updated)
     * @param rank map storing tree rank/approximate height (will be updated)
     * @param ra root of first set
     * @param rb root of second set
     */
    static void union(Map<Integer, Integer> parent, Map<Integer, Integer> rank, int ra, int rb) {
        int rra = rank.getOrDefault(ra, 0);
        int rrb = rank.getOrDefault(rb, 0);
        if(rra < rrb) {
            parent.put(ra, rb);
        } else if(rrb < rra) {
            parent.put(rb, ra);
        } else {
            parent.put(rb, ra);
            rank.put(ra, rra + 1);
        }
    }
}
