package org.jcap.Core.Packets.DNS;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import org.jcap.Core.Utils.ByteOperations;
import org.jcap.Core.Utils.InetConverter;

/**
 * 
 * <pre>
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    ADDRESS                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+

where:

ADDRESS         A 32 bit Internet address.

Hosts that have multiple Internet addresses will have multiple A
records.
 * </pre>
 * 
 * 
 */
public class DnsRDataA implements DnsRData {

    private Inet4Address address;

    private boolean addressPlainText;

    private DnsRDataA(DnsRDataABuilder builder) {
        // TODO Validation is to be done ...
        this.address = builder.address;
        this.addressPlainText = builder.addressPlainText;
        System.out.println("The DnsRDataA is successfully created");
    }

    public static DnsRDataABuilder Builder() {
        return new DnsRDataABuilder();
    }

    public static DnsRDataABuilder Builder(byte[] rawData, int offset, int len) {
        return new DnsRDataABuilder(rawData, offset, len);
    }

    public Inet4Address getAddress() {
        return this.address;
    }

    public boolean isAddressPlainText() {
        return this.addressPlainText;
    }

    public String getAddressString() {
        return address.getHostAddress();
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
        if (addressPlainText) {
            return address.getHostAddress().getBytes();
        } else {
            return address.getAddress();
        }
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

    public static final class DnsRDataABuilder implements DnsRDataBuilder {
        Inet4Address address;
        private boolean addressPlainText;
        private boolean sealed;

        public DnsRDataABuilder() {// For custom building of the DnsRDataA..

        }

        public DnsRDataABuilder(byte[] rawData, int offset, int len) {

            if (len < Byte.BYTES * 4)
                throw new IllegalArgumentException("The data is not sufficient");

            if (len == Byte.BYTES * 4) {
                this.address = InetConverter.toInet4Address(rawData, offset, ByteOrder.BIG_ENDIAN);
                this.addressPlainText = false;
            }

            else {
                String addr = new String(ByteOperations.getSubArray(rawData, offset, len));
                try {
                    byte[] octets = ByteOperations.parseInet4Address(addr);
                    this.address = (Inet4Address) Inet4Address.getByAddress(octets);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("The address is not valid");

                } catch (UnknownHostException e) {
                    throw new AssertionError("Never get here.");
                }
                this.addressPlainText = true;
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

        public DnsRDataABuilder addressPlainText(boolean addressPlainText) {
            this.addressPlainText = addressPlainText;
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
}
