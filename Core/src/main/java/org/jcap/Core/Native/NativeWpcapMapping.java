package org.jcap.Core.Native;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;


import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;

/*
 * @author Ankit Bhattarai
 */
public class NativeWpcapMapping {
    public static final String PCAP_LIB_NAME =
      System.getProperty(
          NativeWpcapMapping.class.getPackage().getName() + ".pcapLibName",
          Platform.isWindows() ? "wpcap" : "pcap");

          static{
            Native.register(NativeWpcapMapping.class, NativeLibrary.getInstance(PCAP_LIB_NAME));
          }

          //native functions

          //int pcap_findalldevs(pcap_if_t *aldevsp, char* errbuf)
          public static native int pcap_findalldevs(PointerByReference alldevsp, PcapErrbuf errbuf);

          public static native void pcap_freealldevs(Pointer alldevsp);

          public static native Pointer pcap_lookupdev(PcapErrbuf errbuf);


          /* The pcap_if structure represents a network interface
           on the system that can be used for capturing the packet */
           /*
            * struct pcap_if{
              struct pcap_if* next;
              char* name;
              char* description;
              pcap_addr* addresses;
              bpf_u_int32 flags;

            }
            */
          public static class pcap_if extends Structure {
          
            public pcap_if.ByReference next;/*pointer that points to the next pcap_if */
            public String name; 
            public String description;/*textual description of the interface */
            public pcap_addr.ByReference addresses; /**/
            public int flags; /*PCAP_IF_ interface flags */

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
              return List.of("next","addr","netmask","brodadr","dstaddr");
             }
          }


          //represents the socket address....
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
              return List.of("sa_family","sa_data");
            }

            public short getFamily(){
              //TODO: Don't know what does this do .....
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

        //IPv4 Socket address, Internet style
        /*
         * struct sockaddr_in{
         * #if(_WIN32_WINNT<0x600)
         * short sin_family
         * #else 
         * ADDRESS_FAMILY sin_family;
         * #endif
         * USHORT sin_port;
         * IN_ADDR sin_addr;
         * CHAR sin_zero[8]
         * }
         */
        public static class sockadr_in extends Structure{
          public short sin_family;
          public short sin_port;
          public in_addr sin_addr;
          public char[] sin_zero = new char[8];

          public static class ByReference extends sockadr_in implements Structure.ByReference{}

          public sockadr_in(){}

          public sockadr_in(Pointer p){
            super(p);
            read();
          }

          @Override
          public List<String> getFieldOrder(){
            return List.of("sin_family","sin_port","sin_addr","sin_zero");
          }

          public short getFamily(){
            //TODO: Don't know what does this do .....
            return (!(Platform.isMac()
            || Platform.isFreeBSD()
            ||Platform.isOpenBSD()
            ||Platform.iskFreeBSD())
            ?  sin_family
            :(ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)
                            ?(short) (0xFF & sin_family)
                            :(short) (0xFF & (sin_family >> 8))));
          }
        }

        public static class in_addr extends Structure{

          public NativeLong S_addr;   //unsigned long S_addr;

          public in_addr(){}

          @Override
          public List<String> getFieldOrder(){
            return List.of("S_addr");
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

                /*
                * Generic per-packet information, as supplied by libpcap/wpcap
                */
                /*
                * struct pcap_pkthdr{
                * struct timeval timeval;
                * bfu_u_int32 caplen;
                * bfu_u_int32 len;
                * }
                */

            public static class pcap_pkthdr extends Structure{
              public timeval timeval; /*time stamp */
              public int caplen;/* length of the portion present */
              public int len;/*length of this packet (of wire) */
              //TODO: must be implemented fully
            }

            public static class timeval extends Structure{
              public NativeLong tv_sec;/*seconds */
              public NativeLong tv_usec;/*microseconds */
              //TODO: must be implemented fully
            }

            public static class pcap_stat extends Structure{
              public int ps_recv;
              public int ps_drop;
              public int ps_ifdrop;
            }

            public static class pcap_stat_ex extends Structure{
              //TODO:To be implemented later
            }

           

}
 
            



