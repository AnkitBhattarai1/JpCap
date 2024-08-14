package org.jpcap.Core.Packets.Network;

import java.net.Inet4Address;
import java.net.InetAddress;

import org.jpcap.Core.Constants.NamedCodes.L3.IpVersion;
import org.jpcap.Core.Constants.NamedCodes.L3.ProcNumber;
import org.jpcap.Core.Packets.Packet;
import org.jpcap.Core.Packets.Abstract_Layer_Packet.L3Packet;
import org.jpcap.Core.Utils.ByteOperations;

public class IpV4Packet implements L3Packet {

    @Override
    public int length() {
        return 0;
    }

    @Override
    public byte[] getRawData() {
        return null;
    }

    @Override
    public L3Header getHeader() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeader'");
    }

    @Override
    public Packet getPlayLoad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayLoad'");
    }

    @Override
    public <T extends Packet> T getPacketOf(Class<T> packetType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPacketOf'");
    }

    @Override
    public <T extends Packet> boolean containsPacketOf(Class<T> packetType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsPacketOf'");
    }

    public static final class IpV4PacketBuilder implements L3PacketBuilder {

        @Override
        public L3Packet build() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'build'");
        }

    }

    /**
     * 
     * 
     * <pre>
     *  
     * 
     *  0                     1                   2                   3
     *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
     *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *  |Version|  IHL  |Type of Service|          Total Length         |
     *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *  |         Identification        |Flags|      Fragment Offset    |
     *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *  |  Time to Live |    Protocol   |         Header Checksum       |
     *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *  |                       Source Address                          |
     *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *  |                    Destination Address                        |
     *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *  |                    Options                    |    Padding    |
     *  +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * 
     * Version  (4 bits):
     *      The Version field indicates the format of the internet header.  This
     *      document describes version 4.
     *
     *  IHL (Internet Header Length) (4 bits):
     *      Internet Header Length is the length of the internet header in 32
     *      bit words, and thus points to the beginning of the data.  Note that
     *      the minimum value for a correct header is 5.
     * 
     * 
     * </pre>
     * 
     * @see also
     *      <a href ="https://datatracker.ietf.org/doc/html/rfc760#page-11">RFC760
     *      </a>
     */
    public static final class IpV4Header implements L3Header {

        private final IpVersion version;
        private final byte ihl;
        // private final Ipv4Tos tos;
        private final short totalLen;
        private final short iden;
        private final boolean reservedFlag;
        private final boolean dontFragmentFlag;
        private final boolean moreFragmentFlag;
        private final short fragmentOffset;
        private final byte ttl;
        private final ProcNumber proc;
        private final short checksum;
        private final Inet4Address srcAddr;
        private final Inet4Address dstAddr;
        // private final List<IpV4Option> options;
        private final byte[] padding;

        private IpV4Header(IpV4HeaderBuilder builder) {
            this.version = builder.version;
            this.ihl = builder.ihl;
            // this.tos=builder.tos;
            this.totalLen = builder.totalLen;
            this.iden = builder.iden;
            this.reservedFlag = builder.reservedFlags;
            this.dontFragmentFlag = builder.dontFragmentFlag;
            this.moreFragmentFlag = builder.moreFragmentFlag;
            this.fragmentOffset = builder.fragmentOffset;
            this.ttl = builder.ttl;
            this.proc = builder.proc;
            this.checksum = builder.checksum;
            this.srcAddr = builder.srcAddr;
            this.dstAddr = builder.destAddr;
            // this.options=builder.options;
            this.padding = builder.padding;
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
        public InetAddress getSourceAddress() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getSourceAddress'");
        }

        @Override
        public InetAddress getDestinationAddress() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDestinationAddress'");
        }

        public static final class IpV4HeaderBuilder implements L3HeaderBuilder {

            private IpVersion version;
            private byte ihl;
            // private IpV4Tos tos;
            private short totalLen;
            private short iden;
            private boolean reservedFlags;
            private boolean dontFragmentFlag;
            private boolean moreFragmentFlag;
            private short fragmentOffset;
            private byte ttl;
            private ProcNumber proc;
            private short checksum;
            private Inet4Address srcAddr;
            private Inet4Address destAddr;
            // private final List<IPV4Option> options;
            private byte[] padding;

            private IpV4HeaderBuilder() {
                // For custom building of the header
            }

            private IpV4HeaderBuilder(byte[] rawData, int offset, int len) {
                if (len < Short.BYTES * 6)
                    throw new IllegalArgumentException("The data is too short to form the IPv4 Header");
                int cursor = 0;
                byte verIhl = ByteOperations.getByte(rawData, offset);
                // version=IpVersion.getInstanceofCode();
                ihl = (byte) (verIhl & 0x0F);
                cursor += Byte.BYTES;
                /// get tos;
                cursor += Byte.BYTES;
                totalLen = ByteOperations.getShort(rawData, offset + cursor);
                cursor += Short.BYTES;

                // TODO to be implementedd...
            }

            @Override
            public L3Header build() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'build'");
            }

        }
    }

}
