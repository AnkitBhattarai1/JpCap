package org.jcap.Core.Constants.NamedCodes.DnsCodes;

import java.util.HashMap;
import java.util.Map;

import org.jcap.Core.Constants.NamedCodes.NamedCode;

public class DnsClass extends NamedCode<Short, DnsClass> {

  public static final DnsClass IN = new DnsClass((short) 1, "INTERNET");
  /**
   * Represents the CSNET class of DNS records.
   * <p>
   * This class is obsolete and was used historically for the CSNET network, a
   * part of the early Internet. It is maintained
   * in modern DNS specifications only for backward compatibility with older
   * systems and is referenced in examples found
   * in some obsolete RFCs. This class is no longer used in practice and should
   * generally be avoided in new DNS-related
   * implementations unless dealing with legacy systems or historical data.
   * </p>
   */
  public static final DnsClass CS = new DnsClass((short) 2, "CSNET");

  public static final DnsClass CH = new DnsClass((short) 3, "CHAOS");

  public static final DnsClass HS = new DnsClass((short) 4, "Hesoid");

  private static final Map<Short, DnsClass> registry = new HashMap<Short, DnsClass>();

  static {
    registry.put((short) 1, IN);
    registry.put((short) 2, CS);
    registry.put((short) 3, CH);
    registry.put((short) 4, HS);
  }

  protected DnsClass(Short value, String name) {
    super(value, name);
  }

  public static DnsClass instanceOfCode(Short value) {
    if (registry.containsKey(value)) {
      return registry.get(value);
    } else {
      return new DnsClass(value, "unknown");
    }
  }

  @Override
  public int compareTo(DnsClass o) {
    // TODO:To be implemented
    return 0;
  }

  @Override
  public String toString() {
    return getName() + "(0x" + String.format("%04x", getValue()) + ")";
  }

}
