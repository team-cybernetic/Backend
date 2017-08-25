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
 *
 * @author Tootoot222
 */
public interface Post extends Destination {
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
    public Path getParentPath();
    public String getParentFullPath();
    void addValue(long amount);
}
