package org.jpcap.Core.Packets.Network.IpV4Options;


import org.jpcap.Core.Constants.NamedCodes.L3.IpV4OptionType;
import org.jpcap.Core.Packets.Network.IpV4Option;

/**
 * EndOfOptions
 *<pre>
 *      +--------+
        |00000000|
        +--------+
          Type=0

        This option indicates the end of the option list.  This might
        not coincide with the end of the internet header according to
        the internet header length.  This is used at the end of all
        options, not the end of each option, and need only be used if
        the end of the options would not otherwise coincide with the end
        of the internet header.

        May be copied, introduced, or deleted on fragmentation, or for
        any other reason.
 *</pre>
 */

public class EndOfOptions  extends IpV4Option{

    private static final EndOfOptions INSTANCE = new EndOfOptions();

    public static EndOfOptions getInstance(){
        return INSTANCE;
    }
    
    public  EndOfOptions() {
        super(IpV4OptionType.END_OF_OPTION_LIST);
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public byte[] getRawData() {
        return new byte[1];
    }

}
