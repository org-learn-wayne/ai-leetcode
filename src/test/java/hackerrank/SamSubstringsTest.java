package hackerrank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SamSubstringsTest {

    @Test
    void returnsIndicesForBasicCase() {
        assertEquals(1+6+16, SamSubstrings.substrings("16"));
        assertEquals(
            3+8+5+2+1+
            38+85+52+21+
            385+852+521+
            3852+8521+
            38521
            , SamSubstrings.substrings("38521"));
        assertEquals(
            (
            8+43+8+5+2+1+
            84+43+38+85+52+21+
            843+438+385+852+521+
            (8438+4385+3852+8521) % 1000000007 +
            (84385+43852+38521) % 1000000007 +
            (843852+438521) % 1000000007 +
            (8438521) % 1000000007 
            ) % 1000000007 
            , SamSubstrings.substrings("8438521"));
    }
}