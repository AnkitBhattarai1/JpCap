package org.jpcap.Core.Native;

import org.jpcap.Core.Native.NativeWpcapMapping.bpf_program;
import org.jpcap.Core.Packets.Packet;

public class BpfProgram {

    private final bpf_program program;
    private final String expression;
    private volatile boolean freed = false;
    private final Object lock = new Object();

    BpfProgram(bpf_program program, String expression) {
        this.program = program;
        this.expression = expression;
    }

    public bpf_program getProgram() {
        return program;
    }

    public String getExpression() {
        return expression;
    }

    public boolean applyFilter(Packet packet) {
        return applyFilter(packet.getRawData());
    }

    public boolean applyFilter(byte[] packet) {
        return applyFilter(packet, packet.length, packet.length);
    }

    public boolean applyFilter(byte[] packet, int orgPacketLen, int packetLen) {
        synchronized (lock) {
            if (freed) {
                return false;
            }

            if (program.bf_insns == null) {
                program.read();
            }

            return NativeWpcapMapping.bpf_filter(program.bf_insns, packet, orgPacketLen, packetLen) != 0;
        }
    }

    /**
     * @return true if the bpf_program represented by this object is freed; false
     *         otherwise.
     */
    public boolean isFreed() {
        return freed;
    }

    /** */
    public void free() {
        if (freed) {
            return;
        }
        synchronized (lock) {
            if (freed) {
                return;
            }
            NativeWpcapMapping.pcap_freecode(program);
            freed = true;
        }
    }

    public static enum BpfCompileMode {

        /** */
        OPTIMIZE(1),

        /** */
        NONOPTIMIZE(0);

        private final int value;

        private BpfCompileMode(int value) {
            this.value = value;
        }

        /** @return value */
        public int getValue() {
            return value;
        }
    }
}
