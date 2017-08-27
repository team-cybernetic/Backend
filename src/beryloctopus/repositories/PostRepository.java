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
        for (Map.Entry<String, Post> post : addressPostMap.entrySet()) {
            Debug.debug("  \"%s\": %s", post.getKey(), post.getValue());
        }
    }

    public void addPost(Post p) {
        String fullPath = p.getPath().getFullPath();
        if (!fullPath.endsWith("/")) {
            fullPath = fullPath + "/";
        }
        Debug.debug("adding post \"%s\" [%d]", fullPath, fullPath.length());
        addressPostMap.put(fullPath, p);
        printAll();
    }

    public void addPost(beryloctopus.models.posts.Post p) {
        addPost((Post) p); //not sure why this method is needed at all
        //addressPostMap.put(p.getPath().getFullPath(), p);
    }

    public Set<Post> getChildrenPostsByAddress(String fullPath) {
        Set<Post> toReturn = new HashSet<>();
        if (fullPath == null || fullPath.length() == 0) {
            return (toReturn);
        }
        if (!fullPath.endsWith("/")) {
            fullPath = fullPath + "/";
        }
        //Return a set of all posts with addresses 1 segment past fullPath
        for (Map.Entry<String, Post> post : addressPostMap.entrySet()) {
            String key = post.getKey(); //relies on the key ending with a /
            if (key == null || key.length() == 0) {
                continue;
            }
            int parentSep = key.lastIndexOf("/", key.length() - 2) + 1;
            if (parentSep == -1) {
                continue;
            }
            String parent = key.substring(0, parentSep);
            if (parent.equals(fullPath)) {
                toReturn.add(post.getValue());
            }
        }
        return toReturn;
    }
}
