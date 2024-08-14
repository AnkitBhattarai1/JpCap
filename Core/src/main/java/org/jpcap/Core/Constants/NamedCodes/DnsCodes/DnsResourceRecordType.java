package org.jcap.Core.Constants.NamedCodes.DnsCodes;

import java.util.HashMap;
import java.util.Map;

import org.jcap.Core.Constants.NamedCodes.NamedCode;
import org.jcap.Core.Packets.DNS.DnsRData;
import org.jcap.Core.Packets.DNS.DnsRDataA;
import org.jcap.Core.Packets.DNS.DnsRDataHInfo;
import org.jcap.Core.Packets.DNS.DnsRDataMInfo;
import org.jcap.Core.Packets.DNS.DnsRDataNull;
import org.jcap.Core.Packets.DNS.DnsRDataSoa;
import org.jcap.Core.Packets.DNS.DnsRDataTxt;
import org.jcap.Core.Packets.DNS.DnsRDataWks;
import org.jcap.Core.Packets.DNS.DnsRMXData;
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

    public static final DnsResourceRecordType MD = new DnsResourceRecordType((short) 3, "MD (Mail destination)",
            SingleDomainNameRR.class);

    public static final DnsResourceRecordType MF = new DnsResourceRecordType((short) 4, "MF (Mail forwarder)",
            SingleDomainNameRR.class);

    public static final DnsResourceRecordType CNAME = new DnsResourceRecordType((short) 5,
            "CNAME (Canonical name for an alias)", SingleDomainNameRR.class);

    public static final DnsResourceRecordType SOA = new DnsResourceRecordType((short) 6,
            "SOA(marks the start of a zone of authority)", DnsRDataSoa.class);

    public static final DnsResourceRecordType MB = new DnsResourceRecordType((short) 7, "MB (Mailbox domain name)",
            SingleDomainNameRR.class);

    public static final DnsResourceRecordType MG = new DnsResourceRecordType((short) 8, "MG (Mail group member)",
            SingleDomainNameRR.class);

    public static final DnsResourceRecordType MR = new DnsResourceRecordType((short) 9, "MR (Mail rename domain name)",
            SingleDomainNameRR.class);

    public static final DnsResourceRecordType NULL = new DnsResourceRecordType((short) 10, "NULL (A null RR)",
            DnsRDataNull.class);

    public static final DnsResourceRecordType WKS = new DnsResourceRecordType((short) 11,
            "WKS(a well know service description)", DnsRDataWks.class);

    public static final DnsResourceRecordType PTR = new DnsResourceRecordType((short) 12, "PTR(a domain name pointer)",
            SingleDomainNameRR.class);

    public static final DnsResourceRecordType HINFO = new DnsResourceRecordType((short) 13, "HINFO(host information)",
            DnsRDataHInfo.class);

    public static final DnsResourceRecordType MINFO = new DnsResourceRecordType((short) 14,
            "MINFO(mailbox or main list information)", DnsRDataMInfo.class);

    public static final DnsResourceRecordType MX = new DnsResourceRecordType((short) 15, "MX(mail exchange)",
            DnsRMXData.class);

    public static final DnsResourceRecordType TXT = new DnsResourceRecordType((short) 16, "TXT (text strings)",
            DnsRDataTxt.class);

    @Override
    public int compareTo(DnsResourceRecordType o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    private static final Map<Short, DnsResourceRecordType> registry = new HashMap<>();
    static {
        registry.put((short) 1, A);
        registry.put((short) 2, NS);
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
