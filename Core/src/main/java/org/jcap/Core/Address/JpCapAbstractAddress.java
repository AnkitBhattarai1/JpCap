package org.jcap.Core.Address;

import java.net.InetAddress;

import org.jcap.Core.Native.NativeWpcapMapping.pcap_addr;
import org.jcap.Core.Native.NativeWpcapMapping.soc_addr;;

public abstract class JpCapAbstractAddress implements JPcapAddress {

	private final InetAddress address;
	private final InetAddress NetMask;
	private final InetAddress bordAddress;
	private final InetAddress destAddress;

	public JpCapAbstractAddress(pcap_addr pcapAddr, short saFamily, String devName) throws NullPointerException {

		if (pcapAddr == null)
			throw new NullPointerException();

		if (pcapAddr.addr != null && pcapAddr.addr.getFamily() != Inets.AF_UNSPEC) {
			if (pcapAddr.addr.getFamily() != saFamily) {
				// warn(pcapAddr.addr.getFamily(), saFamily, devName, "addr");
				this.address = null;
			} else {
				this.address = toInetAddress(pcapAddr.addr);
			}
		} else {
			this.address = null;
		}

		if (pcapAddr.netmask != null && pcapAddr.addr.getFamily() != Inets.AF_UNSPEC) {
			if (pcapAddr.addr.getFamily() != saFamily) {
				// warn(pcapAddr.addr.getFamily(), saFamily, devName, "addr");
				this.NetMask = null;
			} else {
				this.NetMask = toInetAddress(pcapAddr.addr);
			}
		} else {
			this.NetMask = null;
		}

		if (pcapAddr.brodaddr != null && pcapAddr.addr.getFamily() != Inets.AF_UNSPEC) {
			if (pcapAddr.addr.getFamily() != saFamily) {
				// warn(pcapAddr.addr.getFamily(), saFamily, devName, "addr");
				this.bordAddress = null;
			} else {
				this.bordAddress = toInetAddress(pcapAddr.addr);
			}
		} else {
			this.bordAddress = null;
		}

		// TODO:Renaming dstaddr to destAddress in pcapAddr......
		if (pcapAddr.dstaddr != null && pcapAddr.addr.getFamily() != Inets.AF_UNSPEC) {
			if (pcapAddr.addr.getFamily() != saFamily) {
				// warn(pcapAddr.addr.getFamily(), saFamily, devName, "addr");
				this.destAddress = null;
			} else {
				this.destAddress = toInetAddress(pcapAddr.addr);
			}
		} else {
			this.destAddress = null;
		}
	}

	protected abstract InetAddress toInetAddress(soc_addr sa);

	

	@Override
	public InetAddress getAddress() {
		// TODO Auto-generated method stub
		return address;
	}

	@Override
	public InetAddress getDestinationAddress() {
		// TODO Auto-generated method stub
		return destAddress;
	}

	@Override
	public InetAddress getSubNetMask() {
		// TODO Auto-generated method stub
		return NetMask;
	}

	@Override
	public InetAddress getbrodAddress() {
		// TODO Auto-generated method stub
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
