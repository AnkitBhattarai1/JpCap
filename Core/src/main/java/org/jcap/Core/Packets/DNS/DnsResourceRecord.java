package org.jcap.Core.Packets.DNS;

import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsClass;
import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsResourceRecordType;

public class DnsResourceRecord {
  /*
   * --------------------1 1 1 1 1 1
   * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
   * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
   * |---------------------------------------------- |
   * /---------------------------------------------- /
   * /-----------------------NAME------------------- /
   * |---------------------------------------------- |
   * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
   * |-----------------------TYPE--------------------|
   * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
   * |-----------------------CLASS-------------------|
   * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
   * |-----------------------TTL---------------------|
   * |---------------------------------------------- |
   * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
   * |-----------------------RDLENGTH----------------|
   * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--|
   * /-----------------------RDATA-------------------/
   * /-----------------------------------------------/
   * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
   * 
   */

  /*
   * Name:
   * Type:
   * Class:
   * TTL:
   * RDLENGTH:
   * RDATA:
   */

  private final DnsDomainName name;
  private final DnsResourceRecordType dataType;
  private final DnsClass dataClass;
  private final int ttl;
  private final short rdLength;
  private final DnsRData rData;

  private DnsResourceRecord(DnsResourceRecordBuilder builder) {
    // TODO validation is to be implemented....
    this.name = builder.name;
    this.dataType = builder.dataType;
    this.dataClass = builder.dataClass;
    this.ttl = builder.ttl;
    this.rdLength = builder.rdLength;
    this.rData = builder.rdData;
  }

  public static final class DnsResourceRecordBuilder {
    private DnsDomainName name;
    private DnsResourceRecordType dataType;
    private DnsClass dataClass;
    private int ttl;
    private short rdLength;
    private DnsRData rdData;
  }
}
