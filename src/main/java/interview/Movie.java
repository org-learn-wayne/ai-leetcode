package interview;

import java.time.LocalDate;
import java.util.List;

public record Movie(
        int id,
        String name,
        String directedBy,
        List<String> castNames,
        LocalDate releaseDate,
        int rating) {

    public Movie {
        castNames = List.copyOf(castNames);
    }
}
