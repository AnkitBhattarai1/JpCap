package org.jpcap.Core.Constants.NamedCodes.L3;

import org.jpcap.Core.Constants.NamedCodes.NamedCode;

/**
 * * @see <a href=
 * "http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xml">IANA
 * Registry</a>
 */
public class ProcNumber extends NamedCode<Byte, ProcNumber> {

    private final String protocol;

    protected ProcNumber(Byte value, String name, String protocol) {
        super(value, name);
        this.protocol = protocol;
        // TODO Auto-generated constructor stub
    }

    /** IPv6 Hop-by-Hop Option: 0 */
    public static final ProcNumber IPV6_HOPOPT = new ProcNumber((byte) 0, "HOPOPT", "IPv6 Hop-by-Hop Option");

    /** Internet Control Message (ICMPv4): 1 */
    public static final ProcNumber ICMP = new ProcNumber((byte) 1, "ICMP", "Internet Control Message (ICMPv4)");

    /** Internet Group Management(IGMP):2 */
    public static final ProcNumber IGMP = new ProcNumber((byte) 2, "IGMP", "Internet Group Management(IGMP)");

    /** Gateway-to-Gateway(GGP):3 */
    public static final ProcNumber GGP = new ProcNumber((byte) 3, "GGP", "Gateway-to-Gateway");

    /** IPv4: 4 */
    public static final ProcNumber IPV4 = new ProcNumber((byte) 4, "IPV4", "IPv4 encapsulation");

    /** Stream: 5 */
    public static final ProcNumber ST = new ProcNumber((byte) 5, "ST", "Stream");

    /** Transmission Control (TCP): 6 */
    public static final ProcNumber TCP = new ProcNumber((byte) 6, "TCP", "Transmission Control");

    /** CBT: 7 */
    public static final ProcNumber CBT = new ProcNumber((byte) 7, "CBT", "CBT");

    /** Exterior Gateway Protocol (EGP): 8 */
    public static final ProcNumber EGP = new ProcNumber((byte) 8, "EGP", "Exterior Gateway Protocol");

    /** IGP: 9 */
    public static final ProcNumber IGP = new ProcNumber((byte) 9, "IGP",
            "any private interior gateway (used by Cisco for their IGRP)");

    /** BBN RCC Monitoring: 10 */
    public static final ProcNumber BBN_RCC_MON = new ProcNumber((byte) 10, "BBN-RCC-MON", "BBN RCC Monitoring");

    /** NVP-II: 11 */
    public static final ProcNumber NVP_II = new ProcNumber((byte) 11, "NVP-II", "Network Voice Protocol");

    /** PUP: 12 */
    public static final ProcNumber PUP = new ProcNumber((byte) 12, "PUP", "PUP");

    /** ARGUS: 13 */
    public static final ProcNumber ARGUS = new ProcNumber((byte) 13, "ARGUS", "ARGUS");

    /** EMCON: 14 */
    public static final ProcNumber EMCON = new ProcNumber((byte) 14, "EMCON", "EMCON");

    /** XNET: 15 */
    public static final ProcNumber XNET = new ProcNumber((byte) 15, "XNET", "Cross Net Debugger");

    /** CHAOS: 16 */
    public static final ProcNumber CHAOS = new ProcNumber((byte) 16, "CHAOS", "Chaos");

    /** User Datagram (UDP): 17 */
    public static final ProcNumber UDP = new ProcNumber((byte) 17, "UDP", "User Datagram");

    /** Multiplexing: 18 */
    public static final ProcNumber MUX = new ProcNumber((byte) 18, "MUX", "Multiplexing");

    /** DCN Measurement Subsystems: 19 */
    public static final ProcNumber DCN_MEAS = new ProcNumber((byte) 19, "DCN-MEAS", "DCN Measurement Subsystems");

    /** Host Monitoring: 20 */
    public static final ProcNumber HMP = new ProcNumber((byte) 20, "HMP", "Host Monitoring");

    /** Packet Radio Measurement: 21 */
    public static final ProcNumber PRM = new ProcNumber((byte) 21, "PRM", "Packet Radio Measurement");

