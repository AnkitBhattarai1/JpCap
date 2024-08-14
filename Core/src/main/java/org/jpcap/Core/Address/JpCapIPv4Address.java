package org.jpcap.Core.Address;

import java.net.InetAddress;

import org.jpcap.Core.Native.NativeWpcapMapping.pcap_addr;
import org.jpcap.Core.Native.NativeWpcapMapping.soc_addr;
import org.jpcap.Core.Native.NativeWpcapMapping.sockaddr_in;
import org.jpcap.Core.Utils.InetConverter;

/**
 * Represents an IPv4 address associated with a network interface, extending the
 * {@link JpCapAbstractAddress} to specifically handle IPv4 addresses. This
 * class provides a concrete implementation of converting low-level address
 * structures into Java {@link InetAddress} instances suitable for IPv4.
 * 
 * <p>
 * Methods overridden from {@link JpCapAbstractAddress} ensure the correct type
 * casting from {@link InetAddress} to IPv4-specific addresses and the
 * specialized conversion of address data to {@link InetAddress} objects
 * considering IPv4 specifics.
 * </p>
 *
 * @see JpCapAbstractAddress
 */
public class JpCapIPv4Address extends JpCapAbstractAddress {

	/**
	 * Constructs a JpCapIPv4Address object initializing its address fields based on
	 * the provided pcap_addr structure and address family.
	 *
	 * @param pcapAddr The pcap_addr structure containing network addresses.
	 * @param saFamily The address family, expected to be compatible with IPv4
	 *                 addresses.
	 * @param devName  The name of the network device, used for diagnostic purposes.
	 * @throws NullPointerException if {@code pcapAddr} is null.
	 */
	public JpCapIPv4Address(pcap_addr pcapAddr, short saFamily, String devName) throws NullPointerException {
		super(pcapAddr, saFamily, devName);
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
		sockaddr_in address = new sockaddr_in(sa.getPointer());
		return InetConverter.toInet4Address(address.sin_addr);
	}

}
