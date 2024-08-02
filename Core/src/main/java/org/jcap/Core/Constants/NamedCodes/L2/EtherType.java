package org.jcap.Core.Constants.NamedCodes.L2;

import org.jcap.Core.Constants.NamedCodes.NamedCode;

/**
 * Represents the EtherType in the ethernet frame....
 */
public class EtherType extends NamedCode<Short, EtherType> {

    public EtherType(Short value, String name) {
        super(value, name);
    }

    @Override
    public int compareTo(EtherType o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
