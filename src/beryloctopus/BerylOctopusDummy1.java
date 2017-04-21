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
package beryloctopus;

import beryloctopus.models.Address;
import beryloctopus.models.posts.TextPost;
import beryloctopus.Post;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;
import java.util.regex.Pattern;

/**
 *
 * @author Tootoot222
 */
public class BerylOctopusDummy1 implements BerylOctopus {

    private Path curPath;

    private String pathSeparator = "/";

    private PostRepository postRepository;
    private UserRepository userRepository;
    private User curUser;

    public BerylOctopusDummy1() {
        this.curUser = new beryloctopus.models.User("123SampleUsername".getBytes());
        postRepository = new PostRepository();
        postRepository.addPost(new TextPost("", "", curUser, "This is the root", System.currentTimeMillis(), postRepository, userRepository));
                            
        postRepository.addPost(new TextPost("/", "hello", curUser, "This is the hello group", System.currentTimeMillis(), postRepository, userRepository));
        postRepository.addPost(new TextPost("/hello", "world", curUser, "This is the world. Deal with it", System.currentTimeMillis(), postRepository, userRepository));
        postRepository.addPost(new TextPost("/hello/world", "existing post", curUser, "This an example of a post which already exists in the system with text that gets rediculously long and then it should push off the end of the world", System.currentTimeMillis(), postRepository, userRepository));
        this.curPath = new Address("/hello/world/", postRepository);

    }

    @Override
    public Path setCurrentPath(String newPath) throws Exception {
        if (!newPath.endsWith(pathSeparator)) {
            newPath = newPath + pathSeparator;
        }
        //TODO: sanitize path
        this.curPath = new Address(newPath, postRepository);
        return (getCurrentPath());
    }

    @Override
    public Path getCurrentPath() {
        return (curPath);
    }

    @Override
    public String[] getCurrentPathArray() {
        return (getCurrentPath().getFullPath().split(Pattern.quote(pathSeparator)));
    }

    @Override
    public String getPathSeparator() {
        return (pathSeparator);
    }

    @Override
    public Post getCurrentPost() {
        return (curPath.getPost());
    }

    @Override
    public Post getPostAt(String fullPath) {
        return (postRepository.getPostByAddress(fullPath));
    }

    @Override
    public Post createPost(Path path, String title, byte[] content, String contentType, User author) {
        return (createPost(path.getFullPath(), title, content, contentType, author));
    }

    @Override
    public Post createPost(String fullPath, String title, byte[] content, String contentType, User author) {
        return (new beryloctopus.models.posts.Post(fullPath, title, author, content, contentType, System.currentTimeMillis(), postRepository, userRepository));
    }

    @Override
    public void tipPost(Post post, long amount) {
        post.addValue(amount);
    }
}
