package org.jpcap.Core.Packets.Network.IpV4Options;

import java.net.Inet4Address;
import java.util.List;

import org.jpcap.Core.Constants.NamedCodes.L3.IpV4OptionType;
import org.jpcap.Core.Exceptions.IllegalRawDataException;
import org.jpcap.Core.Utils.ByteOperations;

/**
 * LooseSrcRoute
 * <pre>
 *      +--------+--------+--------+---------//--------+
        |10000011| length | pointer|     route data    |
        +--------+--------+--------+---------//--------+
         Type=131

         The loose source and record route (LSRR) option provides a means for the source of 
         internet datagram to supply routing  inforjation to be used by teh gateways  in forwarding 
         the datagram to the destination, and to record teh route information


 *</pre>

 */
public class LooseSrcRoute extends RouteOption {

    public LooseSrcRoute(byte[] rawData, int offset, int len) throws IllegalRawDataException
    {
        super(rawData, offset, len);
        ByteOperations.validate(rawData, offset, len);

    }
    
    public LooseSrcRoute(byte length , byte pointer,List<Inet4Address> routeData ){
        super(length,pointer,routeData,IpV4OptionType.LOOSE_SOURCE_ROUTING);
    }

    @Override
    public int getLength() {
        return super.getLength();
    }

    @Override
    public byte getPointer() {
        return super.getPointer();
    }

    @Override
    public byte[] getRawData() {
        return super.getRawData();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
}
