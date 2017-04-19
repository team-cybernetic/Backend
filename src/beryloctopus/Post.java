/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beryloctopus;

import java.util.Set;

/**
 *
 * @author Tootoot222
 */
public interface Post {
    public String getTitle();

    public String getFullPath();

    public String getAuthorUsername();

    public long getValue();

    public long getByteSize();

    public long getTimestamp();

    public Set<Post> getSubposts();

}