    /** XEROX NS IDP: 22 */
    public static final ProcNumber XNS_IDP = new ProcNumber((byte) 22, "XNS-IDP", "XEROX NS IDP");

    /** Trunk-1: 23 */
    public static final ProcNumber TRUNK_1 = new ProcNumber((byte) 23, "TRUNK-1", "Trunk-1");

    /** Trunk-2: 24 */
    public static final ProcNumber TRUNK_2 = new ProcNumber((byte) 24, "TRUNK-2", "Trunk-2");

    /** Leaf-1: 25 */
    public static final ProcNumber LEAF_1 = new ProcNumber((byte) 25, "LEAF-1", "Leaf-1");

    /** Leaf-2: 26 */
    public static final ProcNumber LEAF_2 = new ProcNumber((byte) 26, "LEAF-2", "Leaf-2");

    /** Reliable Data Protocol (RDP): 27 */
    public static final ProcNumber RDP = new ProcNumber((byte) 27, "RDP", "Reliable Data Protocol");

    /** Internet Reliable Transaction (IRTP): 28 */
    public static final ProcNumber IRTP = new ProcNumber((byte) 28, "IRTP", "Internet Reliable Transaction");

    /** ISO Transport Protocol Class 4 (ISO-TP4): 29 */
    public static final ProcNumber ISO_TP4 = new ProcNumber((byte) 29, "ISO-TP4", "ISO Transport Protocol Class 4");

    /** Bulk Data Transfer Protocol (NETBLT): 30 */
    public static final ProcNumber NETBLT = new ProcNumber((byte) 30, "NETBLT", "Bulk Data Transfer Protocol");

    /** MFE Network Services Protocol: 31 */
    public static final ProcNumber MFE_NSP = new ProcNumber((byte) 31, "MFE-NSP", "MFE Network Services Protocol");

    /** MERIT Internodal Protocol: 32 */
    public static final ProcNumber MERIT_INP = new ProcNumber((byte) 32, "MERIT-INP", "MERIT Internodal Protocol");

    /** Datagram Congestion Control Protocol (DCCP): 33 */
    public static final ProcNumber DCCP = new ProcNumber((byte) 33, "DCCP", "Datagram Congestion Control Protocol");

    /** Third Party Connect Protocol: 34 */
    public static final ProcNumber ThirdPC = new ProcNumber((byte) 34, "ThirdPC", "Third Party Connect Protocol");

    /** Inter-Domain Policy Routing Protocol: 35 */
    public static final ProcNumber IDPR = new ProcNumber((byte) 35, "IDPR", "Inter-Domain Policy Routing Protocol");

    /** XTP: 36 */
    public static final ProcNumber XTP = new ProcNumber((byte) 36, "XTP", "XTP");

    /** Datagram Delivery Protocol (DDP): 37 */
    public static final ProcNumber DDP = new ProcNumber((byte) 37, "DDP", "Datagram Delivery Protocol");

    /** IDPR Control Message Transport Proto: 38 */
    public static final ProcNumber IDPR_CMTP = new ProcNumber((byte) 38, "IDPR-CMTP",
            "IDPR Control Message Transport Proto");

    /** TP++ Transport Protocol: 39 */
    public static final ProcNumber TP_PLUS_PLUS = new ProcNumber((byte) 39, "TP++", "TP++ Transport Protocol");

    /** IL Transport Protocol: 40 */
    public static final ProcNumber IL = new ProcNumber((byte) 40, "IL", "IL Transport Protocol");

    /** IPv6 encapsulation: 41 */
    public static final ProcNumber IPV6 = new ProcNumber((byte) 41, "IPV6", "IPv6 encapsulation");

    /** Source Demand Routing Protocol: 42 */
    public static final ProcNumber SDRP = new ProcNumber((byte) 42, "SDRP", "Source Demand Routing Protocol");

    /** Routing Header for IPv6: 43 */
    public static final ProcNumber IPV6_ROUTE = new ProcNumber((byte) 43, "IPV6-ROUTE", "Routing Header for IPv6");

