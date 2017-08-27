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

import beryloctopus.lib.Debug;
import beryloctopus.models.posts.TextPost;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Pattern;

/**
 * @author Tootoot222
 */
public class BerylOctopusDummy1 implements BerylOctopus {

    private Path curPath;

    private String pathSeparator = "/";

    private PostRepository postRepository;
    private UserRepository userRepository;
    private UserIdentity curUser;

    public BerylOctopusDummy1() throws NoSuchAlgorithmException {
        this.curUser = new beryloctopus.models.UserIdentity();
        postRepository = new PostRepository();
        Debug.debug("creating /");
        postRepository.addPost(new TextPost("", "", curUser, "This is the root", System.currentTimeMillis(), postRepository, userRepository));
        Debug.debug("creating /hello/");
        postRepository.addPost(new TextPost("/", "hello", curUser, "This is the hello group", System.currentTimeMillis(), postRepository, userRepository));
        Debug.debug("creating /hello/world/");
        postRepository.addPost(new TextPost("/hello", "world", curUser, "This is the world. Deal with it", System.currentTimeMillis(), postRepository, userRepository));
        Debug.debug("creating /hello/world/existing post");
        postRepository.addPost(new TextPost("/hello/world", "existing post", curUser, "This an example of a post which already exists in the system with text that gets rediculously long and then it should push off the end of the world", System.currentTimeMillis(), postRepository, userRepository));
        this.curPath = new beryloctopus.models.Path("/hello/world/", postRepository);
    }

    @Override
    public Path setCurrentPath(String newPath) throws Exception {
        if (!newPath.endsWith(pathSeparator)) {
            newPath = newPath + pathSeparator;
        }
        //TODO: sanitize path
        this.curPath = new beryloctopus.models.Path(newPath, postRepository);
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
    public Post createPost(beryloctopus.Path path, String title, byte[] content, String contentType, User author) {
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

    @Override
    public UserIdentity createUserIdentity(PublicKey pubkey, PrivateKey privkey) {
        return (new beryloctopus.models.UserIdentity(pubkey, privkey));
    }

    @Override
    public UserIdentity createUserIdentity(byte[] pubkey, byte[] privkey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return (new beryloctopus.models.UserIdentity(pubkey, privkey));
    }

    @Override
    public long getValueLocal(ValueHolder holder) {
        return (123);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getValueLocal(ValueHolder holder, Path path) {
        return (432);
    }

    @Override
    public long getValueLocal(ValueHolder holder, String fullPath) {
        return (543);
    }

    @Override
    public long getValueGlobal(ValueHolder holder) {
        return (getValueLocal(holder, "/"));
    }
}
