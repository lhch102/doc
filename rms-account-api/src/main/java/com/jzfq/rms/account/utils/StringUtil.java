package com.jzfq.rms.account.utils;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtil
 * @Description: (对于字符串的常用操作)
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class StringUtil {


	private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * @Title: isEmpty
	 * @Description: (判断对象是否为空)
	 * @param @param obj
	 * @param @return 判断的对象
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && String.valueOf(obj).trim().equals("")) {
			return true;
		}
		// else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
		// return true;
		// }
		else if (obj instanceof Boolean && !((Boolean) obj)) {
			return true;
		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getRandomString
	 * @Description: (产生随机数)
	 * @param length 产生随机数的长度
	 * @return String 返回类型
	 * @throws
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(62);// [0,62)

			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * @Title: getRandomString
	 * @Description: (产生随机数)
	 * @param length 产生随机数的长度
	 * @return
	 *         String 返回类型
	 * @throws
	 */
	public static String getRandomString2(int length) {
		Random random = new Random();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(3);
			long result = 0;

			switch (number) {
			case 0:
				result = Math.round(Math.random() * 25 + 65);
				sb.append(String.valueOf((char) result));
				break;
			case 1:
				result = Math.round(Math.random() * 25 + 97);
				sb.append(String.valueOf((char) result));
				break;
			case 2:
				sb.append(String.valueOf(new Random().nextInt(10)));
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * @Title: arrayToString
	 * @Description: (把对象数组转化为字符串)
	 * @param objs 需要转化字符串的数组
	 * @return String 返回类型
	 * @throws
	 */
	public static String arrayToString(Object[] objs) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < objs.length; i++) {
			if (i < objs.length - 1) {
				sb.append(objs[i] + ",");
			} else {
				sb.append(objs[i]);
			}
		}

		String s = sb.toString();

		return s;
	}

	/**
	 * @Title: isNumeric
	 * @Description: (判断字符串是否为数字)
	 * @param str 需要判断的字符串
	 * @return boolean
	 *         返回类型
	 * @throws
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static String getLocalIP() {
		try {
			InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return "127.0.0.1";
	}
	public static void main(String[] args) {

		String xml = "{\"type\":\"" + 1 + "\",\"isNotice\":\"1\"}";
		System.out.println(xml);

	}

}
