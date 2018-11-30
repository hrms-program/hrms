package com.paladin.framework.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	public static final String ENCODE="UTF-8";
	
	public static String encode(String data){ 
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
		String ing = encoder.encode(data.getBytes());
		return ing.replace("=", "_");  //cookie中不允许存在类似于等号的特殊字符，而加密后的密文有时候会有等号存在
	}
	
	public static String decode(String data){
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		byte[] de = null;
		try {
			de = decoder.decodeBuffer(data.replace("_", "="));
			return new String(de);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     *通过图片base64流判断图片等于多少字节
     *image 图片流
     */
    public static Integer imageSize(String imageStr){
       // String str=image.substring(22); // 1.需要计算文件流大小，首先把头部的data:image/png;base64,（注意有逗号）去掉。
        Integer equalIndex= imageStr.indexOf("=");//2.找到等号，把等号也去掉
        if(imageStr.indexOf("=")>0) {
        	imageStr=imageStr.substring(0, equalIndex);
        }
        Integer strLength=imageStr.length();//3.原来的字符流大小，单位为字节
        Integer size=strLength-(strLength/8)*2;//4.计算后得到的文件流大小，单位为字节
        return size;
    }

	/**
	 *  图片转化成base64字符串
	 * 
	 */
	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/** 
	 * base64字符串转化成图片
	 * 
	 */
	public static boolean GenerateImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * base64字符串转化成字节流
	 * 
	 */
	public static byte[] GenerateImageStream(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null || imgStr.length()==0) {// 图像数据为空
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}
	
}
