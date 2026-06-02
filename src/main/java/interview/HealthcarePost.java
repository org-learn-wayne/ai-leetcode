package interview;

import java.time.LocalDateTime;

public record HealthcarePost(
        int id,
        int groupId,
        String authorName,
        String title,
        String body,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
