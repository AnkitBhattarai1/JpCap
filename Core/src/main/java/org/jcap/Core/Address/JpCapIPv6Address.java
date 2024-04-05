package org.jcap.Core.Address;

import java.net.Inet6Address;
import java.net.InetAddress;

import org.jcap.Core.Native.NativeWpcapMapping.pcap_addr;
import org.jcap.Core.Native.NativeWpcapMapping.soc_addr;
import org.jcap.Core.Native.NativeWpcapMapping.socaddr_in6;
import org.jcap.Core.Native.NativeWpcapMapping.sockadr_in;
import org.jcap.Core.Utils.InetConverter;

/**
 * JpCaopIPv6Address
 */
public class JpCapIPv6Address extends JpCapAbstractAddress {


    public JpCapIPv6Address(pcap_addr pcapAddr, short saFamily, String devName) throws NullPointerException {
        super(pcapAddr, saFamily, devName);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected InetAddress toInetAddress(soc_addr sa) {
       socaddr_in6 address = new socaddr_in6(sa.getPointer());
		return InetConverter.toInet6Address(address.sin6_addr,address.sin6_scope_id);
       
    }

    @Override
    public InetAddress getAddress() {
        // TODO Auto-generated method stub
        return (Inet6Address)super.getAddress();
    }

    @Override
    public InetAddress getDestinationAddress() {
        // TODO Auto-generated method stub
        return (Inet6Address) super.getDestinationAddress();
    }

    @Override
    public InetAddress getSubNetMask() {
        // TODO Auto-generated method stub
        return (Inet6Address) super.getSubNetMask();
    }

    @Override
    public InetAddress getbrodAddress() {
        // TODO Auto-generated method stub
        return (Inet6Address) super.getbrodAddress();
    }

    
}