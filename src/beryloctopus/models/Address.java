package beryloctopus.models;

import beryloctopus.models.posts.Post;
import beryloctopus.repositories.PostRepository;

import java.util.*;

public class Address {
    //The part of the address after the last /
    private String lastSegment;
    //The full path of the address
    private String fullPath;
    //The first part of the address
    //Allows UserPosts to live at addresses beginning with user:
    private String topLevelSegment;
    //The post living at the address
    private Post post;
    //The list of parents. The first in the list is the direct parent.
    private List<Address> parents;
    //The children of the post at the address
    private Set<Post> children;

    public Address(String fullPath, PostRepository postRepository) {
        fullPath = fullPath.replace("//", "/");
        if (fullPath.lastIndexOf("/") == fullPath.length() - 1) {
            fullPath = fullPath.substring(0, fullPath.length() - 1);
        }
        this.fullPath = fullPath;
        this.children = (Set<Post>) postRepository.getChildrenPostsByAddress(fullPath);
        List<String> allSegments = new ArrayList<String>(Arrays.asList(fullPath.split("/")));
        lastSegment = allSegments.remove(allSegments.size() - 1);
        if (fullPath.indexOf("user:") == 0) {
            topLevelSegment = "user:";
            this.post = null;
        } else {
            topLevelSegment = "";
            this.post = postRepository.getPostByAddress(fullPath);
        }
        if (!allSegments.isEmpty()) {
            String newFullPath = topLevelSegment + "/" + String.join("/", allSegments);
            Address directParent = new Address(newFullPath, postRepository);
            parents = new ArrayList<>();
            parents.add(directParent);
            parents.addAll(directParent.parents);
        } else {
            parents = new ArrayList<>();
        }
    }

    public List<Address> getParents() {
        return parents;
    }

    public String getLastSegment() {
        return lastSegment;
    }

    public Set<Post> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return fullPath;
    }
}
