package beryloctopus.models;

import beryloctopus.models.posts.UserPost;

import java.util.List;
import java.util.UUID;

public class User {
    //The user's UUID
    private UUID uuid;
    //The most recent bio for the user
    private String bio;
    //The most recent name for the user
    private String name;
    //The most recent avatar URL for the user
    private String avatarUrl;
    //The wallet associated with the user
    private Wallet wallet;
    //List of UserPosts that represent revisions to the User overtime.
    //The last one is the most recent.
    private List<UserPost> userRevisions;

    public User(UUID uuid, List<UserPost> userRevisions) {
        this.uuid = uuid;
        this.userRevisions = userRevisions;
        for (UserPost userPost : userRevisions) {
            applyUserPostData(userPost);
        }
    }

    private void applyUserPostData(UserPost post) {
        if (post.getBio() != null) {
            bio = post.getBio();
        }
        if (post.getName() != null) {
            name = post.getName();
        }
        if (post.getAvatarUrl() != null) {
            avatarUrl = post.getAvatarUrl();
        }
        if (post.getPublicKey() != null) {
            wallet = new Wallet(post.getPublicKey());
        }
    }
}
