package org.jpcap.Core.Native;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;

/*
char => byte
short => short
wchar_t => char
int => int
long => com.sun.jna.NativeLong
long long => long
float => float
double => double
char * => String
*/

/**
 * @author Ankit Bhattarai
 */
public class NativeWpcapMapping {

	public static final String PCAP_LIB_NAME = System.getProperty(
			NativeWpcapMapping.class.getPackage().getName() + ".pcapLibName", Platform.isWindows() ? "wpcap" : "pcap");

			//Native.register() --> When called from a class static initializer, maps
			// all native methods found within that class to native libraries via the JNA raw calling interface.
	static {
		Native.register(NativeWpcapMapping.class, NativeLibrary.getInstance(PCAP_LIB_NAME));
	}

	// native functions

	// int pcap_findalldevs(pcap_if_t *aldevsp, char* errbuf)
	public static native int pcap_findalldevs(PointerByReference alldevsp, PcapErrbuf errbuf);

	public static native void pcap_freealldevs(Pointer alldevsp);

	public static native Pointer pcap_lookupdev(PcapErrbuf errbuf);
	//device is the name of the device to open
	//snaplen is the maximum length of the packets to capture
	//promisc is the promiscuous mode to open the device in
	//to_ms is the read timeout in milliseconds
	//auth is the authentication to open the device in
	//errbuf is the error buffer

	public static native Pointer pcap_open_live(String device, int snaplen, int promisc, int to_ms, Pointer auth, PcapErrbuf errbuf);

	// linktype is the link layer type of the device
	// snaplen is the maximum length of the packets to capture
	public static native Pointer pcap_open_dead(int linktype, int snaplen);

	// linktype is the link layer type of the device
	// snaplen is the maximum length of the packets to capture
	// tstamp_precision is the timestamp precision
	public static native Pointer pcap_open_dead_with_tstamp_precision(int linktype, int snaplen, int tstamp_precision);


	public static native Pointer pcap_open_offline_with_tstamp_precision(String fname, int tstamp_precision);
	
	
	public static native int pcap_loop(Pointer p, int cnt, pcap_handler callback, Pointer user);
	
	public static native int pcap_dispatch(Pointer p, int cnt,  pcap_handler callback, Pointer user);

//	PCAP_AVAILABLE_0_4
//	PCAP_API const u_char *pcap_next(pcap_t *, struct pcap_pkthdr *);
	public static native String pcap_next(Pointer pcap, pcap_pkthdr header);

	//PCAP_AVAILABLE_0_8
	//PCAP_API int 	pcap_next_ex(pcap_t *, struct pcap_pkthdr **, const u_char **);
	public static native  int pcap_next_ex(Pointer pcap, PointerByReference header, PointerByReference pkt);

	//PCAP_AVAILABLE_0_8
	//PCAP_API void	pcap_breakloop(pcap_t *);
	public static native int pcap_breakloop(Pointer pcap);

	//PCAP_AVAILABLE_0_4
	//PCAP_API int	pcap_stats(pcap_t *, struct pcap_stat *);
	public static native int pcap_stats(Pointer pcap, pcap_stat stat);

	public static native int  pcap_setfilter(Pointer p, bpf_program fp);

	//public static native int  pcap_setdirection(Pointer p, pcap_direction direction);

	public static native int  pcap_getnonblock(Pointer pcap , byte b);

	public static native int pcap_setnonblock(Pointer p, int non_block, byte b);

	//public static native int pcap_inject(Pointer p, Pointer buf, int size);//don't know what is size

	//PCAP_AVAILABLE_0_8
	//	PCAP_API void PCAP_API int pcap_sendpacket(pcap_t *p, const u_char *, int);
		public static native int pcap_sendpacket(Pointer p, Pointer buf, int size);


	//PCAP_API pcap_t	*pcap_open(const char *source, int snaplen, int flags,
	//int read_timeout, struct pcap_rmtauth *auth, char *errbuf);
	public static native Pointer pcap_open(String source,int snaplen, int promisc,int read_timeout,Pointer auth,PcapErrbuf errbuf);

	//PCAP_AVAILABLE_0_4
	//PCAP_API int	pcap_datalink(pcap_t *);
	public static native int pcap_datalink(Pointer p);

	//PCAP_AVAILABLE_1_0
	//PCAP_API int pcap_dataLink_ext(pcap_t *)
	public static native int pcap_datalink_ext(Pointer p);

