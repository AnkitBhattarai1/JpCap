package org.jcap.Core.Packets.DNS;

import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsClass;
import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsResourceRecordType;
import org.jcap.Core.Utils.ByteOperations;

/**
 * Dns Question
 * 
 * <pre>
                                    1  1  1  1  1  1
      0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                                               |
    /                     QNAME                     /
    /                                               /
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                     QTYPE                     |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    |                     QCLASS                    |
    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * 
 * </pre>
 * 
 * Represents a DNS (Domain Name System) question.
 * 
 * <p>
 * A DNS question consists of three main components:
 * <ul>
 * <li>The <b>QNAME</b> field, representing the domain name being queried.</li>
 * <li>The <b>QTYPE</b> field, indicating the type of resource record being
 * queried.</li>
 * <li>The <b>QCLASS</b> field, specifying the class of the query.</li>
 * </ul>
 * 
 * <p>
 * This class encapsulates these components and provides methods to construct,
 * manipulate, and represent DNS questions.
 * 
 * <p>
 * Instances of this class are immutable once constructed, ensuring thread
 * safety in concurrent environments.
 * 
 * <p>
 * The class also includes a builder pattern for constructing DNS questions,
 * enabling flexible initialization with
 * domain names, types, and classes.
 * 
 * @see DnsDomainName
 * @see DnsResourceRecordType
 * @see DnsClass
 * 
 * @author Your Name
 * @version 1.0
 * @since 2024-05-16
 */
public class DnsQuestion {

    private final DnsDomainName questionName;
    private final DnsResourceRecordType questionType;
    private final DnsClass questionClass;

    private DnsQuestion(DnsQuestionBuilder builder) {
        // TODO: Validation is to be done....
        this.questionName = builder.questionName;
        this.questionClass = builder.questionClass;
        this.questionType = builder.questionType;
    }

    /**
     * Returns a builder to construct a DNS question.
     * 
     * @return A new {@link DnsQuestionBuilder} instance.
     */
    public static DnsQuestionBuilder Builder() {
        return new DnsQuestionBuilder();
    }

    /**
     * Returns a builder to construct a DNS question from raw data.
     * 
     * @param rawdata The raw byte array containing DNS question data.
     * @param offset  The offset in the byte array where the question data starts.
     * @param len     The length of the question data.
     * @return A new {@link DnsQuestionBuilder} instance initialized with the
     *         provided raw data.
     */
    public static DnsQuestionBuilder Builder(byte[] rawdata, int offset, int len) {
        return new DnsQuestionBuilder(rawdata, offset, len);
    }

    private static final class DnsQuestionBuilder {

        private DnsDomainName questionName;
        private DnsResourceRecordType questionType;
        private DnsClass questionClass;

        private boolean sealed = false;

        public DnsQuestionBuilder() {// For custom building
        }

        /**
         * Constructs a new DNS question builder from raw data.
         * 
         * @param rawdata The raw byte array containing DNS question data.
         * @param offset  The offset in the byte array where the question data starts.
         * @param len     The length of the question data.
         */
        public DnsQuestionBuilder(byte[] rawdata, int offset, int len) {
            int cursor = 0;
            this.questionName = DnsDomainName.Builder(rawdata, offset, len).build();
            cursor += questionName.length();

            if (len - cursor != Short.BYTES * 2)
                throw new IllegalArgumentException("NOt enough data to make a dns Domain Name");

            this.questionType = DnsResourceRecordType.instanceOfCode(ByteOperations.getShort(rawdata,
                    offset + cursor));
            cursor += Short.BYTES;
            this.questionClass = DnsClass.instanceOfCode(ByteOperations.getShort(rawdata, offset + cursor));

            this.sealed = true;
        }

        /**
         * Sets the domain name for the DNS question.
         * 
         * @param questionName The domain name for the DNS question.
         * @return This builder instance.
         * @throws UnsupportedOperationException If the builder is sealed.
         */

        public DnsQuestionBuilder questionName(DnsDomainName questionName) {
            if (sealed)
                throw new UnsupportedOperationException("The field questionName cannot be initialized again");
            this.questionName = questionName;
            return this;
        }

        /**
         * Sets the type of resource record being queried.
         * 
         * @param questionType The type of resource record being queried.
         * @return This builder instance.
         * @throws UnsupportedOperationException If the builder is sealed.
         */
        public DnsQuestionBuilder questionType(DnsResourceRecordType questionType) {
            if (sealed)
                throw new UnsupportedOperationException("The field questionType cannot be initialized again ");
            this.questionType = questionType;
            return this;
        }

        /**
         * Sets the class of the query.
         * 
         * @param questionClass The class of the query.
         * @return This builder instance.
         * @throws UnsupportedOperationException If the builder is sealed.
         */
        public DnsQuestionBuilder questionClass(DnsClass questionClass) {
            if (sealed)
                throw new UnsupportedOperationException("The field questionClass cannot be initialized again");
            this.questionClass = questionClass;
            return this;
        }

        /**
         * Builds a new DNS question instance.
         * 
         * @return A new {@link DnsQuestion} instance.
         */
        public DnsQuestion build() {
            validate(this);
            return new DnsQuestion(this);
        }

        /**
         * Validates the DNS question builder.
         * 
         * @param dnsQuestionBuilder The DNS question builder to validate.
         * @throws IllegalArgumentException If validation fails.
         */
        public void validate(DnsQuestionBuilder dnsQuestionBuilder) {
            // TODO To be implemennted
        }
    }

    public DnsDomainName getQuestionName() {
        return questionName;
    }

    public DnsResourceRecordType getQuestionType() {
        return questionType;
    }

    public DnsClass getQuestionClass() {
        return questionClass;
    }

    /**
     * Calculates the length of the DNS question.
     * 
     * @return The length of the DNS question.
     */
    public int length() {
        return questionName.length() + Short.BYTES * 2;
    }

    /**
     * Gets the raw byte array representation of the DNS question.
     * 
     * @return The raw byte array representation of the DNS question.
     */
    public byte[] getRawData() {
        int position = 0;

        byte[] rawData = new byte[length()];

        byte[] rawQData = questionName.getRawData();

        System.arraycopy(rawQData, 0, rawData, position, rawQData.length);
        position += rawQData.length;

        System.arraycopy(ByteOperations.getByteArray(questionType.getValue()), 0, rawData, position, Short.BYTES);
        position += Short.BYTES;

        System.arraycopy(ByteOperations.getByteArray(questionClass.getValue()), 0, rawData, position, Short.BYTES);

        return rawData;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((questionName == null) ? 0 : questionName.hashCode());
        result = prime * result + ((questionType == null) ? 0 : questionType.hashCode());
        result = prime * result + ((questionClass == null) ? 0 : questionClass.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DnsQuestion other = (DnsQuestion) obj;
        if (questionName == null) {
            if (other.questionName != null)
                return false;
        } else if (!questionName.equals(other.questionName))
            return false;
        if (questionType == null) {
            if (other.questionType != null)
                return false;
        } else if (!questionType.equals(other.questionType))
            return false;
        if (questionClass == null) {
            if (other.questionClass != null)
                return false;
        } else if (!questionClass.equals(other.questionClass))
            return false;
        return true;
    }

}
