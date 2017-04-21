package beryloctopus.models.posts;

import beryloctopus.Ruleset;
import beryloctopus.User;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.nio.charset.StandardCharsets;

public class TextPost extends Post {
    private String textContent;

    public TextPost(String parentPath, String title, User author, String body, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        super(parentPath, title, author, body.getBytes(), "text/plain", timestamp, postRepository, userRepository);
        this.textContent = new String(rawPostContent, StandardCharsets.UTF_8);
    }

    public String getTextContent() {
        return textContent;
    }
}
