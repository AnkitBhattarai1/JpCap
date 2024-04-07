package org.jcap.Core.Utils;

import java.nio.ByteOrder;

public class ByteOperations {

	private final static int BYTE_IN_BITS = 8;

	public static byte[] reverse(byte[] array) {

		byte[] reverse = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			reverse[i] = array[array.length - 1 - i];
		}
		return reverse;
	}

	public static byte[] getByteArray(long value, ByteOrder bo) {
		if (bo.equals(ByteOrder.LITTLE_ENDIAN)) {
			return new byte[] { (byte) (value), (byte) (value >> BYTE_IN_BITS * 1), (byte) (value >> BYTE_IN_BITS * 2),
					(byte) (value >> BYTE_IN_BITS * 3), };
		} else {
			return new byte[] { (byte) (value >> BYTE_IN_BITS * 3), (byte) (value >> BYTE_IN_BITS * 2),
					(byte) (value >> BYTE_IN_BITS * 1), (byte) (value) };
		}
	}
}
