package org.jpcap.Core.Packets.Network.IpV4Options;

import java.net.Inet4Address;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.jpcap.Core.Constants.NamedCodes.L3.IpV4OptionType;
import org.jpcap.Core.Exceptions.IllegalRawDataException;
import org.jpcap.Core.Packets.Network.IpV4Option;
import org.jpcap.Core.Utils.InetConverter;

public abstract class RouteOption extends IpV4Option{

    private final byte length;
    private final byte pointer;
    private final List<Inet4Address> routeData;

    public RouteOption(byte length, byte pointer , List<Inet4Address> routeData, IpV4OptionType type){
        super(type);
        this.length=length;
        this.pointer=pointer;
        this.routeData=routeData;
    }

    public RouteOption(byte[] rawData, int offset, int len) throws IllegalRawDataException{
            super(IpV4OptionType.getInstance(rawData[offset]));
   
            if(len< 3) 
            throw new IllegalRawDataException("The rawData Length must be more than 2");
   
            this.length=rawData[offset+1];
            int length_int = 0xFF & length;

            if(length < length_int)
                throw new IllegalRawDataException("The data is too short to build the option");
     
            if (length_int < 3) {
                StringBuilder sb = new StringBuilder(100);
                sb.append("The length field value must be equal or more than 3 but it is: ")
                .append(length_int);
                throw new IllegalRawDataException(sb.toString());
                    }

             if ((length_int - 3) % 4 != 0) {
                throw new IllegalRawDataException("Invalid length for this option: " + length_int);
             }

             this.pointer=rawData[offset+2];

             this.routeData = new ArrayList<Inet4Address>();
            for (int i = 3; i < length_int; i += 4) {
                routeData.add(InetConverter.toInet4Address(rawData, i+offset,ByteOrder.nativeOrder()));
                    }         
    
    }

  @Override
  public int getLength() {
    return routeData.size() * 4 + 3;
  }

    @Override
  public byte[] getRawData() {
    byte[] rawData = new byte[getLength()];
    rawData[0] = getType().getValue();
    rawData[1] = length;
    rawData[2] = pointer;

    int i = 3;
    for (Inet4Address addr : routeData) {
      System.arraycopy(addr.getAddress(), 0, rawData, i, 4);
      i += 4;
    }

    return rawData;
  }

  public byte getPointer(){
    return this.pointer;
  }

  @Override 
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("[option-type: ").append(getType());
    sb.append("] [option-length: ").append(getLength() & 0xFF);
    sb.append(" bytes] [pointer: ").append(getPointer() & 0xFF);
    sb.append("] [route data:");

    for (Inet4Address addr : routeData) {
      sb.append(" ").append(addr);
    }
    sb.append("]");
    return sb.toString();
  }

}
