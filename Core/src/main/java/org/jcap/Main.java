package org.jcap;

import java.lang.reflect.InvocationTargetException;

import org.jcap.Core.Packets.DNS.DnsRData;
import org.jcap.Core.Packets.DNS.DnsRDataA;
import org.jcap.Core.Packets.DNS.DnsRData.DnsRDataBuilder;

public class Main {
    public static void main(String[] args) {
        // System.out.println(DnsDomainName.parse("google.com").toString());
        DnsRData d;
        Class<DnsRDataA> clazz = DnsRDataA.class;
        DnsRDataBuilder b = null;
        try {
            b = (DnsRDataBuilder) clazz.getMethod("Builder", null).invoke(null, null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        b.build();

    }
}