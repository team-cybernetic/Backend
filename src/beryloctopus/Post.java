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
    public String[] getTags();
    public byte[] getContent();
    public String getContentType();
    public Path getPath();
    public User getAuthor();
    public long getValue();
    public long getByteSize();
    public long getTimestampMillis();
    public Set<Post> getSubposts();
    public Ruleset getRuleset();
    public Post getParent();
    void addValue(long amount);
}
