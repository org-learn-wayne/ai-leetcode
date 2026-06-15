package hackerrank;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import hackerrank.KruskalMST.Bar;

public class KruskalMSTTest {
    @Test
    void testLocking1() {
        System.out.println();
        Bar.locks();

        System.out.println();
        KruskalMST.runAndWaitForThreads();

        System.out.println();
        Bar.writeToList(new ArrayList<>(List.of(123,234,345,213)));
    }

    @Test
    void test0() {
        var froms = List.of(
1,
1,
4,
2,
3,
3
        );
        var tos = List.of(
2,
3,
1,
4,
2,
4
        );
        var weights = List.of(
5,
3,
6,
7,
4,
5
        );


        System.out.println();
        assertEquals(12, KruskalMST.kruskals(
            1,
            froms,
            tos,
            weights 
        ));
    }

    @Test
    void test1() {
        System.out.println();
        var froms = List.of(
1 ,
1 ,
1 ,
1 ,
2 ,
3 ,
4 
        );
        var tos = List.of(
2 ,
3 ,
4 ,
5 ,
3 ,
4 ,
5 
        );
        var weights = List.of(
20,
50,
70,
90,
30,
40,
60
        );
        assertEquals(150, KruskalMST.kruskals(
            1,
            froms,
            tos,
            weights 
        ));
    }

    // Looks for src/test/resources/KruskalMSTTest_test5.txt
    @Test
    void test5() throws IOException, URISyntaxException {
        var resource = getClass().getClassLoader().getResource("KruskalMSTTest_test5.txt");
        assertNotNull(resource, "File not found!");
        System.out.println(resource.toURI());
        var path = Paths.get(resource.toURI());
        var lines = Files.readAllLines(path);
        assertNotNull(lines);    

        // Expected #nodes and #edges in the file:
        // 1000 10000
        var froms = new ArrayList<Integer>(10000);
        var tos = new ArrayList<Integer>(10000);
        var weights = new ArrayList<Integer>(10000);
        for(var line : lines) {
            var parts = line.split("\\s+");
            var nums = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
            assertTrue(nums.length == 3);
            froms.add(nums[0]);
            tos.add(nums[1]);
            weights.add(nums[2]);
        }

        assertEquals(6359060, KruskalMST.kruskals(
            1,
            froms,
            tos,
            weights
        ));
    }
}
