package org.jpcap.Core.Packets.DNS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jpcap.Core.Utils.ByteOperations;

/*
 * Notes:
 * In DNS protocol, each label of a domain name is prefixed by a single byte indicating the label's length, but only the lower 6 bits of this byte are used for this purpose.
 *  This allows a maximum label length of `63` because the largest number a 6-bit number can represent is `63` (`2^6 - 1`).
 *  The restriction helps ensure efficient parsing, memory management, and protocol uniformity, minimizing the risk of buffer overflow vulnerabilities in network communications.
 */

/**
 * Represents a domain name in the DNS protocol.
 * 
 * <p>
 * This class provides methods to construct a domain name from raw DNS message
 * data, retrieve the raw byte representation of a domain name, and convert a
 * domain name to a human-readable string. It also supports domain name
 * compression through pointers.
 * </p>
 * 
 * <p>
 * Example usage:
 * 
 * <pre>
 * {@code
 * /// Creating a domain name from raw data
 * byte[] rawData = ...;
 * DnsDomainName domainName = DnsDomainName.Builder(rawData, offset, length).build();
 * 
 * /// Creating a custom domain name
 * DnsDomainName customDomainName = DnsDomainName.Builder()
 *     .labels(new String[] {"www", "example", "com"})
 *     .build();
 * 
 * ///Getting the raw byte data of a domain name
 * byte[] rawData = domainName.getRawData();
 * 
 * ///Converting a domain name to a string
 * String domainNameString = domainName.toString();
 * }
 * </pre>
 * </p>
 * 
 * <p>
 * The root domain name is represented by a static instance,
 * {@code ROOT_DOMAIN}.
 * </p>
 */

public class DnsDomainName {

    /**
     * The root domain name.
     */
    public static final DnsDomainName ROOT_DOMAIN;

    static {
        try {
            ROOT_DOMAIN = DnsDomainName.Builder(new byte[] { 0 }, 0, 1).build();
        } catch (IllegalArgumentException e) {
            throw new AssertionError("Never get here.");
        }
    }

    private final List<String> labels;
    private final String name;
    private final Short pointer;

    private static final int LABEL_POINTER_FLAG = 0xC0;// 11000000.
    private static final int LABEL_MASK = 0X3F;// 00111111
    /*
     * 0x3FFF in binary is 0011 1111 1111 1111.
     * This mask is used to clear the two most significant bits of the retrieved
     * short value
     */
    private static final int POINTER_MASK = 0x3FFF;

    /**
     * Creates a new builder for constructing a {@link DnsDomainName} instance.
     * 
     * @return a new {@link DnsDomainNameBuilder} instance
     */
    public static DnsDomainNameBuilder Builder() {
        return new DnsDomainNameBuilder();
    }

    /**
     * Creates a new builder for constructing a {@link DnsDomainName} instance from
     * raw data.
     * 
     * @param rawData the raw byte array containing the DNS domain name data
     * @param offset  the offset within the raw data where the domain name starts
     * @param len     the length of the domain name data
     * @return a new {@link DnsDomainNameBuilder} instance
     */
    public static DnsDomainNameBuilder Builder(byte[] rawData, int offset, int len) {
        return new DnsDomainNameBuilder(rawData, offset, len);
    }

    private DnsDomainName(DnsDomainNameBuilder builder) {

        this.labels = new ArrayList<>(builder.labels);
        this.name = builder.name;
        this.pointer = builder.pointer;
        // TODO Validation is to be done
    }

    /**
     * A builder for constructing {@link DnsDomainName} instances.
     *
     */
    public static class DnsDomainNameBuilder {

        private List<String> labels;
        private Short pointer = null;
        private String name;

        private boolean sealed = false;

        private DnsDomainNameBuilder() {
        }// For custom building

