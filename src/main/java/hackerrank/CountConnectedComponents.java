package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class CountConnectedComponents {
    /*
     *  https://www.hackerrank.com/contests/software-engineer-prep-kit/challenges/count-connected-components-in-network/problem?isFullScreen=true     * Complete the 'countIsolatedCommunicationGroups' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY links
     *  2. INTEGER n
     */

    public static int countIsolatedCommunicationGroups(List<List<Integer>> links, int n) {
        System.out.printf("N: %d\n", n);
        System.out.printf("links: %s\n", links);

        List<List<Integer>> linksCopy = new ArrayList<>(links.size()*2);
        linksCopy.addAll(links);
        var origSize = links.size();
        for(var i=0; i<origSize; i++) {
            var link = links.get(i);
            linksCopy.add(List.of(link.get(1), link.get(0)));
        }
        var nodeToNgbrs = linksCopy.stream().collect(Collectors.groupingBy(
                item -> item.get(0),
                Collectors.mapping(
                    item -> item.get(1),
                    Collectors.toSet()
                )        
            ));
        System.out.printf("nodeToNgbrs: %s\n", nodeToNgbrs);
        // for(var i=0; i<n; i++) {
        //     nodeToNgbrs.putIfAbsent(i, new HashSet<>());
        // }
        // for(var entry : nodeToNgbrs.entrySet()) {
        //     var key = entry.getKey();
        //     for(var value : entry.getValue()) {
        //         nodeToNgbrs.get(value).add(key);
        //     }
        // }
        System.out.printf("nodeToNgbrs: %s\n", nodeToNgbrs);

        // new HashMap<Integer, List<Integer>>();
        var nodeToParent = new int[n];
        Arrays.fill(nodeToParent, -1);
        
        var nGraphs = 0;
        for(var node = 0; node<n; node++) {
            var par = nodeToParent[node];
            if(par < 0) { // not set?
                nGraphs++;
                
                var currRoot = node;

                var toVisit = new ArrayList<Integer>();
                toVisit.add(node);
                
                while(!toVisit.isEmpty()) {
                    var toVNode = toVisit.remove(toVisit.size()-1);
                    nodeToParent[toVNode] = currRoot;
                    
                    if(nodeToNgbrs.containsKey(toVNode)) {
                        for(var x : nodeToNgbrs.get(toVNode)) {
                            if(nodeToParent[x] < 0) {
                                toVisit.add(x);
                            }
                        }
                    }
                }
            }
        }
        
        return nGraphs;

    }
}
