package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {
	public static int bytesToInt(byte[] bytes) {
		int result = 0;
		int[] ubytes = unsignBytes(bytes);
		for (int i=0; i<ubytes.length; i++) {
			result += (int) ubytes[i] << (8*i);
		}
		return result;
	}
	public static byte[] intToSignedBytes(int value) {
		byte[] result = new byte[4];
		for (int i=0; i<result.length; i++) {
			int temp = value;
			temp = temp << (i*8);
			temp = temp >> 24;
			result[i] = (byte) temp;
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
	public static Pixmap scalePixmap (Pixmap source, Vector2 desiredSize) {
		Pixmap target = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
		int sourceX, sourceY;
		int targetWidth = target.getWidth();
		int targetHeight = target.getHeight();
		int sourceWidth = source.getWidth();
		int sourceHeight = source.getHeight();
		for (int targetX=0; targetX<targetWidth; targetX++) {
			for (int targetY=0; targetY<targetHeight; targetY++) {
				sourceX = Math.round((float)targetX/(float)targetWidth*(float)sourceWidth);
				sourceY = Math.round((float)targetY/(float)targetHeight*(float)sourceHeight);
				target.drawPixel(targetX, targetY, source.getPixel(sourceX, sourceY));
			}
		}
		return target;
	}
}
