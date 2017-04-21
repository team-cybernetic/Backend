package beryloctopus.repositories;

import beryloctopus.Post;
import beryloctopus.lib.Debug;

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

    private void printAll() {
        Debug.debug("This PostRepository contains:");
        for(Map.Entry<String, Post> post : addressPostMap.entrySet()) {
            Debug.debug("  \"%s\": %s", post.getKey(), post.getValue());
        }
    }

    public void addPost(Post p) {
        String fullPath = p.getPath().getFullPath();
        Debug.debug("adding post \"%s\" [%d]", fullPath, fullPath.length());
        printAll();
        addressPostMap.put(fullPath, p); //TODO: ensure that all keys end with /
    }

    public void addPost(beryloctopus.models.posts.Post p) {
        addPost((Post)p); //not sure why this method is needed at all
        //addressPostMap.put(p.getPath().getFullPath(), p);
    }

    public Set<Post> getChildrenPostsByAddress(String fullPath) {
        Set<Post> toReturn = new HashSet<>();
        if (fullPath == null || fullPath.length() == 0) {
            return (toReturn);
        }
        if (fullPath.charAt(fullPath.length() - 1) != '/') {
            fullPath = fullPath + "/";
        }
        //Return a set of all posts with addresses 1 segment past fullPath
        for(Map.Entry<String, Post> post : addressPostMap.entrySet()) {
            String key = post.getKey(); //relies on the key ending with a /
            Debug.debug("testing key \"%s\" len %d", key, key.length());
            if (key == null || key.length() == 0) {
                Debug.debug("nope");
                continue;
            }
            int parentSep = key.lastIndexOf("/", key.length() - 2) + 1;
            Debug.debug("testing parentSep %d", parentSep);
            if (parentSep == -1) {
                Debug.debug("nope");
                continue;
            }
            String parent = key.substring(0, parentSep);
            Debug.debug("testing key \"%s\" (from \"%s\") vs request \"%s\" = %b", parent, key, fullPath, parent.equals(fullPath));
            if (parent.equals(fullPath)) {
                Debug.debug("yep! \"%s\" is a child of \"%s\"!", key, fullPath);
                toReturn.add(post.getValue());
            }
        }
        return toReturn;
    }
}