    /** Fragment Header for IPv6: 44 */
    public static final ProcNumber IPV6_FRAG = new ProcNumber((byte) 44, "IPV6-FRAG", "Fragment Header for IPv6");

    /** Inter-Domain Routing Protocol: 45 */
    public static final ProcNumber IDRP = new ProcNumber((byte) 45, "IDRP", "Inter-Domain Routing Protocol");

    /** Resource Reservation Protocol: 46 */
    public static final ProcNumber RSVP = new ProcNumber((byte) 46, "RSVP", "Resource Reservation Protocol");

    /** Generic Routing Encapsulation: 47 */
    public static final ProcNumber GRE = new ProcNumber((byte) 47, "GRE", "Generic Routing Encapsulation");

    /** Dynamic Source Routing Protocol: 48 */
    public static final ProcNumber DSR = new ProcNumber((byte) 48, "DSR", "Dynamic Source Routing Protocol");

    /** Encapsulating Security Payload: 50 */
    public static final ProcNumber ESP = new ProcNumber((byte) 50, "ESP", "Encapsulating Security Payload");

    /** Authentication Header: 51 */
    public static final ProcNumber AH = new ProcNumber((byte) 51, "AH", "Authentication Header");

    /** Integrated Net Layer Security TUBA: 52 */
    public static final ProcNumber I_NLSP = new ProcNumber((byte) 52, "I-NLSP", "Integrated Net Layer Security  TUBA");

    /** IP with Encryption: 53 */
    public static final ProcNumber SWIPE = new ProcNumber((byte) 53, "SWIPE", "IP with Encryption");

    /** NBMA Address Resolution Protocol: 54 */
    public static final ProcNumber NARP = new ProcNumber((byte) 54, "NARP", "NBMA Address Resolution Protocol");

    /** IP Mobility: 55 */
    public static final ProcNumber MOBILE = new ProcNumber((byte) 55, "MOBILE", "IP Mobility");

    /** Transport Layer Security Protocol: 56 */
    public static final ProcNumber TLSP = new ProcNumber((byte) 56, "TLSP", "Transport Layer Security Protocol");

    /** SKIP: 57 */
    public static final ProcNumber SKIP = new ProcNumber((byte) 57, "SKIP", "SKIP");

    /** ICMP for IPv6: 58 */
    public static final ProcNumber ICMPV6 = new ProcNumber((byte) 58, "ICMPV6", "ICMP for IPv6");

    /** No Next Header for IPv6: 59 */
    public static final ProcNumber IPV6_NONXT = new ProcNumber((byte) 59, "IPV6-NONXT", "No Next Header for IPv6");

    /** Destination Options for IPv6: 60 */
    public static final ProcNumber IPV6_OPTS = new ProcNumber((byte) 60, "IPV6-OPTS", "Destination Options for IPv6");

    /** Any host internal protocol: 61 */
    public static final ProcNumber HOST_INTERNAL = new ProcNumber((byte) 61, "HOST-INTERNAL",
            "Any host internal protocol");

    /** CFTP: 62 */
    public static final ProcNumber CFTP = new ProcNumber((byte) 62, "CFTP", "CFTP");

    /** Any local network: 63 */
    public static final ProcNumber LOCAL_NET = new ProcNumber((byte) 63, "LOCAL-NET", "Any local network");

    /** SATNET and Backroom EXPAK: 64 */
    public static final ProcNumber SAT_EXPAK = new ProcNumber((byte) 64, "SAT-EXPAK", "SATNET and Backroom EXPAK");

    /** Kryptolan: 65 */
    public static final ProcNumber KRYPTOLAN = new ProcNumber((byte) 65, "KRYPTOLAN", "Kryptolan");

    /** MIT Remote Virtual Disk Protocol: 66 */
    public static final ProcNumber RVD = new ProcNumber((byte) 66, "RVD", "MIT Remote Virtual Disk Protocol");

    /** Internet Pluribus Packet Core: 67 */
    public static final ProcNumber IPPC = new ProcNumber((byte) 67, "IPPC", "Internet Pluribus Packet Core");

