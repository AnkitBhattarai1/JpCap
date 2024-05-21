package org.jcap.Core.Packets.Transport_Layer;

import java.util.Arrays;
import java.util.List;

import org.jcap.Core.Constants.NamedCodes.L4.Port;
import org.jcap.Core.Constants.NamedCodes.L4.TcpOptionKind;
import org.jcap.Core.Constants.NamedCodes.L4.TcpPort;
import org.jcap.Core.Packets.Packet;
import org.jcap.Core.Packets.Abstract_Layer_Packet.L4Packet.L4Header;
import org.jcap.Core.Utils.ByteOperations;

public class TcpPacket {

    private final TcpHeader header;
    private final Packet payLoad;

    private TcpPacket(TcpPacketBuilder builder) {
        this.header = builder.header;
        this.payLoad = builder.playLoad;
    }

    public static TcpPacketBuilder Builder() {
        return new TcpPacketBuilder();
    }

    public static TcpPacketBuilder Builder(byte[] rawData, int offset, int len) {
        return new TcpPacketBuilder(rawData, offset, len);
    }

    private static final class TcpPacketBuilder {

        private TcpHeader header;
        private Packet playLoad;

        private boolean sealed = true;

        private TcpPacketBuilder() {

        }

        private TcpPacketBuilder(byte[] rawData, int offset, int len) {
            ByteOperations.validate(rawData, offset, len);
            this.header = TcpHeader.builder(rawData, offset, len).build();

            int playLoadlen = len - header.length();

            // TODO Implementation is to be done
            this.sealed = true;
        }

        public TcpPacketBuilder header(TcpHeader header) {
            if (sealed)
                throw new UnsupportedOperationException();
            this.header = header;
            return this;
        }

        public TcpPacketBuilder playLoad(Packet packet) {
            if (sealed)
                throw new UnsupportedOperationException();

            this.playLoad = packet;
            return this;
        }

        public TcpPacket build() {
            validate(this);
            return new TcpPacket(this);
        }

