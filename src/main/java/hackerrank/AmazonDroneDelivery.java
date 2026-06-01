package hackerrank;

import java.util.*;

public class AmazonDroneDelivery {
    /**
     * 
     * @param costs: costs from node 1 to 2, 2 to 3, ..., N to 1.
     * @param dsts: nodes to deliever to, in this order. 1-based.
     * @return total cost to deliever to all dsts in order, starting from node 1.
     */
    public static int minTime(List<Integer> costs, List<Integer>  dsts) {
        var totalCost =costs.stream().mapToInt(i->i).sum();

        int N = costs.size();
        int[][] costsFromTo = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            costsFromTo[i][i] = 0;
            // if(i == N) {
            //     costsFromTo[i][1] = costs.get(N-1);
            // } else {
            //     costsFromTo[i][i+1] = costs.get(i-1);
            // }
        }
        for(int len=1; len<N; len++) {
            for(int i=1; i<=N; i++) {
                var j = i+len;
                //  1: 1=>2, 2=>3, 3=>4 ... N-1=>N
                //  2: 1=>3, 2=>4, 3=>5 ... N-2=>N
                //  3: 1=>4, 2=>5, 3=>6 ... N-3=>N
                // ...
                //N-1:                      N-1=>N
                if(j <= N) {
                    // i => j-1 => j
                    costsFromTo[i][j] = costsFromTo[i][j-1] + costs.get(j-2);
                    // j => N => 1 => i
                    costsFromTo[i][j] = Math.min(costsFromTo[i][j], totalCost - costsFromTo[i][j]);
                }
            }
        }
        // System.out.printf("costs FROM TO  %s\n", Arrays.deepToString(costsFromTo));
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i<j)
                    System.out.printf("%d => %d = %d\n", i, j, costsFromTo[i][j]);
            }
        }

        var minCosts = 0;
        var prev = 1;
        for(var dst : dsts) {
            var from = prev;
            var to = dst;
            if(from > to) {
                from = dst;
                to = prev;
            }

            minCosts += costsFromTo[from][to];

            prev = dst;
        }

        return minCosts;
     }
}
