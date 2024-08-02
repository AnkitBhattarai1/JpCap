package org.jcap.Core.Packets.DNS;

import java.net.Inet4Address;
import java.nio.ByteOrder;

import org.jcap.Core.Constants.NamedCodes.L3.IpNumber;
import org.jcap.Core.Utils.ByteOperations;
import org.jcap.Core.Utils.InetConverter;

/**
 * <pre>
 *  +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *   |                    ADDRESS                    |
 *   +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *   |       PROTOCOL        |                       |
 *   +--+--+--+--+--+--+--+--+                       |
 *   |                                               |
 *   /                   <BIT MAP>                   /
 *   /                                               /
 *   +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *
 * where:

*ADDRESS         An 32 bit Internet address
*
*PROTOCOL        An 8 bit IP protocol number
*
*<BIT MAP>       A variable length bit map.  The bit map must be a
*                multiple of 8 bits long.
 * </pre>
 */

public record DnsRDataWks(Inet4Address address, IpNumber protocol, byte[] bitMap) implements DnsRData {

    public static DnsRDataWksBuilder Builder() {
        return new DnsRDataWksBuilder();
    }

    public static DnsRDataWksBuilder Builder(byte[] rawData, int offset, int length) {
        return new DnsRDataWksBuilder(rawData, offset, length);
    }

    @Override
    public int length() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'length'");
    }

    @Override
    public byte[] getRawData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRawData'");
    }

    @Override
    public String toString(String indent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

    @Override
    public String toString(String indent, byte[] headerRawData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }

    public static final class DnsRDataWksBuilder {
        private Inet4Address address;
        private IpNumber protocol;
        private byte[] bitMap;

        private DnsRDataWksBuilder() {
            // For custom Building of
        }

        private DnsRDataWksBuilder(byte[] rawData, int offset, int length) {
            if (length < Byte.BYTES * 4 + Byte.BYTES)
                throw new IllegalArgumentException("The data is too short to build DnsRDataWks");

            int position = 0;
            this.address = (Inet4Address) InetConverter.toInet4Address(rawData, offset + position,
                    ByteOrder.nativeOrder());
            position += Byte.BYTES * 4;
            // Todo to be done later.....
        }

        public DnsRDataWks build() {
            validate();
            return new DnsRDataWks(this.address, this.protocol, this.bitMap);
        }

        private void validate() {
            // TODO: Validation is to be done ....
        }

        public DnsRDataWksBuilder address(Inet4Address address) {
            // Todo Validation is to be done ....
            this.address = address;
            return this;
        }

        public DnsRDataWksBuilder protocol(IpNumber protocol) {
            this.protocol = protocol;
            return this;
        }

        public DnsRDataWksBuilder bitMap(byte[] bitMap) {
            this.bitMap = bitMap;
            return this;
        }

    }
}