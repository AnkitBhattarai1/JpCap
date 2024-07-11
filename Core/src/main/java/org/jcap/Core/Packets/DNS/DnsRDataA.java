package org.jcap.Core.Packets.DNS;

import java.net.Inet4Address;
import java.nio.ByteOrder;

import org.jcap.Core.Utils.InetConverter;

/*
 *  +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    ADDRESS                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 */
public class DnsRDataA implements DnsRData {

    private Inet4Address address;

    private boolean addressPlainText;

    private DnsRDataA(DnsRDataABuilder builder) {
        // TODO Validation is to be done ...
        // this.address = builder.address;
        System.out.println("The DnsRDataA is build successfully");
    }

    @Override
    public int length() {
        if (addressPlainText) {
            return address.getHostAddress().length();
        } else {
            return Integer.BYTES;
        }
    }

    @Override
    public byte[] getRawData() {
        byte[] rawData = new byte[length()];
        // TODO To be implemented later......
        return rawData;
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

    public static DnsRDataABuilder Builder() {
        return new DnsRDataABuilder();
    }

    public static DnsRDataABuilder Builder(byte[] rawData, int offset, int len) {
        return new DnsRDataABuilder(rawData, offset, len);
    }

    public static final class DnsRDataABuilder implements DnsRDataBuilder {
        Inet4Address address;
        private boolean sealed;

        public DnsRDataABuilder() {

        }

        public DnsRDataABuilder(byte[] rawData, int offset, int len) {
            if (len < Byte.BYTES * 4)
                throw new IllegalArgumentException("The data is not sufficient");

            if (len == Byte.BYTES * 4) {
                this.address = InetConverter.toInet4Address(rawData, offset, ByteOrder.BIG_ENDIAN);
                // TODO To be continued
            }

            this.sealed = true;
        }

        public DnsRDataABuilder address(Inet4Address address) {
            if (sealed)
                throw new UnsupportedOperationException("The address cannot be set again ");

            this.address = address;
            this.sealed = true;
            return this;
        }

        public DnsRData build() {
            validate(this);
            return new DnsRDataA(this);
        }

        private void validate(DnsRDataABuilder dnsRDataABuilder) {
            // TODO Validation is to be done
        }
    }

    public Inet4Address getAddress() {
        return this.address;
    }
}
