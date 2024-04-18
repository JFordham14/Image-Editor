package com.mygdx.image_editor;

public class Util {
	public static int bytesToInt(byte[] bytes) {
		int result = 0;
		int[] ubytes = unsignBytes(bytes);
		for (int i=0; i<ubytes.length; i++) {
			result += (int) ubytes[i] << (8*i);
		}
		return result;
	}
	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for (int i=0; i<bytes.length; i++) {
			int temp = bytes[i];
			if (temp<0) {
				temp += 129+127;
			}
			ints[i] = temp;
		}
		return ints;
	}
}
