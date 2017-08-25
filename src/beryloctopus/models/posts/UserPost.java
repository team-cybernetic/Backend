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
import beryloctopus.User;
import beryloctopus.repositories.PostRepository;
import beryloctopus.repositories.UserRepository;


/*
 * A type of post classified as 'User'
 * Each user has at least one of these posts associated with them.
 * Altogether, the list of these posts for a particular user will be applied in chronological order
 * to get the current state of the user.
 */
public class UserPost extends Post {
    //The bio of the user
    private String bio;
    //The name of the user (note, not unique)
    private String name;
    //The avatar URL of the user
    private String avatarUrl;
    //The public key of the user (note this can only be set once)
    private String publicKey;

    public UserPost(String parentPath, String title, User author, byte[] rawPostContent, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        super(parentPath, title, author, rawPostContent, "bkc/user", timestamp, postRepository, userRepository);
        this.bio = "";
        this.name = "";
        this.avatarUrl = "";
        this.publicKey = "";
    }

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
