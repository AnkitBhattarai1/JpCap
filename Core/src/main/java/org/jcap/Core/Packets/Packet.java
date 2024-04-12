package org.jcap.Core.Packets;

public interface Packet {

	public int length();

	public Header getHeader();

	public Packet getPlayLoad();

	public <T extends Packet> T getPacketOf(Class<T> packetType);

	public <T extends Packet> boolean containsPacketOf(Class<T> packetType);

	public byte[] getRawData();

	public PacketBuilder Builder();
	
	public static interface PacketBuilder {

		public<T extends PacketBuilder> T get(Class<T> cls);

		public Packet build();
	}

	public interface Header {
		public int length();
		public byte[] getRawData();
	}
}
