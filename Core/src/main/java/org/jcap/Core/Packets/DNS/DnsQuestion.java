package org.jcap.Core.Packets.DNS;

import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsClass;
import org.jcap.Core.Constants.NamedCodes.DnsCodes.DnsResourceRecordType;
import org.jcap.Core.Utils.ByteOperations;

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

    public static DnsQuestionBuilder Builder() {
        return new DnsQuestionBuilder();
    }

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

        public DnsQuestionBuilder questionName(DnsDomainName questionName) {
            if (sealed)
                throw new UnsupportedOperationException("The field questionName cannot be initialized again");
            this.questionName = questionName;
            return this;
        }

        public DnsQuestionBuilder questionType(DnsResourceRecordType questionType) {
            if (sealed)
                throw new UnsupportedOperationException("The field questionType cannot be initialized again ");
            this.questionType = questionType;
            return this;
        }

        public DnsQuestionBuilder questionClass(DnsClass questionClass) {
            if (sealed)
                throw new UnsupportedOperationException("The field questionClass cannot be initialized again");
            this.questionClass = questionClass;
            return this;
        }

        public DnsQuestion build() {
            validate(this);
            return new DnsQuestion(this);
        }

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
}
