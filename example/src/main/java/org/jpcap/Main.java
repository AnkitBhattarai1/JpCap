package org.jpcap;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.jcap.Core.Native.NativeWpcapMapping.pcap_pkthdr;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.function.BiConsumer;

import org.jcap.Core.JpCap;
import org.jcap.Core.Interfaces.JpCapNetworkInterface;

public class Main {

    public static void main(String[] args) {

        List<JpCapNetworkInterface> interfaces = JpCap.findAllDevs();
        Pointer p = JpCap.openInteface(interfaces.get(3).name);

        System.out.println("Listening");

        File f = new File("C:/Users/Ankit/Desktop/" + System.currentTimeMillis() + ".txt");
        try {
            f.createNewFile();
        } catch (Exception e) {
            // TODO: handle exception
        }

        BiConsumer<PointerByReference, PointerByReference> onCapture = (header, packet) -> {
            Pointer pkt = packet.getValue();
            pcap_pkthdr hdr = new pcap_pkthdr(header.getValue());

            for (int i = 0; i < hdr.len; i++) {
                System.out.print((char) pkt.getByte(i));
                try {
                    // System.out.print(f.toString());
                    FileWriter fw = new FileWriter(f, true);
                    fw.write((char) pkt.getByte(i));
                    fw.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
            System.out.println();
        };

        JpCap.capture(p, onCapture);

    }
}