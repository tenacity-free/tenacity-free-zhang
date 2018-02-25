package com.tenacity.free.util;

import java.util.Random;
import java.util.UUID;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: RandomUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午6:35:35
 * @desc: 随机数工具类
 */
public class RandomUtils {

	public static final char[] n = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	public static final char[] c = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Z', 'Y', 'Z' };
	public static final char[] s = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'Z', 'Y', 'Z' };
	public static final char[] sc = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'Z', 'Y', 'Z', '=', '/', '+', '-', '*', '_', '~', '!', '#', '@', '%', '^', '&', '(', ')', '|', '.',
			'`', ':' };

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:37:05
	 * @desc: 获取一个固定长度的随机整数
	 * @param size
	 * @return
	 */
	public static String getRandomNumber(int size) {
		StringBuffer sb = new StringBuffer();
		Random rd = new Random();
		for (int i = 0; i < size; i++) {
			sb.append(n[rd.nextInt(n.length)]);
		}
		return sb.toString();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:36:57
	 * @desc: 获取一个随机的字符串
	 * @param size
	 * @param withNumber
	 * @return
	 */
	public static String getRandomString(int size, boolean withNumber) {
		StringBuffer sb = new StringBuffer();
		Random rd = new Random();
		if (withNumber) {
			for (int i = 0; i < size; i++) {
				sb.append(s[rd.nextInt(s.length)]);
			}
		} else {
			for (int i = 0; i < size; i++) {
				sb.append(c[rd.nextInt(c.length)]);
			}
		}
		return sb.toString();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:36:48
	 * @desc: 获取一个随机的字符串,含有特殊字符
	 * @param size
	 * @return
	 */
	public static String getRandomStringWithSpecialChar(int size) {
		StringBuffer sb = new StringBuffer();
		Random rd = new Random();
		for (int i = 0; i < size; i++) {
			sb.append(sc[rd.nextInt(sc.length)]);
		}
		return sb.toString();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:36:38
	 * @desc: 获取一个双精度随机数
	 * @return
	 */
	public static Double getRandomDouble() {
		return Math.random();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:36:27
	 * @desc: 获取UUID
	 * @return
	 */
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:36:19
	 * @desc: 获取某个区间的整形
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午6:36:02
	 * @desc: 获取一个随机整形值
	 * @return
	 */
	public static int getRandomInt() {
		Random random = new Random();
		return random.nextInt();
	}

}