        private void validate(TcpPacketBuilder tcpPacketBuilder) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'validate'");
        }
    }

    /**
     * {@link TcpHeader}
     * 
     * <pre>
      * 0                   1                   2                   3
      * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
      * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      * |         Source_Port            |         Destination_Port     |
      * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      * /                        Sequence_Number                        |
      * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      * |                    Acknowledgment_Number                      |
      * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      * | Data |           |U|A|P|R|S|F|                                |
      * |Offset| Reserved  |R|C|S|S|Y|I|            Window              |
      * |      |           |G|K|H|T|N|N|                                |
      * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      * |           Checksum            |         Urgent_Pointer        |
      * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      * |                    Options                    |    padding    |
      * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      * |                             data                              |
      * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * </pre>
     */

    public static final class TcpHeader implements L4Header {

        private final TcpPort srcPort;
        private final TcpPort destPort;
        private final Integer seqNumber;
        private final Integer ackNumber;
        private final byte dataoffset;
        private final byte reserved;
        private final boolean urg;
        private final boolean ack;
        private final boolean psh;
        private final boolean rst;
        private final boolean syn;
        private final boolean fin;
        private final short Window;
        private final short checksum;
        private final short urgPointer;
        private final List<TCPOption> options;
        private final byte[] padding;

        private TcpHeader(TcpHeaderBuilder builder) {
            this.srcPort = builder.srcPort;
            this.destPort = builder.destPort;
            this.seqNumber = builder.seqNumber;
            this.ackNumber = builder.ackNumber;
            this.dataoffset = builder.dataoffset;
            this.reserved = builder.reserved;
            this.urg = builder.urg;
            this.ack = builder.ack;
            this.psh = builder.psh;
            this.rst = builder.rst;
            this.syn = builder.syn;
            this.fin = builder.fin;
            this.Window = builder.Window;
            this.checksum = builder.checksum;
            this.urgPointer = builder.urgPointer;
            this.options = builder.options;
            this.padding = builder.padding;
        }

        public static TcpHeaderBuilder builder() {
            return new TcpHeaderBuilder();
        }

        public static TcpHeaderBuilder builder(byte[] rawData, int offset, int len) {
            return new TcpHeaderBuilder(rawData, offset, len);
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
        public Port getSrcPort() {
            return srcPort;
        }

        @Override
        public Port getDestPort() {
            return destPort;
        }

        public int getSeqNumber() {
            return seqNumber;
        }

        public int getAckNumber() {
            return ackNumber;
        }

        public byte getDataoffset() {
            return dataoffset;
        }

        public byte getReserved() {
            return reserved;
        }

        public boolean isUrg() {
            return urg;
        }

        public boolean isAck() {
            return ack;
        }

        public boolean isPsh() {
            return psh;
        }

        public boolean isRst() {
            return rst;
        }

        public boolean isSyn() {
            return syn;
        }

        public boolean isFin() {
            return fin;
        }

        public short getWindow() {
            return Window;
        }

        public short getChecksum() {
            return checksum;
        }

        public short getUrgPointer() {
            return urgPointer;
        }

        public List<TCPOption> getOptions() {
            return options;
        }

        public byte[] getPadding() {
            return padding;
        }

        public static final class TcpHeaderBuilder {
            private TcpPort srcPort;
            private TcpPort destPort;
            private Integer seqNumber;
            private Integer ackNumber;
            private byte dataoffset;
            private byte reserved;
            private boolean urg;
            private boolean ack;
            private boolean psh;
            private boolean rst;
            private boolean syn;
            private boolean fin;
            private short Window;
            private short checksum;
            private short urgPointer;
            private List<TCPOption> options;
            private byte[] padding;

            private boolean sealed;

            private TcpHeaderBuilder() {
            }

            private TcpHeaderBuilder(byte[] rawData, int offset, int len) {

            }

            public void isSealed() {
                if (sealed)
                    throw new UnsupportedOperationException();
            }

            public TcpHeaderBuilder srcPort(TcpPort srcPort) {
                isSealed();
                this.srcPort = srcPort;
                return this;
            }

            public TcpHeaderBuilder destPort(TcpPort destPort) {
                isSealed();
                this.destPort = destPort;
                return this;
            }

            public TcpHeaderBuilder seqNumber(int seqNumber) {
                isSealed();
                this.seqNumber = seqNumber;
                return this;
            }

            public TcpHeaderBuilder ackNumber(int ackNumber) {
                isSealed();
                this.ackNumber = ackNumber;
                return this;
            }

            public TcpHeaderBuilder dataOffset(byte dataoffset) {
                isSealed();
                this.dataoffset = dataoffset;
                return this;
            }

            public TcpHeaderBuilder reserved(byte reserved) {
                isSealed();
                this.reserved = reserved;
                return this;
            }

            public TcpHeaderBuilder urg(boolean urg) {
                isSealed();
                this.urg = urg;
                return this;
            }

            public TcpHeaderBuilder ack(boolean ack) {
                isSealed();
                this.ack = ack;
                return this;
            }

            public TcpHeaderBuilder psh(boolean psh) {
                isSealed();
                this.psh = psh;
                return this;
            }

            public TcpHeaderBuilder rst(boolean rst) {
                isSealed();
                this.rst = rst;
                return this;
            }

            public TcpHeaderBuilder syn(boolean syn) {
                isSealed();
                this.syn = syn;
                return this;
            }

            public TcpHeaderBuilder fin(boolean fin) {
                isSealed();
                this.fin = fin;
                return this;
            }

            public TcpHeaderBuilder windowSize(short windowSize) {
                isSealed();
                this.Window = windowSize;
                return this;
            }

            public TcpHeaderBuilder checksum(short checksum) {
                isSealed();
                this.checksum = checksum;
                return this;
            }

            public TcpHeaderBuilder urgentPointer(short urgentPointer) {
                isSealed();
                this.urgPointer = urgentPointer;
                return this;
            }

            public TcpHeaderBuilder options(List<TCPOption> options) {
                isSealed();
                this.options = options;
                return this;
            }

            public TcpHeaderBuilder options(TCPOption... options) {
                isSealed();
                this.options = Arrays.asList(options);
                return this;
            }

            public TcpHeaderBuilder options(TCPOption option) {
                isSealed();
                this.options = Arrays.asList(option);
                return this;
            }

            public TcpHeader build() {
                validate(this);
                return new TcpHeader(this);
            }

            private void validate(TcpHeaderBuilder tcpHeaderBuilder) {
                if (tcpHeaderBuilder.srcPort == null) {
                    throw new IllegalStateException("sourcePort is not set");
                }
                if (tcpHeaderBuilder.destPort == null) {
                    throw new IllegalStateException("destinationPort is not set");
                }
                if (tcpHeaderBuilder.seqNumber == null) {
                    throw new IllegalStateException("sequenceNumber is not set");
                }
                if (tcpHeaderBuilder.ackNumber == null) {
                    throw new IllegalStateException("acknowledgementNumber is not set");
                }
                if (tcpHeaderBuilder.dataoffset == 0) {
                    throw new IllegalStateException("dataOffset is not set");
                }

                // TODO To be completed

            }
        }

    }

    public interface TCPOption {

        public TcpOptionKind getKind();

        public int length();

        public byte[] getRawData();
    }

}
