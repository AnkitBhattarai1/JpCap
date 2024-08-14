package org.jcap.Core.Address;

import java.net.Inet6Address;
import java.net.InetAddress;

import org.jcap.Core.Native.NativeWpcapMapping.pcap_addr;
import org.jcap.Core.Native.NativeWpcapMapping.soc_addr;
import org.jcap.Core.Native.NativeWpcapMapping.socaddr_in6;
import org.jcap.Core.Utils.InetConverter;

/**
 * Represents an IPv6 address associated with a network interface, extending the
 * {@link JpCapAbstractAddress} to specifically handle IPv6 addresses. This
 * class provides a concrete implementation of converting low-level address
 * structures into Java {@link InetAddress} instances suitable for IPv6.
 * 
 * <p>
 * Methods overridden from {@link JpCapAbstractAddress} ensure the correct type
 * casting from {@link InetAddress} to IPv6-specific addresses and the
 * specialized conversion of address data to {@link InetAddress} objects
 * considering IPv6 specifics.
 * </p>
 *
 * @see JpCapAbstractAddress
 * @see InetAddress
 */
public class JpCapIPv6Address extends JpCapAbstractAddress {

	public JpCapIPv6Address(pcap_addr pcapAddr, short saFamily, String devName) throws NullPointerException {
		super(pcapAddr, saFamily, devName);
	}

	@Override
	protected InetAddress toInetAddress(soc_addr sa) {
		socaddr_in6 address = new socaddr_in6(sa.getPointer());

		return InetConverter.toInet6Address(address.sin6_addr, address.sin6_scope_id);

	}

	@Override
	public InetAddress getAddress() {
		return (Inet6Address) super.getAddress();
	}

	@Override
	public InetAddress getDestinationAddress() {
		return (Inet6Address) super.getDestinationAddress();
	}

	@Override
	public InetAddress getSubNetMask() {
		return (Inet6Address) super.getSubNetMask();
	}

	@Override
	public InetAddress getbrodAddress() {
		return (Inet6Address) super.getbrodAddress();
	}

}