	//PCAP AVAILABLE_0_8
	//PCAP_API int	pcap_list_datalinks(pcap_t *, int**);
	public static native int pcap_list_datalinks(Pointer p, PointerByReference links);









	  // void pcap_freecode(struct bpf_program *fp)
	public   static native void pcap_freecode(bpf_program fp);

    // u_int bpf_filter(const struct bpf_insn *, const u_char *, u_int, u_int)
  	public static native int bpf_filter(
      bpf_insn.ByReference bpf_insn, byte[] packet, int wirelen, int buflen);

	//PCAP_AVAILABLE_0_4
	//PCAP_API int	pcap_compile(pcap_t *, struct bpf_program *, const char *, int,
	    //bpf_u_int32);
	public static native int pcap_compile(Pointer p, bpf_program fp, String str, int optimize, int netmask);
	  

	@FunctionalInterface										  
	public static interface  pcap_handler extends Callback {
		public void gotPacket(Pointer args, Pointer header, Pointer packet);
	}

	/**
	 * The {@code  pcap_if} class represents a network inteface on the system that
	 * can be used for capturing the packet
	 * <br>
	 * </br>
	 * Structure in c:
	 * 
	 * <pre>
	 * <code> struct pcap_if
	 * {
	 * struct pcap_if* next; //pointer that points to the next pcap_if 
	 * char* name; 
	 * char* description; // textual description of the interface 
	 * pcap_addr* addresses; 
	 * bpf_u_int32 flags; // PCAP_IF_ interface flags 
	 * }</code>
	 * </pre>
	 * 
	 * @author Ankit Bhattarai
	 */
 
	public static class pcap_if extends Structure {

		public pcap_if.ByReference next;/* pointer that points to the next pcap_if */
		public String name;
		public String description;/* textual description of the interface */
		public pcap_addr.ByReference addresses; /**/
		public int flags; /* PCAP_IF_ interface flags */

		public pcap_if() {
		}

		public static class ByReference extends pcap_if implements Structure.ByReference {
		}

		public pcap_if(Pointer p) {
			super(p);
			read();
		}

		@Override
		public List<String> getFieldOrder() {
			return List.of("next", "name", "description", "addresses", "flags");
		}
	}

	/**
	 * Representation of an interface address
	 * 
	 * <pre>
	 * Structure pcap_addr  in c:
	 * {@code
	 * struct pcap_addr{
	 * 	struct pcap_addr * next; 
	 * 	struct sockaddr * addr; //address
	 * 	struct sockaddr * netmask; //netmask for that address
	 * 	struct sockaddr * brodaddr; // broadcast address for that address
	 * 	struct sockaddr * dstaddr; // P2P destination address for that addres
	 * }
	 * 
	 * </pre>
	 */

	public static class pcap_addr extends Structure {
		public pcap_addr.ByReference next;/* pointer that points to the next---struct pcap_addr* next */
		public soc_addr.ByReference addr; /* address struc---soc_addr* address */
		public soc_addr.ByReference netmask;/* netmask for that addrress---soc_addr* netmask */
		public soc_addr.ByReference brodaddr;/* broadcast address for tha taddress */
		public soc_addr.ByReference dstaddr; /* P2P destination address for that address */

		// noArgs constructor
		public pcap_addr() {
		}

		public pcap_addr(Pointer p) {
			super(p);
			read();
		}

		public static class ByReference extends pcap_addr implements Structure.ByReference {
		}

		@Override
		protected List<String> getFieldOrder() {
			return List.of("next", "addr", "netmask", "brodaddr", "dstaddr");
		}
	}

	/**
	 * The {@code soc_addr} class represents the socket address
	 * <br></br>
	 * The Structure soc_addr in c:
	 * 
	 * <pre>
	 * <code>
	 * struct soc_addr{
	 * #if (_WIN32_WINNT < 0x600)
	 * 	u_short sa_family;
	 * #else 
	 * 	ADDRESS_FAMILY sa_family; //Address Family
	 * #endif //(_WIN32_WINNT < 0x600)
	 * 		CHAR sa_data[14]; // Up to 14 bytes of direct address.
	 * 
	 * } SOCKADDR, *PSOCKADDR, FAR *LPSOCKADDR;
	 * </code>
	 * </pre>
	 */

	public static class soc_addr extends Structure {

		public short sa_family; // don't know what is this.....
		public byte[] sa_data = new byte[14];

		public soc_addr() {
		}

		public soc_addr(Pointer p) {
			super(p);
			read();
		}

