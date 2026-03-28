package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0017LetterCombinationsOfAPhoneNumberTest {
    private final Leet0017LetterCombinationsOfAPhoneNumber solution =
            new Leet0017LetterCombinationsOfAPhoneNumber();

    @Test
    void returnsStandardTwoDigitCombinations() {
        assertEquals(
                List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"),
                solution.letterCombinations("23"));
    }

    @Test
    void returnsEmptyForEmptyInput() {
        assertEquals(List.of(), solution.letterCombinations(""));
    }

    @Test
    void handlesSingleDigitWithFourLetters() {
        assertEquals(List.of("p", "q", "r", "s"), solution.letterCombinations("7"));
    }
}
