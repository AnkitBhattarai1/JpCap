package org.jpcap.Core.Constants.NamedCodes.L4;

import org.jpcap.Core.Constants.NamedCodes.NamedCode;

public abstract class Port extends NamedCode<Short, Port> {

    protected Port(Short value, String name) {
        super(value, name);
        // TODO Auto-generated constructor stub
    }

    public int valueAsInt() {
        return 0xFFFF & getValue();
    }

    @Override
    public int compareTo(Port o) {
        return getValue().compareTo(o.getValue());
    }

}
