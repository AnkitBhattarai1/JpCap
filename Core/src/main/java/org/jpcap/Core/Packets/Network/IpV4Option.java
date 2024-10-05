package org.jpcap.Core.Packets.Network;


import org.jpcap.Core.Constants.NamedCodes.L3.IpV4OptionType;


/**
 * <pre>
 *The option filed is variable in length. There may be zero or more options.
 *There are two cases for the format of an options:
 *--> A single octect of option-type
 *--> An option-type octect, an option-length octect , and the actual option-data octects.
 *  The option-length octet counts the option-type octet and the
 *  option-length octet as well as the option-data octets.
 *
   The option-type octet is viewed as having 3 fields:

      1 bit   copied flag,
      2 bits  option class,
      5 bits  option number.

    The copied flag indicates that this option is copied into all
    fragments on fragmentation.

      0 = not copied
      1 = copied

    The option classes are:

      0 = control
      1 = reserved for future use
      2 = debugging and measurement
 *    3 = reserved for future use
 *
 *
 *<pre>
 * 
 * */
public abstract class IpV4Option{

    protected IpV4OptionType type;

    protected IpV4Option(IpV4OptionType type){
        this.type = type;
    }

    public  IpV4OptionType getType(){
        return this.type;
    }; 
    public abstract int getLength();
    public abstract byte[] getRawData();
    
}