    /** Any distributed file system: 68 */
    public static final ProcNumber DFS = new ProcNumber((byte) 68, "DFS", "Any distributed file system");

    /** SATNET Monitoring: 69 */
    public static final ProcNumber SAT_MON = new ProcNumber((byte) 69, "SAT-MON", "SATNET Monitoring");

    /** VISA Protocol: 70 */
    public static final ProcNumber VISA = new ProcNumber((byte) 70, "VISA", "VISA Protocol");

    /** Internet Packet Core Utility: 71 */
    public static final ProcNumber IPCV = new ProcNumber((byte) 71, "IPCV", "Internet Packet Core Utility");

    /** CPNX: 72 */
    public static final ProcNumber CPNX = new ProcNumber((byte) 72, "CPNX", "CPNX");

    /** CPHB: 73 */
    public static final ProcNumber CPHB = new ProcNumber((byte) 73, "CPHB", "CPHB");

    /** WSN: 74 */
    public static final ProcNumber WSN = new ProcNumber((byte) 74, "WSN", "WSN");

    /** PVP: 75 */
    public static final ProcNumber PVP = new ProcNumber((byte) 75, "PVP", "PVP");

    /** BR-SAT-MON: 76 */
    public static final ProcNumber BR_SAT_MON = new ProcNumber((byte) 76, "BR-SAT-MON", "BR-SAT-MON");

    /** SUN ND PROTOCOL-Temporary: 77 */
    public static final ProcNumber SUN_ND = new ProcNumber((byte) 77, "SUN-ND", "SUN ND PROTOCOL-Temporary");

    /** WB-MON: 78 */
    public static final ProcNumber WB_MON = new ProcNumber((byte) 78, "WB-MON", "WB-MON");

    /** WB-EXPAK: 79 */
    public static final ProcNumber WB_EXPAK = new ProcNumber((byte) 79, "WB-EXPAK", "WB-EXPAK");

    /** ISO-IP: 80 */
    public static final ProcNumber ISO_IP = new ProcNumber((byte) 80, "ISO-IP", "ISO-IP");

    /** VMTP: 81 */
    public static final ProcNumber VMTP = new ProcNumber((byte) 81, "VMTP", "VMTP");

    /** SECURE-VMTP: 82 */
    public static final ProcNumber SECURE_VMTP = new ProcNumber((byte) 82, "SECURE-VMTP", "SECURE-VMTP");

    /** VINES: 83 */
    public static final ProcNumber VINES = new ProcNumber((byte) 83, "VINES", "VINES");

    /** TTP: 84 */
    public static final ProcNumber TTP = new ProcNumber((byte) 84, "TTP", "TTP");

    /** NSFNET-IGP: 85 */
    public static final ProcNumber NSFNET_IGP = new ProcNumber((byte) 85, "NSFNET-IGP", "NSFNET-IGP");

    /** DGP: 86 */
    public static final ProcNumber DGP = new ProcNumber((byte) 86, "DGP", "DGP");

    /** TCF: 87 */
    public static final ProcNumber TCF = new ProcNumber((byte) 87, "TCF", "TCF");

    /** EIGRP: 88 */
    public static final ProcNumber EIGRP = new ProcNumber((byte) 88, "EIGRP", "EIGRP");

    /** OSPFIGP: 89 */
    public static final ProcNumber OSPFIGP = new ProcNumber((byte) 89, "OSPFIGP", "OSPFIGP");

    /** Sprite RPC Protocol: 90 */
    public static final ProcNumber Sprite_RPC = new ProcNumber((byte) 90, "Sprite-RPC", "Sprite RPC Protocol");

    /** Locus Address Resolution Protocol: 91 */
    public static final ProcNumber LARP = new ProcNumber((byte) 91, "LARP", "Locus Address Resolution Protocol");

    /** Multicast Transport Protocol: 92 */
    public static final ProcNumber MTP = new ProcNumber((byte) 92, "MTP", "Multicast Transport Protocol");

    /** AX.25 Frames: 93 */
    public static final ProcNumber AX_25 = new ProcNumber((byte) 93, "AX.25", "AX.25 Frames");

