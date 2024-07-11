package org.jcap.Core.Packets.DNS;

import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsClass;
import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsResourceRecordType;
import org.jcap.Core.Packets.DNS.DnsRData.DnsRDataBuilder;
import org.jcap.Core.Utils.ByteOperations;

/**
 * <pre>
 *                                 1  1  1  1  1  1
 *   0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                                               |
 * /                                               /
 * /                      NAME                     /
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                      TYPE                     |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                     CLASS                     |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                      TTL                      |
 * |                                               |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * |                   RDLENGTH                    |
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--|
 * /                     RDATA                     /
 * /                                               /
 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * 
 * where:

 * NAME            a domain name to which this resource record pertains.
 * 
 * TYPE            two octets containing one of the RR type codes.  This
 *                 field specifies the meaning of the data in the RDATA
 *                 field.
 * 
 * CLASS           two octets which specify the class of the data in the
 *                 RDATA field.
 * 
 * TTL             a 32 bit unsigned integer that specifies the time
 *                 interval (in seconds) that the resource record may be
 *                 cached before it should be discarded.  Zero values are
 *                 interpreted to mean that the RR can only be used for the
 *                 transaction in progress, and should not be cached.
 *RDLENGTH         an unsigned 16 bit integer that specifies the length in
 *                 octets of the RDATA field.
 *
 *RDATA           a variable length string of octets that describes the
 *               resource.  The format of this information varies
 *               according to the TYPE and CLASS of the resource record.
 *               For example, the if the TYPE is A and the CLASS is IN,
 *               the RDATA field is a 4 octet ARPA Internet address.
 * 
 * </pre>
 * 
 * @see <a href="https://tools.ietf.org/html/rfc1035">RFC 1035</a>
 */
public class DnsResourceRecord {

  private final DnsDomainName name;
  private final DnsResourceRecordType dataType;// 16bit
  private final DnsClass dataClass;// 16bit
  private final int ttl;// 32bbit
  private final short rdLength;// 16bit
  private final DnsRData rData;// varibleLength

  private DnsResourceRecord(DnsResourceRecordBuilder builder) {

    // TODO validation is to be implemented....
    this.name = builder.name;
    this.dataType = builder.dataType;
    this.dataClass = builder.dataClass;
    this.ttl = builder.ttl;
    this.rdLength = builder.rdLength;
    this.rData = builder.rdData;

  }

  /**
   * Creates a new DnsResourceRecordBuilder to build a DnsResourceRecord.
   * 
   * @return A new instance of DnsResourceRecordBuilder
   */
  public static DnsResourceRecordBuilder Builder() {
    return new DnsResourceRecordBuilder();
  }

  public static DnsResourceRecordBuilder Builder(byte[] rawData, int offset, int length) {
    ByteOperations.validate(rawData, offset, length);
    return new DnsResourceRecordBuilder(rawData, offset, length);
  }

  public DnsDomainName getDomainName() {
    return name;
  }

  public DnsResourceRecordType getDataType() {
    return dataType;
  }

  public DnsClass getClazz() {
    return dataClass;
  }

  public int getTTL() {
    return ttl;
  }

  public short getRdLength() {
    return rdLength;
  }

  public DnsRData getRData() {
    return rData;
  }

  public byte[] getRawData() {
    int position = 0;
    byte[] rawData = new byte[length()];
    byte[] rawNameBytes = name.getRawData();
    System.arraycopy(rawNameBytes, 0, rawData, position, rawNameBytes.length);
    position += rawNameBytes.length;

    System.arraycopy(ByteOperations.getByteArray(dataType.getValue()), 0, rawData, position, Short.BYTES);
    position += Short.BYTES;

    System.arraycopy(ByteOperations.getByteArray(dataClass.getValue()), 0, rawData, position, Short.BYTES);
    position += Short.BYTES;

    System.arraycopy(ByteOperations.getByteArray(ttl), 0, rawData, position, Integer.BYTES);
    position += Integer.BYTES;

    System.arraycopy(ByteOperations.getByteArray(rdLength), 0, rawData, position, Short.BYTES);
    position += Short.BYTES;

    byte[] rawRData = rData.getRawData();
    System.arraycopy(rawRData, 0, rawData, position, rawRData.length);

    return rawData;
  }

  public int length() {
    int len = (rData == null) ? 0 : rData.length();
    len += (Short.BYTES * 3 + Integer.BYTES + name.length() + name.length());
    return len;

  }

  public static final class DnsResourceRecordBuilder {

    private DnsDomainName name;
    private DnsResourceRecordType dataType;
    private DnsClass dataClass;
    private int ttl;
    private short rdLength;
    private DnsRData rdData;

    private DnsResourceRecordBuilder() {// For custom building of DnsResourceRecord....
    }

    private DnsResourceRecordBuilder(byte[] rawData, int offset, int length) {

      int position = 0;
      this.name = DnsDomainName.Builder(rawData, offset, length).build();
      position += name.length();

      if (length - position < (Short.BYTES * 3 + Integer.BYTES))
        throw new IllegalArgumentException("The data is not sufficient to build the Packet");

      this.dataType = DnsResourceRecordType.instanceOfCode(ByteOperations.getShort(rawData, offset + position));
      position += Short.BYTES;

      this.dataClass = DnsClass.instanceOfCode(ByteOperations.getShort(rawData, offset + position));
      position += Short.BYTES;

      this.ttl = ByteOperations.getInt(rawData, offset + position);
      position += Integer.BYTES;

      this.rdLength = ByteOperations.getShort(rawData, offset + position);

      if (length - position < rdLength)
        throw new IllegalArgumentException("The data is insufficient for the declared length of the rdData");

      Class<? extends DnsRData> clazz = dataType.getRDataClass();
      if (rdLength == 0)
        this.rdData = null;

      try {

        // Invoke the Builder(byte[], int, int) method via reflection
        DnsRDataBuilder b = (DnsRDataBuilder) clazz.getMethod("Builder", byte[].class, int.class, int.class)
            .invoke(null, rawData, offset + position, rdLength);

        this.rdData = b.build();

      } catch (Exception e) {
        throw new IllegalArgumentException("Error building rdData", e);
      }

    }

    public DnsResourceRecord build() {
      validate(this);
      return new DnsResourceRecord(this);
    }

    private void validate(DnsResourceRecordBuilder builder) {
      // TODO:Validate the builder...
    }

  }
}
