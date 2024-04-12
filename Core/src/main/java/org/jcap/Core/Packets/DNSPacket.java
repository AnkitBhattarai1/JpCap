package org.jcap.Core.Packets;

import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsOpCode;
import org.jcap.Core.Utils.ByteOperations;

public class DNSPacket extends AbstractPacket{

   //private final DNSHeader header;

    @Override
    public int length() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'length'");
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

    @Override
    public byte[] getRawData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRawData'");
    }

    @Override
    public PacketBuilder Builder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Builder'");
    }


    public static class DNSPacketBuilder{
        private DNSHeader header;

    }

    /*
     *                              1  1  1  1  1  1
      0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                      ID                       |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |QR|   Opcode  |AA|TC|RD|RA|   Z    |   RCODE   |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    QDCOUNT                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    ANCOUNT                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    NSCOUNT                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                    ARCOUNT                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
     */

    /*
     * ID:
     *  A 16 bit identifier assigned by the program that
     * generated any kind of query. This identifier is copied
     * the corresponding reply and can be used by the rrequester to match 
     * up replies to outstanding queries 
     * 
     * QR:
     *  A one bit fiedl that speicifies whether this message is a query(0),
     * or a response(1)
     * 
     * OPCODE:
     *  A four bit field that specifies kind of query in this message. This 
     * value is set by the originator of a query and copied into the reaponse. The
     * values are:
     *  0---> a standard query 
     *  1---> an inverse qyery (IQUERY)
     *  2---> a server status request (STATUS)
     *  3-15--> reserved for future use..
     * 
     * AA: 
     * Authoritative Answer - this bit is valid in responses, and specifies that
     * the responding name server is an authority for the domain name in question section.
     * The contents of the answer section may have multiple owner names becaue of aliases.
     * The AA bit corresponds to the name which mathces the query name, or the first
     * owner name in the answer section.
     * 
     * TC: 
     * Truncation- specifies that this message was truncatd due to length greater than 
     * that permitted to the transmission channel.
     * 
     * RD:
     * Recursion Desired- this bit may be set ina query and is copied into the response. 
     * IF RD is set, it directs the name server to pursue the query recursively. 
     * 
     * RA:
     * Recursion Available - this bit is set or cleared in a response and denotes whether
     * recursive query support is availave in the name server
     * 
     * RCODE: 
     * Response Code- 4 bit field is set as part of responses. 
     *  0- No error condition
     *  1- Format error 
     *  2- Server failure
     *  
     *      
     * 
     */
    public static final class DNSHeader extends AbstractHeader{
        private final short Id;  
        private final boolean response; //
        private final DnsOpCode dnsOpCode;
        private final boolean authoritativeAnswer;
        private final boolean truncation;
        private final boolean recursionDesired;
        private final boolean recursionAvailable;
        private final boolean reserved=false;
        //private final DnsRCode rCode;
        private final short QDCount;
        private final short ANCount;
        private final short NSCount;
        private final short ARcount;


        private static final int ID_OFFSET = 0;
        private static final int ID_SIZE = Short.BYTES;
        private static final int FLAGS_OFFSET = ID_OFFSET + ID_SIZE;
        private static final int FLAGS_SIZE = Short.BYTES;
        private static final int QDCOUNT_OFFSET = FLAGS_OFFSET + FLAGS_SIZE;
        private static final int QDCOUNT_SIZE = Short.BYTES;
        private static final int ANCOUNT_OFFSET = QDCOUNT_OFFSET + QDCOUNT_SIZE;
        private static final int ANCOUNT_SIZE = Short.BYTES;
        private static final int NSCOUNT_OFFSET = ANCOUNT_OFFSET + ANCOUNT_SIZE;
        private static final int NSCOUNT_SIZE = Short.BYTES;
        private static final int ARCOUNT_OFFSET = NSCOUNT_OFFSET + NSCOUNT_SIZE;
        private static final int ARCOUNT_SIZE = Short.BYTES;
        private static final int DNS_MIN_HEADER_SIZE = ARCOUNT_OFFSET + ARCOUNT_SIZE;

        private DNSHeader(DNSHeaderBuilder builder){
            this.Id=builder.Id;
            this.response=builder.response;
            this.dnsOpCode=builder.dnsOpCode;
            this.authoritativeAnswer=builder.authoritativeAnswer;
            this.truncation=builder.truncation;
            this.recursionDesired=builder.recursionDesired;
            this.recursionAvailable=builder.recursionAvailable;
            //this.rCode = builder.rCode;
            this.QDCount=builder.QDCount;
            this.ANCount=builder.ANCount;
            this.NSCount=builder.NSCount;
            this.ARcount=builder.ARcount;
        }

        public static DNSHeaderBuilder Builder(byte[] arr, int offset, int len){
            return new DNSHeaderBuilder(arr, offset, len);
        }

        public static DNSHeaderBuilder Builder(){
            return new DNSHeaderBuilder();
        }

    public static final class DNSHeaderBuilder {
            private  short Id;  
            private  boolean response; //
            private  DnsOpCode dnsOpCode;
            private  boolean authoritativeAnswer;
            private  boolean truncation;
            private  boolean recursionDesired;
            private  boolean recursionAvailable;
            private  boolean reserved=false;
            //private final DnsRCode rCode;
            private  short QDCount;
            private  short ANCount;
            private  short NSCount;
            private  short ARcount;

            private DNSHeaderBuilder(byte[] rawData, int offset, int len){  
                if(len<DNS_MIN_HEADER_SIZE) throw new IllegalArgumentException("The data is too short to make the header"); //TODO:Make a custom exception....

                this.Id=ByteOperations.getShort(rawData, ID_OFFSET+offset);
                short flags = ByteOperations.getShort(rawData, FLAGS_OFFSET+offset);
                this. response = (flags&0x8000)!=0;  
                this.dnsOpCode=DnsOpCode.instanceOfCode((byte)((flags>>11)&0x0F));
                this.authoritativeAnswer=(flags&0x0400)!=0;
                this.truncation=(flags&0x0200)!=0;
                this.recursionDesired=(flags&0x0100)!=0;
                this.recursionAvailable=(flags&0x0080)!=0;
                this.QDCount=ByteOperations.getShort(rawData, offset+QDCOUNT_OFFSET);
                this.ANCount=ByteOperations.getShort(rawData, offset+ANCOUNT_OFFSET);
                this.NSCount=ByteOperations.getShort(rawData, offset+NSCOUNT_OFFSET);
                this.ARcount=ByteOperations.getShort(rawData, offset+ARCOUNT_OFFSET);
            }

            public DNSHeaderBuilder() {
            }

            public DNSHeader build(){
                return new DNSHeader(this);
            }
        }

        public short getId(){
            return this.Id;
        }

        public boolean isResponse(){
            return response;
        }
    }  
}
