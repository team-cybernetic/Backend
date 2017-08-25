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

import beryloctopus.exceptions.InsufficientFundsException;
import beryloctopus.exceptions.NoSuchAddressException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Tootoot222
 */
public class WalletIdentity extends Wallet implements beryloctopus.WalletIdentity {

    protected Set<beryloctopus.AddressIdentity> addresses = new HashSet<>();

    public WalletIdentity() {
        this(null);
    }

    public WalletIdentity(Set<beryloctopus.AddressIdentity> addresses) {
        super();
        if (addresses != null) {
            this.addresses.addAll(addresses);
            for (beryloctopus.AddressIdentity addr : addresses) {
                this.balance += addr.getBalance();
            }
        }
    }

    @Override
    public Set<beryloctopus.AddressIdentity> getAllAddressIdentities() {
        return (Collections.unmodifiableSet(addresses));
    }

    @Override
    public Set<beryloctopus.Address> getAllAddresses() {
        return (Collections.unmodifiableSet(addresses));
    }

    @Deprecated
    @Override
    public void addAddress(beryloctopus.Address address) {
        throw new UnsupportedOperationException("Cannot add address without identity to WalletIdentity!");
    }

    @Override
    public void addAddress(beryloctopus.AddressIdentity address) {
        if (address != null) {
            if (!addresses.contains(address)) {
                balance += address.getBalance();
                addresses.add(address);
            }
        }
    }

    @Override
    public long getBalance() {
        return (balance);
    }

    @Override
    public void sendValue(beryloctopus.Destination dest, long amount) throws InsufficientFundsException {
        beryloctopus.AddressIdentity single = null;
        Set<beryloctopus.AddressIdentity> multi = new HashSet<>();
        long multiBalance = 0;
        boolean sufficient = false;
        for (beryloctopus.AddressIdentity addr : addresses) {
            if (addr.getBalance() >= amount) {
                single = addr;
                sufficient = true;
                break;
            } else {
                multi.add(addr);
                multiBalance += addr.getBalance();
                if (multiBalance >= amount) {
                    sufficient = true;
                    break;
                }
            }
        }
        if (!sufficient) {
            throw (new InsufficientFundsException(String.format("Not enough funds to send %d to \"%s\"!", amount, dest.getFullPath())));
        }
        //TODO: create aggregate transactions from multi
        single.sendValue(dest, amount);
    }

    @Override
    public void sendValue(beryloctopus.Address source, beryloctopus.Destination dest, long amount) throws InsufficientFundsException, NoSuchAddressException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
