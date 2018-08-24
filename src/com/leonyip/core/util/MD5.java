package com.leonyip.core.util;

import com.leonyip.core.exception.BusinessException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String crypt(String str) {
		if ((str == null) || (str.length() == 0)) {
			throw new IllegalArgumentException(
					"String to encript cannot be null or zero length");
		}

		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash = md.digest();

			for (int i = 0; i < hash.length; ++i)
				if ((0xFF & hash[i]) < 16) {
					hexString.append("0" + Integer.toHexString(0xFF & hash[i]));
				} else
					hexString.append(Integer.toHexString(0xFF & hash[i]));
		} catch (NoSuchAlgorithmException e) {
			throw new BusinessException("" + e);
		}

		return hexString.toString();
	}
}