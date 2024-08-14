package org.jcap.Core.Packets.DNS;

import org.jcap.Core.Utils.ByteOperations;

/**
 * DNS SOA RDATA
 * 
 * <pre style="white-space: pre;">
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * /                     MNAME                     /
 * /                                               /
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * /                     RNAME                     /
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                    SERIAL                     |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                    REFRESH                    |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                     RETRY                     |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                    EXPIRE                     |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                    MINIMUM                    |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * 
 *
 * where:
 *
 * MNAME           The &lt;domain-name&gt; of the name server that was the
 *                 original or primary source of data for this zone.
 *
 * RNAME           A &lt;domain-name&gt; which specifies the mailbox of the
 *                 person responsible for this zone.
 *
 * SERIAL          The unsigned 32 bit version number of the original copy
 *                 of the zone.  Zone transfers preserve this value.  This
 *                 value wraps and should be compared using sequence space
 *                 arithmetic.
 *
 * REFRESH         A 32 bit time interval before the zone should be
 *                 refreshed.
 *
 * RETRY           A 32 bit time interval that should elapse before a
 *                 failed refresh should be retried.
 *
 * EXPIRE          A 32 bit time value that specifies the upper limit on
 *                 the time interval that can elapse before the zone is no
 *                 longer authoritative.
 *
 * MINIMUM         The unsigned 32 bit minimum TTL field that should be
 *                 exported with any RR from this zone.
 *
 * </pre>
 * 
 * Represents the Start of Authority (SOA) Resource Record (RR) data structure
 * in the Domain Name System (DNS).
 * <p>
 * An SOA record is used by DNS servers to indicate the authoritative name
 * server for a DNS zone.
 * It contains administrative information about the zone, such as the primary
 * name server, the email of the domain administrator,
 * the serial number that changes when the zone is updated, and timers that
 * specify how long secondary servers should cache data.
 * <p>
 * This class provides methods to build and manipulate SOA RData as well as
 * validate its components.
 */
public class DnsRDataSoa implements DnsRData {

    /**
     * <pre style="white-space: pre;">
     * The &lt;domain-name&gt; of the name server that was the
     * original or primary source of data for this zone.
     */
    private final DnsDomainName mName;
    /**
     * <pre style="white-space: pre;">
     * A &lt;domain-name&gt; which specifies the mailbox of the
     * person responsible for this zone.
     */
    private final DnsDomainName rName;
    /**
     * The unsigned 32 bit version number of the original copy
     * of the zone. Zone transfers preserve this value. This
     * value wraps and should be compared using sequence space
     * arithmetic.
     */
    private final int serial;
    /**
     * A 32 bit time interval before the zone should be
     * refreshed.
     */
    private final int refresh;
    /**
     * A 32 bit time interval that should elapse before a
     * failed refresh should be retried.
     */
    private final int retry;
    /**
     * A 32 bit time value that specifies the upper limit on
     * the time interval that can elapse before the zone is no
     * longer authoritative.
     */
    private final int expire;
    /**
     * The unsigned 32 bit minimum TTL field that should be
     * exported with any RR from this zone.
     * 
     */
    private final int minimum;

    /**
     * Constructs a new instance of {@link DnsRDataSoa} with the provided builder.
     * <p>
     * This constructor initializes the SOA RData with the components provided by
     * the builder.
     * Validation of the components is pending.
     *
     * @param builder The builder containing the components to initialize the SOA
     *                RData.
     */
    private DnsRDataSoa(DnsRDataSoaBuilder builder) {
        // TODO validation is to be done ....
        this.mName = builder.mName;
        this.rName = builder.rName;
        this.serial = builder.serial;
        this.refresh = builder.refresh;
        this.retry = builder.retry;
        this.expire = builder.expire;
        this.minimum = builder.minimum;
    }

    /**
     * Returns a new instance of {@link DnsRDataSoaBuilder} to start building an SOA
     * RData.
     *
     * @return A new instance of {@link DnsRDataSoaBuilder}.
     */
    public DnsRDataSoaBuilder Builder() {
        return new DnsRDataSoaBuilder();
    }

    /**
     * Returns a new instance of {@link DnsRDataSoaBuilder} initialized with raw
     * data to continue building an SOA RData.
     *
     * @param rawData The raw data representing the SOA RData.
     * @param offset  The starting position in the raw data array.
     * @param len     The length of the raw data.
     * @return A new instance of {@link DnsRDataSoaBuilder} initialized with the
     *         provided raw data.
     */
    public DnsRDataSoaBuilder Builder(byte[] rawData, int offset, int len) {
        return new DnsRDataSoaBuilder(rawData, offset, len);
    }

    /**
     * Builder pattern implementation for constructing instances of
     * {@link DnsRDataSoa}.
     * <p>
     * This builder facilitates the construction of SOA RData instances either from
     * raw data or through individual components.
     * It enforces validation checks and immutability once the object is
     * constructed.
     * <p>
     * After setting the necessary components, call the {@link #build()} method to
     * obtain an immutable {@link DnsRDataSoa} object.
     */
    private static final class DnsRDataSoaBuilder {

