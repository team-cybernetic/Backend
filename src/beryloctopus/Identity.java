/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beryloctopus;

import java.security.PrivateKey;

/**
 *
 * @author Tootoot222
 */
public interface Identity extends User {
    public PrivateKey getPrivateKey();
    public byte[] signMessage(byte[] message);
}
