package org.jcap.Core.Packets.DNS;

public class DnsRDataHInfo implements DnsRData {

    private final String os;
    private final String cpu;

    private DnsRDataHInfo(DnsRDataHInfoBuilder builder) {
        // TODO validation is to be done
        this.cpu = builder.cpu;
        this.os = builder.os;
    }

    public static DnsRDataHInfoBuilder Builder() {
        return new DnsRDataHInfoBuilder();
    }

    public static DnsRDataHInfoBuilder Builder(byte[] rawData, int offset, int len) {
        return new DnsRDataHInfoBuilder(rawData, offset, len);
    }

    public static final class DnsRDataHInfoBuilder {

        private String os;
        private String cpu;

        private boolean sealed;

        private DnsRDataHInfoBuilder() {
        }

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

        public DnsRDataHInfoBuilder OS(String os) {
            if (sealed)
                throw new UnsupportedOperationException("Cannot change the field os once  made from byte data");
            if (os.length() > 255)
                throw new IllegalArgumentException("The length of the os cannot be more than 256");
            this.os = os;
            return this;
        }

        public DnsRDataHInfoBuilder cpu(String cpu) {
            if (sealed)
                throw new UnsupportedOperationException("Cannot change the field os once  made from byte data");
            if (os.length() > 255)
                throw new IllegalArgumentException("The length of the os cannot be more than 256");
            this.cpu = cpu;
            return this;
        }

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
