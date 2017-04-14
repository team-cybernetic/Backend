package beryloctopus.repositories;

import beryloctopus.models.posts.Post;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PostRepository {
    public static Map<String, Post> addressPostMap;

    public PostRepository() {
        addressPostMap = new HashMap<>();
    }

    public Post getPostByAddress(String fullPath) {
        return addressPostMap.get(fullPath);
    }
    public void addPost(String path, Post p) {
        addressPostMap.put(path, p);
    }

    public Set<? extends Post> getChildrenPostsByAddress(String fullPath) {
        Set<Post> toReturn = new HashSet();
        //Return a set of all posts with addresses 1 segment past fullPath
        for(Map.Entry<String, Post> post : addressPostMap.entrySet()) {
            if (post.getKey().substring(0,post.getKey().substring(0,
                    ((post.getKey()).length()-1)).lastIndexOf("/") + 1)
                    .equals(fullPath)) {
                toReturn.add(post.getValue());
            }
        }
        return toReturn;
    }
}
