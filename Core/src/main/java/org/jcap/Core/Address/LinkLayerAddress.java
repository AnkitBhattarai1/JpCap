package org.jcap.Core.Address;

import java.util.regex.Pattern;

public class LinkLayerAddress {

    public byte[] address;

    protected static final Pattern HEX_SEPARATOR_PATTERN = Pattern.compile("([^0-9a-fA-F])");

    public LinkLayerAddress(byte[] address) {
        this.address = address;
    }

}
