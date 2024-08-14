package org.jpcap.Core.Packets.Abstract_Layer_Packet;

import org.jpcap.Core.Constants.NamedCodes.L4.Port;
import org.jpcap.Core.Packets.Packet;

public interface L4Packet extends Packet {

    @Override
    public L4Header getHeader();

    public interface L4Header extends Header {

        public Port getSrcPort();

        public Port getDestPort();

    }

}
