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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Wallet implements beryloctopus.Wallet {
    private Set<beryloctopus.Address> addresses = new HashSet<>();
    protected long balance;

    public Wallet() {
        this(null);
    }

    public Wallet(Set<beryloctopus.Address> addresses) {
        if (addresses != null) {
            this.addresses.addAll(addresses);
        }
        this.balance = 0;
        for (beryloctopus.Address addr : addresses) {
            this.balance += addr.getBalance();
        }
    }

    @Override
    public long getBalance() {
        return (balance);
    }

    @Override
    public Set<beryloctopus.Address> getAllAddresses() {
        return (Collections.unmodifiableSet(addresses));
    }

    @Override
    public void addAddress(beryloctopus.Address address) {
        if (address != null) {
            if (!addresses.contains(address)) {
                balance += address.getBalance();
                addresses.add(address);
            }
        }
    }
}
