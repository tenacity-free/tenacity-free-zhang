package com.tenacity.free.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: ResultUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午4:59:09
 * @desc: 返回结果封装
 */
public class ResultUtils {

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:51:01
	 * @desc: 异常信息代码块
	 * @param logger
	 * @param throwable
	 * @return
	 */
	public static Map<String, Object> resultErrorMessage(Logger logger, String className, String methodName,
			Throwable throwable) {
		Map<String, Object> resultJson = new HashMap<>(4);
		resultJson.put("status", "50003");
		resultJson.put("msg", "系统异常");
		resultJson.put("excepitonMsg", ExceptionUtils.getTrace(throwable));

		LoggerUtils.loggerErrorInfo(logger, className, methodName, resultJson.toString());
		throwable.printStackTrace();
		return resultJson;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:50:46
	 * @desc: 错误信息代码
	 * @param logger
	 * @param status
	 * @param message
	 * @return
	 */
	public static Map<String, Object> resultErrorMessage(Logger logger, String className, String methodName,
			String status, String message) {
		Map<String, Object> resultJson = new HashMap<>(3);
		resultJson.put("status", status);
		resultJson.put("msg", message);
		LoggerUtils.loggerErrorInfo(logger, className, methodName, resultJson.toString());
		return resultJson;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:50:11
	 * @desc: 操作成功返回
	 * @param logger
	 * @param message
	 * @return
	 */
	public static Map<String, Object> resultSuccessMessage(Logger logger, String className, String methodName,
			String message) {

		return resultSuccessMessage(logger, className, methodName, null, message);
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:50:28
	 * @desc: 操作成功返回(带返回数据)
	 * @param logger
	 * @param object
	 * @param message
	 * @return
	 */
	public static Map<String, Object> resultSuccessMessage(Logger logger, String className, String methodName,
			Object object, String message) {
		Map<String, Object> resultJson = new HashMap<>(4);
		resultJson.put("status", 1);
		resultJson.put("msg", message);
		if (null != object) {
			resultJson.put("data", object);
		}
		LoggerUtils.loggerInfo(logger, className, methodName, resultJson.toString());
		return resultJson;
	}

}
