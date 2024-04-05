package org.jcap.Core.Utils;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.nio.ByteOrder;

import org.jcap.Core.Native.NativeWpcapMapping.in6_addr;
import org.jcap.Core.Native.NativeWpcapMapping.in_addr;

public class InetConverter {

    
    
    public static Inet4Address toInet4Address(in_addr inAddr) {
		if (inAddr == null)
			return null;
		try {
			return (Inet4Address) Inet4Address
					.getByAddress(ByteOperations.getByteArray(inAddr.S_addr.longValue(), ByteOrder.nativeOrder()));
		} catch (Exception e) {
			// TODO: handle exception
			throw new AssertionError(e);
		}
	}

	public static Inet6Address toInet6Address(in6_addr in6Addr, int sin6_scope_id){

		if(in6Addr==null) return null;
		try {
			return (Inet6Address) Inet6Address.getByAddress("",in6Addr.in6_addr,sin6_scope_id);

		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}


}