        private DnsDomainName mName;
        private DnsDomainName rName;
        private int serial;
        private int refresh;
        private int retry;
        private int expire;
        private int minimum;

        private boolean sealed;

        private final static int MIN_LEN = Integer.BYTES * 6 + Short.BYTES;

        private DnsRDataSoaBuilder() {

        }

        private DnsRDataSoaBuilder(byte[] rawData, int offset, int len) {
            ByteOperations.validate(rawData, offset, len);
            int position = 0;

            if (len < MIN_LEN)
                throw new IllegalArgumentException("The data is too short to make a SOA data");

            this.mName = DnsDomainName.Builder(rawData, offset, len).build();
            position += mName.length();

            if ((position + MIN_LEN - Integer.BYTES) > len)
                throw new IllegalArgumentException("The data is too short ");

            this.rName = DnsDomainName.Builder(rawData, offset + position, len).build();
            position += rName.length();

            if ((position + MIN_LEN - Integer.BYTES - Short.BYTES) > len)
                throw new IllegalArgumentException("the data is too short");

            this.serial = ByteOperations.getInt(rawData, offset + position);
            position += Integer.BYTES;

            this.refresh = ByteOperations.getInt(rawData, offset + position);
            position += Integer.BYTES;

            this.retry = ByteOperations.getInt(rawData, offset + position);
            position += Integer.BYTES;

            this.expire = ByteOperations.getInt(rawData, offset + position);
            position += Integer.BYTES;

            this.minimum = ByteOperations.getInt(rawData, offset + position);
            position += Integer.BYTES;

            this.sealed = true;
        }

        public DnsRDataSoaBuilder MName(DnsDomainName name) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "Cannot inintialize the mName once it is initialized from rawData");
            this.mName = name;
            return this;
        }

        public DnsRDataSoaBuilder RName(DnsDomainName name) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "Cannot inintialize the rName once it is initialized from rawData");

            this.rName = name;
            return this;
        }

        public DnsRDataSoaBuilder serial(int value) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "Cannot inintialize the serial once it is initialized from rawData");

            this.serial = value;
            return this;
        }

        public DnsRDataSoaBuilder refresh(int value) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "Cannot inintialize the refresh once it is initialized from rawData");

            this.refresh = value;
            return this;
        }

        public DnsRDataSoaBuilder retry(int value) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "Cannot inintialize the retry once it is initialized from rawData");

            this.retry = value;
            return this;
        }

        public DnsRDataSoaBuilder expire(int value) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "Cannot inintialize the expire once it is initialized from rawData");

            this.expire = value;
            return this;
        }

        public DnsRDataSoaBuilder minimum(int value) {
            if (sealed)
                throw new UnsupportedOperationException(
                        "Cannot inintialize the minimum once it is initialized from rawData");

            this.minimum = value;
            return this;
        }

        /**
         * Validates the components of the SOA RData being built and constructs a new
         * immutable {@link DnsRDataSoa} instance.
         * <p>
         * This method should be called after setting all required components using the
         * builder methods.
         *
         * @return A new immutable instance of {@link DnsRDataSoa}.
         * @throws IllegalArgumentException If any of the required components are not
         *                                  set or fail validation.
         */
        public DnsRDataSoa build() {
            validate(this);
            return new DnsRDataSoa(this);
        }

        private void validate(DnsRDataSoaBuilder dnsRDataSoaBuilder) {
            // TODO Validation is to be done .....
        }
    }

    /**
     * Returns the length of the SOA RData in bytes.
     * <p>
     * This method is not implemented.
     *
     * @return The length of the SOA RData in bytes.
     * @throws UnsupportedOperationException Always, as this method is
     *                                       unimplemented.
     */
    @Override
    public int length() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'length'");
    }

    /**
     * Returns the raw data representing the SOA RData.
     * <p>
     * This method is not implemented.
     *
     * @return The raw data representing the SOA RData.
     * @throws UnsupportedOperationException Always, as this method is
     *                                       unimplemented.
     */
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mName == null) ? 0 : mName.hashCode());
        result = prime * result + ((rName == null) ? 0 : rName.hashCode());
        result = prime * result + serial;
        result = prime * result + refresh;
        result = prime * result + retry;
        result = prime * result + expire;
        result = prime * result + minimum;
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
        DnsRDataSoa other = (DnsRDataSoa) obj;
        if (mName == null) {
            if (other.mName != null)
                return false;
        } else if (!mName.equals(other.mName))
            return false;
        if (rName == null) {
            if (other.rName != null)
                return false;
        } else if (!rName.equals(other.rName))
            return false;
        if (serial != other.serial)
            return false;
        if (refresh != other.refresh)
            return false;
        if (retry != other.retry)
            return false;
        if (expire != other.expire)
            return false;
        if (minimum != other.minimum)
            return false;
        return true;
    }

}
