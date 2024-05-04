package org.jcap.Core.Constants.NamedCodes.DnsCodes;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<Short, DnsResourceRecordType> registry = new HashMap<>();
    static {
        registry.put((short) 1, A);
    }

    public static DnsResourceRecordType instanceOfCode(short value) {

        if (registry.containsKey(value)) {
            return registry.get(value);
        } else {
            return new DnsResourceRecordType(value, "unknown");
        }
    }
}
