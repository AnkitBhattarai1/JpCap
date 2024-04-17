package org.jcap.Core.Constants.NamedCodes.DnsCodes;

import java.util.HashMap;
import java.util.Map;

import org.jcap.Core.Constants.NamedCodes.NamedCode;

public final class DnsOpCode extends NamedCode<Byte, DnsOpCode> {

  public static final DnsOpCode QUERY = new DnsOpCode((byte) 0, "Query");

  public static final DnsOpCode IQUERY = new DnsOpCode((byte) 1, "IQuery");

  public static final DnsOpCode STATUS = new DnsOpCode((byte) 2, "Status");

  public static final DnsOpCode NOTIFY = new DnsOpCode((byte) 4, "Notify");

  public static final DnsOpCode UPDATE = new DnsOpCode((byte) 5, "Update");

  public static final Map<Byte, DnsOpCode> registry = new HashMap<Byte, DnsOpCode>();

  static {
    registry.put((byte) 0, QUERY);
    registry.put((byte) 1, IQUERY);
    registry.put((byte) 2, STATUS);
    registry.put((byte) 4, NOTIFY);
    registry.put((byte) 5, UPDATE);
  }

  protected DnsOpCode(Byte value, String name) {
    super(value, name);
    if ((value & 0xF0) != 0) {
      throw new IllegalArgumentException(
          value + " is invalid value. " + "DNS OpCode must be between 0 and 15");
    }
  }

  public static DnsOpCode instanceOfCode(Byte key) { // Key is the code number
    if (registry.containsKey(key)) {
      return registry.get(key);
    } else {
      return new DnsOpCode(key, "unknown");
    }
  }

  @Override
  public int compareTo(DnsOpCode o) {
    return getValue().compareTo(o.getValue());
  }

}
