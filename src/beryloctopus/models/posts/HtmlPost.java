package beryloctopus.models.posts;

import beryloctopus.Ruleset;
import beryloctopus.User;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.nio.charset.StandardCharsets;

public class HtmlPost extends Post {
    private String htmlContent;

    public HtmlPost(String parentPath, String title, User author, byte[] rawPostContent, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        super(parentPath, title, author, rawPostContent, "text/html", timestamp, postRepository, userRepository);
        this.htmlContent = new String(rawPostContent, StandardCharsets.UTF_8);
    }

    public String getHtmlContent() {
        return htmlContent;
    }
}
