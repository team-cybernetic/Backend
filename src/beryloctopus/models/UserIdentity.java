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

import beryloctopus.Address;
import beryloctopus.Destination;
import beryloctopus.WalletIdentity;
import beryloctopus.exceptions.InsufficientFundsException;
import beryloctopus.exceptions.NoSuchAddressException;
import beryloctopus.lib.crypto.factory.CryptoAlgorithmFactory;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 *
 * @author Tootoot222
 */
public class UserIdentity extends User implements beryloctopus.UserIdentity {

    private WalletIdentity wallet;
    private PrivateKey privkey;
    private byte[] privEncoded;

    protected final void init(PrivateKey privkey) {
        this.privkey = privkey;
        if (privkey != null) {
            this.privEncoded = privkey.getEncoded();
        } else {
            //TODO: throw exception? it doesn't really make sense for a UserIdentity to exist but not have a private key
            throw (new NullPointerException("NULL private key passed to UserIdentity!"));
        }
    }

    protected final void init(byte[] privkey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (privkey == null) {
            init((PrivateKey)null);
        } else {
            KeyFactory keyFactory = KeyFactory.getInstance(CryptoAlgorithmFactory.getKeypairAlgorithm());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privkey);
            PrivateKey privK = keyFactory.generatePrivate(keySpec);
            init(privK);
        }   
    }

    public UserIdentity(PublicKey pubkey, PrivateKey privkey) {
        super(pubkey);
        this.init(privkey);
    }

    public UserIdentity() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(CryptoAlgorithmFactory.getKeypairAlgorithm());
        SecureRandom random = SecureRandom.getInstance(CryptoAlgorithmFactory.getRandomNumberGeneratorAlgorithm());

        keyGen.initialize(CryptoAlgorithmFactory.getKeypairAlgorithmBits(), random);

        KeyPair pair = keyGen.generateKeyPair();
        super.init(pair.getPublic());
        this.init(pair.getPrivate());
    }
    
    public UserIdentity(byte[] pubkey, byte[] privkey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(pubkey);
        init(privkey);
   }

    @Override
    public PrivateKey getPrivateKey() {
        return (privkey);
    }

    @Override
    public byte[] getPrivateKeyEncoded() {
        return (privEncoded);
    }

    @Override
    public byte[] signMessage(byte[] message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendValue(Destination dest, long amount) throws InsufficientFundsException {
        wallet.sendValue(dest, amount);
    }

    @Override
    public void sendValue(Address source, Destination dest, long amount) throws InsufficientFundsException, NoSuchAddressException {
        wallet.sendValue(source, dest, amount);
    }

    @Override
    public long getBalance() {
        return (wallet.getBalance());
    }
}
