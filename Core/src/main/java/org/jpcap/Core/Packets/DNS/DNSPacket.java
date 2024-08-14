package org.jpcap.Core.Packets.DNS;

import java.util.ArrayList;
import java.util.List;

import org.jpcap.Core.Constants.NamedCodes.DnsCodes.DnsOpCode;
import org.jpcap.Core.Constants.NamedCodes.DnsCodes.DnsRCode;
import org.jpcap.Core.Packets.Packet;
import org.jpcap.Core.Utils.ByteOperations;

/**
 * Represents a DNS packet that includes a {@link DNSHeader},
 * {@link DnsQuestion
 * }, and various {@link DnsResourceRecord} sections such as answers, authority,
 * and additional information. This class Implements the {@Packet} Interface to
 * provide specific implementations for DNS packet structures.
 * 
 * <pre>
 * +---------------------+
 *   |        Header       |
 *   +---------------------+
 *   |       Question      | the question for the name server
 *   +---------------------+
 *   |        Answer       | RRs answering the question
 *   +---------------------+
 *   |      Authority      | RRs pointing toward an authority
 *   +---------------------+
 *   |      Additional     | RRs holding additional information
 *   +---------------------+
 * </pre>
 */

public class DNSPacket implements Packet {

	private final DNSHeader dnsHeader;
	private final List<DnsQuestion> questions;
	private final List<DnsResourceRecord> answers;
	private final List<DnsResourceRecord> authority;
	private final List<DnsResourceRecord> additionlInformation;

	private DNSPacket(DNSPacketBuilder builder) {

		this.dnsHeader = builder.dnsHeader;
		this.questions = builder.question;
		this.answers = builder.answers;
		this.authority = builder.authority;
		this.additionlInformation = builder.additionalInformation;
	}

	// private Function<List<DnsResourceRecord>, Integer> getRecordLength = (ls) ->
	// {
	// int len = 0;
	// for (DnsResourceRecord r : ls)
	// len += r.length();
	// return len;
	// };

	@Override
	public int length() {

		int len = 0;
		len += dnsHeader.length();
		for (DnsQuestion q : questions)
			len += q.length();

		return len;

	}

