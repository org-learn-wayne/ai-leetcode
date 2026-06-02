package interview;

import java.time.LocalDate;
import java.util.List;

public record MovieInput(
        String name,
        String directedBy,
        List<String> castNames,
        LocalDate releaseDate,
        int rating) {

    public MovieInput {
        castNames = List.copyOf(castNames);
    }
}
