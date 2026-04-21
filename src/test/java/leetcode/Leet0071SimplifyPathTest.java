package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Leet0071SimplifyPathTest {
    private final Leet0071SimplifyPath solution = new Leet0071SimplifyPath();

    @Test
    void simplifiesBasicPath() {
        assertEquals("/home", solution.simplifyPath("/home/"));
    }

    @Test
    void collapsesParentDirectorySegments() {
        assertEquals("/c", solution.simplifyPath("/a/./b/../../c/"));
    }

    @Test
    void staysAtRootWhenParentWouldGoAboveIt() {
        assertEquals("/", solution.simplifyPath("/../"));
    }

    @Test
    void collapsesRepeatedSlashes() {
        assertEquals("/home/foo", solution.simplifyPath("/home//foo/"));
    }

    @Test
    void keepsDirectoryNamesThatContainDots() {
        assertEquals("/.../b/d", solution.simplifyPath("/.../a/../b/c/../d/./"));
    }
}
