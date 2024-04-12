package org.jcap.Core.Packets;

public abstract class AbstractPacket implements Packet {
    

    private final  int length;
    private final byte[] rawData;

    public AbstractPacket(){
        this.length= calculateLength();
        this.rawData= rawData();
    }

    private byte[] rawData() {
        throw new UnsupportedOperationException("Unimplemented method 'rawData'");
    }

    @Override 
    public Header getHeader(){
        return null;
    }

    @Override
    public Packet getPlayLoad(){
        return null;
    }

    public int getLength(){
        return length;
    }

    private int calculateLength() {
        int length =0;
        if(getHeader()!=null) length+=getHeader().length();
        if(getPlayLoad()!=null) length+= getPlayLoad().length();
        return length;
    }
    
    

   public  static class AbstractHeader  implements Header{

    private final int length;
    private final byte[] rawData;

    protected AbstractHeader(){
        this.length=calculateLength();
        this.rawData=null;
    }

    @Override
    public int length() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'length'");
    }

    @Override
    public byte[] getRawData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRawData'");
    }


    private int calculateLength(){
        int length =0; 
        return length;
    }
}
    
}
