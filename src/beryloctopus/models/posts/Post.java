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
package beryloctopus.models.posts;

import beryloctopus.Ruleset;
import beryloctopus.models.Path;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.util.Set;

/*
 * Represents a generic post
 */
public class Post implements beryloctopus.Post {

    //The post title
    protected String title;
    //The full address to the post
    protected beryloctopus.Path path;
    //The user who authored the post
    protected beryloctopus.User author;
    //The balance of the post in bytes
    protected long value;
    //The size of the post in bytes
    protected long byteSize;
    //The raw bytes of the post's content. Could be text, html, image, etc
    protected byte[] rawPostContent;
    //A timestamp of when the post was created
    protected long timestamp;

    protected String contentType;

    protected PostRepository postRepository;

    public Post(String parentPath, String title, beryloctopus.User author, byte[] rawPostContent, String contentType, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        this.title = title;
        this.path = new Path(parentPath + "/" + sanitizeTitle(title), postRepository);
        this.author = author;
        this.value = 0;
        this.byteSize = rawPostContent.length;
        this.rawPostContent = rawPostContent;
        this.timestamp = timestamp;
        this.contentType = contentType;
        this.postRepository = postRepository;
        postRepository.addPost(this);
    }

    public Post(Path parentPath, String title, beryloctopus.User author, byte[] rawPostContent, String contentType, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        this(parentPath.getFullPath(), title, author, rawPostContent, contentType, timestamp, postRepository, userRepository);
    }

    public Post(Post parent, String title, beryloctopus.User author, byte[] rawPostContent, String contentType, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        this(parent.getFullPath(), title, author, rawPostContent, contentType, timestamp, postRepository, userRepository);
    }

    private static String sanitizeTitle(String title) {
        return (title == null ? null : title.replace("/", ""));
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public beryloctopus.Path getPath() {
        return path;
    }

    @Override
    public beryloctopus.Post getParent() {
        beryloctopus.Path parentPath = path.getParentPath();
        return parentPath != null ? parentPath.getPost() : null;
    }

    @Override
    public beryloctopus.User getAuthor() {
        return author;
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public long getByteSize() {
        return byteSize;
    }

    @Override
    public long getTimestampMillis() {
        return timestamp;
    }

    @Override
    public byte[] getContent() {
        return (rawPostContent);
    }

    @Override
    public Set<beryloctopus.Post> getSubposts() {
        return postRepository.getChildrenPostsByAddress(path.getFullPath());
    }

    @Override
    public String[] getTags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContentType() {
        return (contentType);
    }

    @Override
    public Ruleset getRuleset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addValue(long amount) {
        this.value += amount;
    }

    @Override
    public beryloctopus.Path getParentPath() {
        beryloctopus.Post parent = getParent();
        return (parent != null ? parent.getPath() : null);
    }

    @Override
    public String getParentFullPath() {
        beryloctopus.Post parent = getParent();
        return (parent != null ? parent.getFullPath() : null);
    }

    @Override
    public String getFullPath() {
        beryloctopus.Path path = getPath();
        return (path != null ? path.getFullPath() : null);
    }
}
