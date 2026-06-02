package interview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public final class MovieStore {
    private final AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, Movie> movies = new LinkedHashMap<>();

    public MovieStore() {
        create(new MovieInput(
                "Inception",
                "Christopher Nolan",
                List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"),
                LocalDate.of(2010, 7, 16),
                9));
        create(new MovieInput(
                "Spirited Away",
                "Hayao Miyazaki",
                List.of("Rumi Hiiragi", "Miyu Irino", "Mari Natsuki"),
                LocalDate.of(2001, 7, 20),
                10));
    }

    public synchronized List<Movie> list() {
        return new ArrayList<>(movies.values());
    }

    public synchronized Optional<Movie> get(int id) {
        return Optional.ofNullable(movies.get(id));
    }

    public synchronized Movie create(MovieInput input) {
        var movie = new Movie(nextId.getAndIncrement(), input.name(), input.directedBy(), input.castNames(), input.releaseDate(), input.rating());
        movies.put(movie.id(), movie);
        return movie;
    }

    public synchronized Optional<Movie> update(int id, MovieInput input) {
        if (!movies.containsKey(id)) {
            return Optional.empty();
        }

        var movie = new Movie(id, input.name(), input.directedBy(), input.castNames(), input.releaseDate(), input.rating());
        movies.put(id, movie);
        return Optional.of(movie);
    }

    public synchronized boolean delete(int id) {
        return movies.remove(id) != null;
    }
}
