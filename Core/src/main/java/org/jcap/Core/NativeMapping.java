package org.jcap.Core;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;


import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;


public class NativeMapping {
    public static final String PCAP_LIB_NAME =
      System.getProperty(
          NativeMapping.class.getPackage().getName() + ".pcapLibName",
          Platform.isWindows() ? "wpcap" : "pcap");

          static{
            Native.register(NativeMapping.class, NativeLibrary.getInstance(PCAP_LIB_NAME));
          }
          
          public static native int pcap_findalldevs(PointerByReference alldevsp, PcapErrbuf errbuf);
          public static native void pcap_freealldevs(Pointer alldevsp);

          public static class pcap_if extends Structure {
          
            public pcap_if.ByReference next;/*pointer that points to the next pcap_if---struct pcap_if* next  */
            public String name; 
            public String description;/*textual description of the interface---char* description */
            public pcap_addr.ByReference addresses; /* ---pacp_addr * addresses*/

            public pcap_if(){}

            public static class ByReference extends pcap_if implements Structure.ByReference{}

            public pcap_if(Pointer p){
              super(p);
              read();
            }

            @Override
            public List<String> getFieldOrder(){
             return  List.of("next","name","description","addresses");
            }


          }

          /*Representation of an interface address */
          /*
            struct pcap_addr {
              struct pcap_addr *next;
              struct sockaddr *addr;		
              struct sockaddr *netmask;	
              struct sockaddr *broadaddr;	
              struct sockaddr *dstaddr;	
            };
           */
          public static class pcap_addr extends Structure{
             public pcap_addr.ByReference next;/*pointer that points to the next---struct pcap_addr* next */
             public soc_addr.ByReference addr; /*address struc---soc_addr* address */
             public soc_addr.ByReference netmask;/* netmask for that addrress---soc_addr* netmask */
             public soc_addr.ByReference brodaddr;/*broadcast address for tha taddress */
             public soc_addr.ByReference dstaddr; /*P2P destination address for that address */

             public pcap_addr(){

             }
             public pcap_addr(Pointer p){
              super(p);
              read();
             }

             public static class ByReference extends pcap_addr implements Structure.ByReference{}

             @Override
             protected List<String> getFieldOrder(){
              List<String> list = new ArrayList<>();
              list.add("next");
              list.add("addr");
              list.add("netmask");
              list.add("brodaddr");
              list.add("dstaddr");
              return list;
             }
          }


          public static class soc_addr extends Structure{

            public short sa_family; // don't know that is this.....
            public byte [] sa_data= new byte[14];
            public soc_addr(){}

            public soc_addr(Pointer p){
              super(p);
              read();
            }

            public static class ByReference extends soc_addr implements Structure.ByReference{}

            @Override 
            protected List<String> getFieldOrder(){
              List<String> list = new ArrayList<>();
              list.add("sa_family");
              list.add("sa_data");
              return list;
            }

            short getfamily(){
              return (!(Platform.isMac()
              || Platform.isFreeBSD()
              ||Platform.isOpenBSD()
              ||Platform.iskFreeBSD())
              ?  sa_family
              :(ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)
                              ?(short) (0xFF & sa_family)
                              :(short) (0xFF & (sa_family >> 8))));
              //   if(!(Platform.isMac()|| Platform.isFreeBSD()|| Platform.isOpenBSD()|| Platform.iskFreeBSD())) return sa_family;
              //   else{
              //   if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
              //     return (short) (0xFF & sa_family);
              //   } else {
              //     return (short) (0xFF & (sa_family >> 8));
              //   }
              // }
                
              }
            }

            public static class PcapErrbuf extends Structure {

              public byte[] buf = new byte[PCAP_ERRBUF_SIZE()];
          
              public PcapErrbuf() {}
          
              private static int PCAP_ERRBUF_SIZE() {
                return 256;
              }
          
              public int length() {
                return toString().length();
              }
          
              @Override
              protected List<String> getFieldOrder() {
                List<String> list = new ArrayList<String>();
                list.add("buf");
                return list;
              }
          
              @Override
              public String toString() {
                return Native.toString(buf);
              }
            }
}
 
            



