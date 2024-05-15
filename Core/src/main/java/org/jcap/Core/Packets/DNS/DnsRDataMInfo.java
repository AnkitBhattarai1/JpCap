package org.jcap.Core.Packets.DNS;

public class DnsRDataMInfo /* implements DnsRData */ {

    private final DnsDomainName RMAILBX;
    private final DnsDomainName EMAILBX;

    private DnsRDataMInfo(DnsRDataMInfoBuilder builder) {
        // TODO Validation is to be done
        this.EMAILBX = builder.EMAILBX;
        this.RMAILBX = builder.RMAILBX;
    }

    public final static class DnsRDataMInfoBuilder {
        private DnsDomainName RMAILBX;
        private DnsDomainName EMAILBX;

        private boolean sealed;

        private DnsRDataMInfoBuilder() {
        }

        private DnsRDataMInfoBuilder(byte rawData, int offset, int len) {
            // TODO To be implemented......
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
}
