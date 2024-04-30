package org.jcap.Core.Constants.NamedCodes.DnsCodes;

import org.jcap.Core.Constants.NamedCodes.NamedCode;

public class DnsResourceRecordType extends NamedCode<Short, DnsResourceRecordType> {

    protected DnsResourceRecordType(Short value, String name) {
        super(value, name);
        // TODO Auto-generated constructor stub
    }

    public static final DnsResourceRecordType A = new DnsResourceRecordType((short) 1,
            "A (Host address)");

    public static final DnsResourceRecordType NS = new DnsResourceRecordType((short) 2,
            "NS (Authoritative name server)");

    @Override
    public int compareTo(DnsResourceRecordType o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
