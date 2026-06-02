package interview;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public final class MovieServer {
    private static final int PORT = 8080;
    private static final Pattern ID_PATTERN = Pattern.compile("^/api/movies/(\\d+)$");
    private static final Pattern GROUP_ID_PATTERN = Pattern.compile("^/api/healthcare/groups/(\\d+)$");
    private static final Pattern GROUP_POSTS_PATTERN = Pattern.compile("^/api/healthcare/groups/(\\d+)/posts$");
    private static final Pattern GROUP_POST_ID_PATTERN = Pattern.compile("^/api/healthcare/groups/(\\d+)/posts/(\\d+)$");

    private final MovieStore store = new MovieStore();
    private final HealthcareBoardStore healthcareBoardStore = new HealthcareBoardStore();

    public static void main(String[] args) throws IOException {
        new MovieServer().start();
    }

    private void start() throws IOException {
        var server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", this::handleHome);
        server.createContext("/api/movies", this::handleMovies);
        server.createContext("/api/movies/", this::handleMovieById);
        server.createContext("/api/healthcare/groups", this::handleHealthcareGroups);
        server.createContext("/api/healthcare/groups/", this::handleHealthcareGroupNested);
        server.setExecutor(null);
        server.start();
        System.out.printf("Movie API running at http://localhost:%d/%n", PORT);
    }

    private void handleHome(HttpExchange exchange) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod()) || !"/".equals(exchange.getRequestURI().getPath())) {
            sendJson(exchange, 404, errorJson("Not found"));
            return;
        }

        sendHtml(exchange, 200, homePage());
    }

    private void handleMovies(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod().toUpperCase(Locale.ROOT)) {
            case "GET" -> sendJson(exchange, 200, moviesJson(store.list()));
            case "POST" -> createMovie(exchange);
            case "OPTIONS" -> sendEmpty(exchange, 204);
            default -> sendJson(exchange, 405, errorJson("Method not allowed"));
        }
    }

    private void handleMovieById(HttpExchange exchange) throws IOException {
        var matcher = ID_PATTERN.matcher(exchange.getRequestURI().getPath());
        if (!matcher.matches()) {
            sendJson(exchange, 404, errorJson("Not found"));
            return;
        }

        int id = Integer.parseInt(matcher.group(1));
        switch (exchange.getRequestMethod().toUpperCase(Locale.ROOT)) {
            case "GET" -> {
                var movie = store.get(id);
                if (movie.isPresent()) {
                    sendJson(exchange, 200, movieJson(movie.get()));
                } else {
                    sendJson(exchange, 404, errorJson("Movie not found"));
                }
            }
            case "PUT" -> updateMovie(exchange, id);
            case "DELETE" -> {
                if (store.delete(id)) {
                    sendJson(exchange, 200, "{\"deleted\":true}");
                } else {
                    sendJson(exchange, 404, errorJson("Movie not found"));
                }
            }
            case "OPTIONS" -> sendEmpty(exchange, 204);
            default -> sendJson(exchange, 405, errorJson("Method not allowed"));
        }
    }

    private void handleHealthcareGroups(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod().toUpperCase(Locale.ROOT)) {
            case "GET" -> sendJson(exchange, 200, healthcareGroupsJson(healthcareBoardStore.listGroups()));
            case "POST" -> createHealthcareGroup(exchange);
            case "OPTIONS" -> sendEmpty(exchange, 204);
            default -> sendJson(exchange, 405, errorJson("Method not allowed"));
        }
    }

    private void handleHealthcareGroupNested(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        var postMatcher = GROUP_POST_ID_PATTERN.matcher(path);
        if (postMatcher.matches()) {
            int groupId = Integer.parseInt(postMatcher.group(1));
            int postId = Integer.parseInt(postMatcher.group(2));
            handleHealthcarePostById(exchange, groupId, postId);
            return;
        }

        var postsMatcher = GROUP_POSTS_PATTERN.matcher(path);
        if (postsMatcher.matches()) {
            int groupId = Integer.parseInt(postsMatcher.group(1));
            handleHealthcarePosts(exchange, groupId);
            return;
        }

        var groupMatcher = GROUP_ID_PATTERN.matcher(path);
        if (groupMatcher.matches()) {
            int groupId = Integer.parseInt(groupMatcher.group(1));
            handleHealthcareGroupById(exchange, groupId);
            return;
        }

        sendJson(exchange, 404, errorJson("Not found"));
    }

    private void handleHealthcareGroupById(HttpExchange exchange, int groupId) throws IOException {
        switch (exchange.getRequestMethod().toUpperCase(Locale.ROOT)) {
            case "GET" -> {
                var group = healthcareBoardStore.getGroup(groupId);
                if (group.isPresent()) {
                    sendJson(exchange, 200, healthcareGroupJson(group.get()));
                } else {
                    sendJson(exchange, 404, errorJson("Group not found"));
                }
            }
            case "PUT" -> updateHealthcareGroup(exchange, groupId);
            case "DELETE" -> {
                if (healthcareBoardStore.deleteGroup(groupId)) {
                    sendJson(exchange, 200, "{\"deleted\":true}");
                } else {
                    sendJson(exchange, 404, errorJson("Group not found"));
                }
            }
            case "OPTIONS" -> sendEmpty(exchange, 204);
            default -> sendJson(exchange, 405, errorJson("Method not allowed"));
        }
    }

    private void handleHealthcarePosts(HttpExchange exchange, int groupId) throws IOException {
        if (healthcareBoardStore.getGroup(groupId).isEmpty()) {
            sendJson(exchange, 404, errorJson("Group not found"));
            return;
        }

        switch (exchange.getRequestMethod().toUpperCase(Locale.ROOT)) {
            case "GET" -> sendJson(exchange, 200, healthcarePostsJson(groupId, healthcareBoardStore.listPosts(groupId)));
            case "POST" -> createHealthcarePost(exchange, groupId);
            case "OPTIONS" -> sendEmpty(exchange, 204);
            default -> sendJson(exchange, 405, errorJson("Method not allowed"));
        }
    }

    private void handleHealthcarePostById(HttpExchange exchange, int groupId, int postId) throws IOException {
        if (healthcareBoardStore.getGroup(groupId).isEmpty()) {
            sendJson(exchange, 404, errorJson("Group not found"));
            return;
        }

        switch (exchange.getRequestMethod().toUpperCase(Locale.ROOT)) {
            case "GET" -> {
                var post = healthcareBoardStore.getPost(groupId, postId);
                if (post.isPresent()) {
                    sendJson(exchange, 200, healthcarePostJson(post.get()));
                } else {
                    sendJson(exchange, 404, errorJson("Post not found"));
                }
            }
            case "PUT" -> updateHealthcarePost(exchange, groupId, postId);
            case "DELETE" -> {
                if (healthcareBoardStore.deletePost(groupId, postId)) {
                    sendJson(exchange, 200, "{\"deleted\":true}");
                } else {
                    sendJson(exchange, 404, errorJson("Post not found"));
                }
            }
            case "OPTIONS" -> sendEmpty(exchange, 204);
            default -> sendJson(exchange, 405, errorJson("Method not allowed"));
        }
    }

    private void createMovie(HttpExchange exchange) throws IOException {
        var input = readMovieInput(exchange);
        if (input.errorMessage() != null) {
            sendJson(exchange, 400, errorJson(input.errorMessage()));
            return;
        }

        var created = store.create(input.movieInput());
        exchange.getResponseHeaders().set("Location", "/api/movies/" + created.id());
        sendJson(exchange, 201, movieJson(created));
    }

    private void updateMovie(HttpExchange exchange, int id) throws IOException {
        var input = readMovieInput(exchange);
        if (input.errorMessage() != null) {
            sendJson(exchange, 400, errorJson(input.errorMessage()));
            return;
        }

        var updated = store.update(id, input.movieInput());
        if (updated.isEmpty()) {
            sendJson(exchange, 404, errorJson("Movie not found"));
            return;
        }

        sendJson(exchange, 200, movieJson(updated.get()));
    }

    private void createHealthcareGroup(HttpExchange exchange) throws IOException {
        var input = readHealthcareGroupInput(exchange);
        if (input.errorMessage() != null) {
            sendJson(exchange, 400, errorJson(input.errorMessage()));
            return;
        }

        var created = healthcareBoardStore.createGroup(input.groupInput());
        exchange.getResponseHeaders().set("Location", "/api/healthcare/groups/" + created.id());
        sendJson(exchange, 201, healthcareGroupJson(created));
    }

    private void updateHealthcareGroup(HttpExchange exchange, int groupId) throws IOException {
        var input = readHealthcareGroupInput(exchange);
        if (input.errorMessage() != null) {
            sendJson(exchange, 400, errorJson(input.errorMessage()));
            return;
        }

        var updated = healthcareBoardStore.updateGroup(groupId, input.groupInput());
        if (updated.isEmpty()) {
            sendJson(exchange, 404, errorJson("Group not found"));
            return;
        }

        sendJson(exchange, 200, healthcareGroupJson(updated.get()));
    }

    private void createHealthcarePost(HttpExchange exchange, int groupId) throws IOException {
        var input = readHealthcarePostInput(exchange);
        if (input.errorMessage() != null) {
            sendJson(exchange, 400, errorJson(input.errorMessage()));
            return;
        }

        var created = healthcareBoardStore.createPost(groupId, input.postInput());
        if (created.isEmpty()) {
            sendJson(exchange, 404, errorJson("Group not found"));
            return;
        }

        exchange.getResponseHeaders().set("Location", "/api/healthcare/groups/" + groupId + "/posts/" + created.get().id());
        sendJson(exchange, 201, healthcarePostJson(created.get()));
    }

    private void updateHealthcarePost(HttpExchange exchange, int groupId, int postId) throws IOException {
        var input = readHealthcarePostInput(exchange);
        if (input.errorMessage() != null) {
            sendJson(exchange, 400, errorJson(input.errorMessage()));
            return;
        }

        var updated = healthcareBoardStore.updatePost(groupId, postId, input.postInput());
        if (updated.isEmpty()) {
            sendJson(exchange, 404, errorJson("Post not found"));
            return;
        }

        sendJson(exchange, 200, healthcarePostJson(updated.get()));
    }

    private ParsedMovieInput readMovieInput(HttpExchange exchange) throws IOException {
        String body = readBody(exchange.getRequestBody());
        if (body.isBlank()) {
            return new ParsedMovieInput(null, "Request body is required");
        }

        try {
            Object parsed = new JsonParser(body).parse();
            if (!(parsed instanceof Map<?, ?> map)) {
                return new ParsedMovieInput(null, "Expected a JSON object");
            }

            String name = requireString(map, "name");
            String directedBy = requireString(map, "directedBy");
            List<String> castNames = requireStringList(map, "castNames");
            LocalDate releaseDate = parseDate(requireString(map, "releaseDate"));
            int rating = parseTinyInt(map.get("rating"));

            return new ParsedMovieInput(new MovieInput(name, directedBy, castNames, releaseDate, rating), null);
        } catch (IllegalArgumentException | DateTimeParseException ex) {
            return new ParsedMovieInput(null, ex.getMessage());
        }
    }

    private ParsedHealthcareGroupInput readHealthcareGroupInput(HttpExchange exchange) throws IOException {
        String body = readBody(exchange.getRequestBody());
        if (body.isBlank()) {
            return new ParsedHealthcareGroupInput(null, "Request body is required");
        }

        try {
            Object parsed = new JsonParser(body).parse();
            if (!(parsed instanceof Map<?, ?> map)) {
                return new ParsedHealthcareGroupInput(null, "Expected a JSON object");
            }

            String name = requireString(map, "name");
            String description = requireString(map, "description");
            return new ParsedHealthcareGroupInput(new HealthcareGroupInput(name, description), null);
        } catch (IllegalArgumentException ex) {
            return new ParsedHealthcareGroupInput(null, ex.getMessage());
        }
    }

    private ParsedHealthcarePostInput readHealthcarePostInput(HttpExchange exchange) throws IOException {
        String body = readBody(exchange.getRequestBody());
        if (body.isBlank()) {
            return new ParsedHealthcarePostInput(null, "Request body is required");
        }

        try {
            Object parsed = new JsonParser(body).parse();
            if (!(parsed instanceof Map<?, ?> map)) {
                return new ParsedHealthcarePostInput(null, "Expected a JSON object");
            }

            String authorName = requireString(map, "authorName");
            String title = requireString(map, "title");
            String bodyText = requireString(map, "body");
            return new ParsedHealthcarePostInput(new HealthcarePostInput(authorName, title, bodyText), null);
        } catch (IllegalArgumentException ex) {
            return new ParsedHealthcarePostInput(null, ex.getMessage());
        }
    }

    private static String requireString(Map<?, ?> map, String key) {
        Object value = map.get(key);
        if (!(value instanceof String s) || s.isBlank()) {
            throw new IllegalArgumentException("Field '" + key + "' must be a non-empty string");
        }
        return s.trim();
    }

    @SuppressWarnings("unchecked")
    private static List<String> requireStringList(Map<?, ?> map, String key) {
        Object value = map.get(key);
        if (!(value instanceof List<?> list) || list.isEmpty()) {
            throw new IllegalArgumentException("Field '" + key + "' must be a non-empty array of strings");
        }

        var result = new ArrayList<String>(list.size());
        for (Object item : list) {
            if (!(item instanceof String s) || s.isBlank()) {
                throw new IllegalArgumentException("Field '" + key + "' must contain only non-empty strings");
            }
            result.add(s.trim());
        }
        return result;
    }

    private static LocalDate parseDate(String value) {
        return LocalDate.parse(value);
    }

    private static int parseTinyInt(Object value) {
        if (value instanceof Number number) {
            int rating = number.intValue();
            if (rating < 0 || rating > 127) {
                throw new IllegalArgumentException("Field 'rating' must be between 0 and 127");
            }
            return rating;
        }
        throw new IllegalArgumentException("Field 'rating' must be a number between 0 and 127");
    }

    private static String healthcareGroupJson(HealthcareGroup group) {
        return "{"
                + "\"id\":" + group.id() + ","
                + "\"name\":" + jsonString(group.name()) + ","
                + "\"description\":" + jsonString(group.description())
                + "}";
    }

    private static String healthcareGroupsJson(List<HealthcareGroup> groups) {
        var builder = new StringBuilder();
        builder.append("{\"items\":[");
        for (int i = 0; i < groups.size(); i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append(healthcareGroupJson(groups.get(i)));
        }
        builder.append("]}");
        return builder.toString();
    }

    private static String healthcarePostJson(HealthcarePost post) {
        return "{"
                + "\"id\":" + post.id() + ","
                + "\"groupId\":" + post.groupId() + ","
                + "\"authorName\":" + jsonString(post.authorName()) + ","
                + "\"title\":" + jsonString(post.title()) + ","
                + "\"body\":" + jsonString(post.body()) + ","
                + "\"createdAt\":" + jsonString(post.createdAt().toString()) + ","
                + "\"updatedAt\":" + jsonString(post.updatedAt().toString())
                + "}";
    }

    private static String healthcarePostsJson(int groupId, List<HealthcarePost> posts) {
        var builder = new StringBuilder();
        builder.append("{\"groupId\":").append(groupId).append(",\"items\":[");
        for (int i = 0; i < posts.size(); i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append(healthcarePostJson(posts.get(i)));
        }
        builder.append("]}");
        return builder.toString();
    }

    private static String movieJson(Movie movie) {
        return "{"
                + "\"id\":" + movie.id() + ","
                + "\"name\":" + jsonString(movie.name()) + ","
                + "\"directedBy\":" + jsonString(movie.directedBy()) + ","
                + "\"castNames\":" + jsonArray(movie.castNames()) + ","
                + "\"releaseDate\":" + jsonString(movie.releaseDate().toString()) + ","
                + "\"rating\":" + movie.rating()
                + "}";
    }

    private static String moviesJson(List<Movie> movies) {
        var builder = new StringBuilder();
        builder.append("{\"items\":[");
        for (int i = 0; i < movies.size(); i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append(movieJson(movies.get(i)));
        }
        builder.append("]}");
        return builder.toString();
    }

    private static String jsonArray(List<String> items) {
        var builder = new StringBuilder("[");
        for (int i = 0; i < items.size(); i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append(jsonString(items.get(i)));
        }
        builder.append(']');
        return builder.toString();
    }

    private static String jsonString(String value) {
        var builder = new StringBuilder("\"");
        for (char ch : value.toCharArray()) {
            switch (ch) {
                case '\\' -> builder.append("\\\\");
                case '"' -> builder.append("\\\"");
                case '\b' -> builder.append("\\b");
                case '\f' -> builder.append("\\f");
                case '\n' -> builder.append("\\n");
                case '\r' -> builder.append("\\r");
                case '\t' -> builder.append("\\t");
                default -> {
                    if (ch < 0x20) {
                        builder.append(String.format("\\u%04x", (int) ch));
                    } else {
                        builder.append(ch);
                    }
                }
            }
        }
        builder.append('"');
        return builder.toString();
    }

    private static String errorJson(String message) {
        return "{\"error\":" + jsonString(message) + "}";
    }

    private static String readBody(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim();
    }

    private static void sendJson(HttpExchange exchange, int statusCode, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(bytes);
        } finally {
            exchange.close();
        }
    }

    private static void sendHtml(HttpExchange exchange, int statusCode, String html) throws IOException {
        byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(bytes);
        } finally {
            exchange.close();
        }
    }

    private static void sendEmpty(HttpExchange exchange, int statusCode) throws IOException {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(statusCode, -1);
        exchange.close();
    }

    private static String homePage() {
        return """
                <!doctype html>
                <html lang="en">
                <head>
                  <meta charset="utf-8" />
                  <meta name="viewport" content="width=device-width, initial-scale=1" />
                  <title>Movie API Sandbox</title>
                  <style>
                    :root {
                      --bg: #0f172a;
                      --panel: #111827;
                      --panel-2: #1f2937;
                      --text: #e5e7eb;
                      --muted: #94a3b8;
                      --accent: #38bdf8;
                      --danger: #f87171;
                      --ok: #34d399;
                    }
                    * { box-sizing: border-box; }
                    body {
                      margin: 0;
                      min-height: 100vh;
                      font-family: Inter, Segoe UI, Arial, sans-serif;
                      background:
                        radial-gradient(circle at top left, rgba(56, 189, 248, 0.18), transparent 30%),
                        radial-gradient(circle at bottom right, rgba(52, 211, 153, 0.16), transparent 25%),
                        var(--bg);
                      color: var(--text);
                    }
                    main {
                      max-width: 1100px;
                      margin: 0 auto;
                      padding: 32px 20px 60px;
                    }
                    .hero {
                      display: grid;
                      gap: 12px;
                      margin-bottom: 24px;
                    }
                    h1 {
                      margin: 0;
                      font-size: clamp(2rem, 4vw, 3.5rem);
                    }
                    .subtitle { color: var(--muted); max-width: 70ch; }
                    .grid {
                      display: grid;
                      grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
                      gap: 16px;
                    }
                    .card {
                      background: rgba(17, 24, 39, 0.82);
                      border: 1px solid rgba(148, 163, 184, 0.18);
                      border-radius: 18px;
                      padding: 18px;
                      box-shadow: 0 18px 50px rgba(0, 0, 0, 0.25);
                      backdrop-filter: blur(12px);
                    }
                    h2 { margin: 0 0 12px; font-size: 1.1rem; }
                    label { display: block; margin: 10px 0 6px; color: var(--muted); font-size: 0.92rem; }
                    input, textarea, button {
                      width: 100%;
                      border-radius: 12px;
                      border: 1px solid rgba(148, 163, 184, 0.25);
                      background: rgba(15, 23, 42, 0.9);
                      color: var(--text);
                      padding: 11px 12px;
                      font: inherit;
                    }
                    textarea { min-height: 92px; resize: vertical; }
                    button {
                      cursor: pointer;
                      margin-top: 12px;
                      background: linear-gradient(135deg, var(--accent), #2563eb);
                      border: none;
                      font-weight: 700;
                    }
                    button.secondary { background: rgba(148, 163, 184, 0.14); border: 1px solid rgba(148, 163, 184, 0.22); }
                    button.danger { background: rgba(248, 113, 113, 0.18); border: 1px solid rgba(248, 113, 113, 0.45); }
                    pre {
                      overflow: auto;
                      background: rgba(2, 6, 23, 0.75);
                      border-radius: 14px;
                      padding: 14px;
                      min-height: 120px;
                      margin: 0;
                      white-space: pre-wrap;
                      word-break: break-word;
                    }
                    .row { display: grid; grid-template-columns: 1fr 1fr; gap: 12px; }
                    .hint { font-size: 0.85rem; color: var(--muted); margin-top: 8px; }
                    .pill { display: inline-block; padding: 4px 10px; border-radius: 999px; background: rgba(56, 189, 248, 0.15); color: #bae6fd; font-size: 0.8rem; }
                    @media (max-width: 720px) { .row { grid-template-columns: 1fr; } }
                  </style>
                </head>
                <body>
                  <main>
                    <section class="hero">
                      <span class="pill">Local REST API</span>
                      <h1>Movie API Sandbox</h1>
                      <div class="subtitle">Use this page to create, inspect, update, and delete movies against the embedded API at <code>/api/movies</code>.</div>
                    </section>

                    <section class="grid">
                      <div class="card">
                        <h2>Create Movie</h2>
                        <label>Name</label><input id="createName" value="The Matrix" />
                        <label>Directed By</label><input id="createDirectedBy" value="The Wachowskis" />
                        <label>Cast Names (comma separated)</label><input id="createCastNames" value="Keanu Reeves, Carrie-Anne Moss, Laurence Fishburne" />
                        <div class="row">
                          <div>
                            <label>Release Date</label><input id="createReleaseDate" type="date" value="1999-03-31" />
                          </div>
                          <div>
                            <label>Rating</label><input id="createRating" type="number" min="0" max="127" value="9" />
                          </div>
                        </div>
                        <button onclick="createMovie()">Create</button>
                      </div>

                      <div class="card">
                        <h2>Get / Update / Delete</h2>
                        <label>Movie ID</label><input id="movieId" type="number" min="1" value="1" />
                        <button class="secondary" onclick="loadMovie()">Load by ID</button>
                        <button class="secondary" onclick="refreshMovies()">Refresh List</button>
                        <button class="danger" onclick="deleteMovie()">Delete by ID</button>
                        <div class="hint">For update, edit the fields below and click Update.</div>
                        <label>Name</label><input id="updateName" />
                        <label>Directed By</label><input id="updateDirectedBy" />
                        <label>Cast Names (comma separated)</label><input id="updateCastNames" />
                        <div class="row">
                          <div>
                            <label>Release Date</label><input id="updateReleaseDate" type="date" />
                          </div>
                          <div>
                            <label>Rating</label><input id="updateRating" type="number" min="0" max="127" />
                          </div>
                        </div>
                        <button onclick="updateMovie()">Update</button>
                      </div>

                      <div class="card">
                        <h2>Output</h2>
                        <pre id="output">Loading...</pre>
                      </div>
                    </section>

                    <section class="hero" style="margin-top: 28px;">
                      <span class="pill">Healthcare Discussion Board</span>
                      <h1 style="font-size: clamp(1.7rem, 3vw, 2.5rem);">Groups and Posts</h1>
                      <div class="subtitle">Manage communities and the discussion posts inside them. Groups and posts are nested under `/api/healthcare/groups`.</div>
                    </section>

                    <section class="grid">
                      <div class="card">
                        <h2>Group CRUD</h2>
                        <label>Group ID</label><input id="groupId" type="number" min="1" value="1" />
                        <button class="secondary" onclick="loadGroup()">Load Group</button>
                        <button class="secondary" onclick="refreshGroups()">Refresh Groups</button>
                        <button class="danger" onclick="deleteGroup()">Delete Group</button>
                        <label>Name</label><input id="groupName" value="Diabetes Support" />
                        <label>Description</label><textarea id="groupDescription">Community space for living well with diabetes.</textarea>
                        <button onclick="createGroup()">Create Group</button>
                        <button class="secondary" onclick="updateGroup()">Update Group</button>
                      </div>

                      <div class="card">
                        <h2>Post CRUD</h2>
                        <label>Group ID</label><input id="postGroupId" type="number" min="1" value="1" />
                        <label>Post ID</label><input id="postId" type="number" min="1" value="1" />
                        <button class="secondary" onclick="loadPost()">Load Post</button>
                        <button class="secondary" onclick="refreshPosts()">Refresh Posts</button>
                        <button class="danger" onclick="deletePost()">Delete Post</button>
                        <label>Author Name</label><input id="postAuthorName" value="Alex" />
                        <label>Title</label><input id="postTitle" value="Welcome to the group" />
                        <label>Body</label><textarea id="postBody">Share your questions, tips, and resources here.</textarea>
                        <button onclick="createPost()">Create Post</button>
                        <button class="secondary" onclick="updatePost()">Update Post</button>
                      </div>

                      <div class="card">
                        <h2>Board Output</h2>
                        <pre id="boardOutput">Loading...</pre>
                      </div>
                    </section>
                  </main>

                  <script>
                    const output = document.getElementById('output');
                    const movieId = document.getElementById('movieId');
                    const updateName = document.getElementById('updateName');
                    const updateDirectedBy = document.getElementById('updateDirectedBy');
                    const updateCastNames = document.getElementById('updateCastNames');
                    const updateReleaseDate = document.getElementById('updateReleaseDate');
                    const updateRating = document.getElementById('updateRating');
                    const boardOutput = document.getElementById('boardOutput');
                    const groupId = document.getElementById('groupId');
                    const groupName = document.getElementById('groupName');
                    const groupDescription = document.getElementById('groupDescription');
                    const postGroupId = document.getElementById('postGroupId');
                    const postId = document.getElementById('postId');
                    const postAuthorName = document.getElementById('postAuthorName');
                    const postTitle = document.getElementById('postTitle');
                    const postBody = document.getElementById('postBody');

                    function castList(value) {
                      return value.split(',').map(s => s.trim()).filter(Boolean);
                    }

                    function formPayload(prefix) {
                      return {
                        name: document.getElementById(prefix + 'Name').value,
                        directedBy: document.getElementById(prefix + 'DirectedBy').value,
                        castNames: castList(document.getElementById(prefix + 'CastNames').value),
                        releaseDate: document.getElementById(prefix + 'ReleaseDate').value,
                        rating: Number(document.getElementById(prefix + 'Rating').value)
                      };
                    }

                    async function request(url, options = {}) {
                      const response = await fetch(url, {
                        headers: { 'Content-Type': 'application/json', ...(options.headers || {}) },
                        ...options
                      });
                      const text = await response.text();
                      let payload = text;
                      try { payload = text ? JSON.parse(text) : null; } catch (e) {}
                      if (!response.ok) {
                        throw new Error((payload && payload.error) ? payload.error : text || response.statusText);
                      }
                      return payload;
                    }

                    async function refreshMovies() {
                      const data = await request('/api/movies');
                      output.textContent = JSON.stringify(data, null, 2);
                    }

                    async function loadMovie() {
                      try {
                        const data = await request('/api/movies/' + movieId.value);
                        updateName.value = data.name || '';
                        updateDirectedBy.value = data.directedBy || '';
                        updateCastNames.value = Array.isArray(data.castNames) ? data.castNames.join(', ') : '';
                        updateReleaseDate.value = data.releaseDate || '';
                        updateRating.value = data.rating ?? '';
                        output.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        output.textContent = 'Load failed: ' + err.message;
                      }
                    }

                    async function createMovie() {
                      const data = await request('/api/movies', {
                        method: 'POST',
                        body: JSON.stringify(formPayload('create'))
                      });
                      movieId.value = data.id;
                      await refreshMovies();
                      output.textContent = JSON.stringify(data, null, 2);
                    }

                    async function updateMovie() {
                      const data = await request('/api/movies/' + movieId.value, {
                        method: 'PUT',
                        body: JSON.stringify({
                          name: updateName.value,
                          directedBy: updateDirectedBy.value,
                          castNames: castList(updateCastNames.value),
                          releaseDate: updateReleaseDate.value,
                          rating: Number(updateRating.value)
                        })
                      });
                      await refreshMovies();
                      output.textContent = JSON.stringify(data, null, 2);
                    }

                    async function deleteMovie() {
                      try {
                        const data = await request('/api/movies/' + movieId.value, { method: 'DELETE' });
                        await refreshMovies();
                        output.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        output.textContent = 'Delete failed: ' + err.message;
                      }
                    }

                    function boardPayloadGroup() {
                      return {
                        name: groupName.value,
                        description: groupDescription.value
                      };
                    }

                    function boardPayloadPost() {
                      return {
                        authorName: postAuthorName.value,
                        title: postTitle.value,
                        body: postBody.value
                      };
                    }

                    async function refreshGroups() {
                      const data = await request('/api/healthcare/groups');
                      boardOutput.textContent = JSON.stringify(data, null, 2);
                    }

                    async function loadGroup() {
                      try {
                        const data = await request('/api/healthcare/groups/' + groupId.value);
                        groupName.value = data.name || '';
                        groupDescription.value = data.description || '';
                        boardOutput.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        boardOutput.textContent = 'Load group failed: ' + err.message;
                      }
                    }

                    async function createGroup() {
                      try {
                        const data = await request('/api/healthcare/groups', {
                          method: 'POST',
                          body: JSON.stringify(boardPayloadGroup())
                        });
                        groupId.value = data.id;
                        postGroupId.value = data.id;
                        await refreshGroups();
                        boardOutput.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        boardOutput.textContent = 'Create group failed: ' + err.message;
                      }
                    }

                    async function updateGroup() {
                      try {
                        const data = await request('/api/healthcare/groups/' + groupId.value, {
                          method: 'PUT',
                          body: JSON.stringify(boardPayloadGroup())
                        });
                        await refreshGroups();
                        boardOutput.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        boardOutput.textContent = 'Update group failed: ' + err.message;
                      }
                    }

                    async function deleteGroup() {
                      try {
                        const data = await request('/api/healthcare/groups/' + groupId.value, { method: 'DELETE' });
                        await refreshGroups();
                        boardOutput.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        boardOutput.textContent = 'Delete group failed: ' + err.message;
                      }
                    }

                    async function refreshPosts() {
                      const data = await request('/api/healthcare/groups/' + postGroupId.value + '/posts');
                      boardOutput.textContent = JSON.stringify(data, null, 2);
                    }

                    async function loadPost() {
                      try {
                        const data = await request('/api/healthcare/groups/' + postGroupId.value + '/posts/' + postId.value);
                        postGroupId.value = data.groupId || postGroupId.value;
                        postAuthorName.value = data.authorName || '';
                        postTitle.value = data.title || '';
                        postBody.value = data.body || '';
                        boardOutput.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        boardOutput.textContent = 'Load post failed: ' + err.message;
                      }
                    }

                    async function createPost() {
                      try {
                        const data = await request('/api/healthcare/groups/' + postGroupId.value + '/posts', {
                          method: 'POST',
                          body: JSON.stringify(boardPayloadPost())
                        });
                        postId.value = data.id;
                        groupId.value = data.groupId;
                        await refreshPosts();
                        boardOutput.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        boardOutput.textContent = 'Create post failed: ' + err.message;
                      }
                    }

                    async function updatePost() {
                      try {
                        const data = await request('/api/healthcare/groups/' + postGroupId.value + '/posts/' + postId.value, {
                          method: 'PUT',
                          body: JSON.stringify(boardPayloadPost())
                        });
                        await refreshPosts();
                        boardOutput.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        boardOutput.textContent = 'Update post failed: ' + err.message;
                      }
                    }

                    async function deletePost() {
                      try {
                        const data = await request('/api/healthcare/groups/' + postGroupId.value + '/posts/' + postId.value, { method: 'DELETE' });
                        await refreshPosts();
                        boardOutput.textContent = JSON.stringify(data, null, 2);
                      } catch (err) {
                        boardOutput.textContent = 'Delete post failed: ' + err.message;
                      }
                    }

                    refreshMovies().catch(err => {
                      output.textContent = String(err);
                    });
                    refreshGroups().catch(err => {
                      boardOutput.textContent = String(err);
                    });
                  </script>
                </body>
                </html>
                """;
    }

    private record ParsedMovieInput(MovieInput movieInput, String errorMessage) {}
    private record ParsedHealthcareGroupInput(HealthcareGroupInput groupInput, String errorMessage) {}
    private record ParsedHealthcarePostInput(HealthcarePostInput postInput, String errorMessage) {}

    private static final class JsonParser {
        private final String input;
        private int index;

        private JsonParser(String input) {
            this.input = input;
        }

        Object parse() {
            skipWhitespace();
            Object value = parseValue();
            skipWhitespace();
            if (index != input.length()) {
                throw error("Unexpected trailing content");
            }
            return value;
        }

        private Object parseValue() {
            skipWhitespace();
            if (index >= input.length()) {
                throw error("Unexpected end of input");
            }

            char ch = input.charAt(index);
            return switch (ch) {
                case '{' -> parseObject();
                case '[' -> parseArray();
                case '"' -> parseString();
                case 't' -> readLiteral("true", Boolean.TRUE);
                case 'f' -> readLiteral("false", Boolean.FALSE);
                case 'n' -> readLiteral("null", null);
                default -> parseNumber();
            };
        }

        private Map<String, Object> parseObject() {
            expect('{');
            skipWhitespace();
            Map<String, Object> result = new LinkedHashMap<>();
            if (peek('}')) {
                index++;
                return result;
            }

            while (true) {
                skipWhitespace();
                String key = parseString();
                skipWhitespace();
                expect(':');
                Object value = parseValue();
                result.put(key, value);
                skipWhitespace();
                if (peek(',')) {
                    index++;
                    continue;
                }
                expect('}');
                return result;
            }
        }

        private List<Object> parseArray() {
            expect('[');
            skipWhitespace();
            List<Object> result = new ArrayList<>();
            if (peek(']')) {
                index++;
                return result;
            }

            while (true) {
                result.add(parseValue());
                skipWhitespace();
                if (peek(',')) {
                    index++;
                    continue;
                }
                expect(']');
                return result;
            }
        }

        private String parseString() {
            expect('"');
            var builder = new StringBuilder();
            while (index < input.length()) {
                char ch = input.charAt(index++);
                if (ch == '"') {
                    return builder.toString();
                }
                if (ch == '\\') {
                    if (index >= input.length()) {
                        throw error("Invalid escape sequence");
                    }
                    char escaped = input.charAt(index++);
                    switch (escaped) {
                        case '"', '\\', '/' -> builder.append(escaped);
                        case 'b' -> builder.append('\b');
                        case 'f' -> builder.append('\f');
                        case 'n' -> builder.append('\n');
                        case 'r' -> builder.append('\r');
                        case 't' -> builder.append('\t');
                        case 'u' -> {
                            if (index + 4 > input.length()) {
                                throw error("Invalid unicode escape");
                            }
                            String hex = input.substring(index, index + 4);
                            builder.append((char) Integer.parseInt(hex, 16));
                            index += 4;
                        }
                        default -> throw error("Unsupported escape character: " + escaped);
                    }
                } else {
                    builder.append(ch);
                }
            }
            throw error("Unterminated string");
        }

        private Number parseNumber() {
            int start = index;
            if (peek('-')) {
                index++;
            }
            while (index < input.length() && Character.isDigit(input.charAt(index))) {
                index++;
            }
            boolean fractional = false;
            if (peek('.')) {
                fractional = true;
                index++;
                while (index < input.length() && Character.isDigit(input.charAt(index))) {
                    index++;
                }
            }
            if (peek('e') || peek('E')) {
                fractional = true;
                index++;
                if (peek('+') || peek('-')) {
                    index++;
                }
                while (index < input.length() && Character.isDigit(input.charAt(index))) {
                    index++;
                }
            }

            String token = input.substring(start, index);
            if (token.isEmpty() || "-".equals(token)) {
                throw error("Invalid number");
            }
            try {
                if (fractional) {
                    return Double.parseDouble(token);
                }
                long longValue = Long.parseLong(token);
                if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
                    return (int) longValue;
                }
                return longValue;
            } catch (NumberFormatException ex) {
                throw error("Invalid number");
            }
        }

        private Object readLiteral(String literal, Object value) {
            if (!input.startsWith(literal, index)) {
                throw error("Unexpected token");
            }
            index += literal.length();
            return value;
        }

        private void skipWhitespace() {
            while (index < input.length() && Character.isWhitespace(input.charAt(index))) {
                index++;
            }
        }

        private void expect(char expected) {
            if (index >= input.length() || input.charAt(index) != expected) {
                throw error("Expected '" + expected + "'");
            }
            index++;
        }

        private boolean peek(char expected) {
            return index < input.length() && input.charAt(index) == expected;
        }

        private IllegalArgumentException error(String message) {
            return new IllegalArgumentException(message);
        }
    }
}
