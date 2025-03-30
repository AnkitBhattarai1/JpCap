package org.jpcap.Core.Packets.Network.IpV4Options;

import java.net.Inet4Address;
import java.util.List;

import org.jpcap.Core.Constants.NamedCodes.L3.IpV4OptionType;
import org.jpcap.Core.Exceptions.IllegalRawDataException;

/**
 * StrictSrcRoute
 */

public class StrictSrcRoute extends RouteOption {

    public StrictSrcRoute(byte length, byte pointer, List<Inet4Address> routeData) {
        super(length, pointer, routeData,IpV4OptionType.STRICT_SOURCE_ROUTING);
    }

    public StrictSrcRoute(byte[] rawData, int offset , int len) throws IllegalRawDataException{
        super(rawData,offset,len);
        //The exception is not done. It needs to be improved to provide more information.
        if(getType() != IpV4OptionType.STRICT_SOURCE_ROUTING ) throw new IllegalRawDataException("The type doesnot Match");
    }

    
}
