package org.jcap.Core.Constants.NamedCodes.DnsCodes;

import java.util.HashMap;
import java.util.Map;

import org.jcap.Core.Constants.NamedCodes.NamedCode;
import org.jcap.Core.Packets.DNS.DnsRData;
import org.jcap.Core.Packets.DNS.DnsRDataA;
import org.jcap.Core.Packets.DNS.SingleDomainNameRR;

public class DnsResourceRecordType extends NamedCode<Short, DnsResourceRecordType> {

    Class<? extends DnsRData> rDataClass;

    protected DnsResourceRecordType(Short value, String name, Class<? extends DnsRData> rDataClass) {
        super(value, name);
        // TODO Auto-generated constructor stub
    }

    public static final DnsResourceRecordType A = new DnsResourceRecordType((short) 1,
            "A (Host address)", DnsRDataA.class);

    public static final DnsResourceRecordType NS = new DnsResourceRecordType((short) 2,
            "NS (Authoritative name server)", SingleDomainNameRR.class);

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
            return new DnsResourceRecordType(value, "unknown", null);
        }
    }

    public Class<? extends DnsRData> getRDataClass() {
        return rDataClass;
    }

    @Override
    public String toString() {
        return getName();
    }
}
