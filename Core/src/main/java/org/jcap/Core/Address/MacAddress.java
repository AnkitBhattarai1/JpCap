package org.jcap.Core.Address;

/**
 * MacAddress
 */
public class MacAddress extends LinkLayerAddress {

    public static final int SIZE_IN_BYTES = 6;

    public MacAddress(byte[] address) {
        super(address);
        // TODO Auto-generated constructor stub
    }

    public static final MacAddress ETHER_BORADCAST_ADDRESS = new MacAddress(
            new byte[] { (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255 });

}
