package org.jpcap.Core.Packets;

public interface Packet {

	public int length();

	public byte[] getRawData();

	public Header getHeader();

	public Packet getPlayLoad();

	public <T extends Packet> T getPacketOf(Class<T> packetType);

	public <T extends Packet> boolean containsPacketOf(Class<T> packetType);

	public static interface PacketBuilder {
		public Packet build();
	}

	public static interface Header {

		public int length();

		public byte[] getRawData();

		public static interface HeaderBuilder {
			public Header build();
		}
	}
}
