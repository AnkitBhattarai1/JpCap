package org.jpcap.Core.Packets.Network.IpV4Options;

import org.jpcap.Core.Constants.NamedCodes.L3.IpV4OptionType;
import org.jpcap.Core.Packets.Network.IpV4Option;

/**
 * NoOperation
 */
public class NoOperation extends IpV4Option
{
    private static final NoOperation INSTANCE = new NoOperation();

    public  NoOperation(){
        super(IpV4OptionType.NO_OPERATION);
    }

    public static NoOperation getInstance(){
        return INSTANCE;
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public byte[] getRawData() {
        return new byte[]{(byte)1};
    }


    
}
