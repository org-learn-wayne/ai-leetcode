package interview;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public final class HealthcareBoardStore {
    private final AtomicInteger nextGroupId = new AtomicInteger(1);
    private final AtomicInteger nextPostId = new AtomicInteger(1);
    private final Map<Integer, HealthcareGroup> groups = new LinkedHashMap<>();
    private final Map<Integer, Map<Integer, HealthcarePost>> postsByGroup = new LinkedHashMap<>();

    public HealthcareBoardStore() {
        createGroup(new HealthcareGroupInput(
                "Diabetes Support",
                "Community space for living well with diabetes."));
        createGroup(new HealthcareGroupInput(
                "Caregiver Corner",
                "For caregivers sharing advice, reminders, and encouragement."));
        createPost(1, new HealthcarePostInput(
                "Alex",
                "Welcome to the group",
                "Share your questions, tips, and resources here."));
    }

    public synchronized List<HealthcareGroup> listGroups() {
        return new ArrayList<>(groups.values());
    }

    public synchronized Optional<HealthcareGroup> getGroup(int id) {
        return Optional.ofNullable(groups.get(id));
    }

    public synchronized HealthcareGroup createGroup(HealthcareGroupInput input) {
        var group = new HealthcareGroup(nextGroupId.getAndIncrement(), input.name(), input.description());
        groups.put(group.id(), group);
        postsByGroup.put(group.id(), new LinkedHashMap<>());
        return group;
    }

    public synchronized Optional<HealthcareGroup> updateGroup(int id, HealthcareGroupInput input) {
        if (!groups.containsKey(id)) {
            return Optional.empty();
        }

        var group = new HealthcareGroup(id, input.name(), input.description());
        groups.put(id, group);
        return Optional.of(group);
    }

    public synchronized boolean deleteGroup(int id) {
        var removedGroup = groups.remove(id);
        postsByGroup.remove(id);
        return removedGroup != null;
    }

    public synchronized List<HealthcarePost> listPosts(int groupId) {
        return new ArrayList<>(posts(groupId).values());
    }

    public synchronized Optional<HealthcarePost> getPost(int groupId, int postId) {
        return Optional.ofNullable(posts(groupId).get(postId));
    }

    public synchronized Optional<HealthcarePost> createPost(int groupId, HealthcarePostInput input) {
        if (!groups.containsKey(groupId)) {
            return Optional.empty();
        }

        var now = LocalDateTime.now();
        var post = new HealthcarePost(
                nextPostId.getAndIncrement(),
                groupId,
                input.authorName(),
                input.title(),
                input.body(),
                now,
                now);
        posts(groupId).put(post.id(), post);
        return Optional.of(post);
    }

    public synchronized Optional<HealthcarePost> updatePost(int groupId, int postId, HealthcarePostInput input) {
        var groupPosts = postsByGroup.get(groupId);
        if (groupPosts == null || !groupPosts.containsKey(postId)) {
            return Optional.empty();
        }

        var existing = groupPosts.get(postId);
        var updated = new HealthcarePost(
                postId,
                groupId,
                input.authorName(),
                input.title(),
                input.body(),
                existing.createdAt(),
                LocalDateTime.now());
        groupPosts.put(postId, updated);
        return Optional.of(updated);
    }

    public synchronized boolean deletePost(int groupId, int postId) {
        var groupPosts = postsByGroup.get(groupId);
        return groupPosts != null && groupPosts.remove(postId) != null;
    }

    private Map<Integer, HealthcarePost> posts(int groupId) {
        var groupPosts = postsByGroup.get(groupId);
        if (groupPosts == null) {
            throw new IllegalArgumentException("Group not found");
        }
        return groupPosts;
    }
}