		public static class ByReference extends soc_addr implements Structure.ByReference {
		}

		@Override
		protected List<String> getFieldOrder() {
			return List.of("sa_family", "sa_data");
		}

		public short getFamily() {
			// TODO: Don't know what does this do .....
			return (!(Platform.isMac() || Platform.isFreeBSD() || Platform.isOpenBSD() || Platform.iskFreeBSD())
					? sa_family
					: (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? (short) (0xFF & sa_family) // 0XFF making
																											// it an
																											// unsigned
																											// integer
							: (short) (0xFF & (sa_family >> 8))));
			// if(!(Platform.isMac()|| Platform.isFreeBSD()|| Platform.isOpenBSD()||
			// Platform.iskFreeBSD())) return sa_family;
			// else{
			// if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
			// return (short) (0xFF & sa_family);
			// } else {
			// return (short) (0xFF & (sa_family >> 8));
			// }
			// }

		}
	}

	 /**
	  * The {@code in_addr} class represents IPv4 Socket address, Internet style.
	  * <br></br>
	  * The Structure sockadr_in in c is as follows:
	  * <pre>
	  * <code>
	  * struct sockaddr_in{
		* #if(_WIN32_WINNT<0x600)
		* 	short sin_family;
		* #else
		* 	ADDRESS_FAMILY sin_family;
		* #endif //(_WIN32_WINNT<0x600)
		* 	USHORT sin_port;
		* 	IN_ADDR sin_addr;
		* 	CHAR sin_zero[8];
		* 	#if(_WIN32_WINNT<0x600)	
		* } SOCKADDR_IN, *PSOCKADDR_IN, *LPSOCKADDR_IN;
	  *	</code>
	  *	</pre>
	  *
	  */
	public static class sockaddr_in extends Structure {
		public short sin_family;
		public short sin_port;
		public in_addr sin_addr;
		public char[] sin_zero = new char[8];

		public static class ByReference extends sockaddr_in implements Structure.ByReference {
		}

		public static class ByValue  extends sockaddr_in implements Structure.ByValue {
		}

		public sockaddr_in() {
		}

		public sockaddr_in(Pointer p) {
			super(p);
			read();
		}

		@Override
		public List<String> getFieldOrder() {
			return List.of("sin_family", "sin_port", "sin_addr", "sin_zero");
		}

		public short getFamily() {
			// TODO: Don't know what does this do .....
			return (!(Platform.isMac() || Platform.isFreeBSD() || Platform.isOpenBSD() || Platform.iskFreeBSD())
					? sin_family
					: (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? (short) (0xFF & sin_family)
							: (short) (0xFF & (sin_family >> 8))));
		}
	}

	/**
	 * The {@code socaddr_in6} class represents a IPv6 socket address , RFC 3439
	 * <p>
	 * </p>
	 * <pre>
	 * <code>
	 * struct sockaddr_in6{
	     ADDRESS_FAMILY sin6_family;
	     USHORT sin6_port;
	     ULONG sin6_flowinfo;
	     IN6_ADDR sin6_addr;
	     SCOPE_ID sin6_scope_id;
	     }SOCKADDR_IN6, *PSOCKADDR_IN6, *LPSOCKADDR_IN6;
	 * </code>
	 * </pre>
	 * 
	 * @author Ankit Bhattarai
	 }
	 * <code>
	 * </pre>
	 *
	 * @author Ankit Bhattarai
	 * @version 1.0
	 */
	public static class socaddr_in6 extends Structure {
		public short addressFamily;
		public short sin6_port;
		public long sin6_flowinfo;
		public in6_addr sin6_addr;
		public int sin6_scope_id;

		public socaddr_in6() {
		}

		public socaddr_in6(Pointer pointer) {
			super(pointer);
			read();
		}

		@Override
		public List<String> getFieldOrder() {
			return List.of("addressFamily", "sin6_port", "sin6_flowinfo", "sin6_addr", "sin6_scope_id");
		}

	}

