package org.jcap.Core.Enums;

public enum LinkLayerType {
    DLT_NULL(0), // BSD loopback encapsulation
    DLT_EN10MB(1), // Ethernet (10Mb)
    DLT_EN3MB(2), // Experimental Ethernet (3Mb)
    DLT_AX25(3), // Amateur Radio AX.25
    DLT_PRONET(4), // Proteon ProNET Token Ring
    DLT_CHAOS(5), // Chaos
    DLT_IEEE802(6), // 802.5 Token Ring
    DLT_ARCNET(7), // ARCNET, with BSD-style header
    DLT_SLIP(8), // Serial Line IP
    DLT_PPP(9), // Point-to-point Protocol
    DLT_FDDI(10), // FDDI
    DLT_ATM_RFC1483(11), // LLC-encapsulated ATM
    DLT_RAW(12), // raw IP (default)
    DLT_SLIP_BSDOS(15), // BSD/OS Serial Line IP
    DLT_PPP_BSDOS(16), // BSD/OS Point-to-point Protocol
    DLT_HIPPI(15), // HIPPI
    DLT_PFSYNC(18), // PFSYNC (OpenBSD, NetBSD, DragonFly BSD, macOS)
    DLT_ATM_CLIP(19), // Linux Classical IP over ATM
    DLT_REDBACK_SMARTEDGE(32), // Redback SmartEdge 400/800
    DLT_PPP_SERIAL(50), // PPP over serial with HDLC encapsulation
    DLT_PPP_ETHER(51), // PPP over Ethernet
    DLT_SYMANTEC_FIREWALL(99), // Symantec Enterprise Firewall
    DLT_C_HDLC(104), // Cisco HDLC
    DLT_IEEE802_11(105); // IEEE 802.11 wireless

    private final int code;

    LinkLayerType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static LinkLayerType fromCode(int code) {
        for (LinkLayerType type : LinkLayerType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown link-layer type code: " + code);
    }
}
