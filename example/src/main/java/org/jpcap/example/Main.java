package org.jpcap.example;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_compile;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_datalink;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_freealldevs;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_loop;
import static org.jpcap.Core.Native.NativeWpcapMapping.pcap_setfilter;

import java.net.Inet4Address;
import java.util.List;

import org.jpcap.Core.JpCap;
import org.jpcap.Core.Enums.LinkLayerType;
import org.jpcap.Core.Interfaces.JpCapNetworkInterface;
import org.jpcap.Core.Native.NativeHeaders.ip_header;
import org.jpcap.Core.Native.NativeWpcapMapping.bpf_program;
import org.jpcap.Core.Native.NativeWpcapMapping.pcap_handler;
import org.jpcap.Core.Native.NativeWpcapMapping.pcap_pkthdr;
import org.jpcap.Core.Packets.Network.IpV4Packet;
import org.jpcap.Core.Utils.InetConverter;

public class Main {
    public static void main(String[] args) {

        List<JpCapNetworkInterface> interfaces = JpCap.findAllDevs();
        // Pointer p = JpCap.openInteface("ankit");

        System.out.println("Listening");

        // File f = new File("C:/Users/Ankit/Desktop/" + System.currentTimeMillis() +
        // ".txt");
        // try {
        // f.createNewFile();
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        Pointer p = JpCap.openInterface(interfaces.get(3).name);

        if (pcap_datalink(p) != LinkLayerType.DLT_EN10MB.getCode()) {
            System.out.println("Not an ethernet interface");
            pcap_freealldevs(p);
            return;
        }

        NativeLong netmask;

        if (interfaces.get(3).addresses != null) {
            Inet4Address address = (Inet4Address) interfaces.get(3).addresses.get(0).getSubNetMask();
            netmask = InetConverter.toIn_addr(address).S_addr;

        } else {
            netmask = new NativeLong(0Xffffff);
        }

        bpf_program fcode = new bpf_program();
        String packetFilter = "ip and udp";

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

            byte[] data = ip.getByteArray(0, 200);

            System.out.println(InetConverter.toInet4Address(h.src_ip).getHostAddress() +
                    " -> "
                    + InetConverter.toInet4Address(h.dst_ip).getHostAddress());

            // System.out.println(Arrays.toString(data));
        };

        pcap_loop(p, 0, onCapture, null);
        IpV4Packet ip = new IpV4Packet();
        // DnsDomainName google = DnsDomainName.Builder().labels(List.of("www",
        // "google", "com")).build();
        // DnsResourceRecordType a = DnsResourceRecordType.A;
        // DnsClass In = DnsClass.IN;
        // DnsQuestion q =
        // DnsQuestion.Builder().questionName(google).questionClass(In).questionType(a).build();
        // byte[] rawData = q.getRawData();

        // DnsQuestion q2 = DnsQuestion.Builder(rawData, 0, rawData.length).build();

        // System.out.println(q);
        // System.out.println(q2);

        // var address = DnsRDataA.Builder(new byte[] { (byte) 192, (byte) 168, 1, 1
        // }).build();

        // System.out.println(address);
        // byte[] addressbyte = address.getRawData();
        // System.out.println(Arrays.toString(addressbyte));

    }
}