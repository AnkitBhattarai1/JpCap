package org.jcap.Core.Constants.NamedCodes.L3;

import org.jcap.Core.Constants.NamedCodes.NamedCode;

/**
 * * @see <a href=
 * "http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xml">IANA
 * Registry</a>
 */
public class IpNumber extends NamedCode<Byte, IpNumber> {

    private final String protocol;

    protected IpNumber(Byte value, String name, String protocol) {
        super(value, name);
        this.protocol = protocol;
        // TODO Auto-generated constructor stub
    }

    /** IPv6 Hop-by-Hop Option: 0 */
    public static final IpNumber IPV6_HOPOPT = new IpNumber((byte) 0, "HOPOPT", "IPv6 Hop-by-Hop Option");

    /** Internet Control Message (ICMPv4): 1 */
    public static final IpNumber ICMP = new IpNumber((byte) 1, "ICMP", "Internet Control Message (ICMPv4)");

    /** Internet Group Management(IGMP):2 */
    public static final IpNumber IGMP = new IpNumber((byte) 2, "IGMP", "Internet Group Management(IGMP)");

    /** Gateway-to-Gateway(GGP):3 */
    public static final IpNumber GGP = new IpNumber((byte) 3, "GGP", "Gateway-to-Gateway");

    /** IPv4: 4 */
    public static final IpNumber IPV4 = new IpNumber((byte) 4, "IPV4", "IPv4 encapsulation");

    /** Stream: 5 */
    public static final IpNumber ST = new IpNumber((byte) 5, "ST", "Stream");

    /** Transmission Control (TCP): 6 */
    public static final IpNumber TCP = new IpNumber((byte) 6, "TCP", "Transmission Control");

    /** CBT: 7 */
    public static final IpNumber CBT = new IpNumber((byte) 7, "CBT", "CBT");

    /** Exterior Gateway Protocol (EGP): 8 */
    public static final IpNumber EGP = new IpNumber((byte) 8, "EGP", "Exterior Gateway Protocol");

    /** IGP: 9 */
    public static final IpNumber IGP = new IpNumber((byte) 9, "IGP",
            "any private interior gateway (used by Cisco for their IGRP)");

    /** BBN RCC Monitoring: 10 */
    public static final IpNumber BBN_RCC_MON = new IpNumber((byte) 10, "BBN-RCC-MON", "BBN RCC Monitoring");

    /** NVP-II: 11 */
    public static final IpNumber NVP_II = new IpNumber((byte) 11, "NVP-II", "Network Voice Protocol");

    /** PUP: 12 */
    public static final IpNumber PUP = new IpNumber((byte) 12, "PUP", "PUP");

    /** ARGUS: 13 */
    public static final IpNumber ARGUS = new IpNumber((byte) 13, "ARGUS", "ARGUS");

    /** EMCON: 14 */
    public static final IpNumber EMCON = new IpNumber((byte) 14, "EMCON", "EMCON");

    /** XNET: 15 */
    public static final IpNumber XNET = new IpNumber((byte) 15, "XNET", "Cross Net Debugger");

    /** CHAOS: 16 */
    public static final IpNumber CHAOS = new IpNumber((byte) 16, "CHAOS", "Chaos");

    /** User Datagram (UDP): 17 */
    public static final IpNumber UDP = new IpNumber((byte) 17, "UDP", "User Datagram");

    /** Multiplexing: 18 */
    public static final IpNumber MUX = new IpNumber((byte) 18, "MUX", "Multiplexing");

    /** DCN Measurement Subsystems: 19 */
    public static final IpNumber DCN_MEAS = new IpNumber((byte) 19, "DCN-MEAS", "DCN Measurement Subsystems");

    /** Host Monitoring: 20 */
    public static final IpNumber HMP = new IpNumber((byte) 20, "HMP", "Host Monitoring");

    /** Packet Radio Measurement: 21 */
    public static final IpNumber PRM = new IpNumber((byte) 21, "PRM", "Packet Radio Measurement");

