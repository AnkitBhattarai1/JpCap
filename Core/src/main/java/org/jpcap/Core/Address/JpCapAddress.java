package org.jpcap.Core.Address;

import java.net.InetAddress;

public interface JpCapAddress {

	public InetAddress getAddress();

	public InetAddress getSubNetMask();

	public InetAddress getbrodAddress();

	public InetAddress getDestinationAddress();

}
