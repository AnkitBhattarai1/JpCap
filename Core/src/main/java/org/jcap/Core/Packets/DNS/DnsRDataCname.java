package org.jcap.Core.Packets.DNS;

import org.jcap.Core.Utils.ByteOperations;

/**
 * DnsDataCname
 * 
 */
/*
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * /--------------------CNAME----------------------/
 * /-----------------------------------------------/
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 */
public class DnsRDataCname implements DnsRData {

    private final DnsDomainName cName;

    private DnsRDataCname(DnsDataCnameBuilder builder) {
        if (builder == null || builder.cName == null)
            throw new IllegalArgumentException(
                    "The " + (builder == null ? "cName builder" : "domainName") + "cannot be null");
        this.cName = builder.cName;
    }

    public DnsDomainName getcName() {
        return cName;
    }

    @Override
    public int length() {
        return cName.length();
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

    public static DnsDataCnameBuilder Builder() {
        return new DnsDataCnameBuilder();
    }

    public static DnsDataCnameBuilder Builder(byte[] rawData, int offset, int len) {
        return new DnsDataCnameBuilder(rawData, offset, len);
    }

    public static final class DnsDataCnameBuilder {

        private DnsDomainName cName;
        private boolean sealed = false;

        private DnsDataCnameBuilder() {
            // for custom building
        }

        private DnsDataCnameBuilder(byte[] rawData, int offset, int len) {
            // TODO: To be implemented...
            ByteOperations.validate(rawData, offset, len);
            this.cName = DnsDomainName.Builder(rawData, offset, len).build();
            sealed = true;
        }

        public DnsDataCnameBuilder cName(DnsDomainName cName) {
            // TODO validation is to be done for cName;
            this.cName = cName;
            return this;
        }

        public DnsRDataCname build() {
            validate(this);
            return new DnsRDataCname(this);
        }

        private void validate(DnsDataCnameBuilder builder) {
            // TODO To be implemented;
        }

    }

}
