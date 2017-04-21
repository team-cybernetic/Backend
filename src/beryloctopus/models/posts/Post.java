package beryloctopus.models.posts;

import beryloctopus.Path;
import beryloctopus.Ruleset;
import beryloctopus.models.Address;
import beryloctopus.models.User;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.util.Set;
import java.util.UUID;

/*
 * Represents a generic post
 */
public class Post implements beryloctopus.Post {

    //The post title
    protected String title;
    //The full address to the post
    protected Path path;
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
    
    private static String sanitizeTitle(String title) {
        return (title == null ? null : title.replace("/", ""));
    }

    
    public Post(String parentPath, String title, beryloctopus.User author, byte[] rawPostContent, String contentType, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        this.title = title;
        this.path = (Path)new Address(parentPath + "/" + sanitizeTitle(title), postRepository);
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

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public beryloctopus.Post getParent() {
        Path parentPath = path.getParentPath();
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
}
