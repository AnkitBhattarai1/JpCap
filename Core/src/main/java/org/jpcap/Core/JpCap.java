package org.jpcap.Core;

import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_findalldevs;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_freealldevs;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_loop;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_next_ex;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_open_live;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.jpcap.Core.Interfaces.JpCapNetworkInterface;
import org.jpcap.Core.Native.NativeWpcapMapping.PcapErrbuf;
import org.jpcap.Core.Native.NativeWpcapMapping.pcap_handler;
import org.jpcap.Core.Native.NativeWpcapMapping.pcap_if;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class JpCap {

	private static final Object lock = new Object();

	/**
	 * Finds all available network interfaces on the system.
	 *
	 * @return a list of {@link JpCapNetworkInterface} representing all network
	 *         interfaces.
	 * @throws RuntimeException if the devices cannot be found.
	 */

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

	/**
	 * Opens a network interface for capturing packets.
	 *
	 * @param name the name of the network interface to open.
	 * @return a {@link Pointer} to the opened interface.
	 * @throws RuntimeException if the interface cannot be opened.
	 */
	public static Pointer openInterface(String name) {

		System.out.println("Attempting to call " + name);

		PcapErrbuf err = new PcapErrbuf();
		Pointer p = pcap_open_live(name, 65536, 1, 1000, err);

		if (p == null)
			throw new RuntimeException(err.toString());

		return p;
	}

	/**
	 * Captures packets from the specified network interface.
	 *
	 * @param pcap      the {@link Pointer} to the opened network interface.
	 * @param onCapture a {@link BiConsumer} to handle captured packets.
	 */
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

	/**
	 * Opens a network interface and captures packets.
	 *
	 * @param name      the name of the network interface to open.
	 * @param onCapture a {@link BiConsumer} to handle captured packets.
	 */
	public static void openAndCapture(String name, BiConsumer<PointerByReference, PointerByReference> onCapture) {
		capture(openInterface(name), onCapture);
	}

	/**
	 * Starts a packet capture loop.
	 *
	 * @param p         the {@link Pointer} to the opened network interface.
	 * @param cnt       the number of packets to capture; use -1 for infinite.
	 * @param onCapture a {@link pcap_handler} to handle captured packets.
	 * @param user      a {@link Pointer} to user-defined data.
	 * @return the result of the capture loop.
	 */
	public static int captureLoop(Pointer p, int cnt, pcap_handler onCapture, Pointer user) {
		int result = pcap_loop(p, cnt, onCapture, user);
		return result;
	}

}
