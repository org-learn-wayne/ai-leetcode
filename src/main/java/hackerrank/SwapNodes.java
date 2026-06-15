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

/*
    https://www.hackerrank.com/challenges/swap-nodes-algo/problem?isFullScreen=false

 */
public class SwapNodes {
    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        System.out.printf("Don't do this problem - too much plumbing needed\n");
        System.out.printf("indexes: %s\n", indexes);
        System.out.printf("queries: %s\n", queries);

        var result = new ArrayList<List<Integer>>();

        var v2Node = buildTree(indexes);
        System.out.printf("TREE: %s\n", v2Node.get(1));

        var depth = calcDepth(v2Node.get(1), v2Node, 0);
        for(var q : queries) {
            for(var multi=1; multi*q <= depth; multi++) {
                var tmpQ = q * multi; // BUG iff "q *= multi": must not update "q" for next loop
                swapAtDepth(v2Node, tmpQ);
                System.out.printf("TREE after q=%d: %s\n", tmpQ, v2Node.get(1));
            }
            
            result.add(inOrder(v2Node));
        }
        
        return result;
    }


    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY indexes
     *  2. INTEGER_ARRAY queries
     */
    static class Node {
        int val;
        Node lNode;
        Node rNode;
        Node(int v, Node l, Node r) {
            val = v;
            lNode = l;
            rNode = r;
        }
        Node(int v) {
            val = v;
        }
        public String toString() {
            var lStr = "";
            var rStr = "";
            if(lNode!=null) lStr = lNode.toString();
            if(rNode!=null) rStr = rNode.toString();
            return String.format("{%d, %s, %s}", val, lStr, rStr);
        }
    }
    // calc depth for "currNode" - it's parent is parentDepth.
    public static int calcDepth(Node currNode, Map<Integer, Node> v2Node, int parentDepth) {
        if(currNode == null) return parentDepth;
        var node = v2Node.get(currNode.val);
        assert node == currNode;
        var lDepth = calcDepth(node.lNode, v2Node, parentDepth+1);
        var rDepth = calcDepth(node.rNode, v2Node, parentDepth+1);
        return Math.max(lDepth, rDepth);
    }
    public static Map<Integer, Node> buildTree(List<List<Integer>> indexes) {
        Map<Integer, Node> v2Node = new HashMap<>();
        for(int i=0; i<indexes.size(); i++) {
            var v = i+1;
            Node node = new Node(v);
            v2Node.put(v, node);
        }
        for(int i=0; i<indexes.size(); i++) {
            var children = indexes.get(i);
            assert children.size() == 2;

            var v = i+1;
            Node node = v2Node.get(v);
            assert node != null;

            {
                var cv = children.get(0);
                if(cv > 0) {
                    Node cNode = v2Node.get(cv);
                    assert cNode != null;
                    node.lNode = cNode;
                }
            }
            {
                var cv = children.get(1);
                if(cv > 0) {
                    Node cNode = v2Node.get(cv);
                    assert cNode != null;
                    node.rNode = cNode;
                }
            }
        }        
        return v2Node;
    }
    public static void swapAtDepth(Map<Integer, Node> v2Node, int depth) {
        var currD = 1;

        var currDNodes = new ArrayList<Node>();
        currDNodes.add(v2Node.get(1));        
        
        while(currD < depth) {
            var nextDNodes = new ArrayList<Node>();
            for(var i=0; i<currDNodes.size(); i++) {
                var node = currDNodes.get(i);
                if(node.lNode != null) nextDNodes.add(node.lNode);
                if(node.rNode != null) nextDNodes.add(node.rNode);
            }
            
            currDNodes = nextDNodes;
            currD++;
        }

        for(var i=0; i<currDNodes.size(); i++) {
            var node = currDNodes.get(i);
            var oldR = node.rNode;
            node.rNode = node.lNode;
            node.lNode = oldR;
        }
    }
    public static void inOrder(Node currNode, List<Integer> result) {
        if(currNode==null)        return ;
        inOrder(currNode.lNode, result);
        result.add(currNode.val);
        inOrder(currNode.rNode, result);
    }
    public static List<Integer> inOrder(Map<Integer, Node> v2Node) {        
        var result = new ArrayList<Integer>();
        inOrder(v2Node.get(1), result);
        return result;
    }
}
