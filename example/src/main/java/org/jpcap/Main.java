package org.jpcap;

import com.sun.jna.ptr.PointerByReference;
import org.jcap.Core.Native.NativeWpcapMapping;

import java.util.ArrayList;
import java.util.List;

import org.jcap.Core.Interfaces.JpCapNetworkInterface;

public class Main {
    public static void main(String[] args) {

        PointerByReference alldevs = new PointerByReference();
        NativeWpcapMapping.PcapErrbuf errbuf = new NativeWpcapMapping.PcapErrbuf();

        List<JpCapNetworkInterface> interfaces = new ArrayList<>();

        if (NativeWpcapMapping.pcap_findalldevs(alldevs, errbuf) != 0) {
            System.err.println("Error callingpcap_findalldevs:" + errbuf);
            return;
        }

        NativeWpcapMapping.pcap_if device = new NativeWpcapMapping.pcap_if(alldevs.getValue());

        while (device != null) {
            interfaces.add(new JpCapNetworkInterface(device, false));
            device = device.next;
        }

        interfaces.forEach(System.out::println);

        NativeWpcapMapping.pcap_freealldevs(alldevs.getValue());
    }
}