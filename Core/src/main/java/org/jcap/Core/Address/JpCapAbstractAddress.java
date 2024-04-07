package org.jcap.Core.Address;

import java.net.InetAddress;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import org.jcap.Core.Native.NativeWpcapMapping.pcap_addr;
import org.jcap.Core.Native.NativeWpcapMapping.soc_addr;;

public abstract class JpCapAbstractAddress implements JpCapAddress {

	private final InetAddress address;
	private final InetAddress NetMask;
	private final InetAddress bordAddress;
	private final InetAddress destAddress;

	/**
	 * Evaluates whether a given network address is valid based on specific
	 * criteria.
	 * 
	 * This BiPredicate takes two parameters: a network address of type
	 * soc_addr.ByReference and a short value representing the expected address
	 * family. It returns true if the network address meets all the following
	 * conditions, otherwise false:
	 * 
	 * 1. The network address is not null. 2. The family of the network address is
	 * not set to Inets.AF_UNSPEC, indicating it's specified. 3. The family of the
	 * network address matches the expected family (the second parameter).
	 * 
	 * Note: This predicate was designed with the potential to emit warnings if the
	 * address family does not match the expected family, although the current
	 * implementation does not emit such warnings.
	 *
	 * @param address A network address as soc_addr.ByReference to evaluate for
	 *                validity.
	 * @param family  A short value representing the expected family of the network
	 *                address.
	 * @return true if the network address is valid based on the specified criteria,
	 *         false otherwise.
	 */
	BiPredicate<soc_addr.ByReference, Short> isValid = (address, family) -> {
		// if(address.getFamily()!=family){
		// //warn();
		// }
		return (address != null && address.getFamily() != Inets.AF_UNSPEC && address.getFamily() == family);
	};

	/**
	 * Converts a valid network address into an InetAddress.
	 * 
	 * This BiFunction attempts to convert a given network address to an InetAddress
	 * if it passes validation checks defined by the {@code isValid} BiPredicate.
	 * The validation checks if the address is non-null, not unspecified
	 * (AF_UNSPEC), and matches the expected address family.
	 *
	 * @param addr     The network address to convert, wrapped in a
	 *                 soc_addr.ByReference object.
	 * @param saFamily The expected address family as a short value.
	 * @return An InetAddress representation of the provided address if valid,
	 *         otherwise null.
	 */

	BiFunction<soc_addr.ByReference, Short, InetAddress> setValidAddress = (addr, saFamily) -> {
		return isValid.test(addr, saFamily) ? toInetAddress(addr) : null;
	};

	/**
	 * Initializes the address fields of a JpCapAbstractAddress object based on a
	 * pcap_addr structure. Validates and sets the main address, netmask, broadcast
	 * address, and destination address by converting them into InetAddress objects
	 * if they match the specified address family.
	 *
	 * @param pcapAddr The pcap_addr structure containing network addresses.
	 * @param saFamily The socket address family against which to validate
	 *                 addresses.
	 * @param devName  The name of the network device, used for warning messages.
	 * @throws NullPointerException if {@code pcapAddr} is null.
	 */

	public JpCapAbstractAddress(pcap_addr pcapAddr, short saFamily, String devName) throws NullPointerException {

		if (pcapAddr == null)
			throw new NullPointerException();

		this.address = setValidAddress.apply(pcapAddr.addr, saFamily);
		this.NetMask = setValidAddress.apply(pcapAddr.netmask, saFamily);
		this.bordAddress = setValidAddress.apply(pcapAddr.brodaddr, saFamily);
		this.destAddress = setValidAddress.apply(pcapAddr.dstaddr, saFamily);

		// if (pcapAddr.addr != null && pcapAddr.addr.getFamily() != Inets.AF_UNSPEC) {
		// if (pcapAddr.addr.getFamily() != saFamily) {
		// // warn(pcapAddr.addr.getFamily(), saFamily, devName, "addr");
		// this.address = null;
		// } else {
		// this.address = toInetAddress(pcapAddr.addr);
		// }
		// } else {
		// this.address = null;
		// }

		// if (pcapAddr.netmask != null && pcapAddr.addr.getFamily() != Inets.AF_UNSPEC)
		// {
		// if (pcapAddr.addr.getFamily() != saFamily) {
		// // warn(pcapAddr.addr.getFamily(), saFamily, devName, "addr");
		// this.NetMask = null;
		// } else {
		// this.NetMask = toInetAddress(pcapAddr.addr);
		// }
		// } else {
		// this.NetMask = null;
		// }

		// if (pcapAddr.brodaddr != null && pcapAddr.addr.getFamily() !=
		// Inets.AF_UNSPEC) {
		// if (pcapAddr.addr.getFamily() != saFamily) {
		// // warn(pcapAddr.addr.getFamily(), saFamily, devName, "addr");
		// this.bordAddress = null;
		// } else {
		// this.bordAddress = toInetAddress(pcapAddr.addr);
		// }
		// } else {
		// this.bordAddress = null;
		// }

		// // TODO:Renaming dstaddr to destAddress in pcapAddr......
		// if (pcapAddr.dstaddr != null && pcapAddr.addr.getFamily() != Inets.AF_UNSPEC)
		// {
		// if (pcapAddr.addr.getFamily() != saFamily) {
		// // warn(pcapAddr.addr.getFamily(), saFamily, devName, "addr");
		// this.destAddress = null;
		// } else {
		// this.destAddress = toInetAddress(pcapAddr.addr);
		// }
		// } else {
		// this.destAddress = null;
		// }
	}

	protected abstract InetAddress toInetAddress(soc_addr sa);

	@Override
	public InetAddress getAddress() {
		return address;
	}

	@Override
	public InetAddress getDestinationAddress() {
		return destAddress;
	}

	@Override
	public InetAddress getSubNetMask() {
		return NetMask;
	}

	@Override
	public InetAddress getbrodAddress() {
		return bordAddress;
	}

	@Override
	public String toString() {
		return "JpCapAbstractAddress [address=" + address + ", NetMask=" + NetMask + ", bordAddress=" + bordAddress
				+ ", destAddress=" + destAddress + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((NetMask == null) ? 0 : NetMask.hashCode());
		result = prime * result + ((bordAddress == null) ? 0 : bordAddress.hashCode());
		result = prime * result + ((destAddress == null) ? 0 : destAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JpCapAbstractAddress other = (JpCapAbstractAddress) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (NetMask == null) {
			if (other.NetMask != null)
				return false;
		} else if (!NetMask.equals(other.NetMask))
			return false;
		if (bordAddress == null) {
			if (other.bordAddress != null)
				return false;
		} else if (!bordAddress.equals(other.bordAddress))
			return false;
		if (destAddress == null) {
			if (other.destAddress != null)
				return false;
		} else if (!destAddress.equals(other.destAddress))
			return false;
		return true;
	}

}
