package org.jcap.Core.Packets.Link;

import org.jcap.Core.Address.MacAddress;
import org.jcap.Core.Constants.NamedCodes.L2.EtherType;
import org.jcap.Core.Packets.Packet;
import org.jcap.Core.Utils.ByteOperations;

public record EthernetPacket(
        EthernetHeader header,
        Packet playload)
        implements Packet {

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

    public record EthernetHeader(
            MacAddress dstAddress,
            MacAddress srcAddress,
            EtherType etherType) {

        public static final class EthernetHeaderBuilder {

            private static final int DST_ADDR_OFFSET = 0;
            private static final int DST_ADDR_SIZE = MacAddress.SIZE_IN_BYTES;
            private static final int SRC_ADDR_OFFSET = DST_ADDR_OFFSET + DST_ADDR_SIZE;
            private static final int SRC_ADDR_SIZE = MacAddress.SIZE_IN_BYTES;
            private static final int TYPE_OFFSET = SRC_ADDR_OFFSET + SRC_ADDR_SIZE;
            private static final int TYPE_SIZE = Short.BYTES;
            private static final int ETHERNET_HEADER_SIZE = TYPE_OFFSET + TYPE_SIZE;

            private EthernetHeaderBuilder() {
            }

            private EthernetHeaderBuilder(byte[] rawData, int offset, int length) {
                // Todo Implementation is to be done....
            }

        }

    }

}
