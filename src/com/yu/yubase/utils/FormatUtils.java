package com.yu.yubase.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

public class FormatUtils {

	public static String floatNumberFormat(String number) {
		DecimalFormat fnum = new DecimalFormat("#,###,###,##0.00");
		return fnum.format(Double.parseDouble(number));
	}
	
	public static String floatNumberFormat(double number) {
		DecimalFormat fnum = new DecimalFormat("#,###,###,##0.00");
		return fnum.format(number);
	}

	public static String intNumberFormat(double number) {
		DecimalFormat fnum = new DecimalFormat("#,###,###,##0.##");
		return fnum.format(number);
	}

	/**
	 * 把中文字符串转换为十六进制Unicode编码字符�?
	 */
	public static String stringToUnicode(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			if (ch > 255) {
				str += "\\u" + Integer.toHexString(ch);
			} else {
				str += "\\" + Integer.toHexString(ch);
			}
		}
		return str;
	}

	/**
	 * 把十六进制Unicode编码字符串转换为中文字符�?
	 */
	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	/**
	 * 匹配短信中间�?6个数字（验证码等�? 
	 * @param patternContent
	 * @return
	 */
	public static String patternCode(String patternContent) {
		String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";
		if (TextUtils.isEmpty(patternContent)) {
			return null;
		}
		Pattern p = Pattern.compile(patternCoder);
		Matcher matcher = p.matcher(patternContent);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
	/**
	 * 匹配短信中间�?6个数字（验证码等�? 
	 * @param patternContent
	 * @return
	 */
	public static String patternCode1(String patternContent) {
		String patternCoder = "[a-z0-9]{8}";
//		String patternCoder = "/^[a-z0-9]{8}$/";
		if (TextUtils.isEmpty(patternContent)) {
			return null;
		}
		Pattern p = Pattern.compile(patternCoder);
		Matcher matcher = p.matcher(patternContent);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
	
	/**
	 * 将银行卡号中间的�?段用�?*”代替�??
	 */
	public static String encodeBankCardNumber(String bankNum){
		char bankCard[] = bankNum.toCharArray();
		
		for (int i = 0; i < bankCard.length; i++) {
			if (i > 3 && i < bankCard.length - 4) {
				bankCard[i] = '*';
			}
		}
		
		return new String(bankCard);
	}
}
