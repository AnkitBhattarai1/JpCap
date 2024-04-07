package org.jcap.Core.Native;

import com.sun.jna.Native;

public class NativePacketMapping {

	static final String PACKET_LIB_NAME = "Packet";
	static {
		Native.register(NativePacketMapping.class, PACKET_LIB_NAME);
	}

}