    /** IP-within-IP Encapsulation Protocol: 94 */
    public static final ProcNumber IPIP = new ProcNumber((byte) 94, "IPIP", "IP-within-IP Encapsulation Protocol");

    /** Mobile Internetworking Control Pro.: 95 */
    public static final ProcNumber MICP = new ProcNumber((byte) 95, "MICP", "Mobile Internetworking Control Pro.");

    /** Semaphore Communications Sec. Pro.: 96 */
    public static final ProcNumber SCC_SP = new ProcNumber((byte) 96, "SCC-SP", "Semaphore Communications Sec. Pro.");

    /** Ethernet-within-IP Encapsulation: 97 */
    public static final ProcNumber ETHERIP = new ProcNumber((byte) 97, "ETHERIP", "Ethernet-within-IP Encapsulation");

    /** Encapsulation Header: 98 */
    public static final ProcNumber ENCAP = new ProcNumber((byte) 98, "ENCAP", "Encapsulation Header");

    /** GMTP: 100 */
    public static final ProcNumber GMTP = new ProcNumber((byte) 100, "GMTP", "GMTP");

    /** IFMP: 101 */
    public static final ProcNumber IFMP = new ProcNumber((byte) 101, "IFMP", "Ipsilon Flow Management Protocol");

    /** PNNI over IP: 102 */
    public static final ProcNumber PNNI = new ProcNumber((byte) 102, "PNNI", "PNNI over IP");

    /** Protocol Independent Multicast: 103 */
    public static final ProcNumber PIM = new ProcNumber((byte) 103, "PIM", "Protocol Independent Multicast");

    /** ARIS: 104 */
    public static final ProcNumber ARIS = new ProcNumber((byte) 104, "ARIS", "ARIS");

    /** SCPS: 105 */
    public static final ProcNumber SCPS = new ProcNumber((byte) 105, "SCPS", "SCPS");

    /** QNX: 106 */
    public static final ProcNumber QNX = new ProcNumber((byte) 106, "QNX", "QNX");

    /** A/N: 107 */
    public static final ProcNumber AN = new ProcNumber((byte) 107, "A/N", "A/N");

    /** Sitara Networks Protocol: 109 */
    public static final ProcNumber SNP = new ProcNumber((byte) 109, "SNP", "Sitara Networks Protocol");

    /** Compaq Peer Protocol: 110 */
    public static final ProcNumber Compaq_Peer = new ProcNumber((byte) 110, "Compaq-Peer", "Compaq Peer Protocol");

    /** IPX in IP: 111 */
    public static final ProcNumber IPX_in_IP = new ProcNumber((byte) 111, "IPX-in-IP", "IPX in IP");

    /** Virtual Router Redundancy Protocol: 112 */
    public static final ProcNumber VRRP = new ProcNumber((byte) 112, "VRRP", "Virtual Router Redundancy Protocol");

    /** PGM Reliable Transport Protocol: 113 */
    public static final ProcNumber PGM = new ProcNumber((byte) 113, "PGM", "PGM Reliable Transport Protocol");

    /** any 0-hop protocol: 114 */
    public static final ProcNumber ZERO_HOP = new ProcNumber((byte) 114, "ZERO-HOP", "any 0-hop protocol");

    /** Layer Two Tunneling Protocol: 115 */
    public static final ProcNumber L2TP = new ProcNumber((byte) 115, "L2TP", "Layer Two Tunneling Protocol");

    /** D-II Data Exchange (DDX): 116 */
    public static final ProcNumber DDX = new ProcNumber((byte) 116, "DDX", "D-II Data Exchange (DDX)");

    /** Interactive Agent Transfer Protocol: 117 */
    public static final ProcNumber IATP = new ProcNumber((byte) 117, "IATP", "Interactive Agent Transfer Protocol");

    /** Schedule Transfer Protocol: 118 */
    public static final ProcNumber STP = new ProcNumber((byte) 118, "STP", "Schedule Transfer Protocol");

