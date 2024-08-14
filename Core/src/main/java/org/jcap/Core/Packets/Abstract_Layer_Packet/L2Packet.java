package org.jcap.Core.Packets.Abstract_Layer_Packet;

import org.jcap.Core.Address.LinkLayerAddress;
import org.jcap.Core.Packets.Packet;

public interface L2Packet extends Packet {

    @Override
    public L2Header getHeader();

    public static interface L2PacketBuilder extends PacketBuilder {

        @Override
        public L2Packet build();
    }

    public static interface L2Header extends Header {

        public LinkLayerAddress getsrcAddress();

        public LinkLayerAddress getDestAddress();
    }

}
