package com.sd.farmework.common.util;
//package com.spdbccc.dreamcard.common.util;
//import java.applet.Applet;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.MediaTracker;
//import java.awt.Toolkit;
//import java.awt.Transparency;
//import java.awt.color.ColorSpace;
//import java.awt.color.ICC_ColorSpace;
//import java.awt.color.ICC_Profile;
//import java.awt.geom.AffineTransform;
//import java.awt.image.AffineTransformOp;
//import java.awt.image.BufferedImage;
//import java.awt.image.ColorConvertOp;
//import java.awt.image.MemoryImageSource;
//import java.awt.image.Raster;
//import java.awt.image.WritableRaster;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.imageio.IIOImage;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageReader;
//import javax.imageio.ImageWriteParam;
//import javax.imageio.ImageWriter;
//import javax.imageio.stream.FileImageOutputStream;
//import javax.imageio.stream.ImageInputStream;
//import javax.imageio.stream.ImageOutputStream;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//
///**
// * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on 8/10/2013
// */
//public class ImageUtil {
//	private static Log log = LogFactory.getLog(ImageUtil.class);
//	
//	/**
//	 * Check if is an image or not.
//	 * @param imagePath full file path or file name with suffix
//	 * @return
//	 */
//	public static boolean isImage(String imagePath) {
//		String suffix = StringUtil.getSuffix(imagePath);
//		suffix = StringUtil.isBlank(suffix) ? suffix : suffix.substring(1);
//		String[] suffixes = ImageIO.getWriterFileSuffixes();
//		for (int i = 0; i < suffixes.length; i++) {
//			if(suffixes[i].equalsIgnoreCase(suffix)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * Check if is an bmp image or not.
//	 * @param imagePath full file path or file name with suffix
//	 * @return
//	 */
//	public static boolean isBmp(String imagePath) {
//		String suffix = StringUtil.getSuffix(imagePath);
//		suffix = StringUtil.isBlank(suffix) ? suffix : suffix.substring(1);
//		if("bmp".equalsIgnoreCase(suffix)) {
//			return true;
//		}
//		return false;
//	}
//    
//	
//	
//	/**
//	 * Zoom in/out original image proportionally with default quality 0.8
//	 * @param imagePath
//	 * @param destPath
//	 * @param outputWidth
//	 * @param outputHeight
//	 */
//	public static void zoom(String imagePath, String destPath, int outputWidth, int outputHeight) {
//		zoom(imagePath, destPath, outputWidth, outputHeight, 0.8f);
//	}
//
//	/**
//	 * Zoom in/out original image proportionally
//	 * @param imagePath
//	 * @param destPath
//	 * @param outputWidth
//	 * @param outputHeight
//	 * @param quality maximum value is 1
//	 */
//	public static void zoom(String imagePath, String destPath, int outputWidth, int outputHeight, float quality) {
//		FileImageOutputStream destOut = null;
//		
//		try {
//			ICC_Profile profile = null;
//			
//			File srcFile = new File(imagePath);
//			Image img = null;
//			ImageInfo imageInfo = null;
//			try {
//				imageInfo = Imaging.getImageInfo(srcFile);
//
//				if (imageInfo.getColorType() == ImageInfo.COLOR_TYPE_CMYK || imageInfo.getColorType() == ImageInfo.COLOR_TYPE_YCCK) {
//					profile = Imaging.getICCProfile(srcFile);
//
//					ImageInputStream stream = ImageIO.createImageInputStream(srcFile);
//					Iterator<ImageReader> iter = ImageIO.getImageReaders(stream);
//
//					WritableRaster raster = null;
//					if (iter.hasNext()) {
//						ImageReader reader = iter.next();
//						reader.setInput(stream);
//						raster = (WritableRaster) reader.readRaster(0, null);
//					}
//
//					if (raster == null) {
//						throw new AppException("Raster is null for file: " + imagePath);
//					}
//
//					if (imageInfo.getColorType() == ImageInfo.COLOR_TYPE_YCCK) {
//						convertYcckToCmyk(raster);
//					}
//
//					if (checkAdobeMarker(srcFile)) {
//						convertInvertedColors(raster);
//					}
//
//					img = convertCmykToRgb(raster, profile);
//				} else {
//					Toolkit tk = Toolkit.getDefaultToolkit();
//
//					if (isBmp(imagePath)) {
//						img = read(new FileInputStream(srcFile));
//					} else {
//						img = tk.getImage(imagePath);
//					}
//				}
//			} catch (Exception ex) {
//				Toolkit tk = Toolkit.getDefaultToolkit();
//
//				if (isBmp(imagePath)) {
//					img = read(new FileInputStream(srcFile));
//				} else {
//					img = tk.getImage(imagePath);
//				}
//			}
//			
//			zoom(img, destPath, outputWidth, outputHeight, quality);
//		} catch (Exception e) {
//			throw new AppException("Zoom file error, from \"" + imagePath + "\" to \"" + destPath + "\"", e);
//		} finally {
//			if(destOut != null) {
//				try {
//					destOut.close();
//				} catch (IOException e) {
//					throw new AppException("Close FileOutputStream for zoomming file error, from \"" + imagePath + "\" to \"" + destPath + "\"", e);
//				}
//			}
//		}
//	}
//	
//	/**
//	 * Zoom in/out original image proportionally
//	 * @param imagePath
//	 * @param destPath
//	 * @param outputWidth
//	 * @param outputHeight
//	 * @param quality maximum value is 1
//	 */
//	public static void zoom(Image img, String destPath, int outputWidth, int outputHeight, float quality) {
//		FileImageOutputStream destOut = null;
//		
//		try {
//			Applet app = new Applet();
//			MediaTracker mt = new MediaTracker(app);
//			
//			mt.addImage(img, 0);
//			mt.waitForID(0);
//			
//			if(img.getWidth(null) == -1) {
//				throw new AppException("There is no width in image file: " + img);
//			}
//			
//			int newWidth = 0;
//			int newHeight = 0;
//			
//			double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;   
//			double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;   
//			   
//			double rate = rate1 > rate2 ? rate1 : rate2;   
//			newWidth = (int) (((double) img.getWidth(null)) / rate);   
//			newHeight = (int) (((double) img.getHeight(null)) / rate); 
//			
//			BufferedImage buffImg = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
//			
//			Graphics g = buffImg.createGraphics();
//			g.setColor(Color.white);  
//			g.fillRect(0, 0, newWidth, newHeight);
//			g.drawImage(img, 0, 0, newWidth, newHeight, null);  
//			g.dispose();
//			
//			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
//			ImageWriter writer = (ImageWriter)iter.next();
//			ImageWriteParam iwp = writer.getDefaultWriteParam();
//			try {
//				iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//				iwp.setCompressionQuality(quality);
//			} catch (Exception e) {
//				log.debug("Image \"" + img + "\" is not supported to compress.");
//			}
//			
//			File destFile = new File(destPath);
//			if(destFile.exists()) {
//				destFile.delete();
//			}
//			FileUtil.mkdirsForParents(destFile);
//			destOut = new FileImageOutputStream(destFile);
//			writer.setOutput(destOut);
//			writer.write(null, new IIOImage(buffImg, null, null), iwp);
//			writer.dispose();
//		} catch (Exception e) {
//			throw new AppException("Zoom file error, from \"" + img + "\" to \"" + destPath + "\"", e);
//		} finally {
//			if(destOut != null) {
//				try {
//					destOut.close();
//				} catch (IOException e) {
//					throw new AppException("Close FileOutputStream for zoomming file error, from \"" + img + "\" to \"" + destPath + "\"", e);
//				}
//			}
//		}
//	}
//	
//	private static boolean checkAdobeMarker(File file) throws IOException, ImageReadException {
//		boolean result = false;
//        JpegImageParser parser = new JpegImageParser();
//        ByteSource byteSource = new ByteSourceFile(file);
//        @SuppressWarnings("rawtypes")
//        List segments = parser.readSegments(byteSource, new int[] { 0xffee }, true);
//        if (segments != null && segments.size() >= 1) {
//        	App14Segment app14Segment = (App14Segment)segments.get(0);
//            byte[] data = app14Segment.bytes;
//            if (data.length >= 12 && data[0] == 'A' && data[1] == 'd' && data[2] == 'o' && data[3] == 'b' && data[4] == 'e')
//            {
//                result = true;
//            }
//        }
//        
//        return result;
//    }
//	
//	public static void convertYcckToCmyk(WritableRaster raster) {
//        int height = raster.getHeight();
//        int width = raster.getWidth();
//        int stride = width * 4;
//        int[] pixelRow = new int[stride];
//        for (int h = 0; h < height; h++) {
//            raster.getPixels(0, h, width, 1, pixelRow);
//
//            for (int x = 0; x < stride; x += 4) {
//                int y = pixelRow[x];
//                int cb = pixelRow[x + 1];
//                int cr = pixelRow[x + 2];
//
//                int c = (int) (y + 1.402 * cr - 178.956);
//                int m = (int) (y - 0.34414 * cb - 0.71414 * cr + 135.95984);
//                y = (int) (y + 1.772 * cb - 226.316);
//
//                if (c < 0) c = 0; else if (c > 255) c = 255;
//                if (m < 0) m = 0; else if (m > 255) m = 255;
//                if (y < 0) y = 0; else if (y > 255) y = 255;
//
//                pixelRow[x] = 255 - c;
//                pixelRow[x + 1] = 255 - m;
//                pixelRow[x + 2] = 255 - y;
//            }
//
//            raster.setPixels(0, h, width, 1, pixelRow);
//        }
//    }
//	
//	private static void convertInvertedColors(WritableRaster raster) {
//        int height = raster.getHeight();
//        int width = raster.getWidth();
//        int stride = width * 4;
//        int[] pixelRow = new int[stride];
//        for (int h = 0; h < height; h++) {
//            raster.getPixels(0, h, width, 1, pixelRow);
//            for (int x = 0; x < stride; x++)
//                pixelRow[x] = 255 - pixelRow[x];
//            raster.setPixels(0, h, width, 1, pixelRow);
//        }
//    }
//	
//	private static BufferedImage convertCmykToRgb(Raster cmykRaster, ICC_Profile cmykProfile) throws IOException {
//        if (cmykProfile == null) {
//            cmykProfile = ICC_Profile.getInstance(Imaging.class.getResourceAsStream("/USWebCoatedSWOP.icc"));
//        }
//
//        if (cmykProfile.getProfileClass() != ICC_Profile.CLASS_DISPLAY) {
//            byte[] profileData = cmykProfile.getData();
//
//            if (profileData[ICC_Profile.icHdrRenderingIntent] == ICC_Profile.icPerceptual) {
//                intToBigEndian(ICC_Profile.icSigDisplayClass, profileData, ICC_Profile.icHdrDeviceClass); // Header is first
//
//                cmykProfile = ICC_Profile.getInstance(profileData);
//            }
//        }
//
//        ICC_ColorSpace cmykCS = new ICC_ColorSpace(cmykProfile);
//        BufferedImage rgbImage = new BufferedImage(cmykRaster.getWidth(), cmykRaster.getHeight(), BufferedImage.TYPE_INT_RGB);
//        WritableRaster rgbRaster = rgbImage.getRaster();
//        ColorSpace rgbCS = rgbImage.getColorModel().getColorSpace();
//        ColorConvertOp cmykToRgb = new ColorConvertOp(cmykCS, rgbCS, null);
//        cmykToRgb.filter(cmykRaster, rgbRaster);
//        return rgbImage;
//    }
//	
//	private static void intToBigEndian(int value, byte[] array, int index) {
//	    array[index]   = (byte) (value >> 24);
//	    array[index+1] = (byte) (value >> 16);
//	    array[index+2] = (byte) (value >>  8);
//	    array[index+3] = (byte) (value);
//	}
//
//	/**
//	 * @param imagePath
//	 * @param degree
//	 * @return
//	 */
//	public static InputStream rotate(String imagePath, int degree) {
//		InputStream  inputStream = null;
//		try {
//			BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
//			
//	        int iw = image.getWidth();
//	        int ih = image.getHeight();
//	        int w = 0;
//	        int h = 0;
//	        int x = 0;
//	        int y = 0;
//	        degree = degree % 360;  
//	        if (degree < 0) {
//	            degree = 360 + degree;
//	        }
//	        double ang = Math.toRadians(degree);
//	  
//	        if (degree == 180 || degree == 0 || degree == 360) {  
//	            w = iw;  
//	            h = ih;  
//	        } else if (degree == 90 || degree == 270) {  
//	            w = ih;  
//	            h = iw;  
//	        } else {  
//	            int d = iw + ih;  
//	            w = (int) (d * Math.abs(Math.cos(ang)));  
//	            h = (int) (d * Math.abs(Math.sin(ang)));  
//	        }  
//	  
//	        x = (w / 2) - (iw / 2);//ȷ��ԭ������  
//	        y = (h / 2) - (ih / 2);  
//	        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());  
//	        Graphics2D gs = (Graphics2D)rotatedImage.getGraphics();  
//	        rotatedImage  = gs.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);  
//	          
//	        AffineTransform at = new AffineTransform();  
//	        at.rotate(ang, w / 2, h / 2);//��תͼ��  
//	        at.translate(x, y);  
//	        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);  
//	        op.filter(image, rotatedImage);  
//	        image = rotatedImage;  
//	
//	        ByteArrayOutputStream  byteOut= new ByteArrayOutputStream();  
//	        ImageOutputStream iamgeOut = ImageIO.createImageOutputStream(byteOut);  
//	          
//	        ImageIO.write(image, "png", iamgeOut);
//	        inputStream = new ByteArrayInputStream(byteOut.toByteArray());  
//		} catch(Exception ex) {
//			throw new AppException("Rotate image \"" + imagePath + "\" error.", ex);
//		}
//	        
//        return inputStream;  
//    }
//	
//	/**
//	 * @param imagePath
//	 * @return
//	 */
//	public static boolean isPortrait(String imagePath) {
//		boolean result = false;
//		try {
//			BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
//			
//	        int iw = image.getWidth();
//	        int ih = image.getHeight();
//	        
//	        result = iw < ih ? true : false;
//		} catch(Exception ex) {
//			throw new AppException("Method isPortrait() error. ", ex);
//		}
//		
//		return result;
//	}
//	
//	/**
//	 * @param imagePath
//	 * @return
//	 */
//	public static boolean isLandscape(String imagePath) {
//		boolean result = false;
//		try {
//			BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
//			
//	        int iw = image.getWidth();
//	        int ih = image.getHeight();
//	        
//	        result = iw > ih ? true : false;
//		} catch(Exception ex) {
//			throw new AppException("Method isPortrait() error. ", ex);
//		}
//		
//		return result;
//	}
//	
//	private static int constructInt(byte[] in, int offset) {
//		int ret = ((int) in[offset + 3] & 0xff);
//		ret = (ret << 8) | ((int) in[offset + 2] & 0xff);
//		ret = (ret << 8) | ((int) in[offset + 1] & 0xff);
//		ret = (ret << 8) | ((int) in[offset + 0] & 0xff);
//		return (ret);
//	}
//
//	private static int constructInt3(byte[] in, int offset) {
//		int ret = 0xff;
//		ret = (ret << 8) | ((int) in[offset + 2] & 0xff);
//		ret = (ret << 8) | ((int) in[offset + 1] & 0xff);
//		ret = (ret << 8) | ((int) in[offset + 0] & 0xff);
//		return (ret);
//	}
//
//	private static long constructLong(byte[] in, int offset) {
//		long ret = ((long) in[offset + 7] & 0xff);
//		ret |= (ret << 8) | ((long) in[offset + 6] & 0xff);
//		ret |= (ret << 8) | ((long) in[offset + 5] & 0xff);
//		ret |= (ret << 8) | ((long) in[offset + 4] & 0xff);
//		ret |= (ret << 8) | ((long) in[offset + 3] & 0xff);
//		ret |= (ret << 8) | ((long) in[offset + 2] & 0xff);
//		ret |= (ret << 8) | ((long) in[offset + 1] & 0xff);
//		ret |= (ret << 8) | ((long) in[offset + 0] & 0xff);
//		return (ret);
//	}
//
//	@SuppressWarnings("unused")
//	private static double constructDouble(byte[] in, int offset) {
//		long ret = constructLong(in, offset);
//		return (Double.longBitsToDouble(ret));
//	}
//
//	private static short constructShort(byte[] in, int offset) {
//		short ret = (short) ((short) in[offset + 1] & 0xff);
//		ret = (short) ((ret << 8) | (short) ((short) in[offset + 0] & 0xff));
//		return (ret);
//	}
//	
//	private static Image read(FileInputStream fs) {
//		try {
//			BitmapHeader bh = new BitmapHeader();
//			bh.read(fs);
//			if (bh.iBitcount == 24) {
//				return (readImage24(fs, bh));
//			}
//			if (bh.iBitcount == 32) {
//				return (readImage32(fs, bh));
//			}
//			fs.close();
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		return (null);
//	}
//
//	// 24λ
//	private static Image readImage24(FileInputStream fs, BitmapHeader bh)
//			throws IOException {
//		Image image;
//		if (bh.iSizeimage == 0) {
//			bh.iSizeimage = ((((bh.iWidth * bh.iBitcount) + 31) & ~31) >> 3);
//			bh.iSizeimage *= bh.iHeight;
//		}
//		int npad = (bh.iSizeimage / bh.iHeight) - bh.iWidth * 3;
//		int ndata[] = new int[bh.iHeight * bh.iWidth];
//		byte brgb[] = new byte[(bh.iWidth + npad) * 3 * bh.iHeight];
//		fs.read(brgb, 0, (bh.iWidth + npad) * 3 * bh.iHeight);
//		int nindex = 0;
//		for (int j = 0; j < bh.iHeight; j++) {
//			for (int i = 0; i < bh.iWidth; i++) {
//				ndata[bh.iWidth * (bh.iHeight - j - 1) + i] = constructInt3(
//						brgb, nindex);
//				nindex += 3;
//			}
//			nindex += npad;
//		}
//		image = Toolkit.getDefaultToolkit().createImage(
//				new MemoryImageSource(bh.iWidth, bh.iHeight, ndata, 0, bh.iWidth));
//		fs.close();
//		return (image);
//	}
//
//	// 32λ
//	private static Image readImage32(FileInputStream fs, BitmapHeader bh)
//			throws IOException {
//		Image image;
//		int ndata[] = new int[bh.iHeight * bh.iWidth];
//		byte brgb[] = new byte[bh.iWidth * 4 * bh.iHeight];
//		fs.read(brgb, 0, bh.iWidth * 4 * bh.iHeight);
//		int nindex = 0;
//		for (int j = 0; j < bh.iHeight; j++) {
//			for (int i = 0; i < bh.iWidth; i++) {
//				ndata[bh.iWidth * (bh.iHeight - j - 1) + i] = constructInt3(
//						brgb, nindex);
//				nindex += 4;
//			}
//		}
//		image = Toolkit.getDefaultToolkit().createImage(
//				new MemoryImageSource(bh.iWidth, bh.iHeight, ndata, 0,
//						bh.iWidth));
//		fs.close();
//		return (image);
//	}
//
//	@SuppressWarnings("unused")
//	private static class BitmapHeader {
//		public int iSize, ibiSize, iWidth, iHeight, iPlanes, iBitcount,
//				iCompression, iSizeimage, iXpm, iYpm, iClrused, iClrimp;
//
//		// ��ȡbmp�ļ�ͷ��Ϣ
//		public void read(FileInputStream fs) throws IOException {
//			final int bflen = 14;
//			byte bf[] = new byte[bflen];
//			fs.read(bf, 0, bflen);
//			final int bilen = 40;
//			byte bi[] = new byte[bilen];
//			fs.read(bi, 0, bilen);
//			iSize = constructInt(bf, 2);
//			ibiSize = constructInt(bi, 2);
//			iWidth = constructInt(bi, 4);
//			iHeight = constructInt(bi, 8);
//			iPlanes = constructShort(bi, 12);
//			iBitcount = constructShort(bi, 14);
//			iCompression = constructInt(bi, 16);
//			iSizeimage = constructInt(bi, 20);
//			iXpm = constructInt(bi, 24);
//			iYpm = constructInt(bi, 28);
//			iClrused = constructInt(bi, 32);
//			iClrimp = constructInt(bi, 36);
//		}
//	}
//
//	
//}
