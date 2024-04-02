package org.jcap.Core.Address;

import java.net.InetAddress;

import org.jcap.Core.Native.NativeWpcapMapping.pcap_addr;
import org.jcap.Core.Native.NativeWpcapMapping.soc_addr;

public class JpCapIPv4Address extends JpCapAbstractAddress {

    

    public JpCapIPv4Address(pcap_addr pcapAddr, short saFamily, String devName) throws NullPointerException {
        super(pcapAddr, saFamily, devName);
        //TODO Auto-generated constructor stub
    }

    @Override
    public InetAddress getAddress() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAddress'");
    }

    @Override
    public InetAddress getSubNetMask() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubNetMask'");
    }

    @Override
    public InetAddress getbrodAddress() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getbrodAddress'");
    }

    @Override
    public InetAddress getDestinationAddress() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDestinationAddress'");
    }

    @Override
    protected InetAddress ntoInetAddress(soc_addr sa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ntoInetAddress'");
    }
    
}
