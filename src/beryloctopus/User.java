/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beryloctopus;

import java.security.PublicKey;

/**
 *
 * @author Tootoot222
 */
public interface User {
    public String getUsername();
    public PublicKey getPublicKey();
    public boolean verifyMessage(byte[] message, byte[] signature);
}
