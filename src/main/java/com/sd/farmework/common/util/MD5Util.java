package com.sd.farmework.common.util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.util.DigestUtils;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on 8/25/2013
 */
public final class MD5Util {

	 

	/**
	 * get md5 string from file
	 * 
	 * @param file
	 * @return
	 */
	public static String getMD5(File file) {

		String result = null;
		if (file.exists() && file.isFile()) {

			BufferedInputStream in = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				in = new BufferedInputStream(new FileInputStream(file));
				byte[] bys = new byte[1024];
				int len = 0;
				while ((len = in.read(bys)) != -1) {
					md.update(bys, 0, len);
				}

				BigInteger bitInt = new BigInteger(1, md.digest());
				result = bitInt.toString(16);
			} catch (Exception ex) {
				throw new IllegalStateException("Got error when getting MD5 for file: " + file.getAbsolutePath(), ex);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException ex) {
						throw new IllegalStateException("Close InputStream error when getting MD5 for file: " + file.getAbsolutePath(), ex);
					}
				}
			}

		}
		return result;
	}

	/**
	 * get md5 string from file
	 * 
	 * @param file
	 * @return
	 */
	public static String getMD5(String filePath) {

		return getMD5(new File(filePath));
	}
}
