package org.jpcap.Core.Interfaces;

import java.util.ArrayList;
import java.util.List;

import org.jpcap.Core.Address.Inets;
import org.jpcap.Core.Address.JpCapAddress;
import org.jpcap.Core.Address.JpCapIPv4Address;
import org.jpcap.Core.Address.JpCapIPv6Address;
import org.jpcap.Core.Native.NativeWpcapMapping.pcap_addr;
import org.jpcap.Core.Native.NativeWpcapMapping.pcap_if;

public class JpCapNetworkInterface {

	public String name;
	public String description;
	public List<JpCapAddress> addresses = new ArrayList<>();

	public JpCapNetworkInterface(pcap_if pcapIf, boolean local) {
		this.name = pcapIf.name;
		this.description = pcapIf.description;

		for (pcap_addr pcapAddr = pcapIf.addresses; pcapAddr != null; pcapAddr = pcapAddr.next) {
			short sa_family = (pcapAddr != null) ? pcapAddr.addr.getFamily()
					: (pcapAddr.netmask) != null ? pcapAddr.netmask.getFamily()
							: pcapAddr.brodaddr != null ? pcapAddr.brodaddr.getFamily()
									: pcapAddr.dstaddr != null ? pcapAddr.dstaddr.getFamily()
											: /* default value */Inets.AF_UNSPEC;

			if (sa_family == Inets.AF_INET)
				addresses.add(new JpCapIPv4Address(pcapAddr, sa_family, name));

			else if (sa_family == Inets.AF_INET6)
				addresses.add(new JpCapIPv6Address(pcapAddr, sa_family, name));

			else {
			}
		}
	}

	@Override
	public String toString() {
		return String.format("""
				Name: %s
				Description: %s
				Addresses:%s
				""", name, description, addresses);
	}

}