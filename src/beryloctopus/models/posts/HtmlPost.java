package beryloctopus.models.posts;

import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class HtmlPost extends Post {
    private String htmlContent;

    public HtmlPost(String title, String path, UUID authorUUID, byte[] rawPostContent, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        super(title, path, authorUUID, rawPostContent, timestamp, postRepository, userRepository);
        this.htmlContent = new String(rawPostContent, StandardCharsets.UTF_8);
    }

    public String getHtmlContent() {
        return htmlContent;
    }
}
