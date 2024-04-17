package org.jcap.Core.Packets.DNS;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * In DNS protocol, each label of a domain name is prefixed by a single byte indicating the label's length, but only the lower 6 bits of this byte are used for this purpose.
 *  This allows a maximum label length of `63` because the largest number a 6-bit number can represent is `63` (`2^6 - 1`).
 *  The restriction helps ensure efficient parsing, memory management, and protocol uniformity, minimizing the risk of buffer overflow vulnerabilities in network communications.
 */

import org.jcap.Core.Utils.ByteOperations;

public class DnsDomainName {

    private final List<String> labels;
    private final String name;
    private final short pointer;

    private static final int LABEL_POINTER_FLAG = 0xC0;
    private static final int LABEL_MASK = 0X3F;
    /*
     * 0x3FFF in binary is 0011 1111 1111 1111.
     * This mask is used to clear the two most significant bits of the retrieved
     * short value
     */
    private static final int POINTER_MASK = 0x3FFF;

    public DnsDomainName(byte[] rawdata, int offset, int len) {
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
        boolean terminated = false;
        Short tempPointer = null;
        this.labels = new ArrayList<>();

        while (position < len) {

            // the first byte representing the length of the label;
            int lengthoflabel = rawdata[offset + position] & 0xFF;

            if (lengthoflabel == 0) { // length=0 meaning the end of labels.......
                terminated = true;
                break;
            }
            if ((lengthoflabel & LABEL_POINTER_FLAG) == LABEL_POINTER_FLAG) {
                // offset+position+1>rawdata.length
                // TODO need to check whether its len-(position+offset)
                if ((len - position) < Short.BYTES)
                    throw new IllegalArgumentException("Not enough data for reading a pointer");
                tempPointer = (short) (ByteOperations.getShort(rawdata, offset + position) & (0x3FFF));
                terminated = true;
                break;
            } else if ((lengthoflabel & LABEL_POINTER_FLAG) == 0) {
                position++; // gets to the start of the label
                // TODO need to check whether its len-(position+offset)
                if (len - position < lengthoflabel)
                    throw new IllegalArgumentException("Not enough data to make the label");
                // fetch the label from specified position from the rawdata..
                this.labels.add(new String(rawdata, offset + position, lengthoflabel));
                position += lengthoflabel;
                continue;
            } else {
                // TODO Implementation of CustomRawDataException.......
                throw new IllegalArgumentException("A label must start with 00 or 11");
            }

        }

        if (!terminated) {
            throw new IllegalArgumentException("No end label found");
        }
        this.pointer = tempPointer;

        this.name = String.join(".", labels);
    }

    private DnsDomainName(DnsDomainNameBuilder builder) {
        this.labels = builder.labels;
        this.name = "";
        this.pointer = builder.pointer;
        // TODO Validation is to be done
    }

    public static class DnsDomainNameBuilder {
        private List<String> labels;
        private Short pointer = null;

        public DnsDomainName build() {
            return null;
            // TODO To be implemented
        }

        public DnsDomainNameBuilder labels(String[] labels) {
            this.labels = Arrays.asList(labels);
            return this;
        }

        public DnsDomainNameBuilder label(List<String> labels) {
            this.labels = labels;
            return this;
        }

        public DnsDomainNameBuilder pointer(short pointer) {
            this.pointer = pointer;
            return this;
        }

    }

}
