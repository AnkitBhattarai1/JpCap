package org.jcap;

import org.jcap.Core.Native.NativeWpcapMapping;

import com.sun.jna.ptr.PointerByReference;



public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));

        // PointerByReference alldevs = new PointerByReference();
        // //char [] errbuf = new char[256];
        // NativeWpcapMapp.PcapErrbuf errbuf = new NativeWpcapMapp.PcapErrbuf();

        // if(NativeWpcapMapp.pcap_findalldevs(alldevs, errbuf)!=0){
        //     System.err.println("Error calling pcap_findalldevs: " + errbuf);
        //     return;
        // }

        // NativeWpcapMapp.pcap_if device = new NativeWpcapMapp.pcap_if(alldevs.getValue());

        // while (device != null) {
        //     System.out.println("Device Name: " + device.name);
        //     System.out.println("Description: " + (device.description != null ? device.description : "No description available"));
            
        //     // Processing addresses for each device
        //     NativeWpcapMapp.pcap_addr.ByReference address = device.addresses;
        //     while (address != null) {
        //         NativeWpcapMapp.soc_addr sa = address.addr;
        //         if (sa != null) {
        //             // Here, we just print the raw byte array. In a real application, you would convert this to a human-readable address.
        //             System.out.println("\tAddress (Raw): " + java.util.Arrays.toString(sa.sa_data));
        //         }
        //         address = address.next;
        //     }
        //     device = device.next;
        //     System.out.println("-----------------------------------------------");
        // }
        //  // Free the device list
        //  NativeWpcapMapp.pcap_freealldevs(alldevs.getValue());
        
    }
}