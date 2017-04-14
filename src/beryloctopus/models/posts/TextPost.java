package beryloctopus.models.posts;

import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TextPost extends Post {
    private String textContent;

    public TextPost(String title, String path, UUID authorUUID, String body, long timestamp, String dateTime, PostRepository postRepository, UserRepository userRepository) {
        super(title, path, authorUUID, body.getBytes(), timestamp, dateTime, postRepository, userRepository);
        this.textContent = new String(rawPostContent, StandardCharsets.UTF_8);
    }

    public String getTextContent() {
        return textContent;
    }
}
