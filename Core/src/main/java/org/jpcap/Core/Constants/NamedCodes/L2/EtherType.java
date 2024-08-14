package org.jcap.Core.Constants.NamedCodes.L2;

import java.util.HashMap;
import java.util.Map;

import org.jcap.Core.Constants.NamedCodes.NamedCode;
import org.jcap.Core.Packets.Abstract_Layer_Packet.L3Packet;
import org.jcap.Core.Packets.Network.IpV4Packet;
import org.jcap.Core.Packets.Network.IpV6Packet;

/**
 * Represents the EtherType in the ethernet frame....
 * 
 * /**
 * Ether Type
 *
 * @see <a
 *      href=
 *      "http://www.iana.org/assignments/ieee-802-numbers/ieee-802-numbers.xhtml#ieee-802-numbers-1">IANA
 *      Registry</a>
 */
public class EtherType extends NamedCode<Short, EtherType> {

    public static final int IEEE802_3_MAX_LENGTH = 1500;

    Class<? extends L3Packet> clazz;
    String desc;

    public EtherType(Short value, String name, String desc, Class<? extends L3Packet> clazz) {
        super(value, name);
        this.clazz = clazz;
        this.desc = desc;
    }

    public Class<? extends L3Packet> getPacketClass() {
        return clazz;
    }

    public String getDescription() {
        return desc;

    }

    public static final EtherType IPv4 = new EtherType((short) 0x0800, "IPv4", "IPv4", IpV4Packet.class);
    public static final EtherType ARP = new EtherType((short) 0x0806, "ARP", "ARP", null);
    public static final EtherType WOL = new EtherType((short) 0x0842, "WOL", "Wake-on-LAN", null);
    public static final EtherType RARP = new EtherType((short) 0x8035, "RARP", "Reverse ARP", null);
    public static final EtherType IPv6 = new EtherType((short) 0x86DD, "IPv6", "IPv6", IpV6Packet.class);
    public static final EtherType VLAN = new EtherType((short) 0x8100, "VLAN", "VLAN-tagged frame", null);
    public static final EtherType PPPoE_Discovery = new EtherType((short) 0x8863, "PPPoE Discovery", "PPPoE Discovery",
            null);
    public static final EtherType PPPoE_Session = new EtherType((short) 0x8864, "PPPoE Session", "PPPoE Session", null);
    public static final EtherType MPLS_UC = new EtherType((short) 0x8847, "MPLS Unicast", "MPLS Unicast", null);
    public static final EtherType MPLS_MC = new EtherType((short) 0x8848, "MPLS Multicast", "MPLS Multicast", null);
    public static final EtherType LLDP = new EtherType((short) 0x88CC, "LLDP", "Link Layer Discovery Protocol", null);
    public static final EtherType SLOW = new EtherType((short) 0x8809, "SLOW", "Slow Protocols (IEEE 802.3)", null);
    public static final EtherType EAPOL = new EtherType((short) 0x888E, "EAPOL",
            "Extensible Authentication Protocol over LAN", null);
    public static final EtherType QINQ = new EtherType((short) 0x88A8, "QinQ", "IEEE 802.1ad Service VLAN", null);

    private static final Map<Short, EtherType> registry = new HashMap<>();

    static {
        registry.put(IPv4.getValue(), IPv4);
        registry.put(ARP.getValue(), ARP);
        registry.put(WOL.getValue(), WOL);
        registry.put(RARP.getValue(), RARP);
        registry.put(IPv6.getValue(), IPv6);
        registry.put(VLAN.getValue(), VLAN);
        registry.put(PPPoE_Discovery.getValue(), PPPoE_Discovery);
        registry.put(PPPoE_Session.getValue(), PPPoE_Session);
        registry.put(MPLS_UC.getValue(), MPLS_UC);
        registry.put(MPLS_MC.getValue(), MPLS_MC);
        registry.put(LLDP.getValue(), LLDP);
        registry.put(SLOW.getValue(), SLOW);
        registry.put(EAPOL.getValue(), EAPOL);
        registry.put(QINQ.getValue(), QINQ);
    }

    public static EtherType instanceOfCode(Short code) {
        if (registry.containsKey(code)) {
            return registry.get(code);
        } else if ((code & 0xFFFF) <= IEEE802_3_MAX_LENGTH) {
            return new EtherType(code, "Length", "length", null);
        } else {
            return new EtherType(code, "unknown", "unknown", null);
        }

    }

    @Override
    public int compareTo(EtherType o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
