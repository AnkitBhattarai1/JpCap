package org.jpcap.Core.Packets.Network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.jpcap.Core.Constants.NamedCodes.L3.IpVersion;
import org.jpcap.Core.Constants.NamedCodes.L3.ProcNumber;
import org.jpcap.Core.Packets.Packet;
import org.jpcap.Core.Packets.Abstract_Layer_Packet.L3Packet;
import org.jpcap.Core.Utils.ByteOperations;
import org.jpcap.Core.Utils.InetConverter;

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
     *  Type of Service (8 bits):
     *      The type of Service an indication of the abstract parameters of the
     *      quality of service desired. These parameters are to be used to guide 
     *      the selection of the actual service parameters when transmitting a 
     *      datagram through a particular network.
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
         private final byte tos;
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
        private final List<IPv4Option> options;
        private final byte[] padding;

        private IpV4Header(IpV4HeaderBuilder builder) {
        
            this.version = builder.version;
            this.ihl = builder.ihl;
             this.tos=builder.tos;
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
            this.options=builder.options;
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

        public IpVersion getVersion(){
            return version;
        }

        public byte getIhl() {
            return ihl;
        }

        public byte getTos() {
            return tos;
        }

        public boolean isReservedFlag() {
            return reservedFlag;
        }

        public boolean isDontFragmentFlag() {
            return dontFragmentFlag;
        }

        public boolean isMoreFragmentFlag() {
            return moreFragmentFlag;
        }

        public short getFragmentOffset() {
            return fragmentOffset;
        }

        public byte getTtl() {
            return ttl;
        }

        public ProcNumber getProc() {
            return proc;
        }

        public short getChecksum() {
            return checksum;
        }

        public Inet4Address getSrcAddr() {
            return srcAddr;
        }

        public Inet4Address getDstAddr() {
            return dstAddr;
        }

        public List<IPv4Option> getOptions() {
            return options;
        }

        public byte[] getPadding() {
            return padding;
        }

        public IpV4HeaderBuilder Builder(){
            return new IpV4HeaderBuilder();
        }

        public IpV4HeaderBuilder Builder(byte[] rawData, int offset, int len){
            return new IpV4HeaderBuilder(rawData, offset, len);
        }

        public static final class IpV4HeaderBuilder implements L3HeaderBuilder {

            private IpVersion version;
            private byte ihl;
             private byte tos;
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
            private List<IPv4Option> options;
            private byte[] padding;
            
            private boolean sealed;

            private IpV4HeaderBuilder() {
                // For custom building of the header
            }

            private IpV4HeaderBuilder(byte[] rawData, int offset, int len) {
                
                ByteOperations.validate(rawData, offset, len);

                if (len < Short.BYTES * 10)
                    throw new IllegalArgumentException("The data is too short to form the IPv4 Header");
                int cursor = 0;
                byte verIhl = ByteOperations.getByte(rawData, offset);
                // version=IpVersion.getInstanceofCode();
                this.ihl = (byte) (verIhl & 0x0F);
                cursor += Byte.BYTES;

                tos=ByteOperations.getByte(rawData,offset+cursor);
                cursor += Byte.BYTES;

                this.totalLen = ByteOperations.getShort(rawData, offset + cursor);
                cursor += Short.BYTES;

                this.iden = ByteOperations.getShort(rawData, offset + cursor);
                cursor += Short.BYTES;

                short flagsAndFragmentOffset = ByteOperations.getShort(rawData, offset + cursor);
                cursor += Short.BYTES;

                this.reservedFlags = (flagsAndFragmentOffset & 0x8000) != 0;
                this.dontFragmentFlag = (flagsAndFragmentOffset & 0x4000) != 0;
                this.moreFragmentFlag = (flagsAndFragmentOffset & 0x2000) != 0;
                this.fragmentOffset = (short) (flagsAndFragmentOffset & 0x1FFF);

                this.ttl = ByteOperations.getByte(rawData, offset + cursor);
                cursor += Byte.BYTES;

                this.proc = ProcNumber.getInstanceOfCode(ByteOperations.getByte(rawData, offset + cursor));
                cursor += Byte.BYTES;

                this.checksum = ByteOperations.getShort(rawData, offset + cursor);
                cursor+= Short.BYTES;

                this.srcAddr = InetConverter.toInet4Address(rawData, offset+cursor, ByteOrder.nativeOrder());
                cursor+=Integer.BYTES;

                this.destAddr = InetConverter.toInet4Address(rawData, offset+cursor, ByteOrder.nativeOrder());
                cursor+= Integer.BYTES;
                
                int h_Len_bytes = (int)(0xFF & ihl) * 4;//represented in  32-bits(4-bytes) increment of the value
                //due to this the min header len =20 and max header len = 60
               

                //checks the given len of rawData to the lenght mentioned in the packet
                if(h_Len_bytes < len) throw new IllegalArgumentException("The data is too short to make a IpV4 Header..");


                if(h_Len_bytes < cursor) throw new IllegalArgumentException("The ihl must be equal or more than "+ cursor/4 +"but it is "+ (int)(0xFF&ihl));


                this.options = new ArrayList<>();
                
                try {
                    while(cursor <h_Len_bytes) {
                        // somethign need to be done here..... paxi aayyerwww garxuu laaa hehehehehehheh.....
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                }

                int padLen = h_Len_bytes - cursor;

                if(padLen!=0) this.padding = ByteOperations.getSubArray(rawData,cursor+offset , padLen);
                
                else this.padding = new byte[0];
                // TODO to implement the IpV4Options here......
            }

            @Override
            public IpV4Header build() {
                return new IpV4Header(this);
            }


            //Version
            public IpV4HeaderBuilder version(IpVersion version){
                this.version=version;
                return this;
            } 

            //Internet Header Length
            //
            public IpV4HeaderBuilder Ihl(byte ihl){
                this.ihl=ihl;
                return this;
            }

            //Type of Service
            public IpV4HeaderBuilder Tos(byte tos){
                this.tos=tos;
                return this;            
            }

            public IpV4HeaderBuilder TotalLen(short l){
                this.totalLen = l;
                return this;
            } 

            public IpV4HeaderBuilder Identification(short iden){
                this.iden=iden;
                return this;
            }
            
            public IpV4HeaderBuilder ReservedFlag(boolean b){
                this.reservedFlags=b;
                return this;
            }
        }
        
    }


}

