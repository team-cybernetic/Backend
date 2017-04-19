package beryloctopus.repositories;

import beryloctopus.models.posts.Post;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PostRepository {
    private Map<String, Post> addressPostMap;

    public PostRepository() {
        addressPostMap = new HashMap<>();
    }

    public Post getPostByAddress(String fullPath) {
        return addressPostMap.get(fullPath);
    }

    public void addPost(String fullPath, Post post) {
        addressPostMap.put(fullPath, post);
    }

    public Set<? extends Post> getChildrenPostsByAddress(String fullPath) {
        Post p = addressPostMap.get(fullPath);
        if (p == null) {
            return (new HashSet<>());
        }
        return (p.getSubposts());
    }
}
