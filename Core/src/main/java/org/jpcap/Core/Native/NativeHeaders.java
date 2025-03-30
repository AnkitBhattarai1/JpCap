package org.jpcap.Core.Native;

import java.util.Arrays;
import java.util.List;

import org.jpcap.Core.Native.NativeWpcapMapping.in_addr;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class NativeHeaders {

    public static class ip_header extends Structure {
        public byte ver_ihl; // version and header length
        public byte tos; // type of service
        public short len; // total length
        public short id; // identification
        public short flags_offset;// flags and fragment offset
        public byte ttl; // time to live
        public byte proto; // protocol
        public short checksum; // checksum
        public in_addr src_ip; // source address
        public in_addr dst_ip; // destination address
        public int options; // options and padding

        public ip_header() {

        }

        public ip_header(Pointer p) {
            super(p);
            read();
        }

        public static final class ByReference extends ip_header implements Structure.ByReference {
        }

        @Override
        protected List<String> getFieldOrder() {

            return Arrays.asList("ver_ihl", "tos", "len", "id", "flags_offset", "ttl", "proto", "checksum", "src_ip",
                    "dst_ip", "options");
        }
    }

    public static class udp_header extends Structure {

        public short sport; // source port
        public short dport; // destination port
        public short len; // length
        public short checksum; // checksum

        public udp_header() {

        }

        public udp_header(Pointer p) {
            super(p);
            read();
        }

        public static final class ByReference extends udp_header implements Structure.ByReference {
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("sport", "dport", "len", "checksum");
        }
    }

    public static class tcp_header extends Structure {

    }

}
