package com.yu.yubase.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import android.util.Log;

import com.yu.jar.yujar.Y;
import com.yu.yubase.BaseApplication;

/**
 * 3DES加密工具�?
 */
public class DES3 {
	// 密钥
	 private final static String secretKey = "my.oschina.net/penngo?#@" ;
	// 向量
		private final static String iv = "01234567";
		// 加解密统一使用的编码方式
		private final static String encoding = "utf-8";

		/**
		 * 3DES加密
		 * 
		 * @param plainText
		 *            普通文本
		 * @return
		 * @throws Exception
		 */
	public static String encode(String secretKey, String plainText) {
		try {

			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);

			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			// byte [] encryptData = cipher.doFinal(plainText.getBytes());
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			String ttem = android.util.Base64.encodeToString(encryptData,
					android.util.Base64.NO_WRAP);
			return ttem;
		} catch (Exception e) {
			Log.e("DES3", "加密error:"+e);
//			BaseApplication.getInstance().showtoast("暂时无法获取数据，请稍后重试",Y.TIP);
			return plainText;
		}
		// return Base64.encode(encryptData);
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String secretKey, String encryptText) {
		try {

			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] temData = android.util.Base64
					.decode(encryptText.getBytes(encoding),
							android.util.Base64.NO_WRAP);
			byte[] decryptData = cipher.doFinal(temData);
			// byte [] decryptData = cipher.doFinal(Base64.decode(encryptText));

			return new String(decryptData, encoding);
		} catch (Exception e) {
			Y.e("DES3", "解密error", "暂时无法获取数据，请稍后重试");
			return "";
		}
	}

	public static String padding(String str) {
		byte[] oldByteArray;
		try {
			oldByteArray = str.getBytes("UTF8");
			int numberToPad = 8 - oldByteArray.length % 8;
			byte[] newByteArray = new byte[oldByteArray.length + numberToPad];
			System.arraycopy(oldByteArray, 0, newByteArray, 0,
					oldByteArray.length);
			for (int i = oldByteArray.length; i < newByteArray.length; ++i) {
				newByteArray[i] = 0;
			}
			return new String(newByteArray, "UTF8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Crypter.padding UnsupportedEncodingException");
		}
		return null;
	}

	/**
	 * Base64编码工具�?
	 * 
	 */
	public static class Base64 {
		private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
				.toCharArray();

		public static String encode(byte[] data) {
			int start = 0;
			int len = data.length;
			StringBuffer buf = new StringBuffer(data.length * 3 / 2);

			int end = len - 3;
			int i = start;
			int n = 0;

			while (i <= end) {
				int d = ((((int) data[i]) & 0x0ff) << 16)
						| ((((int) data[i + 1]) & 0x0ff) << 8)
						| (((int) data[i + 2]) & 0x0ff);

				buf.append(legalChars[(d >> 18) & 63]);
				buf.append(legalChars[(d >> 12) & 63]);
				buf.append(legalChars[(d >> 6) & 63]);
				buf.append(legalChars[d & 63]);

				i += 3;

				if (n++ >= 14) {
					n = 0;
					buf.append(" ");
				}
			}

			if (i == start + len - 2) {
				int d = ((((int) data[i]) & 0x0ff) << 16)
						| ((((int) data[i + 1]) & 255) << 8);

				buf.append(legalChars[(d >> 18) & 63]);
				buf.append(legalChars[(d >> 12) & 63]);
				buf.append(legalChars[(d >> 6) & 63]);
				buf.append("=");
			} else if (i == start + len - 1) {
				int d = (((int) data[i]) & 0x0ff) << 16;

				buf.append(legalChars[(d >> 18) & 63]);
				buf.append(legalChars[(d >> 12) & 63]);
				buf.append("==");
			}

			return buf.toString();
		}

		private static int decode(char c) {
			if (c >= 'A' && c <= 'Z')
				return ((int) c) - 65;
			else if (c >= 'a' && c <= 'z')
				return ((int) c) - 97 + 26;
			else if (c >= '0' && c <= '9')
				return ((int) c) - 48 + 26 + 26;
			else
				switch (c) {
				case '+':
					return 62;
				case '/':
					return 63;
				case '=':
					return 0;
				default:
					throw new RuntimeException("unexpected code: " + c);
				}
		}

		/**
		 * Decodes the given Base64 encoded String to a new byte array. The byte
		 * array holding the decoded data is returned.
		 */

		public static byte[] decode(String s) {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				decode(s, bos);
			} catch (IOException e) {
				throw new RuntimeException();
			}
			byte[] decodedBytes = bos.toByteArray();
			try {
				bos.close();
				bos = null;
			} catch (IOException ex) {
				System.err.println("Error while decoding BASE64: "
						+ ex.toString());
			}
			return decodedBytes;
		}

		private static void decode(String s, OutputStream os)
				throws IOException {
			int i = 0;

			int len = s.length();

			while (true) {
				while (i < len && s.charAt(i) <= ' ')
					i++;

				if (i == len)
					break;

				int tri = (decode(s.charAt(i)) << 18)
						+ (decode(s.charAt(i + 1)) << 12)
						+ (decode(s.charAt(i + 2)) << 6)
						+ (decode(s.charAt(i + 3)));

				os.write((tri >> 16) & 255);
				if (s.charAt(i + 2) == '=')
					break;
				os.write((tri >> 8) & 255);
				if (s.charAt(i + 3) == '=')
					break;
				os.write(tri & 255);

				i += 4;
			}
		}
	}

	/*
	 * public static void main(String[] args) throws Exception{ String plainText
	 * = "来自http://my.oschina.net/penngo的博�?"; String encryptText =
	 * DES3.encode(secretKey,plainText); System.out.println(encryptText);
	 * System.out.println(DES3.decode(secretKey,encryptText)); }
	 */
//	public static void main(String args[]) throws Exception {
////		String encrypt = ZTSA54Q67();
////		System.out.println("加密后的内容�?     " + encrypt);
////		System.out.println("解密后的内容�?     " + DES3DecryptMode(encrypt));
//		String begain = "272604545@qq.com测事故测事故";
//		System.out.println("原始数据为：" + begain);
//		String encrypt = encode(secretKey, begain);
//		System.out.println("加密后的数据为：" +encrypt );
//		System.out.println("解密后的数据为：" + decode(secretKey, encrypt));
//
//	}
}