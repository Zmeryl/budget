package com.leonyip.budget.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import javax.mail.internet.MimeUtility;





public class EncryptUtil {
	
	/**
    对字符串进行MD5编码，不可逆
    @param strInfo
    @return String
    @roseuid 3F60185E019D
     */
    public static String encodeByMD5(String strInfo) {
		try {
			java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
			alga.update(strInfo.getBytes());
			byte[] bytDigest = alga.digest();
			return byte2hex(bytDigest);
		} catch (java.security.NoSuchAlgorithmException ex) {
			System.out.println("非法摘要算法");
		}
		return "";
	}

    /**
    判断MD5码与原码是否匹配
    @param strInfo
    @param strEncode
    @return boolean
    @roseuid 3F60185E01BC
     */
    public static boolean equalByMD5(String strInfo, String strEncode) {
		try {
			java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
			alga.update(strInfo.getBytes());
			if (MessageDigest.isEqual(hex2byte(strEncode), alga.digest())) {
				return true;
			}
		} catch (java.security.NoSuchAlgorithmException ex) {
			System.out.println("非法摘要算法");
		}
		return false;
	}

    /**
    十六进制转二进制
    @param strHex
    @return byte[]
    @roseuid 3F60185E0216
     */
    private static byte[] hex2byte(String strHex) {
		String Digital="0123456789ABCDEF";
    	byte[] bytes=new byte[strHex.length()/2];
    	int temp;
    	for(int i=0;i<bytes.length;i++){
	      	temp=Digital.indexOf(strHex.substring(2*i,2*i+1))*16;
    	  	temp+=Digital.indexOf(strHex.substring(2*i+1,2*i+2));
			bytes[i]=(byte)(temp&0xff);
		}
		return bytes;
    }

    /**
    二进制转十六进制
    @param b
    @return String
    @roseuid 3F60185E01E4
     */
    private static String byte2hex(byte[] b) {
		String hs="";
		String stmp="";
		for (int n=0;n<b.length;n++){
			stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length()==1) hs=hs+"0"+stmp;
			else hs=hs+stmp;
			//if (n<b.length-1)  hs=hs;
		}
		return hs.toUpperCase();
    }
    
    
    public static BufferedReader decode(String b64string) throws Exception {
    	return new BufferedReader(
                new InputStreamReader(
                  MimeUtility.decode(new ByteArrayInputStream(b64string.getBytes()), "base64")));
    }

    public static ByteArrayOutputStream encode(String plaintext) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] in = plaintext.getBytes();
		ByteArrayOutputStream inStream = new ByteArrayOutputStream();
		inStream.write(in, 0, in.length);
		// 补0
		if ((in.length % 3) == 1) {
			inStream.write(0);
			inStream.write(0);
		} else if ((in.length % 3) == 2) {
			inStream.write(0);
		}
		inStream.writeTo(MimeUtility.encode(out, "base64"));
		return out;
	}
    
    public static String decodeAsString(String b64string) throws Exception {
		if (b64string == null) {
			return b64string;
		}
		String returnString = decode(b64string).readLine();
		if (returnString == null) {
			return returnString;
		}
		return returnString.trim();
	}
    
    public static String encodeAsString(String plaintext) throws Exception {
		return encode(plaintext).toString();
	}
    
    public static void main(String args[]) throws Exception {
    	double d = 1.23d;
    	String str = String.valueOf(d);
    	String pwd = encodeAsString(str);
		System.out.println(encodeAsString(str));
		System.out.println(decodeAsString(pwd));
	}
}
