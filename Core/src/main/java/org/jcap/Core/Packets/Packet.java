package org.jcap.Core.Packets;

public interface Packet {
	public int length();

	public Header getHeader();

	public Packet getPlayLoad();

	public <T extends Packet> T getPacketOf(Class<T> packetType);

	public <T extends Packet> boolean containsPacketOf(Class<T> packetType);

	public static interface PacketBuilder {
	}

	public interface Header {
	}
}
