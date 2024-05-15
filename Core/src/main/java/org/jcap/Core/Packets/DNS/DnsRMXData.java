package org.jcap.Core.Packets.DNS;

import org.jcap.Core.Utils.ByteOperations;

public class DnsRMXData implements DnsRData {

    private final short preference;
    private final DnsDomainName exchange;

    private DnsRMXData(DnsRMXDataBuilder builder) {
        // TODO Validation is to be done
        this.exchange = builder.exchange;
        this.preference = builder.preference;
    }

    public static final class DnsRMXDataBuilder {

        private Short preference;
        private DnsDomainName exchange;
        private boolean sealed;

        private DnsRMXDataBuilder() {
        }

        private DnsRMXDataBuilder(byte[] rawData, int offset, int len) {
            ByteOperations.validate(rawData, offset, len);
            // TODO Implentaion is remaining
            this.sealed = true;
        }

        public DnsRMXDataBuilder preference(Short value) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "The preference cannot be set again once it is initialized with rawdata");

            this.preference = value;
            return this;
        }

        public DnsRMXDataBuilder exchange(DnsDomainName name) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "The exchange cannot be set again once it is initialized with rawdata");

            this.exchange = name;
            return this;
        }

        public DnsRMXData build() {
            validate(this);
            return new DnsRMXData(this);
        }

        private void validate(DnsRMXDataBuilder dnsRMXDataBuilder) {
            // TODO Validation is to be done ....
        }
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

    public Short getPreference() {
        return this.preference;
    }

    public DnsDomainName getExchange() {
        return this.exchange;
    }
}
