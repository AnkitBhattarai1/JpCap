package org.jpcap.Core.Packets.DNS;

import java.net.Inet6Address;

import org.jpcap.Core.Utils.ByteOperations;

public class DnsRDataA6 implements DnsRData {
    private final Inet6Address address;

    private DnsRDataA6(DnsDataA6Builder builder) {
        // TODO Validation is to be done
        this.address = builder.address;
    }

    public static DnsDataA6Builder Builder() {
        return new DnsDataA6Builder();
    }

    public static DnsDataA6Builder Builder(byte[] rawData, int offset, int len) {
        return new DnsDataA6Builder(rawData, offset, len);
    }

    public static final class DnsDataA6Builder {

        private Inet6Address address;

        private boolean sealed = false;

        private DnsDataA6Builder() {
        }

        private DnsDataA6Builder(byte[] rawData, int offset, int len) {
            ByteOperations.validate(rawData, offset, len);
            // TODO implementation is to be done
            this.sealed = true;
        }

        public DnsDataA6Builder address(Inet6Address address) {
            if (sealed)
                throw new UnsupportedOperationException("The address cannot be set once it had been set");
            this.address = address;
            this.sealed = true;
            return this;
        }

        public DnsRDataA6 build() {
            validate(this);
            return new DnsRDataA6(this);
        }

        private void validate(DnsDataA6Builder builder) {
            // TODO Validation is to be implemented
        }
    }

    public Inet6Address getAddress() {
        return this.address;
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
}
