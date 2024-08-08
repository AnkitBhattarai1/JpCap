package org.jcap.Core.Packets.Link;

import org.jcap.Core.Address.LinkLayerAddress;
import org.jcap.Core.Address.MacAddress;
import org.jcap.Core.Constants.NamedCodes.L2.EtherType;
import org.jcap.Core.Packets.Packet;
import org.jcap.Core.Packets.Abstract_Layer_Packet.L2Packet;
import org.jcap.Core.Packets.Abstract_Layer_Packet.L3Packet;
import org.jcap.Core.Packets.Abstract_Layer_Packet.L3Packet.L3PacketBuilder;
import org.jcap.Core.Utils.ByteOperations;

public class EthernetPacket
        implements L2Packet {

    EthernetHeader header;
    Packet playload;
    private final byte[] pad;

    private EthernetPacket(EthernetPacketBuilder builder) {
        this.header = builder.ethHeader;
        this.playload = builder.payload;
        this.pad = builder.pad;
    }

    @Override
    public int length() {
        int len = 0;
        len += header.length();

        if (playload != null)
            len += playload.length();

        len += pad.length;

        return len;
    }

    @Override
    public byte[] getRawData() {
        byte[] rawDAta = new byte[length()];

        byte[] hraw = header.getRawData();// rawData of the headerrrr...
        System.arraycopy(hraw, 0, rawDAta, 0, hraw.length);

        byte[] plraw = playload.getRawData();
        System.arraycopy(plraw, 0, rawDAta, hraw.length, plraw.length);

        System.arraycopy(pad, 0, rawDAta, plraw.length + hraw.length, pad.length);

        return rawDAta;
    }

    @Override
    public L2Header getHeader() {
        return header;
    }

    @Override
    public Packet getPlayLoad() {
        return playload;
    }

    public byte[] getPad() {
        return pad;
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

    public static EthernetPacketBuilder Builder(byte[] rawData, int offset, int len) {
        return new EthernetPacketBuilder(rawData, offset, len);
    }

    public static EthernetPacketBuilder Builder() {
        return new EthernetPacketBuilder();
    }

    public static final class EthernetPacketBuilder implements L2PacketBuilder {

        private EthernetHeader ethHeader;
        private Packet payload;

        private byte[] pad;

        private boolean sealed;

        private EthernetPacketBuilder(byte[] rawData, int offset, int len) {

            this.ethHeader = EthernetHeader.Builder(rawData, offset, len).build();

            if (ethHeader.getEtherType().getValue() <= EtherType.IEEE802_3_MAX_LENGTH) {

                int payloadLength = ethHeader.etherType.getValue();
                int padLength = len - ethHeader.length() - payloadLength;
                int payloadoffset = offset + ethHeader.length();

                if (padLength < 0)
                    throw new IllegalArgumentException(
                            "The value of the ether type (length) field seems to be wrong: "
                                    + ethHeader.getEtherType().getValue());

                if (payloadLength > 0) {
                    Class<? extends L3Packet> clazz = ethHeader.getEtherType().getPacketClass();

                    try {
                        L3PacketBuilder b = (L3PacketBuilder) clazz
                                .getDeclaredMethod("Builder", byte[].class, int.class, int.class).invoke(null, rawData,
                                        payloadoffset, payloadLength);
                        this.payload = b.build();
                    } catch (Exception e) {
                        this.payload = null;
                        e.printStackTrace();
                    }
                } else
                    this.payload = null;

                // if (padLength > 0) {
                // this.pad = ByteOperations.getSubArray(rawData, payloadoffset +
                // payloadLength, padLength);
                // } else
                // this.pad = new byte[0];

                this.pad = (padLength > 0)
                        ? ByteOperations.getSubArray(rawData, payloadoffset + payloadLength, padLength)
                        : new byte[0];
            }

            else {
                int payloadAndPadLength = len - ethHeader.length();
                int payLoadOffset = offset + ethHeader.length();

                if (payloadAndPadLength > 0) {

                    Class<? extends L3Packet> clazz = ethHeader.getEtherType().getPacketClass();

                    try {
                        L3PacketBuilder b = (L3PacketBuilder) clazz
                                .getDeclaredMethod("Builder", byte[].class, int.class, int.class).invoke(null, rawData,
                                        offset + ethHeader.length(), payloadAndPadLength);
                        this.payload = b.build();
                    } catch (Exception e) {
                        this.payload = null;
                        e.printStackTrace();
                    }

                    int padlength = payloadAndPadLength - payload.length();

                    // if (padlength > 0)
                    // this.pad = ByteOperations.getSubArray(rawData, payLoadOffset +
                    // payload.length(), padlength);
                    // else
                    // this.pad = new byte[0];

                    this.pad = (padlength > 0)
                            ? ByteOperations.getSubArray(rawData,
                                    payLoadOffset + ((this.payload != null) ? this.payload.length() : 0), padlength)
                            : new byte[0];
                } else {
                    this.payload = null;
                    this.pad = new byte[0];
                }
            }

            this.sealed = true;
        }

        private EthernetPacketBuilder() {
            // For custom building of the packet
        }

        public EthernetPacketBuilder ethHeader(EthernetHeader ethHeader) {

            if (sealed)
                throw new UnsupportedOperationException();
            this.ethHeader = ethHeader;

            return this;
        }

        public EthernetPacketBuilder payload(L3Packet payload) {
            if (sealed)
                throw new UnsupportedOperationException();
            // Here we need to check if the supplied playload is of the same type as the
            // etherType or not.....
            return this;
        }

        public EthernetPacketBuilder pad(byte[] pad) {
            if (sealed)
                throw new UnsupportedOperationException();
            this.pad = pad;
            return this;
        }

        @Override
        public EthernetPacket build() {
            validate();
            return new EthernetPacket(this);
        }

        private void validate() {
            // TOdo validation is to be done.....;
        }
    }

    public static class EthernetHeader implements L2Header {

        MacAddress dstAddress;
        MacAddress srcAddress;
        EtherType etherType;

        private EthernetHeader(EthernetHeaderBuilder builder) {
            this.dstAddress = builder.dstAddress;
            this.srcAddress = builder.srcAddress;
            this.etherType = builder.etherType;
        }

        private static final int DST_ADDR_OFFSET = 0;
        private static final int DST_ADDR_SIZE = MacAddress.SIZE_IN_BYTES;
        private static final int SRC_ADDR_OFFSET = DST_ADDR_OFFSET + DST_ADDR_SIZE;
        private static final int SRC_ADDR_SIZE = MacAddress.SIZE_IN_BYTES;
        private static final int TYPE_OFFSET = SRC_ADDR_OFFSET + SRC_ADDR_SIZE;
        private static final int TYPE_SIZE = Short.BYTES;
        private static final int ETHERNET_HEADER_SIZE = TYPE_OFFSET + TYPE_SIZE;

        public static EthernetHeaderBuilder Builder() {
            return new EthernetHeaderBuilder();
        }

        public static EthernetHeaderBuilder Builder(byte[] rawData, int offset, int len) {
            return new EthernetHeaderBuilder(rawData, offset, len);

        }

        public MacAddress getDstAddress() {
            return dstAddress;
        }

        public MacAddress getSrcAddress() {
            return srcAddress;
        }

        public EtherType getEtherType() {
            return etherType;
        }

        @Override
        public int length() {
            return EthernetHeader.ETHERNET_HEADER_SIZE;

        }

        @Override
        public byte[] getRawData() {
            byte[] rawData = new byte[length()];
            System.arraycopy(dstAddress.getAddress(), 0, rawData, 0, MacAddress.SIZE_IN_BYTES);
            System.arraycopy(srcAddress.getAddress(), 0, rawData, MacAddress.SIZE_IN_BYTES, MacAddress.SIZE_IN_BYTES);

            var rawType = ByteOperations.getByteArray(etherType.getValue());
            System.arraycopy(rawType, 0, rawData, MacAddress.SIZE_IN_BYTES * 2, rawType.length);

            return rawData;
        }

        public static final class EthernetHeaderBuilder {

            MacAddress dstAddress;
            MacAddress srcAddress;
            EtherType etherType;
            private boolean sealed;

            private EthernetHeaderBuilder() {
            }

            private EthernetHeaderBuilder(byte[] rawData, int offset, int length) {
                if (length < ETHERNET_HEADER_SIZE)
                    throw new IllegalArgumentException("The data is too short to build an Ethernet Header");

                this.dstAddress = MacAddress.getByAddress(rawData, offset + DST_ADDR_OFFSET);
                this.srcAddress = MacAddress.getByAddress(rawData, offset + SRC_ADDR_OFFSET);
                this.etherType = EtherType.instanceOfCode(ByteOperations.getShort(rawData, offset + TYPE_OFFSET));

                sealed = true;
                // Todo Implementation is to be done....
            }

            public EthernetHeaderBuilder dstAddress(MacAddress dstAddress) {
                if (sealed)
                    throw new UnsupportedOperationException(
                            "Cannot re initialize the dst addr once it is initialized by using the rawData");

                this.dstAddress = dstAddress;
                return this;
            }

            public EthernetHeaderBuilder srcAddress(MacAddress dstAddress) {
                if (sealed)
                    throw new UnsupportedOperationException(
                            "Cannot re initialize the src addr once it is initialized by using the rawData");

                this.dstAddress = dstAddress;
                return this;
            }

            public EthernetHeaderBuilder etherType(EtherType etherType) {
                if (sealed)
                    throw new UnsupportedOperationException(
                            "Cannot re initialize the etherType once it is initialized by using the rawData");

                this.etherType = etherType;
                return this;
            }

            public EthernetHeader build() {
                return new EthernetHeader(this);
            }

            private void validate() {
                // TOdo Validation is to be done....
            }

        }

        @Override
        public LinkLayerAddress getsrcAddress() {
            return srcAddress;
        }

        @Override
        public LinkLayerAddress getDestAddress() {
            return dstAddress;
        }

    }

}
