package com.as.export;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.as.operate.Model;
import com.demo.entity.ArticleEntity;


public class Export {

	 
	/*
	 * select t1.* from word t1 ,relation_word_nature t2 where t1.id = t2.word_id and t2.nature_id=3 and t1.id > 3000 and t1.type = 0
	 */
	 /**  
     * @功能：手工构建一个简单格式的Excel 
     */  
      
    public static void saveResultList(String filePath,List<Model> list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("标题");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("内容");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("事件");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);  
        cell.setCellValue("文章摘要");  
        cell.setCellStyle(style);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        int i = 0;
        for (Model model : list) {
        	row = sheet.createRow((int) i + 1);  
    		row.createCell((short) 0).setCellValue(model.getTitle());
    		row.createCell((short) 1).setCellValue(model.getContent());
    		row.createCell((short) 2).setCellValue(model.getEvent());
    		row.createCell((short) 3).setCellValue(model.getTextSummary());
    		i++;
        }
       
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
  
    public static void saveResultSim(String filePath,Map<Integer,Set<String>> map,Map<String,String> articleMap_keyId){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("ID");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("文章编号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("文章内容");  
        cell.setCellStyle(style);  
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        int i = 0;
        for(Map.Entry<Integer, Set<String>> entry : map.entrySet()){
        	Set<String> value = entry.getValue();
        	if(!value.isEmpty()){
        		for (String string : value) {
        			row = sheet.createRow((int) i + 1);  
        			row.createCell((short) 0).setCellValue(entry.getKey());
        			row.createCell((short) 1).setCellValue(string);
        			row.createCell((short) 2).setCellValue(articleMap_keyId.get(string));
        			i++;
				}
        	}
        }
       
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
  
    public static void saveResultDataTest(String filePath,List<ArticleEntity> list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("ID");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("文章标题");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("isShow");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("simTitle");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 4);  
        cell.setCellValue("simId");  
        cell.setCellStyle(style);  
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        int i = 0;
        for(ArticleEntity entity : list){
			row = sheet.createRow((int) i + 1);  
			row.createCell((short) 0).setCellValue(entity.getId());
			row.createCell((short) 1).setCellValue(entity.getTitle());
			row.createCell((short) 2).setCellValue(entity.getIsShow());
			row.createCell((short) 3).setCellValue(entity.getSimId().toString());
			row.createCell((short) 4).setCellValue(entity.getPublishTime());
			i++;
        }
       
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
  
    
    public static void saveResultSim_All(String filePath,Map<Integer,Set<String>> map,Map<String,String> articleMap,Map<Integer,String> eventMap,Map<Integer,String> summaryMap){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("文章编号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("文章内容");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("事件名称");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);  
        cell.setCellValue("文章摘要");  
        cell.setCellStyle(style);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        int i = 0;
        for(Map.Entry<Integer, Set<String>> entry : map.entrySet()){
        	Set<String> value = entry.getValue();
        	if(!value.isEmpty()){
        		for (String string : value) {
        			row = sheet.createRow((int) i + 1);  
        			row.createCell((short) 0).setCellValue(string);
        			row.createCell((short) 1).setCellValue(articleMap.get(string));
        			row.createCell((short) 2).setCellValue(eventMap.get(entry.getKey()));
        			row.createCell((short) 3).setCellValue(summaryMap.get(entry.getKey()));
        			i++;
				}
        	}
        }
       
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    
    public static void saveResultMap(String filePath,Map<String,Map<String,List<Model>>> list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("事件");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("事件摘要");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("标题");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);  
        cell.setCellValue("内容");  
        cell.setCellStyle(style);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        int i = 0;
        for (Map.Entry<String, Map<String,List<Model>>> model : list.entrySet()) {
    		Map<String, List<Model>> value = model.getValue();
    		Iterator<Entry<String, List<Model>>> iterator = value.entrySet().iterator();
    		Entry<String, List<Model>> next = iterator.next();
    		List<Model> value2 = next.getValue();
    		int a = 0;
    		for (Model model2 : value2) {
    			row = sheet.createRow((int) i + 1);  
    			if(a == 0){
    				row.createCell((short) 0).setCellValue(model.getKey());
    				row.createCell((short) 1).setCellValue(next.getKey());
    			}else{
    				row.createCell((short) 0).setCellValue("");
    				row.createCell((short) 1).setCellValue("");
    			}
    			a++;
    			row.createCell((short) 2).setCellValue(model2.getTitle());
    			row.createCell((short) 3).setCellValue(model2.getContent());
    			i++;
			}
        }
       
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    public static void saveResultJD(String filePath,List<Model> list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("一、功能1 新词发现结果");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("文章编号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("文章内容");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("新词发现");  
        cell.setCellStyle(style);  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        int i = 0;
		for (Model model2 : list) {
			row = sheet.createRow((int) i + 1);  
			row.createCell((short) 0).setCellValue(model2.getId());
			row.createCell((short) 1).setCellValue(model2.getContent());
			row.createCell((short) 2).setCellValue(model2.getNewWord());
			i++;
		}
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    public static void saveResultJD2(String filePath,List<Model> list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("二、功能2 主体情感词结果");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("文章编号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("文章内容");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("事件名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("文章摘要");  
        cell.setCellStyle(style);  
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        int i = 0;
		for (Model model2 : list) {
			row = sheet.createRow((int) i + 1);  
			row.createCell((short) 0).setCellValue(model2.getId());
			row.createCell((short) 1).setCellValue(model2.getContent());
			row.createCell((short) 2).setCellValue(model2.getEvent());
			row.createCell((short) 3).setCellValue(model2.getTextSummary());
			i++;
		}
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    public static void saveResultJD3(String filePath,List<Model> list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("三、功能3 事件聚合结果");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("文章编号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("文章内容");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("结果1");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("结果2");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 4);  
        cell.setCellValue("结果3");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);  
        cell.setCellValue("结果4");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);  
        cell.setCellValue("结果5");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);  
        cell.setCellValue("结果6");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);  
        cell.setCellValue("结果7");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);  
        cell.setCellValue("结果8");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);  
        cell.setCellValue("结果9");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);  
        cell.setCellValue("结果10");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 12);  
        cell.setCellValue("结果11");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 13);  
        cell.setCellValue("结果12");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 14);  
        cell.setCellValue("结果13");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 15);  
        cell.setCellValue("结果14");  
        cell.setCellStyle(style);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        int i = 0;
		for (Model model2 : list) {
			row = sheet.createRow((int) i + 1);  
			row.createCell((short) 0).setCellValue(model2.getId());
			row.createCell((short) 1).setCellValue(model2.getContent());
			Map<String, String> itemColls = model2.getItemColls();
			if(itemColls != null && !itemColls.isEmpty()){
				int j = 2;
				for(Map.Entry<String, String> entry : itemColls.entrySet()){
					row.createCell((short) j).setCellValue(entry.getKey()+"："+entry.getValue());
					j++;
				}
			}
			i++;
		}
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    public static void saveResult(String filePath,Map<String,Double> map){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("负面词词翼数据");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("关键词");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("权重");  
        cell.setCellStyle(style);  
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        int i = 0;
        for(Map.Entry<String, Double> entry : map.entrySet()){
        	if(i < 25){
        		row = sheet.createRow((int) i + 1);  
        		// 第四步，创建单元格，并设置值  
        		row.createCell((short) 0).setCellValue(entry.getKey());  
        		row.createCell((short) 1).setCellValue(entry.getValue());  
        		i++;
        	}else{
        		continue;
        	}
        }
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath);  
            wb.write(fout);  
            fout.close();  
            System.out.println("success");
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    /*
	 * 读取文件内容
	 */
	 public static List<String> readTxtFile(String filePath){
	        try {
	        	List<String> list = new ArrayList<String>();
                String encoding="utf-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    
                    String line=null;
                    while((line=bufferedReader.readLine()) != null){
                    	list.add(line.trim());
                    }
                    read.close();
                    
                    return list;
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
			return null;
	    }
}
