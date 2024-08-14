package org.jpcap.Core.Constants.NamedCodes.L3;

import org.jpcap.Core.Constants.NamedCodes.NamedCode;

/**
 * 
 * 
 * 
 * @see <a href=
 *      "http://www.iana.org/assignments/version-numbers/version-numbers.xml">IANA</a>
 */

public class IpVersion extends NamedCode<Byte, IpVersion> {

    protected IpVersion(Byte value, String name) {
        super(value, name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int compareTo(IpVersion o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
