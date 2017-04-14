package beryloctopus.models.posts;

import beryloctopus.models.Address;
import beryloctopus.models.User;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.util.Set;
import java.util.UUID;

/*
 * Represents a generic post
 */
public abstract class Post {
    //The post title. Is converted to lowercase and spaces replaced with '-' to create the address
    protected String title;
    //The full address to the post
    protected Address address;
    //The user who authored the post
    protected User author;
    //The balance of the post
    protected double value;
    //The size of the post's content
    protected long byteSize;
    //The raw bytes of the post's content. Could be text or an image
    protected byte[] rawPostContent;
    //A timestamp of when the post was created
    protected long timestamp;
    
    protected String dateTime;

    public Post(String title, String path, UUID authorUUID, byte[] rawPostContent, long timestamp, String dateTime, PostRepository postRepository, UserRepository userRepository) {
        this.title = title;
        this.address = new Address(path + "/" + title.toLowerCase().replace(" ", "-"), postRepository);
        this.author = userRepository.getUserByUUID(authorUUID, postRepository);
        this.value = 0;
        this.byteSize = rawPostContent.length;
        this.rawPostContent = rawPostContent;
        this.timestamp = timestamp;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public Address getAddress() {
        return address;
    }

    public User getAuthor() {
        return author;
    }

    public double getValue() {
        return value;
    }

    public long getByteSize() {
        return byteSize;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
    public String getDate() {
        return dateTime;
    }

    public Set<Post> getSubposts() {
        return address.getChildren();
    }
}