    /** SpectraLink Radio Protocol: 119 */
    public static final ProcNumber SRP = new ProcNumber((byte) 119, "SRP", "SpectraLink Radio Protocol");

    /** UTI: 120 */
    public static final ProcNumber UTI = new ProcNumber((byte) 120, "UTI", "UTI");

    /** Simple Message Protocol: 121 */
    public static final ProcNumber SMP = new ProcNumber((byte) 121, "SMP", "Simple Message Protocol");

    /** Simple Multicast Protocol (SM): 122 */
    public static final ProcNumber SM = new ProcNumber((byte) 122, "SM", "Simple Multicast Protocol");

    /** Performance Transparency Protocol: 123 */
    public static final ProcNumber PTP = new ProcNumber((byte) 123, "PTP", "Performance Transparency Protocol");

    /** ISIS over IPv4: 124 */
    public static final ProcNumber ISIS = new ProcNumber((byte) 124, "ISIS", "ISIS over IPv4");

    /** FIRE: 125 */
    public static final ProcNumber FIRE = new ProcNumber((byte) 125, "FIRE", "FIRE");

    /** Combat Radio Transport Protocol: 126 */
    public static final ProcNumber CRTP = new ProcNumber((byte) 126, "CRTP", "Combat Radio Transport Protocol");

    /** Combat Radio User Datagram: 127 */
    public static final ProcNumber CRUDP = new ProcNumber((byte) 127, "CRUDP", "Combat Radio User Datagram");

    /** SSCOPMCE: 128 */
    public static final ProcNumber SSCOPMCE = new ProcNumber((byte) 128, "SSCOPMCE", "SSCOPMCE");

    /** IPLT: 129 */
    public static final ProcNumber IPLT = new ProcNumber((byte) 129, "IPLT", "IPLT");

    /** Secure Packet Shield: 130 */
    public static final ProcNumber SPS = new ProcNumber((byte) 130, "SPS", "Secure Packet Shield");

    /** Private IP Encapsulation within IP: 131 */
    public static final ProcNumber PIPE = new ProcNumber((byte) 131, "PIPE", "Private IP Encapsulation within IP");

    /** Stream Control Transmission Protocol: 132 */
    public static final ProcNumber SCTP = new ProcNumber((byte) 132, "SCTP", "Stream Control Transmission Protocol");

    /** Fibre Channel: 133 */
    public static final ProcNumber FC = new ProcNumber((byte) 133, "FC", "Fibre Channel");

    /** Reservation Protocol: 134 */
    public static final ProcNumber RSVP_E2E_IGNORE = new ProcNumber((byte) 134, "RSVP-E2E-IGNORE",
            "Reservation Protocol");

    /** Mobility Header: 135 */
    public static final ProcNumber MOBILITY_HEADER = new ProcNumber((byte) 135, "MOBILITY-HEADER", "Mobility Header");

    /** UDP-Lite: 136 */
    public static final ProcNumber UDPLite = new ProcNumber((byte) 136, "UDPLite", "UDP-Lite");

    /** MPLS-in-IP: 137 */
    public static final ProcNumber MPLS_IN_IP = new ProcNumber((byte) 137, "MPLS-IN-IP", "MPLS-in-IP");

    /** MANET Protocols: 138 */
    public static final ProcNumber MANET = new ProcNumber((byte) 138, "MANET", "MANET Protocols");

    /** Host Identity Protocol: 139 */
    public static final ProcNumber HIP = new ProcNumber((byte) 139, "HIP", "Host Identity Protocol");

    /** Shim6 Protocol: 140 */
    public static final ProcNumber Shim6 = new ProcNumber((byte) 140, "Shim6", "Shim6 Protocol");

    /** Wrapped Encapsulating Security Payload: 141 */
    public static final ProcNumber WESP = new ProcNumber((byte) 141, "WESP", "Wrapped Encapsulating Security Payload");

    /** Robust Header Compression: 142 */
    public static final ProcNumber ROHC = new ProcNumber((byte) 142, "ROHC", "Robust Header Compression");

    String getProtocol() {
        return protocol;
    }

    @Override
    public int compareTo(ProcNumber o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
