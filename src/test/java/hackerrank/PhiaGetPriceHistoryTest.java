package hackerrank;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class PhiaGetPriceHistoryTest {

    @Test
    void test1() throws IOException, URISyntaxException {
        {
            var actual = PhiaGetPriceHistory.getPriceHistory("000", "2026-01-04");
            testX(actual, "[140, 130, 120]");
        }
        {
            var actual = PhiaGetPriceHistory.getPriceHistory("000", "2026-01-06");
            testX(actual, "[160, 150, 140]");
        }
        {
            var actual = PhiaGetPriceHistory.getPriceHistory("01a", "2026-01-04");
            testX(actual, "[103, 113, 111]");
        }
    }
    void testX(String actual, String expect) {
        assertNotNull(actual);
        assertEquals(expect, actual);
    }
}
