
package org.jpcap.Core.Constants.NamedCodes.L3;

import java.util.HashMap;
import java.util.Map;

import org.jpcap.Core.Constants.NamedCodes.NamedCode;
import org.jpcap.Core.Packets.Network.IpV4Option;

public final class IpV4OptionType extends NamedCode<Byte, IpV4OptionType> {

    // Option constants
    public static final IpV4OptionType END_OF_OPTION_LIST = new IpV4OptionType((byte) 0, "End of Option List", null);
    public static final IpV4OptionType NO_OPERATION = new IpV4OptionType((byte) 1, "No Operation", null);
    public static final IpV4OptionType SECURITY = new IpV4OptionType((byte) 130, "Security", null);
    public static final IpV4OptionType LOOSE_SOURCE_ROUTING = new IpV4OptionType((byte) 131, "Loose Source Routing", null);
    public static final IpV4OptionType INTERNET_TIMESTAMP = new IpV4OptionType((byte) 68, "Internet Timestamp", null);
    public static final IpV4OptionType EXTENDED_SECURITY = new IpV4OptionType((byte) 133, "Extended Security", null);
    public static final IpV4OptionType CIPSO = new IpV4OptionType((byte) 134, "CIPSO", null);
    public static final IpV4OptionType RECORD_ROUTE = new IpV4OptionType((byte) 7, "Record Route", null);
    public static final IpV4OptionType STREAM_ID = new IpV4OptionType((byte) 136, "Stream ID", null);
    public static final IpV4OptionType STRICT_SOURCE_ROUTING = new IpV4OptionType((byte) 137, "Strict Source Routing", null);
    public static final IpV4OptionType ZSU = new IpV4OptionType((byte) 10, "ZSU", null);
    public static final IpV4OptionType MTUP = new IpV4OptionType((byte) 11, "MTUP", null);
    public static final IpV4OptionType MTUR = new IpV4OptionType((byte) 12, "MTUR", null);
    public static final IpV4OptionType FINN = new IpV4OptionType((byte) 205, "FINN", null);
    public static final IpV4OptionType VISA = new IpV4OptionType((byte) 142, "VISA", null);
    public static final IpV4OptionType ENCODE = new IpV4OptionType((byte) 15, "ENCODE", null);
    public static final IpV4OptionType IMITD = new IpV4OptionType((byte) 144, "IMITD", null);
    public static final IpV4OptionType EIP = new IpV4OptionType((byte) 145, "EIP", null);
    public static final IpV4OptionType TRACEROUTE = new IpV4OptionType((byte) 82, "Traceroute", null);
    public static final IpV4OptionType ADDRESS_EXTENSION = new IpV4OptionType((byte) 147, "Address Extension", null);
    public static final IpV4OptionType ROUTER_ALERT = new IpV4OptionType((byte) 148, "Router Alert", null);
    public static final IpV4OptionType SELECTIVE_DIRECTED_BROADCAST = new IpV4OptionType((byte) 149, "Selective Directed Broadcast", null);
    public static final IpV4OptionType DYNAMIC_PACKET_STATE = new IpV4OptionType((byte) 151, "Dynamic Packet State", null);
    public static final IpV4OptionType UPSTREAM_MULTICAST_PACKET = new IpV4OptionType((byte) 152, "Upstream Multicast Packet", null);
    public static final IpV4OptionType QUICK_START = new IpV4OptionType((byte) 25, "Quick-Start", null);

    private static final Map<Byte, IpV4OptionType> registry = new HashMap<>();

    static {
        registry.put(END_OF_OPTION_LIST.getValue(), END_OF_OPTION_LIST);
        registry.put(NO_OPERATION.getValue(), NO_OPERATION);
        registry.put(SECURITY.getValue(), SECURITY);
        registry.put(LOOSE_SOURCE_ROUTING.getValue(), LOOSE_SOURCE_ROUTING);
        registry.put(INTERNET_TIMESTAMP.getValue(), INTERNET_TIMESTAMP);
        registry.put(EXTENDED_SECURITY.getValue(), EXTENDED_SECURITY);
        registry.put(CIPSO.getValue(), CIPSO);
        registry.put(RECORD_ROUTE.getValue(), RECORD_ROUTE);
        registry.put(STREAM_ID.getValue(), STREAM_ID);
        registry.put(STRICT_SOURCE_ROUTING.getValue(), STRICT_SOURCE_ROUTING);
        registry.put(ZSU.getValue(), ZSU);
        registry.put(MTUP.getValue(), MTUP);
        registry.put(MTUR.getValue(), MTUR);
        registry.put(FINN.getValue(), FINN);
        registry.put(VISA.getValue(), VISA);
        registry.put(ENCODE.getValue(), ENCODE);
        registry.put(IMITD.getValue(), IMITD);
        registry.put(EIP.getValue(), EIP);
        registry.put(TRACEROUTE.getValue(), TRACEROUTE);
        registry.put(ADDRESS_EXTENSION.getValue(), ADDRESS_EXTENSION);
        registry.put(ROUTER_ALERT.getValue(), ROUTER_ALERT);
        registry.put(SELECTIVE_DIRECTED_BROADCAST.getValue(), SELECTIVE_DIRECTED_BROADCAST);
        registry.put(DYNAMIC_PACKET_STATE.getValue(), DYNAMIC_PACKET_STATE);
        registry.put(UPSTREAM_MULTICAST_PACKET.getValue(), UPSTREAM_MULTICAST_PACKET);
        registry.put(QUICK_START.getValue(), QUICK_START);
    }

    private final boolean copied;
    private final byte number;
    Class<? extends IpV4Option> clazz;

    
    protected IpV4OptionType(Byte value, String name, Class<? extends IpV4Option> clazz) {
        super(value, name);
        this.clazz=clazz;
        this.copied = (value & 0x80) != 0;
        this.number = (byte) (value & 0x1F);
    }
    
    public Class<? extends IpV4Option> getClazz()
    {
        return clazz;
    }

    public boolean isCopied() {
        return copied;
    }

    public byte getNumber() {
        return number;
    }

    public static IpV4OptionType getInstance(Byte value) {
        return registry.getOrDefault(value, new IpV4OptionType(value, "Unknown", null));
    }

    public static IpV4OptionType register(IpV4OptionType type) {
        return registry.put(type.getValue(), type);
    }

    @Override
    public int compareTo(IpV4OptionType o) {
        return getValue().compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(getValue() & 0xFF);
    }
}
