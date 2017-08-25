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
package beryloctopus.models;

import beryloctopus.lib.AlphaNumeric64;
import beryloctopus.lib.crypto.factory.CryptoAlgorithmFactory;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;


public class User implements beryloctopus.User {
    //The user's UUID
    protected PublicKey pubkey;
    //The most recent name for the user
    protected String name;
     //The wallet associated with the user
    protected Wallet wallet;

    public static final int IDENTITY_LENGTH = 91; 
    public static final User ANY = new User((PublicKey) null);

    private byte[] pubEncoded;

    protected final void init(PublicKey pubkey) {
        this.pubkey = pubkey;
        if (pubkey != null) {
            this.pubEncoded = pubkey.getEncoded();
            this.name = pubkeyToUsername(pubkey);
        } else {
            this.pubEncoded = ByteBuffer.allocate(IDENTITY_LENGTH).array();;
            this.name = "<ANY>";
        }   
    }

    protected void init(byte[] pubkey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (isPubkeyEmpty(pubkey)) {
            init((PublicKey)null);
        } else {
            KeyFactory keyFactory = KeyFactory.getInstance(CryptoAlgorithmFactory.getKeypairAlgorithm());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubkey);
            PublicKey pubK = keyFactory.generatePublic(keySpec);
            init(pubK);
        }   
    }

    protected User() {
        this.pubkey = null;
        this.pubEncoded = null;
        this.wallet = null;
        this.name = "<unset>";
    }   

    public User(PublicKey pubkey) {
        init(pubkey);
    }   

    public User(byte[] pubkey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        init(pubkey);
    }   
    
    public static boolean isPubkeyEmpty(byte[] pubkey) {
        return (pubkey == null || Arrays.equals(new byte[IDENTITY_LENGTH], pubkey));
    }

    protected String pubkeyToUsername(PublicKey pubkey) {
        ECPublicKey ecpub = (ECPublicKey)pubkey;
        ECPoint w = ecpub.getW();
        return (AlphaNumeric64.toAlphaNumeric64(w.getAffineX().toByteArray()));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public PublicKey getPublicKey() {
        return (pubkey);
    }

    @Override
    public byte[] getPublicKeyEncoded() {
        return (pubEncoded);
    }

    @Override
    public boolean verifyMessage(byte[] message, byte[] signature) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getFullPath() {
        return (getUsername() + "/");
    }
}
