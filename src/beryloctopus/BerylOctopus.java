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

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Tootoot222
 */
public interface BerylOctopus {
    /**
     * Attempts to navigate into the given path. Similar to the ``cd'' command.
     * Possible exceptions encountered:
     * bad path
     *
     * @param newPath
     * @return Returns the new current path
     * @throws Exception TODO: make proper exceptions
     */
    Path setCurrentPath(String newPath) throws Exception;

    /**
     * Gets the current path location
     *
     * @return The current path location
     */
    Path getCurrentPath();

    /**
     * Returns the current path location as an array of subdirectories
     *
     * @return An array of String, where each element is a subdirectory
     */
    String[] getCurrentPathArray();

    String getPathSeparator();

    /**
     * Gets the post at the current Path
     *
     * @return The post
     */
    Post getCurrentPost();

    Post getPostAt(String fullPath);

    Post createPost(Path path, String title, byte[] content, String contentType, User author);

    Post createPost(String fullPath, String title, byte[] content, String contentType, User author);

    void tipPost(Post post, long amount);

    UserIdentity createUserIdentity(PublicKey pubkey, PrivateKey privkey);

    UserIdentity createUserIdentity(byte[] pubkey, byte[] privkey) throws InvalidKeySpecException, NoSuchAlgorithmException;

    /**
     * Gets value held at current address
     *
     * @param holder
     * @return
     */
    long getValueLocal(ValueHolder holder);

    long getValueLocal(ValueHolder holder, Path path);

    long getValueLocal(ValueHolder holder, String fullPath);

    long getValueGlobal(ValueHolder holder);
}