    /** XEROX NS IDP: 22 */
    public static final IpNumber XNS_IDP = new IpNumber((byte) 22, "XNS-IDP", "XEROX NS IDP");

    /** Trunk-1: 23 */
    public static final IpNumber TRUNK_1 = new IpNumber((byte) 23, "TRUNK-1", "Trunk-1");

    /** Trunk-2: 24 */
    public static final IpNumber TRUNK_2 = new IpNumber((byte) 24, "TRUNK-2", "Trunk-2");

    /** Leaf-1: 25 */
    public static final IpNumber LEAF_1 = new IpNumber((byte) 25, "LEAF-1", "Leaf-1");

    /** Leaf-2: 26 */
    public static final IpNumber LEAF_2 = new IpNumber((byte) 26, "LEAF-2", "Leaf-2");

    /** Reliable Data Protocol (RDP): 27 */
    public static final IpNumber RDP = new IpNumber((byte) 27, "RDP", "Reliable Data Protocol");

    /** Internet Reliable Transaction (IRTP): 28 */
    public static final IpNumber IRTP = new IpNumber((byte) 28, "IRTP", "Internet Reliable Transaction");

    /** ISO Transport Protocol Class 4 (ISO-TP4): 29 */
    public static final IpNumber ISO_TP4 = new IpNumber((byte) 29, "ISO-TP4", "ISO Transport Protocol Class 4");

    /** Bulk Data Transfer Protocol (NETBLT): 30 */
    public static final IpNumber NETBLT = new IpNumber((byte) 30, "NETBLT", "Bulk Data Transfer Protocol");

    /** MFE Network Services Protocol: 31 */
    public static final IpNumber MFE_NSP = new IpNumber((byte) 31, "MFE-NSP", "MFE Network Services Protocol");

    /** MERIT Internodal Protocol: 32 */
    public static final IpNumber MERIT_INP = new IpNumber((byte) 32, "MERIT-INP", "MERIT Internodal Protocol");

    /** Datagram Congestion Control Protocol (DCCP): 33 */
    public static final IpNumber DCCP = new IpNumber((byte) 33, "DCCP", "Datagram Congestion Control Protocol");

    /** Third Party Connect Protocol: 34 */
    public static final IpNumber ThirdPC = new IpNumber((byte) 34, "ThirdPC", "Third Party Connect Protocol");

    /** Inter-Domain Policy Routing Protocol: 35 */
    public static final IpNumber IDPR = new IpNumber((byte) 35, "IDPR", "Inter-Domain Policy Routing Protocol");

    /** XTP: 36 */
    public static final IpNumber XTP = new IpNumber((byte) 36, "XTP", "XTP");

    /** Datagram Delivery Protocol (DDP): 37 */
    public static final IpNumber DDP = new IpNumber((byte) 37, "DDP", "Datagram Delivery Protocol");

    /** IDPR Control Message Transport Proto: 38 */
    public static final IpNumber IDPR_CMTP = new IpNumber((byte) 38, "IDPR-CMTP",
            "IDPR Control Message Transport Proto");

    /** TP++ Transport Protocol: 39 */
    public static final IpNumber TP_PLUS_PLUS = new IpNumber((byte) 39, "TP++", "TP++ Transport Protocol");

    /** IL Transport Protocol: 40 */
    public static final IpNumber IL = new IpNumber((byte) 40, "IL", "IL Transport Protocol");

    /** IPv6 encapsulation: 41 */
    public static final IpNumber IPV6 = new IpNumber((byte) 41, "IPV6", "IPv6 encapsulation");

    /** Source Demand Routing Protocol: 42 */
    public static final IpNumber SDRP = new IpNumber((byte) 42, "SDRP", "Source Demand Routing Protocol");

    /** Routing Header for IPv6: 43 */
    public static final IpNumber IPV6_ROUTE = new IpNumber((byte) 43, "IPV6-ROUTE", "Routing Header for IPv6");

