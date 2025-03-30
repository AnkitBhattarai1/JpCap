package org.jpcap.Core.Address;

import java.nio.ByteOrder;

import org.jpcap.Core.Constants.NamedCodes.L2.Oui;
import org.jpcap.Core.Utils.ByteOperations;

/**
 * MacAddress
 */
public class MacAddress extends LinkLayerAddress {

    public static final int SIZE_IN_BYTES = 6;

    public static final MacAddress ETHER_BORADCAST_ADDRESS = new MacAddress(
            new byte[] { (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255, (byte) 255 });

    private MacAddress(byte[] address) {
        super(address);
    }

    public static MacAddress getByAddress(byte[] address, int offset) {
        return getByAddress(address, offset, ByteOrder.BIG_ENDIAN);
    }

    public static MacAddress getByAddress(byte[] address, int offset, ByteOrder bo) {

        ByteOperations.validate(address, offset, SIZE_IN_BYTES);

        if (bo == null)
            throw new NullPointerException("bo " + bo);

        if (bo.equals(ByteOrder.LITTLE_ENDIAN))
            return new MacAddress(ByteOperations.reverse(ByteOperations.getSubArray(address, offset, SIZE_IN_BYTES)));
        else
            return new MacAddress(ByteOperations.getSubArray(address, offset, SIZE_IN_BYTES));

    }

    public static MacAddress getByAddress(byte[] address) {

        if (address.length != SIZE_IN_BYTES)
            throw new IllegalArgumentException("The length of the address must be " + SIZE_IN_BYTES);

        return new MacAddress(address);
    }

    public MacAddress(String address, String separator) {
        super(address, separator);
    }

    /** @return OUI */
    public Oui getOui() {
        // return Oui.getInstance(ByteOperations.getInt(getAddress(), 0) >>> 8);
        return null;
    }

    /**
     * @return true if the MAC address represented by this object is a unicast
     *         address; otherwise
     *         false.
     */
    public boolean isUnicast() {
        return (getAddress()[0] & 1) == 0;
    }

    /**
     * @return true if the MAC address represented by this object is a globally
     *         unique address;
     *         otherwise false.
     */
    public boolean isGloballyUnique() {
        return (getAddress()[0] & 2) == 0;
    }

    public byte[] getAddress() {
        return address;
    }

}
