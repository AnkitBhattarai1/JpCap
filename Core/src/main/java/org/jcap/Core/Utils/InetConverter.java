package org.jcap.Core.Utils;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.ByteOrder;

import org.jcap.Core.Native.NativeWpcapMapping.in6_addr;
import org.jcap.Core.Native.NativeWpcapMapping.in_addr;

/**
 * An util class for converting the Network addresses
 * 
 * @author Ankit Bhattarai
 * 
 */
public class InetConverter {

	/**
	 * Converts a IPv4 address from in_addr structure to a {@code Inet4Address}
	 * 
	 * @param inAddr
	 * @return An {@code Inet4Address} from a {@code in_addr} structure
	 */
	public static Inet4Address toInet4Address(in_addr inAddr) {
		if (inAddr == null)
			return null;
		try {
			return (Inet4Address) Inet4Address
					.getByAddress(ByteOperations.getByteArray(inAddr.S_addr.intValue(), ByteOrder.nativeOrder()));
		} catch (Exception e) {
			// TODO: handle exception
			throw new AssertionError(e);
		}
	}

	public static Inet6Address toInet6Address(in6_addr in6Addr, int sin6_scope_id) {

		if (in6Addr == null)
			return null;
		try {
			return (Inet6Address) Inet6Address.getByAddress("", in6Addr.in6_addr, sin6_scope_id);

		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	public static Inet4Address toInet4Address(byte[] rawData, int offset, ByteOrder bo) {
		ByteOperations.validate(rawData, offset, Byte.BYTES * 4);

		try {
			if (bo == ByteOrder.LITTLE_ENDIAN)
				return (Inet4Address) InetAddress.getByAddress(ByteOperations.reverse(rawData));
			else
				return (Inet4Address) Inet4Address.getByAddress(rawData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError(e);
			// TODO To be done later
		}
	}

	public static Inet6Address toInet6Address(byte[] rawData, int offset, ByteOrder bo) {
		ByteOperations.validate(rawData, offset, Byte.BYTES * 16);

		try {
			if (bo == ByteOrder.LITTLE_ENDIAN)
				return (Inet6Address) InetAddress.getByAddress(ByteOperations.reverse(rawData));

			else
				return (Inet6Address) InetAddress.getByAddress(rawData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError(e);
			// TODO TO be done later...
		}
	}

}