        private DnsDomainNameBuilder(byte[] rawData, int offset, int len) {
            /*
             * represents the position of label to be get from the byte array...
             */
            int position = 0;
            /*
             * represents flag to indicate whether the
             * parsing or reading of the domain name from
             * the raw data has reached the end of the domain name string within the DNS
             * message
             */
            boolean terminated = false;// to indicate the end of the domain name string.
            Short tempPointer = null; // to store the pointer value.
            this.labels = new ArrayList<>();// list of the labels...

            while (position < len) {
                // the first byte representing the length of the label;
                int lengthoflabel = rawData[offset + position] & 0xFF;// masking with 0xFF to make it unsigned...

                if (lengthoflabel == 0) { // length=0 meaning the end of labels.......
                    terminated = true;
                    break;
                }

                if ((lengthoflabel & LABEL_POINTER_FLAG) == LABEL_POINTER_FLAG) {
                    // if the first two bit is 11 the preceeding 14 bits represents the pointer
                    // value....
                    // offset+position+1>rawdata.length
                    if ((len - position) < Short.BYTES)
                        throw new IllegalArgumentException("Not enough data for reading a pointer");

                    /* 0X3FFF =0011 1111 1111 1111 */
                    tempPointer = (short) (ByteOperations.getShort(rawData, offset + position) & (0x3FFF));

                    terminated = true;
                    break;
                }
                // if the first two bit is 00 then the remaining 6 bits represents label.
                // of the label in bytes ....
                else if ((lengthoflabel & LABEL_POINTER_FLAG) == 0) {
                    position++; // gets to the start of the label
                    if (len - position < lengthoflabel)
                        throw new IllegalArgumentException("Not enough data to make the label");
                    // fetch the label from specified position from the rawdata..
                    this.labels.add(new String(rawData, offset + position, lengthoflabel));
                    position += lengthoflabel;
                    continue;
                } else {
                    throw new IllegalArgumentException("A label must start with 00 or 11");
                }

            }

            if (!terminated) {
                throw new IllegalArgumentException("No end label found");
            }

            this.pointer = tempPointer;
            this.name = String.join(".", labels);
            this.sealed = true;
        }

        public DnsDomainNameBuilder labels(String[] labels) {
            for (String s : labels) {

                if (s.length() > 63)
                    throw new IllegalArgumentException("The length of a label must not be more than 63");
            }
            if (sealed)
                throw new UnsupportedOperationException("The field labels cannot be initialized again");

            this.labels = Arrays.asList(labels);
            this.name = String.join(".", this.labels);

            return this;
        }

        /**
         * Sets the labels of the domain name.
         * 
         * @param labels the array of labels
         * @return the builder instance
         * @throws IllegalArgumentException      if any label exceeds 63 characters
         * @throws UnsupportedOperationException if the labels field is already
         *                                       initialized
         */
        public DnsDomainNameBuilder labels(List<String> labels) {
            for (String s : labels) {
                if (s.length() > 63)
                    throw new IllegalArgumentException("The length of a label must not be more than 63");
            }

            if (sealed)
                throw new UnsupportedOperationException("The field labels cannot be initialized again");

            this.labels = labels;
            this.name = String.join(".", this.labels);
            return this;
        }

        public DnsDomainNameBuilder pointer(short pointer) {
            if ((pointer & LABEL_POINTER_FLAG) != LABEL_POINTER_FLAG)
                throw new IllegalArgumentException("The pointer must start with '11' ");
            if (sealed)
                throw new UnsupportedOperationException("The field pointer cannot be initialized again");
            this.pointer = (short) (pointer & POINTER_MASK);
            return this;
        }

        /**
         * Builds the {@link DnsDomainName} instance.
         * 
         * @return the constructed {@link DnsDomainName} instance
         */
        public DnsDomainName build() {
            validate(this);
            return new DnsDomainName(this);
            // TODO To be implemented
        }

        private void validate(DnsDomainNameBuilder dnsDomainNameBuilder) {
        }

    }

    /**
     * Gets the length of the domain name in bytes.
     * 
     * @return the length of the domain name in bytes
     */
    public int length() {

        int len = 0;
        for (String label : labels) {
            len += label.length() + 1;
        }
        if (pointer != null) {
            len += 2;
        } else {
            len++;
        }
        return len;
    }

    /**
     * Gets the raw byte data of the domain name.
     * 
     * @return the raw byte data of the domain name
     */
    public byte[] getRawData() {
        byte[] data = new byte[length()];
        int cursor = 0;

        for (String label : labels) {
            byte[] labelBytes = label.getBytes();
            data[cursor] = (byte) labelBytes.length;
            cursor++;
            System.arraycopy(labelBytes, 0, data, cursor, labelBytes.length);
            cursor += labelBytes.length;
        }

        if (pointer != null) {
            byte[] offsetBytes = ByteOperations.getByteArray(pointer);
            offsetBytes[0] |= 0xC0;
            System.arraycopy(offsetBytes, 0, data, cursor, offsetBytes.length);
        }
        return data;
    }

    @Override
    public String toString() {

        if (labels.size() == 0 && pointer == null) {
            return "<ROOT>";
        }

        if (pointer == null) {
            return name;

        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[name: ").append(name).append(", pointer: ").append(pointer).append("]");
            return sb.toString();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((labels == null) ? 0 : labels.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pointer == null) ? 0 : pointer.hashCode());
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
        DnsDomainName other = (DnsDomainName) obj;
        if (labels == null) {
            if (other.labels != null)
                return false;
        } else if (!labels.equals(other.labels))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (pointer == null) {
            if (other.pointer != null)
                return false;
        } else if (!pointer.equals(other.pointer))
            return false;
        return true;
    }

}
