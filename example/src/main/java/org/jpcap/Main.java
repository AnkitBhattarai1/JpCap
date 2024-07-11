package org.jpcap;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.jcap.Core.Native.NativeWpcapMapping.pcap_pkthdr;
import org.jcap.Core.Packets.DNS.DnsDomainName;
import org.jcap.Core.Packets.DNS.DnsQuestion;
import org.jcap.Core.Packets.DNS.DnsDomainName.DnsDomainNameBuilder;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.function.BiConsumer;

import org.jcap.Core.JpCap;
import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsClass;
import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsResourceRecordType;
import org.jcap.Core.Interfaces.JpCapNetworkInterface;

public class Main {

    public static void main(String[] args) {

        // List<JpCapNetworkInterface> interfaces = JpCap.findAllDevs();
        // // Pointer p = JpCap.openInteface("ankit");

        // System.out.println("Listening");

        // File f = new File("C:/Users/Ankit/Desktop/" + System.currentTimeMillis() +
        // ".txt");
        // try {
        // f.createNewFile();
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        // BiConsumer<PointerByReference, PointerByReference> onCapture = (header,
        // packet) -> {
        // Pointer pkt = packet.getValue();
        // pcap_pkthdr hdr = new pcap_pkthdr(header.getValue());

        // for (int i = 0; i < hdr.len; i++) {
        // System.out.print((char) pkt.getByte(i));
        // try {
        // // System.out.print(f.toString());
        // FileWriter fw = new FileWriter(f, true);
        // fw.write((char) pkt.getByte(i));
        // fw.close();
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        // }
        // System.out.println();
        // };

        // JpCap.openAndCapture(interfaces.get(3).name, onCapture);

        DnsDomainName google = DnsDomainName.Builder().labels(List.of("www", "google", "com")).build();
        DnsResourceRecordType a = DnsResourceRecordType.A;
        DnsClass In = DnsClass.IN;
        DnsQuestion q = DnsQuestion.Builder().questionName(google).questionClass(In).questionType(a).build();

        System.out.println(q);

    }
}