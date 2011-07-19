package org.archstudio.sysutils;

import java.net.InetAddress;
import java.util.Random;

public class UIDGenerator {

	private static boolean inited = false;

	protected static int machineID;
	protected static Random rnd;
	protected static int counter = 0;

	public static void main(String[] args) {
		System.out.println(generateUID());
	}

	protected static void init() {
		byte[] mid = new byte[] { 127, 0, 0, 1 };
		try {
			InetAddress localAddress = InetAddress.getLocalHost();
			mid = localAddress.getAddress();
		}
		catch (Exception e) {
		}
		machineID = mid[0] << 24 | mid[1] << 16 | mid[2] << 8 | mid[3];
		rnd = new Random();

		int r = rnd.nextInt();
		r <<= 16;
		counter = r;
		inited = true;
	}

	private static String toPaddedHex(int n) {
		String hex = Integer.toHexString(n);
		if (hex.length() == 8) {
			return hex;
		}
		StringBuffer sb = new StringBuffer(8);
		for (int i = hex.length(); i < 8; i++) {
			sb.append('0');
		}
		sb.append(hex);
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private static String toPaddedHex(long n) {
		String hex = Long.toHexString(n);
		if (hex.length() == 16) {
			return hex;
		}
		StringBuffer sb = new StringBuffer(16);
		for (int i = hex.length(); i < 16; i++) {
			sb.append('0');
		}
		sb.append(hex);
		return sb.toString();
	}

	public static String generateUID() {
		if (!inited) {
			init();
		}

		StringBuffer sb = new StringBuffer();
		sb.append(toPaddedHex(machineID));
		sb.append("-");
		sb.append(toPaddedHex((int) System.currentTimeMillis()));
		sb.append("-");
		sb.append(toPaddedHex(rnd.nextInt()));
		sb.append("-");
		sb.append(toPaddedHex(counter++));

		return sb.toString();
	}

	public static String generateUID(String prefix) {
		return prefix + generateUID();
	}

}
