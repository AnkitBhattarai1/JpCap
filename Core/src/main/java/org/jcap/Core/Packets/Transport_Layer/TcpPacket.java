package org.jcap.Core.Packets.Transport_Layer;

import java.util.List;

import org.jcap.Core.Constants.NamedCodes.L4.Port;
import org.jcap.Core.Constants.NamedCodes.L4.TcpOptionKind;
import org.jcap.Core.Constants.NamedCodes.L4.TcpPort;
import org.jcap.Core.Packets.Abstract_Layer_Packet.L4Packet.L4Header;

public class TcpPacket {

    /*
     * TCP Header
     * 0------------------ 1-------------------2-------------------3
     * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |----------Source Port----------|----------Destination Port-----|
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |------------------------Sequence Number------------------------|
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |--------------------Acknowledgment Number----------------------|
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * | Data |-----------|U|A|P|R|S|F|-------------------------------|
     * |Offset|-Reserved--|R|C|S|S|Y|I|------------Window-------------|
     * |------|-----------|G|K|H|T|N|N|-------------------------------|
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |-----------Checksum------------|---------Urgent Pointer--------|
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |--------------------Options--------------------|----padding----|
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |-----------------------------data------------------------------|
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * 
     * 
     */

    public final class TcpHeader implements L4Header {

        private final TcpPort srcPort;
        private final TcpPort destPort;
        private final int seqNumber;
        private final int ackNumber;
        private final byte dataoffset;
        private final byte reserved;
        private final boolean urg;
        private final boolean ack;
        private final boolean psh;
        private final boolean rst;
        private final boolean syn;
        private final boolean fin;
        private final short Window;
        private final short checksum;
        private final short urgPointer;
        private final List<TCPOption> options;
        private final byte[] padding;

        private TcpHeader(TcpHeaderBuilder builder) {
            this.srcPort = builder.srcPort;
            this.destPort = builder.destPort;
            this.seqNumber = builder.seqNumber;
            this.ackNumber = builder.ackNumber;
            this.dataoffset = builder.dataoffset;
            this.reserved = builder.reserved;
            this.urg = builder.urg;
            this.ack = builder.ack;
            this.psh = builder.psh;
            this.rst = builder.rst;
            this.syn = builder.syn;
            this.fin = builder.fin;
            this.Window = builder.Window;
            this.checksum = builder.checksum;
            this.urgPointer = builder.urgPointer;
            this.options = builder.options;
            this.padding = builder.padding;
        }

        @Override
        public int length() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'length'");
        }

        @Override
        public byte[] getRawData() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getRawData'");
        }

        @Override
        public Port getSrcPort() {
            return srcPort;
        }

        @Override
        public Port getDestPort() {
            return destPort;
        }

        public static final class TcpHeaderBuilder {

            private TcpPort srcPort;
            private TcpPort destPort;
            private int seqNumber;
            private int ackNumber;
            private byte dataoffset;
            private byte reserved;
            private boolean urg;
            private boolean ack;
            private boolean psh;
            private boolean rst;
            private boolean syn;
            private boolean fin;
            private short Window;
            private short checksum;
            private short urgPointer;
            private List<TCPOption> options;
            private byte[] padding;

            private TcpHeaderBuilder() {
            }

            private TcpHeaderBuilder(byte[] rawData, int offset, int len) {

            }
        }

    }

    public interface TCPOption {

        public TcpOptionKind getKind();

        public int length();

        public byte[] getRawData();
    }

}