	/**
	 * The {@code in_addr} class represents the IPv4 Internet address (RFC 790)
	 * <p> This is 'on-wire' format structure </p>
	 * The Structure in c is as follows: 
	 * <pre>
	 * <code>
	 * typedef struct in_addr {
	*  union {
	*	struct{UCHAR s_b1,s_b2,s_b3,s_b4;} S_un_b;
	*	struct{USHORT s_w1,s_w2;} S_un_w;
	*	ULONG S_addr;
	*	} S_un;
	* #define s_addr S_un.S_addr // can be used for most tcp/ip code
	* #define s_host S_un.S_un_b.s_b2 //host on imp
	* #define s_net S_un.S_un_b.s_b1 //network
	* #define s_imp S_un.S_un_w.s_w2 //sub-network
	* #define s_impno S_un.S_un_b.s_b4 //node (host) number
	* #define s_lh S_un.S_un_b.s_b3 //sub-host
	*}IN_ADDR, *PIN_ADDR, FAR *LPIN_ADDR;
	 * </code>
	 * </pre>
	 * 
	 * 
	 */
	public static class in_addr extends Structure {
		// unsigned long S_addr
		public NativeLong S_addr; // Represents the addres in a 32 bit long format

		public in_addr() {
		}

		@Override
		public List<String> getFieldOrder() {
			return List.of("S_addr");
		}
	}

	/**
	 * The {@code in6_addr } class represents the IPv6 Internet address (RFC 2553)
	 * <p>
	 * this is 'on-wire' format structure
	 * </p>
	 * 
	 * <pre>
	 * <code>
	 * typedef struct in6_addr {
	       union {
	*		unsigned char  Byte[16];
	*		unsigned short Word[8];
	*		} u;
	*	} IN6_ADDR, *PIN6_ADDR, *LPIN6_ADDR;
	 * </code>
	 * <pre>
	 * 
	 * @author Ankit Bhattarai
	 * @version 1.0
	 */

	public static class in6_addr extends Structure {
		public byte[] in6_addr = new byte[16];

		public in6_addr() {
		}

		@Override
		public List<String> getFieldOrder() {
			return List.of("in6_addr");
		}
	}

	public static class PcapErrbuf extends Structure {

		public byte[] buf = new byte[PCAP_ERRBUF_SIZE()];

		public PcapErrbuf() {
		}

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

	 /**
	 * Generic per-packet information, as supplied by libpcap.
	 *<pre>
	 * <code>
	 * typedef struct pcap_pkthdr {
		struct timeval ts; // time stamp
		bf_u_int32 caplen; // length of portion present
		bf_u_int32 len; // length this packet (off wire)
	 }	
	 </code>
	 </pre>
	 * 
	 */
	

	public static class pcap_pkthdr extends Structure {
		public timeval timeval; /* time stamp */
		public int caplen;/* length of the portion present */
		public int len;/* length of this packet (of wire) */

		public pcap_pkthdr(){
		}
		
		public pcap_pkthdr(Pointer p){
			super(p);
			read();
		}

		public static class ByReference extends pcap_pkthdr implements Structure.ByReference {
		}

		@Override
		protected List<String> getFieldOrder(){
			return List.of("timeval","caplen","len");
		}
		// TODO: must be implemented fully
	}

	public static class timeval extends Structure {
		public NativeLong tv_sec;/* seconds */
		public NativeLong tv_usec;/* microseconds */

		public timeval() {
		}

		public timeval(Pointer p) {
			super(p);
			read();
		}

		public static class ByReference extends timeval implements Structure.ByReference {
		    
		}

		@Override
		protected List<String> getFieldOrder(){
			return List.of("tv_sec","tv_usec");
		}
		// TODO: must be implemented fully
	}

	public static class pcap_stat extends Structure {
		public int ps_recv;
		public int ps_drop;
		public int ps_ifdrop;
	}

	public static class pcap_stat_ex extends Structure {
		// TODO:To be implemented later
	}

	public static class bpf_program extends Structure {
	    public int bf_len;
		public bpf_insn.ByReference bf_insns;

		public bpf_program() {
      	setAutoSynch(false);
    		}
	
			@Override
			protected List<String> getFieldOrder() {
			    return Arrays.asList("bf_len", "bf_insns");
			}
	}

	public static class bpf_insn extends Structure {
		public short code;
		public byte jt;
		public byte jf;
		public int k;

		public static final class ByReference extends bpf_insn implements Structure.ByReference {
		}

		public bpf_insn() {
		    setAutoSynch(false);
		}

		@Override
		protected List<String> getFieldOrder() {
		    return Arrays.asList("code","jt","jf","k");
		}

	}


	public enum pcap_direction{
		PCAP_D_INOUT((byte)0),
		PCA__D_IN((byte)1),
		PCAP_D_OUT((byte)2);


		byte value;

		pcap_direction(byte  value){
		    this.value=value;
		}

		public int getValue(){ return value;}
	}

}
