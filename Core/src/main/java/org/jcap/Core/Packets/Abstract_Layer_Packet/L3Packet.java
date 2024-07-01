package org.jcap.Core.Packets.Abstract_Layer_Packet;

import java.net.InetAddress;

import org.jcap.Core.Packets.Packet;

public interface L3Packet extends Packet {

    @Override
    public L3Header getHeader();

    public interface L3Header extends Header {

        public InetAddress getSourceAddress();

        public InetAddress getDestinationAddress();

    }
}
