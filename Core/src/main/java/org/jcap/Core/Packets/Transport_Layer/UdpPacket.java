package org.jcap.Core.Packets.Transport_Layer;

import org.jcap.Core.Packets.Packet;
import org.jcap.Core.Packets.Abstract_Layer_Packet.L4Packet.L4Header;
import org.jcap.Core.Constants.NamedCodes.L4.Port;
import org.jcap.Core.Constants.NamedCodes.L4.UdpPort;
import org.jcap.Core.Utils.ByteOperations;

public class UdpPacket implements Packet {

    private final UdpHeader header;
    private final Packet playLoad;

    private UdpPacket() {
        this.header = null;
        this.playLoad = null;
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
    public Header getHeader() {
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

    @Override
    public PacketBuilder Builder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Builder'");
    }

    public static final class UdpHeader implements L4Header {

        private final UdpPort srcPort;
        private final UdpPort destPort;
        private final short length;
        private final short checkSum;

        private UdpHeader(UdpHeaderBuilder builder) {
            this.srcPort = builder.srcPort;
            this.destPort = builder.destPort;
            this.checkSum = builder.checkSum;
            this.length = builder.length;
        }

        private UdpHeaderBuilder Builder() {
            return new UdpHeaderBuilder();
        }

        private UdpHeaderBuilder Builder(byte[] rawData, int offset, int len) {
            return new UdpHeaderBuilder(rawData, offset, len);
        }

        public final static class UdpHeaderBuilder {
            private UdpPort srcPort;
            private UdpPort destPort;
            private short length;
            private short checkSum;

            private boolean sealed;

            private UdpHeaderBuilder() {
            }

            private UdpHeaderBuilder(byte[] rawData, int offset, int len) {
                ByteOperations.validate(rawData, offset, len);

                sealed = true;
            }

            public UdpHeader build() {
                validate(this);
                return new UdpHeader(this);
            }

            private void validate(UdpHeaderBuilder udpHeaderBuilder) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'validate'");
            }
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
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getSrcPort'");
        }

        @Override
        public Port getDestPort() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDestPort'");
        }

    }

}
