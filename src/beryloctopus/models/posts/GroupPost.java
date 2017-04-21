package beryloctopus.models.posts;

import beryloctopus.Ruleset;
import beryloctopus.User;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.nio.charset.StandardCharsets;

public class GroupPost extends Post {
    private String rules;

    public GroupPost(String parentPath, String title, User author, byte[] rawPostContent, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        super(parentPath, title, author, rawPostContent, "bkc/group", timestamp, postRepository, userRepository);
        this.rules = new String(rawPostContent, StandardCharsets.UTF_8);
    }

    public String getRules() {
        return rules;
    }
}