    /** Fragment Header for IPv6: 44 */
    public static final IpNumber IPV6_FRAG = new IpNumber((byte) 44, "IPV6-FRAG", "Fragment Header for IPv6");

    /** Inter-Domain Routing Protocol: 45 */
    public static final IpNumber IDRP = new IpNumber((byte) 45, "IDRP", "Inter-Domain Routing Protocol");

    /** Resource Reservation Protocol: 46 */
    public static final IpNumber RSVP = new IpNumber((byte) 46, "RSVP", "Resource Reservation Protocol");

    /** Generic Routing Encapsulation: 47 */
    public static final IpNumber GRE = new IpNumber((byte) 47, "GRE", "Generic Routing Encapsulation");

    /** Dynamic Source Routing Protocol: 48 */
    public static final IpNumber DSR = new IpNumber((byte) 48, "DSR", "Dynamic Source Routing Protocol");

    /** Encapsulating Security Payload: 50 */
    public static final IpNumber ESP = new IpNumber((byte) 50, "ESP", "Encapsulating Security Payload");

    /** Authentication Header: 51 */
    public static final IpNumber AH = new IpNumber((byte) 51, "AH", "Authentication Header");

    /** Integrated Net Layer Security TUBA: 52 */
    public static final IpNumber I_NLSP = new IpNumber((byte) 52, "I-NLSP", "Integrated Net Layer Security  TUBA");

    /** IP with Encryption: 53 */
    public static final IpNumber SWIPE = new IpNumber((byte) 53, "SWIPE", "IP with Encryption");

    /** NBMA Address Resolution Protocol: 54 */
    public static final IpNumber NARP = new IpNumber((byte) 54, "NARP", "NBMA Address Resolution Protocol");

    /** IP Mobility: 55 */
    public static final IpNumber MOBILE = new IpNumber((byte) 55, "MOBILE", "IP Mobility");

    /** Transport Layer Security Protocol: 56 */
    public static final IpNumber TLSP = new IpNumber((byte) 56, "TLSP", "Transport Layer Security Protocol");

    /** SKIP: 57 */
    public static final IpNumber SKIP = new IpNumber((byte) 57, "SKIP", "SKIP");

    /** ICMP for IPv6: 58 */
    public static final IpNumber ICMPV6 = new IpNumber((byte) 58, "ICMPV6", "ICMP for IPv6");

    /** No Next Header for IPv6: 59 */
    public static final IpNumber IPV6_NONXT = new IpNumber((byte) 59, "IPV6-NONXT", "No Next Header for IPv6");

    /** Destination Options for IPv6: 60 */
    public static final IpNumber IPV6_OPTS = new IpNumber((byte) 60, "IPV6-OPTS", "Destination Options for IPv6");

    /** Any host internal protocol: 61 */
    public static final IpNumber HOST_INTERNAL = new IpNumber((byte) 61, "HOST-INTERNAL", "Any host internal protocol");

    /** CFTP: 62 */
    public static final IpNumber CFTP = new IpNumber((byte) 62, "CFTP", "CFTP");

    /** Any local network: 63 */
    public static final IpNumber LOCAL_NET = new IpNumber((byte) 63, "LOCAL-NET", "Any local network");

    /** SATNET and Backroom EXPAK: 64 */
    public static final IpNumber SAT_EXPAK = new IpNumber((byte) 64, "SAT-EXPAK", "SATNET and Backroom EXPAK");

    /** Kryptolan: 65 */
    public static final IpNumber KRYPTOLAN = new IpNumber((byte) 65, "KRYPTOLAN", "Kryptolan");

    /** MIT Remote Virtual Disk Protocol: 66 */
    public static final IpNumber RVD = new IpNumber((byte) 66, "RVD", "MIT Remote Virtual Disk Protocol");

    /** Internet Pluribus Packet Core: 67 */
    public static final IpNumber IPPC = new IpNumber((byte) 67, "IPPC", "Internet Pluribus Packet Core");

