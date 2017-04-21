package beryloctopus.repositories;

import beryloctopus.models.User;
import beryloctopus.models.posts.UserPost;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {

    /*
    public User getUserByUUID(UUID userUUID, PostRepository p) {
        Set<UserPost> userPosts = (Set<UserPost>) p.getChildrenPostsByAddress("user:" + userUUID.toString());
        List<UserPost> sortedPosts = new ArrayList<>(userPosts).stream().sorted((UserPost o1, UserPost o2) -> (int) (o1.getTimestamp() - o2.getTimestamp())).collect(Collectors.toList());
        return new User(userUUID, sortedPosts);
    }
*/
}
