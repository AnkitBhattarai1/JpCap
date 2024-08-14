package org.jcap.Core.Address;

import java.util.regex.Pattern;

import org.jcap.Core.Utils.ByteOperations;

public class LinkLayerAddress {

    public byte[] address;

    protected static final Pattern HEX_SEPARATOR_PATTERN = Pattern.compile("([^0-9a-fA-F])");

    public LinkLayerAddress(byte[] address) {
        this.address = address;
    }

    public LinkLayerAddress(String address, String separator) {
        this.address = ByteOperations.getByteArray(address, separator);
    }

    public byte[] getAddress() {
        return address;
    }

    public int length() {
        return address.length;
    }

    @Override
    public String toString() {
        return ByteOperations.toHexString(address, ":", 0, address.length);

    }

}