    /** Any distributed file system: 68 */
    public static final IpNumber DFS = new IpNumber((byte) 68, "DFS", "Any distributed file system");

    /** SATNET Monitoring: 69 */
    public static final IpNumber SAT_MON = new IpNumber((byte) 69, "SAT-MON", "SATNET Monitoring");

    /** VISA Protocol: 70 */
    public static final IpNumber VISA = new IpNumber((byte) 70, "VISA", "VISA Protocol");

    /** Internet Packet Core Utility: 71 */
    public static final IpNumber IPCV = new IpNumber((byte) 71, "IPCV", "Internet Packet Core Utility");

    /** CPNX: 72 */
    public static final IpNumber CPNX = new IpNumber((byte) 72, "CPNX", "CPNX");

    /** CPHB: 73 */
    public static final IpNumber CPHB = new IpNumber((byte) 73, "CPHB", "CPHB");

    /** WSN: 74 */
    public static final IpNumber WSN = new IpNumber((byte) 74, "WSN", "WSN");

    /** PVP: 75 */
    public static final IpNumber PVP = new IpNumber((byte) 75, "PVP", "PVP");

    /** BR-SAT-MON: 76 */
    public static final IpNumber BR_SAT_MON = new IpNumber((byte) 76, "BR-SAT-MON", "BR-SAT-MON");

    /** SUN ND PROTOCOL-Temporary: 77 */
    public static final IpNumber SUN_ND = new IpNumber((byte) 77, "SUN-ND", "SUN ND PROTOCOL-Temporary");

    /** WB-MON: 78 */
    public static final IpNumber WB_MON = new IpNumber((byte) 78, "WB-MON", "WB-MON");

    /** WB-EXPAK: 79 */
    public static final IpNumber WB_EXPAK = new IpNumber((byte) 79, "WB-EXPAK", "WB-EXPAK");

    /** ISO-IP: 80 */
    public static final IpNumber ISO_IP = new IpNumber((byte) 80, "ISO-IP", "ISO-IP");

    /** VMTP: 81 */
    public static final IpNumber VMTP = new IpNumber((byte) 81, "VMTP", "VMTP");

    /** SECURE-VMTP: 82 */
    public static final IpNumber SECURE_VMTP = new IpNumber((byte) 82, "SECURE-VMTP", "SECURE-VMTP");

    /** VINES: 83 */
    public static final IpNumber VINES = new IpNumber((byte) 83, "VINES", "VINES");

    /** TTP: 84 */
    public static final IpNumber TTP = new IpNumber((byte) 84, "TTP", "TTP");

    /** NSFNET-IGP: 85 */
    public static final IpNumber NSFNET_IGP = new IpNumber((byte) 85, "NSFNET-IGP", "NSFNET-IGP");

    /** DGP: 86 */
    public static final IpNumber DGP = new IpNumber((byte) 86, "DGP", "DGP");

    /** TCF: 87 */
    public static final IpNumber TCF = new IpNumber((byte) 87, "TCF", "TCF");

    /** EIGRP: 88 */
    public static final IpNumber EIGRP = new IpNumber((byte) 88, "EIGRP", "EIGRP");

    /** OSPFIGP: 89 */
    public static final IpNumber OSPFIGP = new IpNumber((byte) 89, "OSPFIGP", "OSPFIGP");

    /** Sprite RPC Protocol: 90 */
    public static final IpNumber Sprite_RPC = new IpNumber((byte) 90, "Sprite-RPC", "Sprite RPC Protocol");

    /** Locus Address Resolution Protocol: 91 */
    public static final IpNumber LARP = new IpNumber((byte) 91, "LARP", "Locus Address Resolution Protocol");

    /** Multicast Transport Protocol: 92 */
    public static final IpNumber MTP = new IpNumber((byte) 92, "MTP", "Multicast Transport Protocol");

    /** AX.25 Frames: 93 */
    public static final IpNumber AX_25 = new IpNumber((byte) 93, "AX.25", "AX.25 Frames");

