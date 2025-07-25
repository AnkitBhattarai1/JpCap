package org.jpcap.Core.Packets.DNS;

public interface DnsRData {

    public int length();

    public byte[] getRawData();

    public String toString(String indent);

    public String toString(String indent, byte[] headerRawData);

    public static interface DnsRDataBuilder {
        public DnsRData build();
    }
}
