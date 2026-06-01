package hackerrank;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import hackerrank.KruskalMST.Bar;

public class AmazonDroneDeliveryTest {
    @Test
    void test1() {
        testUtil(List.of(3,2,1), List.of(3), 1);
        testUtil(List.of(3,2,1), List.of(2), 3);
        testUtil(List.of(3,2,1), List.of(1), 0);
        testUtil(List.of(3,2,1), List.of(3,2,3,1), 1+2+2+1);
        testUtil(List.of(1,3,6,11), List.of(2,4,1,3,2,1), 1+9+10+4+3+1);
    }

    static void testUtil(List<Integer> costs, List<Integer>  dsts, int expect) {
        System.out.println();
        System.out.printf("costs   %s\n", costs);
        System.out.printf("deliver %s\n", dsts);
        System.out.printf("expect %d\n", expect);
        var actual = AmazonDroneDelivery.minTime(costs, dsts);
        System.out.printf("actual %d\n", actual);
        assertEquals(expect, actual);
    }
    
}
