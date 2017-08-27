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

import java.util.Set;

/**
 * @author Tootoot222
 */
public interface Post extends Destination {
    String getTitle();

    String[] getTags();

    byte[] getContent();

    String getContentType();

    Path getPath();

    User getAuthor();

    long getValue();

    long getByteSize();

    long getTimestampMillis();

    Set<Post> getSubposts();

    Ruleset getRuleset();

    Post getParent();

    Path getParentPath();

    String getParentFullPath();

    void addValue(long amount);
}
