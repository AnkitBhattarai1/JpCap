package org.jcap.Core.Packets;

public class RawPacket implements Packet {

    private byte[] data;

    private RawPacket(RawPacketBuilder builder) {
        this.data = builder.data;
    }

    @Override
    public int length() {
        return data.length;
    }

    @Override
    public byte[] getRawData() {
        byte[] copy = new byte[data.length];
        System.arraycopy(data, 0, copy, 0, copy.length);
        return copy;
    }

    public static RawPacketBuilder Builder(byte[] rawData, int offset, int len) {
        return new RawPacketBuilder(rawData, offset, len);
    }

    public static final class RawPacketBuilder implements PacketBuilder {

        private byte[] data;

        private boolean sealed;

        public RawPacketBuilder(byte[] data, int offset, int len) {
            this.data = new byte[len];
            System.arraycopy(data, offset, this.data, 0, len);
            this.sealed = true;
        }

        public RawPacketBuilder(byte[] data) {
            if (sealed)
                throw new UnsupportedOperationException();
            this.data = data;
            this.sealed = true;
        }

        public RawPacketBuilder(RawPacket packet) {
            this.data = packet.getRawData();
            this.sealed = true;
        }

        @Override
        public RawPacket build() {
            return new RawPacket(this);
        }

    }

    @Override
    public Header getHeader() {
        return null;
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

}

// Add any additional methods or functionality specific to RawPacket here
