package com.ctvit.utils.md5;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;
/**
 * MD5加密工具类
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-12-28
 */
public class EncryptUtils {
	/**
	 * 对字符串加密的方法
	 * @param str
	 * @return
	 */

	public static String toMessageDigest(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

	}
	
	public static void main(String[] args) {
		String messageDigest = toMessageDigest("cmshttp://jingji.cntv.cn/2013/01/31/VIDE1359637762316430.shtml");
		System.out.println(messageDigest); //ADEF2D9F622C9C45F528BD36560988B4
	}


}