    /** IP-within-IP Encapsulation Protocol: 94 */
    public static final IpNumber IPIP = new IpNumber((byte) 94, "IPIP", "IP-within-IP Encapsulation Protocol");

    /** Mobile Internetworking Control Pro.: 95 */
    public static final IpNumber MICP = new IpNumber((byte) 95, "MICP", "Mobile Internetworking Control Pro.");

    /** Semaphore Communications Sec. Pro.: 96 */
    public static final IpNumber SCC_SP = new IpNumber((byte) 96, "SCC-SP", "Semaphore Communications Sec. Pro.");

    /** Ethernet-within-IP Encapsulation: 97 */
    public static final IpNumber ETHERIP = new IpNumber((byte) 97, "ETHERIP", "Ethernet-within-IP Encapsulation");

    /** Encapsulation Header: 98 */
    public static final IpNumber ENCAP = new IpNumber((byte) 98, "ENCAP", "Encapsulation Header");

    /** GMTP: 100 */
    public static final IpNumber GMTP = new IpNumber((byte) 100, "GMTP", "GMTP");

    /** IFMP: 101 */
    public static final IpNumber IFMP = new IpNumber((byte) 101, "IFMP", "Ipsilon Flow Management Protocol");

    /** PNNI over IP: 102 */
    public static final IpNumber PNNI = new IpNumber((byte) 102, "PNNI", "PNNI over IP");

    /** Protocol Independent Multicast: 103 */
    public static final IpNumber PIM = new IpNumber((byte) 103, "PIM", "Protocol Independent Multicast");

    /** ARIS: 104 */
    public static final IpNumber ARIS = new IpNumber((byte) 104, "ARIS", "ARIS");

    /** SCPS: 105 */
    public static final IpNumber SCPS = new IpNumber((byte) 105, "SCPS", "SCPS");

    /** QNX: 106 */
    public static final IpNumber QNX = new IpNumber((byte) 106, "QNX", "QNX");

    /** A/N: 107 */
    public static final IpNumber AN = new IpNumber((byte) 107, "A/N", "A/N");

    /** Sitara Networks Protocol: 109 */
    public static final IpNumber SNP = new IpNumber((byte) 109, "SNP", "Sitara Networks Protocol");

    /** Compaq Peer Protocol: 110 */
    public static final IpNumber Compaq_Peer = new IpNumber((byte) 110, "Compaq-Peer", "Compaq Peer Protocol");

    /** IPX in IP: 111 */
    public static final IpNumber IPX_in_IP = new IpNumber((byte) 111, "IPX-in-IP", "IPX in IP");

    /** Virtual Router Redundancy Protocol: 112 */
    public static final IpNumber VRRP = new IpNumber((byte) 112, "VRRP", "Virtual Router Redundancy Protocol");

    /** PGM Reliable Transport Protocol: 113 */
    public static final IpNumber PGM = new IpNumber((byte) 113, "PGM", "PGM Reliable Transport Protocol");

    /** any 0-hop protocol: 114 */
    public static final IpNumber ZERO_HOP = new IpNumber((byte) 114, "ZERO-HOP", "any 0-hop protocol");

    /** Layer Two Tunneling Protocol: 115 */
    public static final IpNumber L2TP = new IpNumber((byte) 115, "L2TP", "Layer Two Tunneling Protocol");

    /** D-II Data Exchange (DDX): 116 */
    public static final IpNumber DDX = new IpNumber((byte) 116, "DDX", "D-II Data Exchange (DDX)");

    /** Interactive Agent Transfer Protocol: 117 */
    public static final IpNumber IATP = new IpNumber((byte) 117, "IATP", "Interactive Agent Transfer Protocol");

    /** Schedule Transfer Protocol: 118 */
    public static final IpNumber STP = new IpNumber((byte) 118, "STP", "Schedule Transfer Protocol");

