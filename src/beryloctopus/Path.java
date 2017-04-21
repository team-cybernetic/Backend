/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beryloctopus;

/**
 *
 * @author Tootoot222
 */
public interface Path {
    public String getFullPath();
    public String getLastSegment();
    public String getParent();
    public Path getParentPath();
    public Post getPost();
}
