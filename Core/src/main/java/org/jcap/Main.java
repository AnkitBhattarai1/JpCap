package org.jcap;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jcap.Core.Address.JpCapAddress;
import org.jcap.Core.Interfaces.JpCapNetworkInterface;
import org.jcap.Core.Native.NativeWpcapMapping;
import org.jcap.Core.Packets.DNS.DnsDomainName;
import org.jcap.Core.Packets.DNS.DnsRDataA;
import org.jcap.Core.Utils.ByteOperations;

import com.sun.jna.ptr.PointerByReference;

public class Main {
    public static void main(String[] args) {
        // System.out.println(System.getProperty("os.name"));

        // PointerByReference alldevs = new PointerByReference();
        // // char [] errbuf = new char[256];
        // NativeWpcapMapping.PcapErrbuf errbuf = new NativeWpcapMapping.PcapErrbuf();

        // if (NativeWpcapMapping.pcap_findalldevs(alldevs, errbuf) != 0) {
        // System.err.println("Error calling pcap_findalldevs: " + errbuf);
        // return;
        // }

        // NativeWpcapMapping.pcap_if device = new
        // NativeWpcapMapping.pcap_if(alldevs.getValue());
        // // JpCapNetworkInterface intf = new JpCapNetworkInterface(device, false);
        // List<JpCapNetworkInterface> list = new ArrayList<>();

        // while (device != null) {
        // list.add(new JpCapNetworkInterface(device, false));
        // device = device.next;
        // }

        // for (JpCapNetworkInterface intf : list) {
        // System.out.println("Device Name: " + intf.name);
        // System.out.println(
        // "Description: " + (intf.description != null ? intf.description : "No
        // description available"));

        // // Processing addresses for each device
        // // NativeWpcapMapping.pcap_addr.ByReference address = device.addresses;
        // List<JpCapAddress> address = intf.addresses;

        // for (JpCapAddress add : address) {
        // // NativeWpcapMapping.soc_addr sa = address.addr;
        // // JpCapAbstractAddress a ;

        // // if(sa.sa_family==Inets.AF_INET) a = new
        // // JpCapIPv4Address(address,sa.sa_family,"Ankit");
        // // else a= new JpCapIPv6Address(address, address.addr.sa_family, "Ankit");
        // // sockadr_in socaddIn = new sockadr_in(sa.getPointer());
        // // Inet4Address a = Inets.toInet4Address(socaddIn.sin_addr);

        // if (add != null) {
        // // Here, we just print the raw byte array. In a real application, you would
        // // convert this to a human-readable address.
        // // System.out.println("\tAddress (Raw): " +
        // // java.util.Arrays.toString(sa.sa_data));
        // System.out.println(add.getAddress());
        // }
        // // address = address.next;
        // }
        // // device = device.next;
        // System.out.println("-----------------------------------------------");
        // }
        // // Free the device list
        // NativeWpcapMapping.pcap_freealldevs(alldevs.getValue());
        // byte[] x = ByteOperations.getByteArray((short) 1000, ByteOrder.BIG_ENDIAN);

        // System.out.println(Arrays.toString(x));
        // System.out.println(ByteOperations.getShort(x, 0));

        // System.out.println();

        DnsDomainName a = DnsDomainName.Builder().labels(List.of("google", "com")).build();

        DnsDomainName b = DnsDomainName.Builder(a.getRawData(), 0, a.getRawData().length).build();
        System.out.println(a);
        System.out.println(b);

    }
}