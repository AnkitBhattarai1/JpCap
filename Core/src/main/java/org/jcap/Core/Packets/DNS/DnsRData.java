package org.jcap.Core.Packets.DNS;

public interface DnsRData {

    public int length();

    public byte[] getRawData();

    public String toString(String indent);

    public String toString(String indent, byte[] headerRawData);
}