	@Override
	public <T extends Packet> T getPacketOf(Class<T> packetType) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPacketOf'");
	}

	@Override
	public <T extends Packet> boolean containsPacketOf(Class<T> packetType) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'containsPacketOf'");
	}

	@Override
	public DNSHeader getHeader() {
		return dnsHeader;
	}

	@Override
	public Packet getPlayLoad() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPlayLoad'");
	}

	@Override
	public byte[] getRawData() {
		return null;

	}

	/**
	 * Creates a new DNSPacketBuilder to build a DNS packet.
	 *
	 * @return A new instance of DNSPacketBuilder.
	 */
	public static DNSPacketBuilder Builder() {
		return new DNSPacketBuilder();
	}

	/**
	 * Creates a new DNSPacketBuilder to build a DNS packet, starting with
	 * predefined raw data and a DNS header.
	 *
	 * @param rawData the raw bytes of the DNS packet.
	 * @param offset  the starting point to read the rawData.
	 * @param len     the length of data to be used from rawData.
	 * @param header  the DNSHeader object to be used in the packet.
	 * @return A new instance of DNSPacketBuilder with the specified header and raw
	 *         data.
	 */
	public PacketBuilder Builder(byte[] rawData, int offset, int len, DNSHeader header) {
		return new DNSPacketBuilder(rawData, offset, len, header);
	}

	/**
	 * Creates a new DNSPacketBuilder to build a DNS packet, with parameters to
	 * specify the location and length of the header and packet data within a byte
	 * array.
	 *
	 * @param rawData   the raw bytes of the DNS packet.
	 * @param offset    the starting point to read the rawData.
	 * @param packetLen the total length of the DNS packet data.
	 * @param headerLen the length of the DNS header within the rawData.
	 * @return A new instance of DNSPacketBuilder initialized with the given
	 *         parameters.
	 */
	public PacketBuilder Builder(byte[] rawData, int offset, int packetLen, int headerLen) {
		return new DNSPacketBuilder(rawData, offset, packetLen, headerLen);
	}

	public static class DNSPacketBuilder implements PacketBuilder {

		private DNSHeader dnsHeader;
		private List<DnsQuestion> question;
		private List<DnsResourceRecord> answers;
		private List<DnsResourceRecord> authority;
		private List<DnsResourceRecord> additionalInformation;

		private DNSPacketBuilder(byte[] rawData, int offset, int len, DNSHeader header) {

			this.dnsHeader = header;
			this.question = new ArrayList<DnsQuestion>((int) (header.QDCount & 0XFFFF));
			this.answers = new ArrayList<DnsResourceRecord>((int) (header.ANCount & 0xFFFF));
			this.authority = new ArrayList<DnsResourceRecord>((int) (header.NSCount & 0xFFFF));
			this.additionalInformation = new ArrayList<DnsResourceRecord>(header.ARCount & 0xFFFF);

		}

		private DNSPacketBuilder(byte[] rawData, int offset, int packetLen, int headerLen) {

			this(rawData, offset + headerLen, packetLen, DNSHeader.Builder(rawData, offset, headerLen).build());
		}

		// For custom building of DnsPacket builder
		public DNSPacketBuilder() {
		}

		public DNSPacketBuilder dnsHeader(DNSHeader dnsHeader) {
			this.dnsHeader = dnsHeader;
			return this;
		}

		public DNSPacketBuilder question(List<DnsQuestion> question) {
			this.question = question;
			return this;
		}

		public DNSPacketBuilder answer(List<DnsResourceRecord> answer) {
			this.answers = answer;
			return this;
		}

		public DNSPacketBuilder authority(List<DnsResourceRecord> authorities) {
			this.authority = authorities;
			return this;
		}

		public DNSPacketBuilder additionalInformation(List<DnsResourceRecord> additionalInfo) {
			this.additionalInformation = additionalInfo;
			return this;
		}

		@Override
		public Packet build() {
			validate(this);
			return new DNSPacket(this);
		}

		private void validate(DNSPacketBuilder dnsPacketBuilder) {
			// TODO:To be implemented
		}
	}

	/**
	 * Represents the header of a DNS message which includes both queries and
	 * responses. The DNSHeader encapsulates various fields defined by the DNS
	 * protocol to facilitate internet name resolution services. The class includes
	 * mechanisms to both parse raw DNS message data and build DNS headers
	 * programmatically.
	 * 
	 * 
	 * <pre>
		 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
		 * |----------------------ID-----------------------|
		 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
		 * |QR|---Opcode--|AA|TC|RD|RA|-Z|AD|CD|---RCODE---|
		 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
		 * |--------------------QDCOUNT--------------------|
		 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
		 * |--------------------ANCOUNT--------------------|
		 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
		 * |--------------------NSCOUNT--------------------|
		 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
		 * |--------------------ARCOUNT--------------------|
		 * +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
	 * </pre>
	 * <p>
	 * The DNS header includes the following fields:
	 * </p>
	 * <ul>
	 * <li>ID - A 16-bit identifier for the request/response transaction.</li>
	 * <li>QR (Query/Response) - A flag indicating whether the message is a query
	 * (0) or a response (1).</li>
	 * <li>Opcode - A 4-bit field that specifies the type of the query.</li>
	 * <li>AA (Authoritative Answer) - Indicates that the responding server is an
	 * authority for the domain name.</li>
	 * <li>TC (Truncation) - Specifies that this message was truncated.</li>
	 * <li>RD (Recursion Desired) - Indicates the client desires recursive query
	 * resolution.</li>
	 * <li>RA (Recursion Available) - Indicates recursive query resolution is
	 * available at the server.</li>
	 * <li>Z - Reserved bits for future use.</li>
	 * <li>RCODE (Response Code) - Provides response codes of the query.</li>
	 * <li>QDCOUNT - Number of entries in the question section.</li>
	 * <li>ANCOUNT - Number of resource records in the answer section.</li>
	 * <li>NSCOUNT - Number of name server resource records in the authority records
	 * section.</li>
	 * <li>ARCOUNT - Number of resource records in the additional records
	 * section.</li>
	 * </ul>
	 *
	 * <p>
	 * Example usage:
	 * </p>
	 * 
	 * <pre>
	 *   byte[] dnsFrame = ...; // DNS frame data
	 *   DNSHeader header = DNSHeader.Builder(dnsFrame, 0, dnsFrame.length).build();
	 * </pre>
	 *
	 * @see DNSHeaderBuilder for building instances of this class.
	 */

	public static final class DNSHeader implements Header {

		private final short Id;
		private final boolean response; //
		private final DnsOpCode dnsOpCode;
		private final boolean authoritativeAnswer;
		private final boolean truncation;
		private final boolean recursionDesired;
		private final boolean recursionAvailable;
		private final boolean reserved = false;
		private final DnsRCode rCode;
		private final short QDCount;
		private final short ANCount;
		private final short NSCount;
		private final short ARCount;

		private static final int ID_OFFSET = 0;
		private static final int ID_SIZE = Short.BYTES;
		private static final int FLAGS_OFFSET = ID_OFFSET + ID_SIZE;
		private static final int FLAGS_SIZE = Short.BYTES;
		private static final int QDCOUNT_OFFSET = FLAGS_OFFSET + FLAGS_SIZE;
		private static final int QDCOUNT_SIZE = Short.BYTES;
		private static final int ANCOUNT_OFFSET = QDCOUNT_OFFSET + QDCOUNT_SIZE;
		private static final int ANCOUNT_SIZE = Short.BYTES;
		private static final int NSCOUNT_OFFSET = ANCOUNT_OFFSET + ANCOUNT_SIZE;
		private static final int NSCOUNT_SIZE = Short.BYTES;
		private static final int ARCOUNT_OFFSET = NSCOUNT_OFFSET + NSCOUNT_SIZE;
		private static final int ARCOUNT_SIZE = Short.BYTES;
		private static final int DNS_MIN_HEADER_SIZE = ARCOUNT_OFFSET + ARCOUNT_SIZE;

		private DNSHeader(DNSHeaderBuilder builder) {

			this.Id = builder.Id;
			this.response = builder.response;
			this.dnsOpCode = builder.dnsOpCode;
			this.authoritativeAnswer = builder.authoritativeAnswer;
			this.truncation = builder.truncation;
			this.recursionDesired = builder.recursionDesired;
			this.recursionAvailable = builder.recursionAvailable;
			this.rCode = builder.rCode;
			this.QDCount = builder.QDCount;
			this.ANCount = builder.ANCount;
			this.NSCount = builder.NSCount;
			this.ARCount = builder.ARcount;
		}

		public static DNSHeaderBuilder Builder(byte[] arr, int offset, int len) {
			return new DNSHeaderBuilder(arr, offset, len);
		}

		public static DNSHeaderBuilder Builder() {
			return new DNSHeaderBuilder();
		}

		public static final class DNSHeaderBuilder {
			private short Id;
			private boolean response; //
			private DnsOpCode dnsOpCode;
			private boolean authoritativeAnswer;
			private boolean truncation;
			private boolean recursionDesired;
			private boolean recursionAvailable;
			private boolean reserved = false;
			private DnsRCode rCode;
			private short QDCount;
			private short ANCount;
			private short NSCount;
			private short ARcount;

			private boolean sealed = false;

			private DNSHeaderBuilder(byte[] rawData, int offset, int len) {

				if (len < DNS_MIN_HEADER_SIZE)
					throw new IllegalArgumentException("The data is too short to make the header"); // TODO:Make
																									// a custom
																									// exception....

				this.Id = ByteOperations.getShort(rawData, ID_OFFSET + offset);
				short flags = ByteOperations.getShort(rawData, FLAGS_OFFSET + offset);
				this.response = (flags & 0x8000) != 0;
				this.dnsOpCode = DnsOpCode.instanceOfCode((byte) ((flags >> 11) & 0x0F));
				this.authoritativeAnswer = (flags & 0x0400) != 0;
				this.truncation = (flags & 0x0200) != 0;
				this.recursionDesired = (flags & 0x0100) != 0;
				this.recursionAvailable = (flags & 0x0080) != 0;
				this.rCode = DnsRCode.InstanceOfCode((byte) (flags & 0x0F));
				this.QDCount = ByteOperations.getShort(rawData, offset + QDCOUNT_OFFSET);
				this.ANCount = ByteOperations.getShort(rawData, offset + ANCOUNT_OFFSET);
				this.NSCount = ByteOperations.getShort(rawData, offset + NSCOUNT_OFFSET);
				this.ARcount = ByteOperations.getShort(rawData, offset + ARCOUNT_OFFSET);

				this.sealed = true;
			}

			public DNSHeaderBuilder() {// For custom building of DnsHeader...
			}

			public DNSHeader build() {
				validate(this);
				return new DNSHeader(this);
			}

			private void validate(DNSHeaderBuilder dnsHeaderBuilder) {
				// TODO Need to validate DNSHeaderBuilder fields
			}

			public DNSHeaderBuilder Id(short id) {
				if (sealed)
					throw new UnsupportedOperationException("The id field cannot be initilaized again");
				this.Id = id;
				return this;
			}

			public DNSHeaderBuilder response(boolean response) {
				if (sealed)
					throw new UnsupportedOperationException("The response field cannot be initilaized again");
				this.response = response;
				return this;
			}

			public DNSHeaderBuilder dnsOPCode(DnsOpCode code) {
				if (sealed)
					throw new UnsupportedOperationException("The opcode field cannot be initilaized again");
				this.dnsOpCode = code;
				return this;
			}

			public DNSHeaderBuilder authoritativeAnswer(boolean ans) {
				if (sealed)
					throw new UnsupportedOperationException(
							"The authoritativeAnswer field cannot be initilaized again");
				this.authoritativeAnswer = ans;
				return this;
			}
			// More setters are to be included.......
		}

		public short getId() {
			return this.Id;
		}

		public boolean isResponse() {
			return response;
		}

		public DnsOpCode getDnsOpCode() {
			return this.dnsOpCode;
		}

		public boolean isAuthoritativeAnswer() {
			return authoritativeAnswer;
		}

		public boolean isTruncation() {
			return truncation;
		}

		public boolean isRecursionDesired() {
			return recursionDesired;
		}

		public boolean isRecursionAvailable() {
			return recursionAvailable;
		}

		public boolean isReserved() {
			return reserved;
		}

		public short getQDCount() {
			return QDCount;
		}

		public short getANCount() {
			return ANCount;
		}

		public short getNSCount() {
			return NSCount;
		}

		public short getARcount() {
			return ARCount;
		}

		@Override
		public int length() {
			return DNS_MIN_HEADER_SIZE;
		}

		@Override
		public byte[] getRawData() {
			byte[] rawData = new byte[length()];

			int position = 0;
			System.arraycopy(ByteOperations.getByteArray(Id), 0, rawData, position,
					ByteOperations.getByteArray(Id).length);
			position += Short.BYTES;

			short flag = (short) (dnsOpCode.getValue() << 3);

			if (response)
				flag |= 0x8000;

			if (authoritativeAnswer)
				flag |= 0x0400;

			if (truncation)
				flag |= 0x0200;

			if (recursionDesired)
				flag |= 0x0100;

			if (recursionAvailable)
				flag |= 0x0080;

			System.arraycopy(ByteOperations.getByteArray(flag), 0, rawData, position,
					ByteOperations.getByteArray(flag).length);
			position += Short.BYTES;

			System.arraycopy(ByteOperations.getByteArray(QDCount), 0, rawData, position,
					ByteOperations.getByteArray(QDCount).length);
			position += Short.BYTES;

			System.arraycopy(ByteOperations.getByteArray(ANCount), 0, rawData, position,
					ByteOperations.getByteArray(ANCount).length);
			position += Short.BYTES;

			System.arraycopy(ByteOperations.getByteArray(NSCount), 0, rawData, position,
					ByteOperations.getByteArray(NSCount).length);
			position += Short.BYTES;

			System.arraycopy(ByteOperations.getByteArray(ARCount), 0, rawData, position,
					ByteOperations.getByteArray(ARCount).length);
			position += Short.BYTES;

			return rawData;
		}

	}
}
