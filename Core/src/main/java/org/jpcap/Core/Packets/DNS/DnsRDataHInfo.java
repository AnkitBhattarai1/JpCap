package org.jcap.Core.Packets.DNS;

/**
 * Represents the RDATA section for DNS HINFO records containing information
 * about
 * the hardware type and operating system of a host.
 * <p>
 * This class provides methods to build an instance from scratch or from raw
 * byte data,
 * retrieve properties, and serialize the instance back to raw bytes suitable
 * for use
 * in DNS packets.
 * </p>
 */
public class DnsRDataHInfo implements DnsRData {

    private final String os;
    private final String cpu;

    /**
     * Constructs a {@link DnsRDataHInfo} instance using the provided builder.
     * 
     * @param builder the {@link DnsRDataHInfoBuilder} to extract properties
     */
    private DnsRDataHInfo(DnsRDataHInfoBuilder builder) {
        // TODO validation is to be done
        this.cpu = builder.cpu;
        this.os = builder.os;
    }

    /**
     * Creates a new builder instance for constructing a {@code DnsRDataHInfo}.
     * 
     * @return a new {@link DnsRDataHInfoBuilder} instance
     */
    public static DnsRDataHInfoBuilder Builder() {
        return new DnsRDataHInfoBuilder();
    }

    /**
     * Creates a new builder instance for constructing a {@code DnsRDataHInfo} from
     * raw byte data.
     * 
     * @param rawData the byte array containing the DNS packet data
     * @param offset  the starting index of the RDATA in {@code rawData}
     * @param len     the length of the RDATA
     * @return a new {@link DnsRDataHInfoBuilder} initialized with data from
     *         {@code rawData}
     */
    public static DnsRDataHInfoBuilder Builder(byte[] rawData, int offset, int len) {
        return new DnsRDataHInfoBuilder(rawData, offset, len);
    }

    /**
     * Inner class to build {@code DnsRDataHInfo} instances.
     */
    public static final class DnsRDataHInfoBuilder {

        private String os;
        private String cpu;

        private boolean sealed;

        /**
         * Private constructor for builder initialization.
         */
        private DnsRDataHInfoBuilder() {
        }

        /**
         * Constructs a builder by parsing RDATA from raw byte data.
         * 
         * @param rawData byte array containing the RDATA
         * @param offset  the offset in {@code rawData} where RDATA begins
         * @param len     the length of the RDATA
         * @throws IllegalArgumentException if data is insufficient to extract required
         *                                  fields
         */
        private DnsRDataHInfoBuilder(byte[] rawData, int offset, int len) {
            int position = 0;
            int cpuLen = rawData[offset] & 0xFF;
            position++;

            if (cpuLen + 1 > len - position)
                throw new IllegalArgumentException("Data is not enough to extract OS and cpu");

            this.cpu = new String(rawData, offset + position, cpuLen);
            position += cpuLen;

            int osLen = rawData[offset + position] & 0xFF;
            position++;

            if (osLen > len - position)
                throw new IllegalArgumentException("Data is not enough to extract OS");

            this.os = new String(rawData, offset + position, osLen);

            this.sealed = true;
        }

        /**
         * Sets the operating system information.
         * 
         * @param os the operating system description
         * @return this builder instance
         * @throws UnsupportedOperationException if the builder is sealed
         * @throws IllegalArgumentException      if the os length exceeds 255 characters
         */
        public DnsRDataHInfoBuilder OS(String os) {
            if (sealed)
                throw new UnsupportedOperationException("Cannot change the field os once  made from byte data");

            if (os.length() > 255)
                throw new IllegalArgumentException("The length of the os cannot be more than 256");

            this.os = os;
            return this;
        }

        /**
         * Sets the CPU type information.
         * 
         * @param cpu the CPU type description
         * @return this builder instance
         * @throws UnsupportedOperationException if the builder is sealed
         * @throws IllegalArgumentException      if the cpu length exceeds 255
         *                                       characters
         */
        public DnsRDataHInfoBuilder cpu(String cpu) {
            if (sealed)
                throw new UnsupportedOperationException("Cannot change the field os once  made from byte data");

            if (cpu.length() > 255)
                throw new IllegalArgumentException("The length of the os cannot be more than 256");

            this.cpu = cpu;

            return this;
        }

        /**
         * Builds a {@code DnsRDataHInfo} instance based on the properties set in this
         * builder.
         * 
         * @return a new {@code DnsRDataHInfo} instance
         */
        public DnsRDataHInfo build() {
            // TODO Validation is to be done
            return new DnsRDataHInfo(this);
        }
    }

    @Override
    public int length() {
        return cpu.length() + os.length() + 2;
    }

    @Override
    public byte[] getRawData() {
        byte[] data = new byte[length()];
        int cursor = 0;

        byte[] rawCpu = cpu.getBytes();
        data[cursor] = (byte) rawCpu.length;
        cursor++;
        System.arraycopy(rawCpu, 0, data, cursor, rawCpu.length);
        cursor += rawCpu.length;

        byte[] rawOs = os.getBytes();
        data[cursor] = (byte) rawOs.length;
        cursor++;
        System.arraycopy(rawOs, 0, data, cursor, rawOs.length);

        return data;
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

    public String getOs() {
        return os;
    }

    public String getCpu() {
        return cpu;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((os == null) ? 0 : os.hashCode());
        result = prime * result + ((cpu == null) ? 0 : cpu.hashCode());
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
        DnsRDataHInfo other = (DnsRDataHInfo) obj;
        if (os == null) {
            if (other.os != null)
                return false;
        } else if (!os.equals(other.os))
            return false;
        if (cpu == null) {
            if (other.cpu != null)
                return false;
        } else if (!cpu.equals(other.cpu))
            return false;
        return true;
    }
}
