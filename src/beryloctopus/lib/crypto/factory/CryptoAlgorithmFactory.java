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
package beryloctopus.lib.crypto.factory;

/**
 * @author Tootoot222
 */
public class CryptoAlgorithmFactory {
    public static final String DEFAULT_SIGNATURE_ALGORITHM = "SHA256withECDSA";
    public static final String DEFAULT_KEYPAIR_ALGORITHM = "EC";
    public static final int DEFAULT_KEYPAIR_ALGORITHM_BITS = 256;
    public static final String DEFAULT_RNG_ALGORITHM = "SHA1PRNG";

    public static String getKeypairAlgorithm() {
        return (DEFAULT_KEYPAIR_ALGORITHM);
    }

    public static int getKeypairAlgorithmBits() {
        return (DEFAULT_KEYPAIR_ALGORITHM_BITS);
    }

    public static String getRandomNumberGeneratorAlgorithm() {
        return (DEFAULT_RNG_ALGORITHM);
    }

    public static String getSignatureAlgorithm() {
        return (DEFAULT_SIGNATURE_ALGORITHM);
    }
}
