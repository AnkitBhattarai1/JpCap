package org.jpcap.Core.Packets.DNS;

import org.jpcap.Core.Constants.NamedCodes.DnsCodes.DnsResourceRecordType;
import org.jpcap.Core.Utils.ByteOperations;

/**
 * <pre>
 *    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *   /                  DomainName                    /
 *   /                                               /
 *   +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * </pre>
 */
public class SingleDomainNameRR implements DnsRData {

    private final DnsDomainName domainName;

    private final DnsResourceRecordType type;

    private SingleDomainNameRR(SingleDomainNameRRBuilder builder) {
        if (builder == null || builder.domainName == null)
            throw new IllegalArgumentException(
                    "The " + (builder == null ? "cName builder" : "domainName") + "cannot be null");
        this.domainName = builder.domainName;
        this.type = builder.type;
    }

    public DnsDomainName getcName() {
        return domainName;
    }

    @Override
    public int length() {
        return domainName.length();
    }

    @Override
    public byte[] getRawData() {
        return domainName.getRawData();
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

    public static SingleDomainNameRRBuilder Builder(DnsResourceRecordType type) {
        return new SingleDomainNameRRBuilder(type);
    }

    public static SingleDomainNameRRBuilder Builder(byte[] rawData, int offset, int len, DnsResourceRecordType type) {
        return new SingleDomainNameRRBuilder(rawData, offset, len, type);
    }

    public static final class SingleDomainNameRRBuilder {

        private DnsDomainName domainName;
        private boolean sealed = false;
        private DnsResourceRecordType type;

        private SingleDomainNameRRBuilder(DnsResourceRecordType type) {
            // for custom building
            this.type = type;
        }

        private SingleDomainNameRRBuilder(byte[] rawData, int offset, int len, DnsResourceRecordType type) {
            // TODO: To be implemented...
            ByteOperations.validate(rawData, offset, len);
            this.domainName = DnsDomainName.Builder(rawData, offset, len).build();
            this.type = type;
            sealed = true;
        }

        public SingleDomainNameRRBuilder domainName(DnsDomainName domainName) {
            // TODO validation is to be done for cName;
            if (sealed)
                throw new UnsupportedOperationException(
                        "The field domainName cannot be set once it is assigned with rawData..");
            this.domainName = domainName;
            return this;
        }

        public SingleDomainNameRRBuilder type(DnsResourceRecordType type) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "The field type cannot be set once it is assigned with rawData..");

            this.type = type;
            return this;
        }

        public SingleDomainNameRR build() {
            validate(this);
            return new SingleDomainNameRR(this);
        }

        private void validate(SingleDomainNameRRBuilder builder) {
            // TODO To be implemented;
        }

    }
}
