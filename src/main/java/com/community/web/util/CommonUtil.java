package com.community.web.util;

public class CommonUtil {

	/**
	 * null 여부
	 * @param obj
	 * @return
	 */
	static public boolean isNull(Object obj) {
		return obj == null ? true : false;
	}

	/**
	 * Windows 여부
	 * @return
	 */
	public static boolean isWindows() {
		return (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);
	}

	/**
	 * Mac 여부
	 * @return
	 */
	public static boolean isMac() {
		return (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0);
	}

	/**
	 * Unix 여부
	 * @return
	 */
	public static boolean isUnix() {
		String osName = System.getProperty("os.name").toLowerCase();
		return (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0);
	}
}
