package org.jcap.Core.Address;

import java.net.Socket;
import java.nio.ByteOrder;

import org.jcap.Core.Native.NativeWpcapMapping.in_addr;
import org.jcap.Core.Utils.ByteOperations;

import java.net.Inet4Address;

public class Inets {

	public static final short AF_UNSPEC = 0; // unspecified
	public static final short AF_UNIX = 1; // local to host (pipes ,portals)
	public static final short AF_INET = 2; // internetwork: UDP,TCP,etc.
	public static final short AF_IMPLINK = 3;// arpanet imp address.
	public static final short AF_PUP = 4; // pup protocols: e.g. BSP
	public static final short AF_CHAOS = 5;// mit CHAOS protocols
	public static final short AF_NS = 6; // XEROX NS protocols
}
