package org.jcap.Core.Constants.NamedCodes.L4;

import org.jcap.Core.Constants.NamedCodes.NamedCode;

public class TcpOptionKind extends NamedCode<Byte, TcpOptionKind> {

    protected TcpOptionKind(Byte value, String name) {
        super(value, name);
    }

    @Override
    public int compareTo(TcpOptionKind o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
