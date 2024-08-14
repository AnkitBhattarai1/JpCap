package org.jpcap.Core.Packets.Abstract_Layer_Packet;

import java.net.InetAddress;

import org.jpcap.Core.Packets.Packet;

public interface L3Packet extends Packet {

    @Override
    public L3Header getHeader();

    public static interface L3PacketBuilder extends PacketBuilder {
        public L3Packet build();
    }

    public interface L3Header extends Header {

        public InetAddress getSourceAddress();

        public InetAddress getDestinationAddress();

        public static interface L3HeaderBuilder extends HeaderBuilder {

            public L3Header build();
        }

    }

}
