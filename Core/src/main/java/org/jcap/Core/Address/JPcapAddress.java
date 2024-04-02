package org.jcap.Core.Address;

import java.net.InetAddress;

public interface JPcapAddress {

    public InetAddress getAddress();
    public InetAddress getSubNetMask();
    public InetAddress  getbrodAddress();
    public InetAddress getDestinationAddress();
    
}
