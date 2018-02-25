package com.tenacity.free.util;

import org.slf4j.Logger;

/**
 * @ProjectName: tenacity-free-common
 * @PackageName: com.tenacity.free.common.util
 * @ClassName: LoggerUtils.java
 * @author: free.zhang
 * @Date: 2018年1月26日 下午2:04:34 
 * @Description: 日志记录工具类
 *
 */
public class LoggerUtils {
	
	
	/**
	 * @author: free.zhang
	 * @Date: 2018年1月26日 下午2:06:28 
	 * @Description: 记录异常信息
	 * @return: void
	 * @param logger
	 * @param className
	 * @param methodName
	 * @param message
	 */
	public static void loggerErrorInfo(Logger logger,String className,String methodName,String message) {
		String content = "XXXX类####方法异常信息: " + message;
		content.replace("XXXX", className).replace("####", methodName);
		logger.error(content);
	}

	/**
	 * @author: free.zhang
	 * @Date: 2018年1月26日 下午2:24:48 
	 * @Description: 记录正常信息
	 * @return: void
	 * @param logger
	 * @param className
	 * @param methodName
	 * @param message
	 */
	public static void loggerInfo(Logger logger,String className,String methodName,String message) {
		String content = "XXXX类####方法信息: " + message;
		content.replace("XXXX", className).replace("####", methodName);
		logger.info(content);
	}
	
}
