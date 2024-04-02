package org.jcap.Core.Address;

import java.net.Socket;
import java.net.Inet4Address;


public class Inets extends Socket{

    public static final short AF_UNSPEC = 0; // unspecified
    public static final short AF_UNIX=1; //local to host (pipes ,portals)
    public static final short AF_INET=2; //internetwork: UDP,TCP,etc.
    public static final short AF_IMPLINK = 3 ;// arpanet imp address.
        
}
