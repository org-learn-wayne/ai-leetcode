package interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HealthcareBoardStoreTest {
    @Test
    void supportsGroupAndPostCrud() {
        var store = new HealthcareBoardStore();

        var createdGroup = store.createGroup(new HealthcareGroupInput(
                "Nutrition Club",
                "Talk about meals, planning, and healthy habits."));
        assertTrue(store.getGroup(createdGroup.id()).isPresent());

        var createdPost = store.createPost(createdGroup.id(), new HealthcarePostInput(
                "Sam",
                "Looking for meal ideas",
                "What are your go-to easy dinners?"));
        assertTrue(createdPost.isPresent());

        var updatedPost = store.updatePost(createdGroup.id(), createdPost.get().id(), new HealthcarePostInput(
                "Sam",
                "Looking for meal ideas",
                "Thanks for the suggestions everyone!"));
        assertTrue(updatedPost.isPresent());
        assertEquals("Thanks for the suggestions everyone!", updatedPost.get().body());

        assertTrue(store.deletePost(createdGroup.id(), createdPost.get().id()));
        assertFalse(store.getPost(createdGroup.id(), createdPost.get().id()).isPresent());
        assertTrue(store.deleteGroup(createdGroup.id()));
    }
}
