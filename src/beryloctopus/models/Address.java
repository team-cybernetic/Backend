package beryloctopus.models;

import beryloctopus.Path;
import beryloctopus.Post;
import beryloctopus.repositories.PostRepository;

import java.util.*;

public class Address implements Path {
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
        if (fullPath.charAt(fullPath.length() - 1) != '/') {
            //fullPath = fullPath.substring(0, fullPath.length() - 1);
            fullPath = fullPath + "/";
        }
        this.fullPath = fullPath;
        this.children = postRepository.getChildrenPostsByAddress(fullPath);
        List<String> allSegments = new ArrayList<>(Arrays.asList(fullPath.substring(0, fullPath.length() - 1).split("/")));
        lastSegment = allSegments.remove(allSegments.size() - 1);
        /*
        if (fullPath.indexOf("user:") == 0) {
            topLevelSegment = "user:";
            this.post = null;
        } else {
*/
            topLevelSegment = "";
            this.post = postRepository.getPostByAddress(fullPath);
//        }
        parents = new ArrayList<>();
        if (allSegments.size() > 1) {
            String newFullPath = topLevelSegment + "/" + String.join("/", allSegments);
            Address directParent = new Address(newFullPath, postRepository);
            parents.add(directParent);
            parents.addAll(directParent.parents);
//        } else {
//            parents = new ArrayList<>();
        }
    }

    public List<Address> getParents() {
        return parents;
    }

    public String getLastSegment() {
        return lastSegment;
    }

    public Set<? extends Post> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return fullPath;
    }

    @Override
    public String getFullPath() {
        return (fullPath);
    }

    @Override
    public String getParent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Path getParentPath() {
        return (parents.size() > 0 ? parents.get(0) : null);
    }

    @Override
    public beryloctopus.Post getPost() {
        return post;
    }
}
