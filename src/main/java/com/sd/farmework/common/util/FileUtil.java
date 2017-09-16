package com.sd.farmework.common.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.channels.FileChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on 8/8/2013
 */
public class FileUtil {
	private static Log log = LogFactory.getLog(FileUtil.class);
    
	/**
	 * Get file, the dir will be created automatically if it is not exist
	 * @param filePath
	 * @return
	 */
	public static File getFile(String filePath) {
		File file = new File(filePath);
		mkdirs(file);
		return file;
	}
	
	/**
	 * Get file, the dir will be created automatically if it is not exist
	 * @param uri
	 * @return
	 */
	public static File getFile(URI uri) {
		File file = new File(uri);
		mkdirs(file);
		return file;
	}
	
	/**
	 * Create all directories
	 * @param file
	 */
	public static void mkdirs(File file) {
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * Create all directories
	 * @param filePath
	 */
	public static void mkdirs(String filePath) {
		mkdirs(new File(filePath));
	}
	
	/**
	 * Create all directories for parent
	 * @param file
	 */
	public static void mkdirsForParents(File file) {
		log.debug("----------- file.getAbsolutePath(): " + file.getAbsolutePath());
		File parent = file.getParentFile();
		if(!parent.exists()) {
			parent.mkdirs();
		}
	}
	
	/**
	 * Create all directories for parent
	 * @param filePath
	 */
	public static void mkdirsForParents(String filePath) {
		mkdirsForParents(new File(filePath));
	}
	
	/**
	 * @param relativePath
	 */
	public static void deleteUploadedFile(String relativePath,String UPLOAD_DIR) {
		String uploadDir = StringUtil.fixPathWithLastSlash(UPLOAD_DIR);
		String filePath = uploadDir + relativePath;
		File file = new File(filePath);
		if(!file.exists()) {
			log.error("Delete file failed, file path is: " + filePath);
		} else {
			file.delete();
		}
	}
	
	/**
	 * Copy file
	 * @param src
	 * @param dest
	 * @throws Exception 
	 */
	public static void copyFile(String src, String dest) throws Exception {
		copyFile(new File(src), new File(dest));
	}
	
	/**
	 * Copy file
	 * @param src
	 * @param dest
	 * @throws Exception 
	 */
	public static void copyFile(File src, File dest) throws Exception {
		copyFile(src, dest, false);
	}
	
	/**
	 * Copy file in appending approach
	 * @param src
	 * @param dest
	 * @throws Exception 
	 */
	public static void copyFileForAppend(File src, File dest) throws Exception {
		copyFile(src, dest, true);
	}
	
	private static void copyFile(File src, File dest, boolean isAppend) throws Exception {
		FileChannel srcIn = null;
		FileChannel destOut = null;
		try {
			mkdirsForParents(src);
			mkdirsForParents(dest);
			srcIn = new FileInputStream(src).getChannel();
			if(isAppend) {
				destOut = new FileOutputStream(dest, true).getChannel();
			} else {
				destOut = new FileOutputStream(dest).getChannel();
			}
			
			srcIn.transferTo(0,srcIn.size(), destOut);
			log.debug("Copy file successful, from \"" + src.getAbsolutePath() + "\" to \"" + dest.getAbsolutePath() + "\"");
		} catch (Exception e) {
			throw new Exception("Copy file error.", e);
		} finally {
			try {
				if(srcIn != null) srcIn.close();
				if(destOut != null) destOut.close();
			} catch (Exception e) {
				throw new Exception("Close resource when copying file error.", e);
			}
		}
	}

}
