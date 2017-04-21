package beryloctopus.models.posts;

import beryloctopus.Ruleset;
import beryloctopus.User;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;


/*
 * A type of post classified as 'User'
 * Each user has at least one of these posts associated with them.
 * Altogether, the list of these posts for a particular user will be applied in chronological order
 * to get the current state of the user.
 */
public class UserPost extends Post {
    //The bio of the user
    private String bio;
    //The name of the user (note, not unique)
    private String name;
    //The avatar URL of the user
    private String avatarUrl;
    //The public key of the user (note this can only be set once)
    private String publicKey;

    public UserPost(String parentPath, String title, User author, byte[] rawPostContent, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        super(parentPath, title, author, rawPostContent, "bkc/user", timestamp, postRepository, userRepository);
        this.bio = "";
        this.name = "";
        this.avatarUrl = "";
        this.publicKey = "";
    }

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
