package com.yu.yubase.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//�?验用户输入是否合�? 的辅助类
public class CheckUtils {

	private CheckUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");

	}

	/*
	 * �?查手机号码是否正�?
	 */
	public static boolean checkPhone(String phoneNum) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

		Matcher m = p.matcher(phoneNum);

		return m.matches();

	}

	/*
	 * �?查身份证号码是否正确
	 */

	/**
	 * 身份证校验工具（支持15位或�?18位身份证�?<br/>
	 * 身份证号码结构：
	 * <ol>
	 * <li>17位数字和1位校验码�?6位地�?码数字，8位生日数字，3位出生时间顺序码�?1位校验码�?</li>
	 * <li>地址码（�?6位）：表示编码对象常住户口所在县(市�?�旗、区)的行政区划代码，按GB/T2260的规定执行�??</li>
	 * <li>出生日期码（第七位至十四位）：表示编码对象出生的年�?�月、日，按GB/T7408的规定执行，年�?�月、日代码之间不用分隔符�??</li>
	 * <li>顺序码（第十五位至十七位�? ：表示在同一地址码所标识的区域范围内，对同年、同月�?�同日出生的人编定的顺序号，
	 * 顺序码的奇数分配给男性，偶数分配给女性�??</li>
	 * <li>校验码（第十八位数）�?<br/>
	 * <ul>
	 * <li>十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, , 16 ，先对前17位数字的权求和；
	 * Ai:表示第i位置上的身份证号码数字�?? Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
	 * 2�?</li>
	 * <li>计算�? Y = mod(S, 11)</li>
	 * <li>通过模得到对应的校验�? Y: 0 1 2 3 4 5 6 7 8 9 10 校验�?: 1 0 X 9 8 7 6 5 4 3 2</li>
	 * </ul>
	 * </li>
	 * </ol>
	 * 
	 * @author xylz
	 * @since 2011-1-4
	 * @see {@link http
	 *      ://www.blogjava.net/zeroline/archive/2011/01/03/342227.html}
	 */

	final static Map<Integer, String> zoneNum = new HashMap<Integer, String>();
	static {
		zoneNum.put(11, "北京");
		zoneNum.put(12, "天津");
		zoneNum.put(13, "河北");
		zoneNum.put(14, "山西");
		zoneNum.put(15, "内蒙�?");
		zoneNum.put(21, "辽宁");
		zoneNum.put(22, "吉林");
		zoneNum.put(23, "黑龙�?");
		zoneNum.put(31, "上海");
		zoneNum.put(32, "江苏");
		zoneNum.put(33, "浙江");
		zoneNum.put(34, "安徽");
		zoneNum.put(35, "福建");
		zoneNum.put(36, "江西");
		zoneNum.put(37, "山东");
		zoneNum.put(41, "河南");
		zoneNum.put(42, "湖北");
		zoneNum.put(43, "湖南");
		zoneNum.put(44, "广东");
		zoneNum.put(45, "广西");
		zoneNum.put(46, "海南");
		zoneNum.put(50, "重庆");
		zoneNum.put(51, "四川");
		zoneNum.put(52, "贵州");
		zoneNum.put(53, "云南");
		zoneNum.put(54, "西藏");
		zoneNum.put(61, "陕西");
		zoneNum.put(62, "甘肃");
		zoneNum.put(63, "青海");
		zoneNum.put(64, "宁夏");
		zoneNum.put(65, "新疆");
		zoneNum.put(71, "台湾");
		zoneNum.put(81, "香港");
		zoneNum.put(82, "澳门");
		zoneNum.put(91, "国外");
	}

	final static int[] PARITYBIT = { '1', '0', 'X', '9', '8', '7', '6', '5',
			'4', '3', '2' };
	final static int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
			5, 8, 4, 2 };

	/**
	 * 身份证号是否基本有效
	 * 440982 19910202 273 4
	 * 430621 19891009 507 X
	 * @param s
	 *            号码内容
	 * @return 是否有效，null�?""都是false
	 */
	public static boolean isIdcard(String s) {
		if (s == null || (s.length() != 15 && s.length() != 18))
			return false;
		final char[] cs = s.toUpperCase().toCharArray();
		// �?1）校验位�?
		int power = 0;
		for (int i = 0; i < cs.length; i++) {// 循环比正则表达式更快
			if (i == cs.length - 1 && cs[i] == 'X')
				break;// �?后一位可以是X或�?�x
			if (cs[i] < '0' || cs[i] > '9')
				return false;
			if (i < cs.length - 1)
				power += (cs[i] - '0') * POWER_LIST[i];
		}
		// �?2）校验区位码
		if (!zoneNum.containsKey(Integer.valueOf(s.substring(0, 2)))) {
			return false;
		}
		// �?3）校验年�?
		String year = s.length() == 15 ? "19" + s.substring(6, 8) : s
				.substring(6, 10);
		final int iyear = Integer.parseInt(year);
		if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)) {
			return false;// 1900年的PASS，超过今年的PASS
		}
		// �?4）校验月�?
		String month = s.length() == 15 ? s.substring(8, 10) : s.substring(10,
				12);
		final int imonth = Integer.parseInt(month);
		if (imonth < 1 || imonth > 12)
			return false;
		// �?5）校验天�?
		String day = s.length() == 15 ? s.substring(10, 12) : s.substring(12,
				14);
		final int iday = Integer.parseInt(day);
		if (iday < 1 || iday > 31)
			return false;
		// �?6）校验一个合法的年月�?
		if (!validate(iyear, imonth, iday))
			return false;
		// �?7）校验�?�校验码�?
		if (s.length() == 15)
			return true;
		return cs[cs.length - 1] == PARITYBIT[power % 11];
	}

	static boolean validate(int year, int month, int day) {
		// 比如考虑闰月，大小月�?
		return true;
	}

/*	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			final String s = "42230219880101100" + i;
			System.out.println(s + " --> " + isIdcard(s));
		}
	}*/

	/**
	 * 密码强度
	 * 
	 * @return Z = 字母 S = 数字 T = 特殊字符
	 */
	public static int checkPassword(String passwordStr) {
		String regexZ = "\\d*";
		String regexS = "[a-zA-Z]+";
		String regexT = "\\W+$";
		String regexZT = "\\D*";
		String regexST = "[\\d\\W]*";
		String regexZS = "\\w*";
		String regexZST = "[\\w\\W]*";

		if (passwordStr.matches(regexZ)) {
			return 0;
		}
		if (passwordStr.matches(regexS)) {
			return 0;
		}
		if (passwordStr.matches(regexT)) {
			return 0;
		}
		if (passwordStr.matches(regexZT)) {
			return 1;
		}
		if (passwordStr.matches(regexST)) {
			return 1;
		}
		if (passwordStr.matches(regexZS)) {
			return 1;
		}
		if (passwordStr.matches(regexZST)) {
			return 2;
		}
		return 3;

	}

	public static boolean isEmail(String email) {
		// String
		// str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,10}([\\.][A-Za-z]{2})?$";
		// String
		// str="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		String str = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		// logger.info(m.matches()+"---");
		return m.matches();
	}

}
