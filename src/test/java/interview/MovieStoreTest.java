package interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MovieStoreTest {
    @Test
    void supportsCrudOperations() {
        var store = new MovieStore();
        int initialSize = store.list().size();

        var created = store.create(new MovieInput(
                "Arrival",
                "Denis Villeneuve",
                List.of("Amy Adams", "Jeremy Renner"),
                LocalDate.of(2016, 11, 11),
                8));

        assertEquals(initialSize + 1, store.list().size());
        assertTrue(store.get(created.id()).isPresent());

        var updated = store.update(created.id(), new MovieInput(
                "Arrival",
                "Denis Villeneuve",
                List.of("Amy Adams", "Jeremy Renner", "Forest Whitaker"),
                LocalDate.of(2016, 11, 11),
                9));

        assertTrue(updated.isPresent());
        assertEquals(9, updated.get().rating());
        assertTrue(store.delete(created.id()));
        assertFalse(store.get(created.id()).isPresent());
    }
}