    /** SpectraLink Radio Protocol: 119 */
    public static final IpNumber SRP = new IpNumber((byte) 119, "SRP", "SpectraLink Radio Protocol");

    /** UTI: 120 */
    public static final IpNumber UTI = new IpNumber((byte) 120, "UTI", "UTI");

    /** Simple Message Protocol: 121 */
    public static final IpNumber SMP = new IpNumber((byte) 121, "SMP", "Simple Message Protocol");

    /** Simple Multicast Protocol (SM): 122 */
    public static final IpNumber SM = new IpNumber((byte) 122, "SM", "Simple Multicast Protocol");

    /** Performance Transparency Protocol: 123 */
    public static final IpNumber PTP = new IpNumber((byte) 123, "PTP", "Performance Transparency Protocol");

    /** ISIS over IPv4: 124 */
    public static final IpNumber ISIS = new IpNumber((byte) 124, "ISIS", "ISIS over IPv4");

    /** FIRE: 125 */
    public static final IpNumber FIRE = new IpNumber((byte) 125, "FIRE", "FIRE");

    /** Combat Radio Transport Protocol: 126 */
    public static final IpNumber CRTP = new IpNumber((byte) 126, "CRTP", "Combat Radio Transport Protocol");

    /** Combat Radio User Datagram: 127 */
    public static final IpNumber CRUDP = new IpNumber((byte) 127, "CRUDP", "Combat Radio User Datagram");

    /** SSCOPMCE: 128 */
    public static final IpNumber SSCOPMCE = new IpNumber((byte) 128, "SSCOPMCE", "SSCOPMCE");

    /** IPLT: 129 */
    public static final IpNumber IPLT = new IpNumber((byte) 129, "IPLT", "IPLT");

    /** Secure Packet Shield: 130 */
    public static final IpNumber SPS = new IpNumber((byte) 130, "SPS", "Secure Packet Shield");

    /** Private IP Encapsulation within IP: 131 */
    public static final IpNumber PIPE = new IpNumber((byte) 131, "PIPE", "Private IP Encapsulation within IP");

    /** Stream Control Transmission Protocol: 132 */
    public static final IpNumber SCTP = new IpNumber((byte) 132, "SCTP", "Stream Control Transmission Protocol");

    /** Fibre Channel: 133 */
    public static final IpNumber FC = new IpNumber((byte) 133, "FC", "Fibre Channel");

    /** Reservation Protocol: 134 */
    public static final IpNumber RSVP_E2E_IGNORE = new IpNumber((byte) 134, "RSVP-E2E-IGNORE", "Reservation Protocol");

    /** Mobility Header: 135 */
    public static final IpNumber MOBILITY_HEADER = new IpNumber((byte) 135, "MOBILITY-HEADER", "Mobility Header");

    /** UDP-Lite: 136 */
    public static final IpNumber UDPLite = new IpNumber((byte) 136, "UDPLite", "UDP-Lite");

    /** MPLS-in-IP: 137 */
    public static final IpNumber MPLS_IN_IP = new IpNumber((byte) 137, "MPLS-IN-IP", "MPLS-in-IP");

    /** MANET Protocols: 138 */
    public static final IpNumber MANET = new IpNumber((byte) 138, "MANET", "MANET Protocols");

    /** Host Identity Protocol: 139 */
    public static final IpNumber HIP = new IpNumber((byte) 139, "HIP", "Host Identity Protocol");

    /** Shim6 Protocol: 140 */
    public static final IpNumber Shim6 = new IpNumber((byte) 140, "Shim6", "Shim6 Protocol");

    /** Wrapped Encapsulating Security Payload: 141 */
    public static final IpNumber WESP = new IpNumber((byte) 141, "WESP", "Wrapped Encapsulating Security Payload");

    /** Robust Header Compression: 142 */
    public static final IpNumber ROHC = new IpNumber((byte) 142, "ROHC", "Robust Header Compression");

    String getProtocol() {
        return protocol;
    }

    @Override
    public int compareTo(IpNumber o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
