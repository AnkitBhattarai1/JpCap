package org.jcap.Core.Address;

import java.net.InetAddress;
import java.net.spi.InetAddressResolver;

import org.jcap.Core.Native.NativeWpcapMapping.in_addr;
import org.jcap.Core.Native.NativeWpcapMapping.pcap_addr;
import org.jcap.Core.Native.NativeWpcapMapping.soc_addr;
import org.jcap.Core.Native.NativeWpcapMapping.sockadr_in;
import org.jcap.Core.Utils.InetConverter;

public class JpCapIPv4Address extends JpCapAbstractAddress {


	public JpCapIPv4Address(pcap_addr pcapAddr, short saFamily, String devName) throws NullPointerException {
		super(pcapAddr, saFamily, devName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InetAddress getAddress() {
		
		return (InetAddress) super.getAddress();
	}

	@Override
	public InetAddress getSubNetMask() {
		
		return (InetAddress) super.getSubNetMask();
	}

	@Override
	public InetAddress getbrodAddress() {
		
		return (InetAddress) super.getbrodAddress();
	}

	@Override
	public InetAddress getDestinationAddress() {
		
		return (InetAddress) super.getDestinationAddress();
	}

	@Override
	protected InetAddress toInetAddress(soc_addr sa) {
		sockadr_in address = new sockadr_in(sa.getPointer());
		return InetConverter.toInet4Address(address.sin_addr);
	}

}
