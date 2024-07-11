package org.jcap.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.jcap.Core.Interfaces.JpCapNetworkInterface;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.jcap.Core.Native.NativeWpcapMapping.PcapErrbuf;
import org.jcap.Core.Native.NativeWpcapMapping.pcap_if;

import static org.jcap.Core.Native.NativeWpcapMapping.pcap_findalldevs;
import static org.jcap.Core.Native.NativeWpcapMapping.pcap_freealldevs;
import static org.jcap.Core.Native.NativeWpcapMapping.pcap_next_ex;
import static org.jcap.Core.Native.NativeWpcapMapping.pcap_open;

public class JpCap {

	private static final Object lock = new Object();

	public static List<JpCapNetworkInterface> findAllDevs() {
		List<JpCapNetworkInterface> intf = new ArrayList<>();
		PointerByReference alldevs = new PointerByReference();
		PcapErrbuf buf = new PcapErrbuf();

		synchronized (lock) {

			if (pcap_findalldevs(alldevs, buf) != 0)
				throw new RuntimeException("Cannot find the devices....");// Custom exception is to be implemented....

			pcap_if dev_ls = new pcap_if(alldevs.getValue());

			while (dev_ls != null) {
				intf.add(new JpCapNetworkInterface(dev_ls, false));
				dev_ls = dev_ls.next;
			}

		}
		pcap_freealldevs(alldevs.getValue());
		return intf;
	}

	public static Pointer openInteface(String name) {
		PcapErrbuf err = new PcapErrbuf();
		Pointer p = pcap_open(name, 65536, 1, 1000, null, err);

		if (p == null)
			throw new RuntimeException(err.toString());// Custom exception is to be
		// implemented....

		return p;
	}

	public static void capture(Pointer pcap, BiConsumer<PointerByReference, PointerByReference> onCapture) {
		PointerByReference header = new PointerByReference();
		PointerByReference packet = new PointerByReference();
		int res;
		while ((res = pcap_next_ex(pcap, header, packet)) >= 0) {
			if (res == 0)
				continue;

			onCapture.accept(header, packet);
		}
	}

	public static void openAndCapture(String name, BiConsumer<PointerByReference, PointerByReference> onCapture) {
		capture(openInteface(name), onCapture);
	}

}
