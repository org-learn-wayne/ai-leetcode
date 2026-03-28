package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Leet0049GroupAnagramsTest {
    private final Leet0049GroupAnagrams solution = new Leet0049GroupAnagrams();

    @Test
    void groupsTypicalInput() {
        assertEquals(
                List.of(
                        List.of("ate", "eat", "tea"),
                        List.of("bat"),
                        List.of("nat", "tan")),
                normalize(solution.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"})));
    }

    @Test
    void handlesEmptyString() {
        assertEquals(List.of(List.of("")), normalize(solution.groupAnagrams(new String[] {""})));
    }

    @Test
    void handlesSingleLetterWord() {
        assertEquals(List.of(List.of("a")), normalize(solution.groupAnagrams(new String[] {"a"})));
    }

    private List<List<String>> normalize(List<List<String>> groups) {
        return groups.stream()
                .map(group -> group.stream().sorted().toList())
                .sorted(Comparator.comparing(group -> String.join(",", group)))
                .toList();
    }
}
