package com.sd.farmework.common.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 
 * @author xsj
 *
 */
public class ExcelUtil {
	
	
	/**
	 * 生成Excel文件
	 * @param exportData
	 * 		文件内容
	 * @param map
	 *      文件头
	 * @param outPutPath
	 * 		文件的路径
	 * @param fileName
	 * 		文件的名字
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static File createExcelFile(List exportData, LinkedHashMap map, String outPutPath,
									 String fileName) {
		File excelFile = null;
		BufferedWriter excelFileOutputStream = null;
		try {
			File file = new File(outPutPath);
			if (!file.exists()) {
				file.mkdir();
			}
			//定义文件名格式并创建
			excelFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
			System.out.println("excelFile：" + excelFile);
			// UTF-8使正确读取分隔符","  
			excelFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
					excelFile), "UTF-8"), 1024);
			System.out.println("excelFileOutputStream：" + excelFileOutputStream);
			// 写入文件头部  
			for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
				java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
				excelFileOutputStream
					.write("\"" + (String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "" + "\"");
				
			}
			excelFileOutputStream.newLine();
			// 写入文件内容  
			for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
				Object row = (Object) iterator.next();
				for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
					.hasNext();) {
					java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
						.next();
					excelFileOutputStream.write((String) BeanUtils.getProperty(row,
						(String) propertyEntry.getKey()));
				}
				if (iterator.hasNext()) {
					excelFileOutputStream.newLine();
				}
			}
			excelFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				excelFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return excelFile;
	}
	public static boolean CreateXslFile(String path, String fileName,String fileType,List<List<String>> list){
	       //创建工作文档对象     
        Workbook wb = null;    
        if (fileType.equals("xls")){    
            wb = new HSSFWorkbook();    
        }else if(fileType.equals("xlsx")){    
            wb = new XSSFWorkbook();    
        }else{  
            System.out.println("您的文档格式不正确！");    
        }    
        //创建sheet对象     
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");    
        //循环写入行数据     
        for (int i = 0; i < list.size(); i++) {    
            Row row = (Row) sheet1.createRow(i);    
            //循环写入列数据     
            for (int j = 0; j < list.get(i).size(); j++) {    
                Cell cell = row.createCell(j);    
                cell.setCellValue(list.get(i).get(j));    
            }    
        }    
        //创建文件流     
        OutputStream stream = null;
        boolean f = false;
        //写入数据     
        try {
        	if(list.size()!=0){
        	 	stream = new FileOutputStream(path+fileName+"."+fileType); 
    			wb.write(stream);
    			f=true;
        	}
       
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭文件流    
			

			 try {
				 if(stream!=null){
					 stream.close();
				 }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		} 
        return f;
	}
	
	/**
	 * 删除该目录filePath下的所有文件
	 * @param filePath
	 *			文件目录路径
	 */
	public static void deleteFiles(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					files[i].delete();
				}
			}
		}
	}
	
	/**
	 * 删除单个文件
	 * @param filePath
	 *		 文件目录路径
	 * @param fileName
	 *		 文件名称
	 */
	public static void deleteFile(String filePath, String fileName) {
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					if (files[i].getName().equals(fileName)) {
						files[i].delete();
						return;
					}
				}
			}
		}
	}
	
	/**
	 * 测试数据
	 * @param args
	 */
	public static void main(String[] args) {
		//String path, String fileName,String fileType
		List topList = new ArrayList();
		topList.add("姓名：");
		topList.add("电话号码：");
		List dataList = new ArrayList();
		
		List dataList1 = new ArrayList();
		dataList1.add("1");
		dataList1.add("2");
		List dataList2 = new ArrayList();
		dataList2.add("1");
		dataList2.add("2");
		dataList.add(topList);
		dataList.add(dataList1);
		dataList.add(dataList2);
		
		System.out.println(ExcelUtil.CreateXslFile("d:/export/", "675", "xls",dataList));
	}
	
}

