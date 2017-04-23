/* 
 * Copyright (C) 2017 Tootoot222
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package beryloctopus.models;

import beryloctopus.lib.Debug;
import beryloctopus.repositories.PostRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Path implements beryloctopus.Path {
    //The part of the address after the last /
    private String lastSegment;
    //The full path of the address
    private String fullPath = "";
    //The first part of the address
    //Allows UserPosts to live at addresses beginning with username
    private String topLevelSegment;
    //The post living at the address
    private beryloctopus.Post post;
    //Direct parent of this path, or null if this path is "/"
    private beryloctopus.Path parent = null;
    //The children of the post at the address
    private Set<beryloctopus.Post> children;

    public Path(String fullPath, PostRepository postRepository) {
        fullPath = fullPath.replace("//", "/");
        if (fullPath.charAt(fullPath.length() - 1) != '/') {
            fullPath = fullPath + "/";
        }
        this.fullPath = fullPath;
        Debug.debug("Full path = %s", fullPath);
        List<String> allSegments = new ArrayList<>(Arrays.asList(fullPath.substring(0, fullPath.length() - 1).split("/")));
        int i = 0;
        for (String s : allSegments) {
            Debug.debug("segment[%d] = \"%s\"", i, s);
            i++;
        }
        this.topLevelSegment = allSegments.get(0);
        Debug.debug("top level segment = \"%s\"", topLevelSegment);
        this.lastSegment = allSegments.remove(allSegments.size() - 1);
        Debug.debug("last segment = \"%s\"", lastSegment);
        this.post = postRepository.getPostByAddress(fullPath);
        if (post != null) {
            Debug.debug("post not null!");
            this.parent = post.getParentPath();
            this.fullPath = post.getFullPath();
            this.children = post.getSubposts();
        }
/*
        if (fullPath.indexOf("user:") == 0) {
            topLevelSegment = "user:";
            this.post = null;
        } else {
            topLevelSegment = "";
            this.post = postRepository.getPostByAddress(fullPath);
        }
*/
/*
        parents = new ArrayList<>();
        if (allSegments.size() > 1) {
            String newFullPath = topLevelSegment + "/" + String.join("/", allSegments);
            parent = new Path(newFullPath, postRepository);
            parents.add(parent);
            parents.addAll(parent.getParents()); //evil
//        } else {
//            parents = new ArrayList<>();
        }
*/
    }

    @Override
    public String getFirstSegment() {
        return (topLevelSegment);
    }

    @Override
    public String getLastSegment() {
        return lastSegment;
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
    public beryloctopus.Path getParentPath() {
        return (parent);
    }

    @Override
    public String getParentFullPath() {
        return (fullPath);
    }

    @Override
    public beryloctopus.Post getPost() {
        return (post);
    }
}
