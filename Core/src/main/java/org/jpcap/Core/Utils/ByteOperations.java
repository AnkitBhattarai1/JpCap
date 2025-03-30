package org.jpcap.Core.Utils;

import java.nio.ByteOrder;
import java.util.regex.Pattern;

import org.jpcap.Core.Address.MacAddress;

/**
 * This class provides methods for performing byte-level operations,
 * such as converting primitive data types to byte arrays, reversing byte
 * arrays,
 * and retrieving primitive data types from byte arrays based on specified byte
 * order.
 */
public class ByteOperations {

	private final static int BYTE_SIZE_IN_BYTES = 1;
	private final static int BYTE_IN_BITS = 8;
	private final static int SHORT_SIZE_IN_BYTES = 2;

	private static final Pattern NO_SEPARATOR_HEX_STRING_PATTERN = Pattern.compile("\\A[0-9a-fA-F]*\\z");

	/**
	 * Reverses the provided byte array.
	 * 
	 * @param array the byte array to reverse
	 * @return a new byte array with elements in reverse order
	 */
	public static byte[] reverse(byte[] array) {
		byte[] reverse = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			reverse[i] = array[array.length - 1 - i];
		}
		return reverse;
	}

	/**
	 * Converts a single byte value to a byte array.
	 * 
	 * @param value the byte value to convert
	 * @return a byte array containing the provided byte
	 */
	public static byte[] getByteArray(byte value) {
		return new byte[] { value };
	}

	/**
	 * Converts a short value to a byte array with Big_Endian byte order....
	 * 
	 * @param value the short to convert
	 * @return a byte array representing the short in the specified byte order
	 */
	public static byte[] getByteArray(short value) {
		return getByteArray(value, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Converts a short value to a byte array based on the specified byte order.
	 * 
	 * @param value the short to convert
	 * @param bo    the byte order to use (either BIG_ENDIAN or LITTLE_ENDIAN)
	 * @return a byte array representing the short in the specified byte order
	 */
	public static byte[] getByteArray(short value, ByteOrder bo) {
		if (bo.equals(ByteOrder.LITTLE_ENDIAN))
			return new byte[] {
					(byte) (value),
					(byte) (value >> BYTE_IN_BITS * 1)
			};

		else
			return new byte[] {
					(byte) (value >> BYTE_IN_BITS * 1),
					(byte) (value)
			};
	}

	/**
	 * Converts a integer value to a byte array with Big_Endian byte order....
	 * 
	 * @param value the short to convert
	 * @return a byte array representing the short in the specified byte order
	 */
	public static byte[] getByteArray(int value) {
		return getByteArray(value, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Converts an integer value to a byte array based on the specified byte order.
	 * 
	 * @param value the integer to convert
	 * @param bo    the byte order to use (either BIG_ENDIAN or LITTLE_ENDIAN)
	 * @return a byte array representing the integer in the specified byte order
	 */
	public static byte[] getByteArray(int value, ByteOrder bo) {
		if (bo.equals(ByteOrder.LITTLE_ENDIAN))
			return new byte[] {
					(byte) (value),
					(byte) (value >> BYTE_IN_BITS * 1),
					(byte) (value >> BYTE_IN_BITS * 2),
					(byte) (value >> BYTE_IN_BITS * 3),
			};
		else
			return new byte[] {
					(byte) (value >> BYTE_IN_BITS * 3),
					(byte) (value >> BYTE_IN_BITS * 2),
					(byte) (value >> BYTE_IN_BITS * 1),
					(byte) (value)
			};

	}

	public static byte[] getByteArray(long value) {
		return getByteArray(value, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Converts a long value to a byte array based on the specified byte order.
	 * 
	 * @param value the long to convert
	 * @param bo    the byte order to use (either BIG_ENDIAN or LITTLE_ENDIAN)
	 * @return a byte array representing the long in the specified byte order
	 */
	public static byte[] getByteArray(long value, ByteOrder bo) {
		if (bo.equals(ByteOrder.LITTLE_ENDIAN))
			return new byte[] {
					(byte) (value),
					(byte) (value >> BYTE_IN_BITS * 1),
					(byte) (value >> BYTE_IN_BITS * 2),
					(byte) (value >> BYTE_IN_BITS * 3),
					(byte) (value >> BYTE_IN_BITS * 4),
					(byte) (value >> BYTE_IN_BITS * 5),
					(byte) (value >> BYTE_IN_BITS * 6),
					(byte) (value >> BYTE_IN_BITS* 7),
			};

		else
			return new byte[] {
					(byte) (value >> BYTE_IN_BITS * 7),
					(byte) (value >> BYTE_IN_BITS * 6),
					(byte) (value >> BYTE_IN_BITS* 5),
					(byte) (value >> BYTE_IN_BITS* 4),
					(byte) (value >> BYTE_IN_BITS* 3),
					(byte) (value >> BYTE_IN_BITS* 2),
					(byte) (value >> BYTE_IN_BITS* 1),
					(byte) (value)
			};
	}

	/**
	 * Retrieves a byte value from a byte array at the specified offset.
	 * 
	 * @param arr    the byte array from which to read
	 * @param offset the offset in the array where reading starts
	 * @return the byte value at the specified offset
	 */
	public static byte getByte(byte[] arr, int offset) {
		validate(arr, offset, BYTE_SIZE_IN_BYTES);
		return (byte) arr[offset];
	}

	/**
	 * Retrieves a short value from a byte array at the specified offset, using
	 * BIG_ENDIAN byte order.
	 * 
	 * @param arr    the byte array from which to read
	 * @param offset the offset in the array where reading starts
	 * @return the short value at the specified offset
	 */
	public static short getShort(byte[] arr, int offset) {
		return getShort(arr, offset, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Retrieves a short value from a byte array at the specified offset, using the
	 * specified byte order.
	 * 
	 * @param arr    the byte array from which to read
	 * @param offset the offset in the array where reading starts
	 * @param bo     the byte order to use for interpreting the bytes
	 * @return the short value at the specified offset
	 */
	public static short getShort(byte[] arr, int offset, ByteOrder bo) {
		if (bo == null)
			throw new NullPointerException("Byte Order cannot be null");

		validate(arr, offset, SHORT_SIZE_IN_BYTES);

		if (bo.equals(ByteOrder.LITTLE_ENDIAN))
			return (short) ((arr[offset + 1] << BYTE_IN_BITS) | (0xFF & arr[offset])); // LSB comes first in the offset
																						// place.... and MSB is in the
																						// int offset+1
		else
			return (short) ((arr[offset] << BYTE_IN_BITS) | ((0xFF) & arr[offset + 1]));
	}

	public static int getInt(byte[] arr, int offset) {
		return getInt(arr, offset, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Retrieves an integer value from a byte array at the specified offset, using
	 * the specified byte order.
	 * 
	 * @param arr    the byte array from which to read
	 * @param offset the offset in the array where reading starts
	 * @param bo     the byte order to use for interpreting the bytes
	 * @return the integer value at the specified offset
	 */
	public static int getInt(byte[] arr, int offset, ByteOrder bo) {
		if (bo == null)
			throw new NullPointerException("Byte Order cannot be nulll");
		validate(arr, offset, Integer.BYTES);

		if (bo.equals(ByteOrder.LITTLE_ENDIAN))
			return (int) (((arr[offset + 3] << BYTE_IN_BITS * 3)) |
					(0xFF & arr[offset + 2] << BYTE_IN_BITS * 2) |
					(0xFF & arr[offset + 1] << BYTE_IN_BITS * 1) |
					(0xFF & arr[offset]));

		else
			return (int) (((arr[offset] << BYTE_IN_BITS * 3)) |
					((0xFF & arr[offset + 1]) << (BYTE_IN_BITS * 2)) |
					((0xFF & arr[offset + 2]) << (BYTE_IN_BITS * 1)) |
					((0xFF & arr[offset + 3])));

	}

	public static long getLong(byte[] arr, int offset) {
		validate(arr, offset, Long.BYTES);
		return getLong(arr, offset, ByteOrder.BIG_ENDIAN);
	}

	/**
	 * Retrieves a long value from a byte array at the specified offset, using the
	 * specified byte order.
	 * 
	 * @param arr    the byte array from which to read
	 * @param offset the offset in the array where reading starts
	 * @param bo     the byte order to use for interpreting the bytes
	 * @return the long value at the specified offset
	 */
	public static long getLong(byte[] arr, int offset, ByteOrder bo) {
		if (bo == null)
			throw new NullPointerException("Byte Order cannot be null");

		if (bo.equals(ByteOrder.LITTLE_ENDIAN))
			return (long) (((arr[offset + 7] << BYTE_IN_BITS * 7)) |
					(0xFF & arr[offset + 6] << BYTE_IN_BITS * 6) |
					(0xFF & arr[offset + 5] << BYTE_IN_BITS * 5) |
					(0xFF & arr[offset + 4] << BYTE_IN_BITS * 4) |
					(0xFF & arr[offset + 3] << BYTE_IN_BITS * 3) |
					(0xFF & arr[offset + 2] << BYTE_IN_BITS * 2) |
					(0xFF & arr[offset + 1] << BYTE_IN_BITS * 1) |
					(0xFF & arr[offset]));
		else
			return (long) ((arr[offset] << BYTE_IN_BITS * 7) |
					(0xFF & arr[offset + 1] << BYTE_IN_BITS * 6) |
					(0xFF & arr[offset + 2] << BYTE_IN_BITS * 5) |
					(0xFF & arr[offset + 3] << BYTE_IN_BITS * 4) |
					(0xFF & arr[offset + 4] << BYTE_IN_BITS * 3) |
					(0xFF & arr[offset + 5] << BYTE_IN_BITS * 2) |
					(0xFF & arr[offset + 6] << BYTE_IN_BITS * 1) |
					(0xFF & arr[offset + 7]));
	}

	/**
	 * Validates the array, offset, and length to ensure safe access to the array.
	 * 
	 * @param arr    the array to validate
	 * @param offset the offset at which the data starts
	 * @param len    the length of data to read
	 * @throws IllegalArgumentException       if the input conditions are not met
	 * @throws ArrayIndexOutOfBoundsException if the requested range is outside of
	 *                                        the array bounds
	 */
	public static void validate(byte[] arr, int offset, int len) {

		if (arr == null | arr.length == 0)
			throw new IllegalArgumentException(
					arr == null ? "the array can not be null" : "the array can not be empty");

		if (len == 0)
			throw new IllegalArgumentException("legth is zer0");

		if (offset < 0 || len < 0 || offset + len > arr.length)
			throw new ArrayIndexOutOfBoundsException();
	}

	public static byte[] parseInet4Address(String addr) {
		String[] octetStrs = addr.split("\\.", 4);
		if (octetStrs.length != Integer.BYTES) {
			throw new IllegalArgumentException("Couldn't get an Inet4Address from " + addr);
		}

		byte[] octets = new byte[4];
		for (int i = 0; i < octets.length; i++) {
			String octetStr = octetStrs[i];
			try {
				int octet = Integer.parseInt(octetStr);
				if (octet < 0 || octet > 255) {
					throw new IllegalArgumentException("Couldn't get an Inet4Address from " + addr);
				}
				octets[i] = (byte) octet;
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Couldn't get an Inet4Address from " + addr);
			}
		}

		return octets;
	}

	public static byte[] getSubArray(byte[] array, int offset, int length) {
		validate(array, offset, length);

		byte[] subArray = new byte[length];
		System.arraycopy(array, offset, subArray, 0, length);
		return subArray;
	}

	public static MacAddress getMacAddress(byte[] arr, int offset) {
		return getMacAddress(arr, offset, ByteOrder.BIG_ENDIAN);
	}

	public static MacAddress getMacAddress(byte[] array, int offset, ByteOrder bo) {
		return null;
	}

	public static String toHexString(short value, String separator) {
		byte[] x = getByteArray(value);
		return toHexString(x, separator, 0, x.length);
	}

	public static String toHexString(short value, String separator, ByteOrder bo) {
		byte[] x = getByteArray(value, bo);
		return toHexString(x, separator, 0, x.length);
	}

	public static String toHexString(int value, String separator) {
		byte[] x = getByteArray(value);
		return toHexString(x, separator, 0, x.length);

	}

	public static String toHexString(int value, String separator, ByteOrder bo) {
		var x = getByteArray(value, bo);
		return toHexString(x, separator, 0, x.length);
	}

	public static String toHexString(long value, String separator) {
		var x = getByteArray(value);
		return toHexString(x, separator, 0, x.length);
	}

	public static String toHexString(long value, String separator, ByteOrder bo) {
		var x = getByteArray(value, bo);
		return toHexString(x, separator, 0, x.length);
	}

	/**
	 * Converts a specified range of a byte array to a hexadecimal string
	 * representation,
	 * with an optional separator between each byte's hexadecimal representation.
	 *
	 * @param array     The byte array to convert.
	 * @param separator The string to insert between each pair of hexadecimal
	 *                  characters. Can be empty.
	 * @param offset    The starting position in the array to begin conversion.
	 * @param length    The number of bytes to convert starting from the offset.
	 * @return A string containing the hexadecimal representation of the specified
	 *         byte array range,
	 *         with the specified separator between each byte's hexadecimal
	 *         representation.
	 * @throws ArrayIndexOutOfBoundsException if the offset or length are out of
	 *                                        bounds of the byte array.
	 */
	public static String toHexString(byte[] array, String separator, int offset, int length) {

		final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
		validate(array, offset, length);

		// Calculate the length of the resulting StringBuilder
		int separatorLength = separator.length();
		int resultLength = length * 2 + (length - 1) * separatorLength;
		StringBuilder hexString = new StringBuilder(resultLength);

		for (int i = 0; i < length; i++) {

			if (i > 0 && separatorLength > 0) {
				hexString.append(separator);
			}

			int v = array[offset + i] & 0x00FF;
//			System.out.println(v);
			hexString.append(HEX_CHARS[v >>> 4]);
			// System.out.println(HEX_CHARS[v >>> 4]);
			hexString.append(HEX_CHARS[v & 0x0F]);
			// System.out.println(HEX_CHARS[v & 0x0F]);
		}

		return hexString.toString();
	}

	public static byte[] getByteArray(String hexString, String separator) {
		if (hexString == null || separator == null)
			throw new NullPointerException("hexString " + hexString + "separator " + separator);

		if (hexString.startsWith("0x"))
			hexString = hexString.substring(2);

		String noSeparatorHexString = hexString;

		if (!separator.isEmpty()) {
			StringBuilder patternSb = new StringBuilder("\\A[0-9a-fA-F]{2}(")
					.append(Pattern.quote(separator))
					.append("[0-9a-fA-F]{2})*\\z");

			String patternString = patternSb.toString();

			if (!Pattern.compile(patternString).matcher(hexString).matches()) {
				throw new IllegalArgumentException(
						"Invalid hex string (" + hexString + "), does not match pattern (" + patternString + ")");
			}
			noSeparatorHexString = hexString.replace(separator, "");
			// System.out.println(noSeparatorHexString);
		} else {

			if (!NO_SEPARATOR_HEX_STRING_PATTERN.matcher(hexString).matches()) {
				throw new IllegalArgumentException("Invalid hex string (" + hexString + "), does not match pattern ("
						+ NO_SEPARATOR_HEX_STRING_PATTERN.pattern() + ")");
			}
		}

		int arrayLength = noSeparatorHexString.length() / 2;

		byte[] array = new byte[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			array[i] = (byte) Integer.parseInt(noSeparatorHexString.substring(i * 2, i * 2 + 2), 16);
		}

		return array;
	}

}
