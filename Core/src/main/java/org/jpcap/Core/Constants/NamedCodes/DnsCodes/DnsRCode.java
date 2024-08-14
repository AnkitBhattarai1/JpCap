package org.jpcap.Core.Constants.NamedCodes.DnsCodes;

import java.util.HashMap;
import java.util.Map;

import org.jpcap.Core.Constants.NamedCodes.NamedCode;

/*
 * 
 * https://www.iana.org/assignments/dns-parameters/dns-parameters.xhtml#dns-parameters-6
 */
public class DnsRCode extends NamedCode<Byte, DnsRCode> {

  private final String description;

  private static final DnsRCode NO_ERR = new DnsRCode((byte) 0, "NoError", "NO Error Condition");

  private static final DnsRCode FORM_ERR = new DnsRCode((byte) 1, "FormErr", "Format Error");

  private static final DnsRCode SERV_FAIL = new DnsRCode((byte) 2, "ServFail", "Server Failure");

  private static final DnsRCode NX_DOMAIN = new DnsRCode((byte) 3, "NxDomain", "Non-Existenet Domain");

  private static final DnsRCode NOT_IMP = new DnsRCode((byte) 4, "NotImp", "Not Implemented");

  private static final DnsRCode REFUSED = new DnsRCode((byte) 5, "Refused", "Query Refused");

  private static final DnsRCode YXDOMAIN = new DnsRCode((byte) 6, "YXDmain", "Name Exists when it should not");

  private static final DnsRCode YXRRSET = new DnsRCode((byte) 7, "YxRRSet", "RR Set Exists when it should not");

  private static final DnsRCode NXRRSET = new DnsRCode((byte) 8, "NXRRSet", "RR Set that should exist does not ");

  private static final DnsRCode NOTAUTH = new DnsRCode((byte) 9, "NotAuth", "Server Not Authoritative for zone");

  private static final DnsRCode NOTZONE = new DnsRCode((byte) 10, "NotZone", "Name not contained in zone");

  private static final DnsRCode DSOTYPENI = new DnsRCode((byte) 11, "DSOTYPENI", "DSO-Type not implemented");

  private static final Map<Byte, DnsRCode> registry = new HashMap<Byte, DnsRCode>();

  static {

    registry.put(NO_ERR.getValue(), NO_ERR);
    registry.put(FORM_ERR.getValue(), FORM_ERR);
    registry.put(SERV_FAIL.getValue(), SERV_FAIL);
    registry.put(NX_DOMAIN.getValue(), NX_DOMAIN);
    registry.put(NOT_IMP.getValue(), NOT_IMP);
    registry.put(REFUSED.getValue(), REFUSED);
    registry.put(YXDOMAIN.getValue(), YXDOMAIN);
    registry.put(YXRRSET.getValue(), YXRRSET);
    registry.put(NXRRSET.getValue(), NXRRSET);
    registry.put(NOTAUTH.getValue(), NOTAUTH);
    registry.put(NOTZONE.getValue(), NOTZONE);
    registry.put(DSOTYPENI.getValue(), DSOTYPENI);

  }

  public static DnsRCode InstanceOfCode(Byte key) {
    return (registry.containsKey(key) ? registry.get(key)
        : new DnsRCode(key, "Unknown", "Unknown"));
  }

  protected DnsRCode(Byte value, String name, String description) {
    super(value, name);
    this.description = description;
    // TODO Validate to be done...
  }

  @Override
  public int compareTo(DnsRCode o) {
    return 0;
    // TODO:To be implemented
  }

}
