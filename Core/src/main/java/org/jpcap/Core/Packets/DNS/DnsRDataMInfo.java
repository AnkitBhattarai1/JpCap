package org.jpcap.Core.Packets.DNS;

import org.jpcap.Core.Utils.ByteOperations;

/**
 * DNS MINFO RDATA
 *
 * <pre style="white-space: pre;">
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * /                    RMAILBX                    /
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * /                    EMAILBX                    /
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *
 * where:
 *
 * RMAILBX         A &lt;domain-name&gt; which specifies a mailbox which is
 *                 responsible for the mailing list or mailbox.  If this
 *                 domain name names the root, the owner of the MINFO RR is
 *                 responsible for itself.  Note that many existing mailing
 *                 lists use a mailbox X-request for the RMAILBX field of
 *                 mailing list X, e.g., Msgroup-request for Msgroup.  This
 *                 field provides a more general mechanism.
 *
 *
 * EMAILBX         A &lt;domain-name&gt; which specifies a mailbox which is to
 *                 receive error messages related to the mailing list or
 *                 mailbox specified by the owner of the MINFO RR (similar
 *                 to the ERRORS-TO: field which has been proposed).  If
 *                 this domain name names the root, errors should be
 *                 returned to the sender of the message.
 * </pre>
 *
 * @see <a href="https://tools.ietf.org/html/rfc1035">RFC 1035</a>
 */
public class DnsRDataMInfo implements DnsRData {

    private final DnsDomainName RMAILBX;
    private final DnsDomainName EMAILBX;

    private DnsRDataMInfo(DnsRDataMInfoBuilder builder) {
        // TODO Validation is to be done
        this.EMAILBX = builder.EMAILBX;
        this.RMAILBX = builder.RMAILBX;
    }

    private DnsRDataMInfoBuilder Builder() {
        return new DnsRDataMInfoBuilder();
    }

    private DnsRDataMInfoBuilder Builder(byte[] rawData, int offset, int len) {
        ByteOperations.validate(rawData, offset, len);
        return new DnsRDataMInfoBuilder(rawData, offset, len);
    }

    public DnsDomainName getRMailBx() {
        return RMAILBX;
    }

    public DnsDomainName getEmailBx() {
        return EMAILBX;
    }

    @Override
    public int length() {
        return RMAILBX.length() + EMAILBX.length();
    }

    @Override
    public byte[] getRawData() {
        byte[] rawData = new byte[length()];

        byte[] rawRMX = RMAILBX.getRawData();
        System.arraycopy(RMAILBX.getRawData(), 0, rawData, 0, rawRMX.length);

        byte[] rawEMX = EMAILBX.getRawData();
        System.arraycopy(rawEMX, 0, rawData, rawRMX.length, rawEMX.length);

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

    public final static class DnsRDataMInfoBuilder {
        private DnsDomainName RMAILBX;
        private DnsDomainName EMAILBX;

        private boolean sealed;

        private DnsRDataMInfoBuilder() {
        }

        private DnsRDataMInfoBuilder(byte[] rawData, int offset, int len) {
            this.RMAILBX = DnsDomainName.Builder(rawData, offset, len).build();
            if (len == RMAILBX.length())
                throw new IllegalArgumentException("Data is too short to make the MINFO record");

            this.EMAILBX = DnsDomainName.Builder(rawData, offset + RMAILBX.length(), len - RMAILBX.length()).build();
            this.sealed = true;
        }

        public DnsRDataMInfoBuilder RMAILBX(DnsDomainName name) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "The data cannot be set after it is initialized with the rawData");

            this.RMAILBX = name;
            return this;
        }

        public DnsRDataMInfoBuilder EMAILBX(DnsDomainName name) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "The data cannot be set after it is initialized with the rawData");

            this.EMAILBX = name;
            return this;
        }

        public DnsRDataMInfo build() {
            return new DnsRDataMInfo(this);
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((RMAILBX == null) ? 0 : RMAILBX.hashCode());
        result = prime * result + ((EMAILBX == null) ? 0 : EMAILBX.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DnsRDataMInfo other = (DnsRDataMInfo) obj;
        if (RMAILBX == null) {
            if (other.RMAILBX != null)
                return false;
        } else if (!RMAILBX.equals(other.RMAILBX))
            return false;
        if (EMAILBX == null) {
            if (other.EMAILBX != null)
                return false;
        } else if (!EMAILBX.equals(other.EMAILBX))
            return false;
        return true;
    }

    

}
