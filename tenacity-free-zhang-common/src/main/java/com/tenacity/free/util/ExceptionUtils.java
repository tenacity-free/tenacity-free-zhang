package com.tenacity.free.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: ExceptionUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午5:39:19
 * @desc: TODO
 */
public class ExceptionUtils extends Exception {

	private static final long serialVersionUID = 1L;

	
	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:40:06
	 * @desc: TODO
	 */
	public ExceptionUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:40:07
	 * @desc: TODO
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ExceptionUtils(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:40:07
	 * @desc: TODO
	 * @param message
	 * @param cause
	 */
	public ExceptionUtils(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:40:07
	 * @desc: TODO
	 * @param message
	 */
	public ExceptionUtils(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:40:07
	 * @desc: TODO
	 * @param cause
	 */
	public ExceptionUtils(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:44:32
	 * @desc: 单例模式
	 * @param message
	 * @return
	 */
	public static ExceptionUtils getInstance() {
		return new ExceptionUtils();
	}
	public static ExceptionUtils getInstance(String message) {
		return new ExceptionUtils(message);
	}
	public static ExceptionUtils getInstance(String message, Throwable cause) {
		return new ExceptionUtils(message, cause);
	}
	public static ExceptionUtils getInstance(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		return new ExceptionUtils(message, cause, enableSuppression, writableStackTrace);
	}
	
	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月13日 下午5:42:07
	 * @desc: 将异常信息转换为字符串
	 * @param t
	 * @return
	 */
	public static String getTrace(Throwable throwable) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        throwable.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
	

}
