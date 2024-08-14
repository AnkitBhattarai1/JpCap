package org.jcap.Core.Constants.NamedCodes.L4;

public class TcpPort extends Port {

    private String description;

    public static final TcpPort TCPMUX = new TcpPort((short) 1, "tcpmux", "TCP Port Service Multiplexer");

    public static final TcpPort COMPRESSNET_MANAGEMENT_UTILITY = new TcpPort((short) 2, "compressnet",
            "Compressnet Management Utility");

    protected TcpPort(Short value, String name, String description) {
        super(value, name);
        this.description = description;
        // TODO Auto-generated constructor stub
    }

}
