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

import java.nio.charset.StandardCharsets;

public class TextPost extends Post {
    private String textContent;

    public TextPost(String parentPath, String title, User author, String body, long timestamp, PostRepository postRepository, UserRepository userRepository) {
        super(parentPath, title, author, body.getBytes(), "text/plain", timestamp, postRepository, userRepository);
        this.textContent = new String(rawPostContent, StandardCharsets.UTF_8);
    }

    public String getTextContent() {
        return textContent;
    }
}
