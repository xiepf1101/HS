package com.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Export {

	public static void main(String[] args) throws Exception {
		saveArticle();
	}

	/*
	 * select t1.* from word t1 ,relation_word_nature t2 where t1.id =
	 * t2.word_id and t2.nature_id=3 and t1.id > 3000 and t1.type = 0
	 */

	public static void saveArticle() throws Exception {

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("工行数据");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("标题");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("作者");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("发布时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("载体");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("站点");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("url");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int j = 0; j < 1; j++) {
			// 第四步，创建单元格，并设置值
			row = sheet.createRow((int) 1);
			row.createCell((short) 0).setCellValue("测试");
			row.createCell((short) 1).setCellValue("张三");
			row.createCell((short) 2).setCellValue("20180208");
			row.createCell((short) 3).setCellValue("新闻");
			row.createCell((short) 4).setCellValue("百度");
			row.createCell((short) 5).setCellValue("baidu");
		}

		try {
			FileOutputStream fout = new FileOutputStream("D:\\X80Data.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 读取文件内容
	 */
	public static List<String> readTxtFile(String filePath) {
		try {
			List<String> list = new ArrayList<String>();
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					list.add(line.trim());
				}
				read.close();

				return list;
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return null;
	}
}
