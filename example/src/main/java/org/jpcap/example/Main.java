package org.jpcap.example;

import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_compile;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_datalink;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_freealldevs;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_loop;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_setfilter;

import java.net.Inet4Address;
import java.util.List;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

import org.jpcap.Core.JpCap;
import org.jpcap.Core.Enums.LinkLayerType;
import org.jpcap.Core.Interfaces.JpCapNetworkInterface;
import org.jpcap.Core.Native.NativeHeaders.ip_header;
import org.jpcap.Core.Native.NativeWpcapMapping.bpf_program;
import org.jpcap.Core.Native.NativeWpcapMapping.pcap_handler;
import org.jpcap.Core.Native.NativeWpcapMapping.pcap_pkthdr;
import org.jpcap.Core.Packets.Abstract_Layer_Packet.L3Packet;
import org.jpcap.Core.Packets.Link.EthernetPacket;
import org.jpcap.Core.Utils.InetConverter;

public class Main {
    public static void main(String[] args) {

        List<JpCapNetworkInterface> interfaces = JpCap.findAllDevs();

        System.out.println(interfaces);

        Pointer p = JpCap.openInterface(interfaces.get(0).name);

        if (pcap_datalink(p) != LinkLayerType.DLT_EN10MB.getCode()) {
            System.out.println("Not an ethernet interface");
            pcap_freealldevs(p);
            return;
        }

        NativeLong netmask;

        if (interfaces.get(0).addresses != null) {
            Inet4Address address = (Inet4Address) interfaces.get(0).addresses.get(0).getSubNetMask();
            netmask = InetConverter.toIn_addr(address).S_addr;

        } else {
            netmask = new NativeLong(0Xffffff);
        }

        bpf_program fcode = new bpf_program();
        String packetFilter = "ip and tcp";

        if (pcap_compile(p, fcode, packetFilter, 1, netmask.intValue()) < 0) {
            System.out.println("Error in compiling filter");
            pcap_freealldevs(p);
            return;
        }

        if (pcap_setfilter(p, fcode) < 0) {
            System.out.println("Error in setting filter");
            pcap_freealldevs(p);
            return;
        }

        pcap_handler onCapture = (arg, head, pkt) -> {
            // Pointer pkt = packet.getValue();
            pcap_pkthdr hdr = new pcap_pkthdr(head);

            Pointer ip = pkt.share(10); // why this is 10 i don't understand....

            // System.out.println(ip_head er.getByte(0));

            ip_header h = new ip_header(ip);
            ip_header h2 = new ip_header(pkt.share(6));

            byte[] data = ip.getByteArray(0, 200);

            System.out.println(InetConverter.toInet4Address(h.src_ip).getHostAddress() +
                    " -> "
                    + InetConverter.toInet4Address(h2.dst_ip).getHostAddress());


            byte[] rawData = pkt.getByteArray(0, hdr.caplen);
            //EthernetPacket e = EthernetPacket.Builder(rawData,0,rawData.length).build();
            EthernetPacket e = new EthernetPacket(rawData, 0, hdr.len);
            System.out.println(e.getHeader());
            System.out.println(((L3Packet)e.getPlayLoad()).getHeader().getSourceAddress());
            System.out.println(((L3Packet)e.getPlayLoad()).getHeader().getDestinationAddress());
        };

        pcap_loop(p, 0, onCapture, null);


    }
}
