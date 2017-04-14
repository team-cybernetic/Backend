package beryloctopus.models.posts;

import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class GroupPost extends Post {
    private String rules;

    public GroupPost(String title, String path, UUID authorUUID, byte[] rawPostContent, long timestamp, String dateTime, PostRepository postRepository, UserRepository userRepository) {
        super(title, path, authorUUID, rawPostContent, timestamp, dateTime, postRepository, userRepository);
        this.rules = new String(rawPostContent, StandardCharsets.UTF_8);
    }

    public String getRules() {
        return rules;
    }
}
