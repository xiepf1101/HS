package com.demo.operate;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelOperate {
    
	public static void main(String[] args) {
		try {
			readArticleList("D:/新建文件夹/JD/舆情/数据/其他/新建文件夹");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readArticleList(String filePath) throws Exception{
		
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				String[][] result = getData(file, 1);
		        int rowLength = result.length;
		        for(int i=0;i<=rowLength-1;i++) {
		        	String content = result[i][2]+";"+result[i][3];
		        	content = content.replaceAll("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?","");
	                content = content.replaceAll("</?[a-zA-Z]+[^><]*>","");
		        	appendContext2File("D:/新建文件夹/JD/舆情/数据/其他/article5.txt", content+"\n");
		        }
			}else if(file.isDirectory()){
				File[] listFiles = file.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					String[][] result = getData(listFiles[i], 1);
			        int rowLength = result.length;
			        for(int j=0;j<=rowLength-1;j++) {
			        	String content = result[j][2]+";"+result[j][3];
			        	content = content.replaceAll("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?","");
		                content = content.replaceAll("</?[a-zA-Z]+[^><]*>","");
			        	appendContext2File("D:/新建文件夹/JD/舆情/数据/其他/article5.txt", content+"\n");
			        }
				}
			}
		}
    }	   
    
	public static void readArticleList_p(String filePath,String srcFilePath) throws Exception{
		
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				String[][] result = getData(file, 1);
		        int rowLength = result.length;
		        for(int i=0;i<=rowLength-1;i++) {
		        	String content = result[i][3]+";"+result[i][4];
		        	content = content.replaceAll("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?","");
	                content = content.replaceAll("</?[a-zA-Z]+[^><]*>","");
		        	appendContext2File(srcFilePath, content+"\n");
		        }
			}else if(file.isDirectory()){
				File[] listFiles = file.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					String[][] result = getData(listFiles[i], 1);
			        int rowLength = result.length;
			        for(int j=0;j<=rowLength-1;j++) {
			        	String content = result[j][3]+";"+result[j][4];
			        	content = content.replaceAll("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?","");
		                content = content.replaceAll("</?[a-zA-Z]+[^><]*>","");
			        	appendContext2File(srcFilePath, content+"\n");
			        }
				}
			}
		}
    }	   
    
	
	public static List<String> readArticleList_(String filePath) throws Exception{
		List<String> list = new ArrayList<String>();
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				String[][] result = getData(file, 1);
		        int rowLength = result.length;
		        for(int i=0;i<=rowLength-1;i++) {
		        	String content = result[i][2]+";"+result[i][3];
		        	content = content.replaceAll("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?","");
	                content = content.replaceAll("</?[a-zA-Z]+[^><]*>","");
		        	list.add(content);
		        }
			}else if(file.isDirectory()){
				File[] listFiles = file.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					String[][] result = getData(listFiles[i], 1);
			        int rowLength = result.length;
			        for(int j=0;j<=rowLength-1;j++) {
			        	String content = result[j][2]+";"+result[j][3];
			        	content = content.replaceAll("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?","");
		                content = content.replaceAll("</?[a-zA-Z]+[^><]*>","");
		                list.add(content);
			        }
				}
			}
		}
		return list;
    }	   
    
	
	public static List<Model> readArticleListTest(String filePath) throws Exception{
		List<Model> modelList = new ArrayList<Model>();
		Model model = null;
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				String[][] result = getData(file, 1);
		        int rowLength = result.length;
		        for(int i=0;i<=rowLength-1;i++) {
		        	model = new Model();
		        	model.setSrcEvent(result[i][0]);
		        	model.setTitle(result[i][3]);
		        	model.setContent(result[i][4]);
		        	model.setUrl(result[i][5]);
		        	modelList.add(model);
		        }
			}else if(file.isDirectory()){
				File[] listFiles = file.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					String[][] result = getData(listFiles[i], 1);
			        int rowLength = result.length;
			        for(int j=0;j<=rowLength-1;j++) {
			        	model = new Model();
			        	model.setSrcEvent(result[i][0]);
			        	model.setTitle(result[i][3]);
			        	model.setContent(result[i][4]);
			        	model.setUrl(result[i][5]);
			        	modelList.add(model);
			        }
				}
			}
		}
		return modelList;
    }
	
	public static List<Model> readArticleListTestJD(String filePath) throws Exception{
		List<Model> modelList = new ArrayList<Model>();
		Model model = null;
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				String[][] result = getData(file, 1);
		        int rowLength = result.length;
		        for(int i=0;i<=rowLength-1;i++) {
		        	model = new Model();
		        	model.setId(result[i][0]);
		        	String string = result[i][1];
		        	String article = string.replaceAll("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?","");
	                article = article.replaceAll("</?[a-zA-Z]+[^><]*>","");
		        	article = article.replaceAll("\\d{4}\\-\\d{2}-\\d{2}", "");
	                model.setContent(article);
		        	modelList.add(model);
		        }
			}else if(file.isDirectory()){
				File[] listFiles = file.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					String[][] result = getData(listFiles[i], 1);
			        int rowLength = result.length;
			        for(int j=0;j<=rowLength-1;j++) {
			        	model = new Model();
			        	model.setId(result[i][0]);
			        	String string = result[i][1];
			        	String article = string.replaceAll("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?","");
		                article = article.replaceAll("</?[a-zA-Z]+[^><]*>","");
		                article = article.replaceAll("\\d{4}\\-\\d{2}-\\d{2}", "");
		                model.setContent(article);
			        	modelList.add(model);
			        }
				}
			}
		}
		return modelList;
    }
	
	public static List<Model> readArticleListTestJD1(String filePath,int sheetIndex) throws Exception{
		List<Model> modelList = new ArrayList<Model>();
		Model model = null;
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				String[][] result = getDatabySheet(file, 1,sheetIndex);
		        int rowLength = result.length;
		        for(int i=0;i<=rowLength-1;i++) {
		        	model = new Model();
		        	model.setId(result[i][0]);
		        	model.setContent(result[i][1]);
		        	modelList.add(model);
		        }
			}else if(file.isDirectory()){
				File[] listFiles = file.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					String[][] result = getDatabySheet(file, 1,sheetIndex);
			        int rowLength = result.length;
			        for(int j=0;j<=rowLength-1;j++) {
			        	model = new Model();
			        	model.setId(result[i][0]);
			        	model.setContent(result[i][1]);
			        	modelList.add(model);
			        }
				}
			}
		}
		return modelList;
    }
	public static Map<String,Set<String>> readWordTestJD(String filePath) throws Exception{
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		Set<String> itemSet = new HashSet<String>();
		Set<String> categorySET = new HashSet<String>();
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				String[][] result = getData(file, 1);
		        int rowLength = result.length;
		        for(int i=0;i<=rowLength-1;i++) {
		        	itemSet.add(result[i][0].trim());
		        	categorySET.add(result[i][1].trim());
		        }
			}
		}
		map.put("item", itemSet);
		map.put("category", categorySET);
		return map;
    }
	
	public static Map<String,Set<String>> readWordTestJD1(String filePath,int sheetIndex) throws Exception{
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		Set<String> itemSet = new HashSet<String>();
		Set<String> categorySET = new HashSet<String>();
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				String[][] result = getDatabySheet(file, 1, sheetIndex);
		        int rowLength = result.length;
		        for(int i=0;i<=rowLength-1;i++) {
		        	itemSet.add(result[i][0].trim());
		        	categorySET.add(result[i][1].trim());
		        }
			}
		}
		map.put("item", itemSet);
		map.put("category", categorySET);
		return map;
    }
	
    /* 
	 * 向文本文件中追加内容
	 */
	public static void appendContext2File(String filePath,String context){
		if(filePath != null && !filePath.equals("")){
			File file = new File(filePath);
			//判断文件是否存在
			if(!file.exists()){
				try {
					//创建文件
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 BufferedWriter out = null;     
	        try {     
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));     
	            out.write(context);     
	        } catch (Exception e) {     
	            e.printStackTrace();     
	        } finally {     
	            try {     
	                if(out != null){  
	                    out.close();     
	                }  
	            } catch (IOException e) {     
	                e.printStackTrace();     
	            }     
	        }     
		}
	} 
    
    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * @param file 读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
	public static String[][] getData(File file, int ignoreRows)
           throws FileNotFoundException, IOException {
       List<String[]> result = new ArrayList<String[]>();
       int rowSize = 0;
       BufferedInputStream in = new BufferedInputStream(new FileInputStream(
              file));
       // 打开HSSFWorkbook
       POIFSFileSystem fs = new POIFSFileSystem(in);
       HSSFWorkbook wb = new HSSFWorkbook(fs);
       HSSFCell cell = null;
       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
           HSSFSheet st = wb.getSheetAt(sheetIndex);
           // 第一行为标题，不取
           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
              HSSFRow row = st.getRow(rowIndex);
              if (row == null) {
                  continue;
              }
              int tempRowSize = row.getLastCellNum() + 1;
              if (tempRowSize > rowSize) {
                  rowSize = tempRowSize;
              }
              String[] values = new String[rowSize];
              Arrays.fill(values, "");
              boolean hasValue = false;
              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                  String value = "";
                  cell = row.getCell(columnIndex);
                  if (cell != null && !cell.toString().contains("-ccf") && !cell.toString().contains("--我觉得我可以买辆13年的535li。。。") && !cell.toString().contains("------------版主删了吧")) {
                	  // 注意：一定要设成这个，否则可能会出现乱码
                   //  cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                     switch (cell.getCellType()) {
                     case HSSFCell.CELL_TYPE_STRING:
                         value = cell.getStringCellValue();
                         break;
                     case HSSFCell.CELL_TYPE_NUMERIC:
                         if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd")
                                       .format(date);
                            } else {
                                value = "";
                            }
                         } else {
                            value = new DecimalFormat("#.00").format(cell
                                   .getNumericCellValue());
                         }
                         break;
                     case HSSFCell.CELL_TYPE_FORMULA:
                         // 导入时如果为公式生成的数据则无值
                    	 System.out.println(cell.toString());
                    	 if(cell.toString().contains("-") || cell.toString().contains("。=")){
                    		 value = cell.toString().replaceAll("-", "");
                    		 value = cell.toString().replaceAll("。=", "");
                    	 }else if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                         } else {
                            value = cell.getNumericCellValue() + "";
                         }
                         break;
                     case HSSFCell.CELL_TYPE_BLANK:
                         break;
                     case HSSFCell.CELL_TYPE_ERROR:
                         value = "";
                         break;
                     case HSSFCell.CELL_TYPE_BOOLEAN:
                         value = (cell.getBooleanCellValue() == true ? "Y"
                                : "N");
                         break;
                     default:
                         value = "";
                     }
                  }
                  if (columnIndex == 0 && value.trim().equals("")) {
                     break;
                  }
                  values[columnIndex] = rightTrim(value);
                  hasValue = true;
              }

              if (hasValue) {
                  result.add(values);
              }
           }
       }
       in.close();
       String[][] returnArray = new String[result.size()][rowSize];
       for (int i = 0; i < returnArray.length; i++) {
           returnArray[i] = (String[]) result.get(i);
       }
       return returnArray;
    }

    @SuppressWarnings("deprecation")
	public static String[][] getDatabySheet(File file, int ignoreRows,int sheetIndex)
           throws FileNotFoundException, IOException {
       List<String[]> result = new ArrayList<String[]>();
       int rowSize = 0;
       BufferedInputStream in = new BufferedInputStream(new FileInputStream(
              file));
       // 打开HSSFWorkbook
       POIFSFileSystem fs = new POIFSFileSystem(in);
       HSSFWorkbook wb = new HSSFWorkbook(fs);
       HSSFCell cell = null;
       HSSFSheet st = wb.getSheetAt(sheetIndex);
       // 第一行为标题，不取
       for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
          HSSFRow row = st.getRow(rowIndex);
          if (row == null) {
              continue;
          }
          int tempRowSize = row.getLastCellNum() + 1;
          if (tempRowSize > rowSize) {
              rowSize = tempRowSize;
          }
          String[] values = new String[rowSize];
          Arrays.fill(values, "");
          boolean hasValue = false;
          for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
              String value = "";
              cell = row.getCell(columnIndex);
              if (cell != null && !cell.toString().contains("-ccf") && !cell.toString().contains("--我觉得我可以买辆13年的535li。。。") && !cell.toString().contains("------------版主删了吧")) {
            	  // 注意：一定要设成这个，否则可能会出现乱码
               //  cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                 switch (cell.getCellType()) {
                 case HSSFCell.CELL_TYPE_STRING:
                     value = cell.getStringCellValue();
                     break;
                 case HSSFCell.CELL_TYPE_NUMERIC:
                     if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            value = new SimpleDateFormat("yyyy-MM-dd")
                                   .format(date);
                        } else {
                            value = "";
                        }
                     } else {
                        value = new DecimalFormat("#.00").format(cell
                               .getNumericCellValue());
                     }
                     break;
                 case HSSFCell.CELL_TYPE_FORMULA:
                     // 导入时如果为公式生成的数据则无值
                	 System.out.println(cell.toString());
                	 if(cell.toString().contains("-") || cell.toString().contains("。=")){
                		 value = cell.toString().replaceAll("-", "");
                		 value = cell.toString().replaceAll("。=", "");
                	 }else if (!cell.getStringCellValue().equals("")) {
                        value = cell.getStringCellValue();
                     } else {
                        value = cell.getNumericCellValue() + "";
                     }
                     break;
                 case HSSFCell.CELL_TYPE_BLANK:
                     break;
                 case HSSFCell.CELL_TYPE_ERROR:
                     value = "";
                     break;
                 case HSSFCell.CELL_TYPE_BOOLEAN:
                     value = (cell.getBooleanCellValue() == true ? "Y"
                            : "N");
                     break;
                 default:
                     value = "";
                 }
              }
              if (columnIndex == 0 && value.trim().equals("")) {
                 break;
              }
              values[columnIndex] = rightTrim(value);
              hasValue = true;
          }

          if (hasValue) {
              result.add(values);
          }
       }
       in.close();
       String[][] returnArray = new String[result.size()][rowSize];
       for (int i = 0; i < returnArray.length; i++) {
           returnArray[i] = (String[]) result.get(i);
       }
       return returnArray;
    }
    
    /**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
     public static String rightTrim(String str) {
       if (str == null) {
           return "";
       }
       int length = str.length();
       for (int i = length - 1; i >= 0; i--) {
           if (str.charAt(i) != 0x20) {
              break;
           }
           length--;
       }
       return str.substring(0, length);
    }
}