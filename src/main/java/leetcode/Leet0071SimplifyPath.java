package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet0071SimplifyPath {
    public String simplifyPath(String path) {
        String[] parts = path.split("/");
        Deque<String> directories = new ArrayDeque<>();

        for (String part : parts) {
            if (part.isEmpty() || ".".equals(part)) {
                continue;
            }

            if ("..".equals(part)) {
                if (!directories.isEmpty()) {
                    directories.removeLast();
                }
                continue;
            }

            directories.addLast(part);
        }

        if (directories.isEmpty()) {
            return "/";
        }

        StringBuilder simplifiedPath = new StringBuilder();
        for (String directory : directories) {
            simplifiedPath.append('/').append(directory);
        }

        return simplifiedPath.toString();
    }
}
