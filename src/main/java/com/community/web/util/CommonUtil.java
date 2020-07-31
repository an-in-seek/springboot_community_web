package com.community.web.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

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
	
	/**
	 * 이미지 사이즈 변경
	 * @param inputStream
	 * @param width
	 * @param height
	 * @return
	 * @throws Throwable
	 */
	public static BufferedImage resizeImage(InputStream inputStream, int width, int height) throws Throwable {
		BufferedImage inputImage = ImageIO.read(inputStream);
		BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
		Graphics2D graphics2D = outputImage.createGraphics();
		graphics2D.drawImage(inputImage, 0, 0, width, height, null);
		graphics2D.dispose();
		return outputImage;
	}
}
