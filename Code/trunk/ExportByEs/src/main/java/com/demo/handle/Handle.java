package com.demo.handle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.utils.DateUtils_;
import com.demo.utils.EmptyUtils;
import com.demo.utils.JSONUtils;



public class Handle {


	public static void exportToExcel() throws IOException {//String startTime, String endTime,String circle
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("舆情");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("ID");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("标题");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("作者");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("发布时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("载体");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("站点");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("url");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("摘要");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("境内外");
		cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("正负面");
		cell.setCellStyle(style);
		cell = row.createCell((short) 10);
		cell.setCellValue("语言");
		cell.setCellStyle(style);

		Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch").put("client.transport.sniff", true).build();

		Client client =   TransportClient.builder().build()

		          .addTransportAddress(new   InetSocketTransportAddress(InetAddress.getByName("localhost"),   9300));
		
				//QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("urlMain", "twitter.com"));

		/*QueryBuilder boolBuilder = QueryBuilders.filteredQuery(notBuilder,
				FilterBuilders.boolFilter().must(FilterBuilders.rangeFilter("publishTime")
						.from(DateUtils_.getBeforeDay(-90)).to(DateUtils_.getBeforeDay(0))));*/
//		QueryBuilder boolBuilder = QueryBuilders.rangeQuery("publishTime").from("2018-05-17T12:33:13.000Z").to("2018-05-17T14:33:13.000Z");//(DateUtils_.getBeforeDay(-1)).to(DateUtils_.getBeforeDay(0));
		/*QueryBuilder weiboBuilder = QueryBuilders.filteredQuery(boolBuilder,
				FilterBuilders.termFilter("carrie", "2004"));*/
		
		/*QueryBuilder weixinBuilder = QueryBuilders.filteredQuery(boolBuilder,
				FilterBuilders.termFilter("carrie", "2009"));*/
		/*QueryBuilder positiveBuilder = QueryBuilders.filteredQuery(boolBuilder,
				FilterBuilders.termFilter("isneutral", "1"));*/
		
		/*QueryBuilder weixinBuilder = QueryBuilders.filteredQuery(boolBuilder,
				FilterBuilders.termFilter("urlMain", "www.facebook.com"));*/
		
		/*QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("title", "巴中")).
				should(QueryBuilders.termQuery("content", "巴中"));
		
		/*QueryBuilder boolBuilder = QueryBuilders.filteredQuery(notBuilder,
				FilterBuilders.boolFilter().must(FilterBuilders.rangeFilter("publishTime")
						.from(DateUtils_.getBeforeDay(-15)).to(DateUtils_.getBeforeDay(0))));*/
		/*QueryBuilder positiveBuilder = QueryBuilders.filteredQuery(boolBuilder,
				FilterBuilders.termFilter("isnegative", "1"));*/
		
		/*QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("title", "土壤污染防治法")).
				should(QueryBuilders.termQuery("content", "土壤污染防治法")).should(QueryBuilders.termQuery("title", "土壤防治法")).
				should(QueryBuilders.termQuery("content", "土壤防治法")).should(QueryBuilders.termQuery("title", "土壤法")).
				should(QueryBuilders.termQuery("content", "土壤法"));*/
		
		/*QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.textPhrase("title", "土壤污染防治法")).
				should(QueryBuilders.textPhrase("content", "土壤污染防治法")).should(QueryBuilders.textPhrase("title", "土壤防治法")).
				should(QueryBuilders.textPhrase("content", "土壤防治法")).should(QueryBuilders.textPhrase("title", "土壤法")).
				should(QueryBuilders.textPhrase("content", "土壤法"));
		*/
		/*QueryBuilder queryBuilder2 = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("title", "土壤防治法")).
				should(QueryBuilders.termQuery("content", "土壤防治法"));
		
		QueryBuilder queryBuilder3 = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("title", "土壤污染")).
				should(QueryBuilders.termQuery("content", "土壤污染"));
		
		QueryBuilder queryBuilder4 = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("title", "防治法")).
				should(QueryBuilders.termQuery("content", "防治法"));
		
		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(queryBuilder1).must(queryBuilder2).must(queryBuilder3).must(queryBuilder4);
		*/
		/*QueryBuilder boolBuilder = QueryBuilders.filteredQuery(queryBuilder,
				FilterBuilders.boolFilter().must(FilterBuilders.rangeFilter("publishTime")
						.from(DateUtils_.getFromOrToConvertor("2018-08-30 00:00:00")).to(DateUtils_.getFromOrToConvertor("2018-09-24 23:59:59"))));
		*/
		//QueryBuilder boolBuilder = QueryBuilders.rangeQuery("gatherTime").from(DateUtils_.getFromOrToConvertor("2018-08-05 16:00:00")).to(DateUtils_.getFromOrToConvertor("2018-09-20 16:00:00"));
		//QueryBuilder boolBuilder = QueryBuilders.rangeQuery("gatherTime").from(DateUtils_.getFromOrToConvertor(startTime)).to(DateUtils_.getFromOrToConvertor(endTime));
		
		System.out.println("boolBuilder");
		SearchResponse response = client.prepareSearch("yuqing_new").setTypes("yuqing_index")
				.setSize(3000).setScroll(new TimeValue(60000)).setSearchType(SearchType.SCAN).execute().actionGet();
		System.out.println("response");
		String scrollid = response.getScrollId();
		System.out.println("scrollid:" + scrollid);
		try {
			while (true) {
				SearchResponse response2 = client.prepareSearchScroll(scrollid).setScroll(new TimeValue(1000000))
						.execute().actionGet();
				SearchHits searchHit = response2.getHits();
				if (searchHit.getHits().length == 0) {
					break;
				}
				System.out.println("查询数量 ：" + searchHit.getHits().length);
				int j = 0;
				for( ; j<searchHit.getHits().length/20000; j++){//32767//
				for ( int i=0; i < 20000; i++) {
					String jsonStr = searchHit.getHits()[j*i + i].getSourceAsString();
					JSONObject json = new JSONObject();
					if (JSONUtils.checkJsonObject(jsonStr)) {
						json = JSONObject.parseObject(jsonStr);
					}
					row = sheet.createRow((int) (i + 1));
					row.createCell((short) 0).setCellValue(String.valueOf(json.get("id")));
					row.createCell((short) 1).setCellValue(String.valueOf(json.get("cleanTitle")));
					row.createCell((short) 2).setCellValue(String.valueOf(json.get("author")));
					row.createCell((short) 3).setCellValue(String.valueOf(json.get("publishTime")));
					
					String carrie = String.valueOf(json.get("carrie"));
					if("2000".equals(carrie)){
						row.createCell((short) 4).setCellValue("综合");
					}else if("2001".equals(carrie)){
						row.createCell((short) 4).setCellValue("新闻");
					}else if("2002".equals(carrie)){
						row.createCell((short) 4).setCellValue("博客");
					}else if("2003".equals(carrie)){
						row.createCell((short) 4).setCellValue("论坛");
					}else if("2004".equals(carrie)){
						row.createCell((short) 4).setCellValue("微博");
					}else if("2005".equals(carrie)){
						row.createCell((short) 4).setCellValue("微信");
					}else if("2006".equals(carrie)){
						row.createCell((short) 4).setCellValue("QQ群");
					}else{
						row.createCell((short) 4).setCellValue("其他");
					}
					
					
					row.createCell((short) 5).setCellValue(String.valueOf(json.get("siteName")));
					row.createCell((short) 6).setCellValue(String.valueOf(json.get("url")));
					String summary = String.valueOf(json.get("content"));//summary
					if(summary.length() > 32767){
						summary = summary.substring(0, 32767);
					}
					row.createCell((short) 7).setCellValue(summary);
					
					String country = String.valueOf(json.get("country"));
					if("1".equals(country)){
						row.createCell((short) 8).setCellValue("境内");
					}else{
						row.createCell((short) 8).setCellValue("境外");
					}
					
					String ispositive = String.valueOf(json.get("ispositive"));
					String isnegative = String.valueOf(json.get("isnegative"));
					String qinggan = "";
					if("1".equals(isnegative)){
						qinggan = "负面";
					}else if("1".equals(ispositive)){
						qinggan = "正面";
					}else{
						qinggan = "中性";
					}
					
					row.createCell((short) 9).setCellValue(qinggan);
					String language = String.valueOf(json.get("language"));
					if(EmptyUtils.isEmpty(language)){
						language = "中文";
					}
					row.createCell((short) 10).setCellValue(language);
				}
				FileOutputStream fout = new FileOutputStream("/data/files/" +j +".xls");
				wb.write(fout);
				fout.close();
			}
				

				for (int i=0 ; i < searchHit.getHits().length - (20000 * j); i++) {
					String jsonStr = searchHit.getHits()[j*i + i].getSourceAsString();
					JSONObject json = new JSONObject();
					if (JSONUtils.checkJsonObject(jsonStr)) {
						json = JSONObject.parseObject(jsonStr);
					}
					row = sheet.createRow((int) (i + 1));
					row.createCell((short) 0).setCellValue(String.valueOf(json.get("id")));
					row.createCell((short) 1).setCellValue(String.valueOf(json.get("cleanTitle")));
					row.createCell((short) 2).setCellValue(String.valueOf(json.get("author")));
					row.createCell((short) 3).setCellValue(String.valueOf(json.get("publishTime")));
					
					String carrie = String.valueOf(json.get("carrie"));
					if("2000".equals(carrie)){
						row.createCell((short) 4).setCellValue("综合");
					}else if("2001".equals(carrie)){
						row.createCell((short) 4).setCellValue("新闻");
					}else if("2002".equals(carrie)){
						row.createCell((short) 4).setCellValue("博客");
					}else if("2003".equals(carrie)){
						row.createCell((short) 4).setCellValue("论坛");
					}else if("2004".equals(carrie)){
						row.createCell((short) 4).setCellValue("微博");
					}else if("2005".equals(carrie)){
						row.createCell((short) 4).setCellValue("微信");
					}else if("2006".equals(carrie)){
						row.createCell((short) 4).setCellValue("QQ群");
					}else{
						row.createCell((short) 4).setCellValue("其他");
					}
					
					
					row.createCell((short) 5).setCellValue(String.valueOf(json.get("siteName")));
					row.createCell((short) 6).setCellValue(String.valueOf(json.get("url")));
					String summary = String.valueOf(json.get("summary"));
					if(summary.length() > 32767){
						summary = summary.substring(0, 32767);
					}
					row.createCell((short) 7).setCellValue(summary);
					
					String country = String.valueOf(json.get("country"));
					if("1".equals(country)){
						row.createCell((short) 8).setCellValue("境内");
					}else{
						row.createCell((short) 8).setCellValue("境外");
					}
					
					String ispositive = String.valueOf(json.get("ispositive"));
					String isnegative = String.valueOf(json.get("isnegative"));
					String qinggan = "";
					if("1".equals(isnegative)){
						qinggan = "负面";
					}else if("1".equals(ispositive)){
						qinggan = "正面";
					}else{
						qinggan = "中性";
					}
					
					row.createCell((short) 9).setCellValue(qinggan);
					String language = String.valueOf(json.get("language"));
					if(EmptyUtils.isEmpty(language)){
						language = "中文";
					}
					row.createCell((short) 10).setCellValue(language);
				}
				FileOutputStream fout = new FileOutputStream("/data/files/"+ (j+1) +".xls");
				wb.write(fout);
				fout.close();
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 批量获取数据
	 */
	public static JSONArray getData() throws Exception {

		JSONArray jsonArray = new JSONArray();
		//配置  集群名称cluster.name
		Settings settings = Settings.settingsBuilder().put("cluster.name", "my-application").put("client.transport.sniff", true).build();
		//连接es' 地址、端口号
		Client client =   TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"),   9300));
		//指定  index type
		System.out.println("boolBuilder");
		SearchResponse response = client.prepareSearch("ix_casedocs").setTypes("external")
				.setSize(200).setScroll(new TimeValue(60000)).setSearchType(SearchType.SCAN).execute().actionGet();
				//size 每个分片的条数   一般5个分片  也就是500条数据		
				//timeValue scroll保留的有效时长
		System.out.println("response");
		String scrollid = response.getScrollId();
		System.out.println("scrollid:" + scrollid);
		try {
			//while (true) {
				SearchResponse response2 = client.prepareSearchScroll(scrollid).setScroll(new TimeValue(1000000))
						.execute().actionGet();
				SearchHits searchHit = response2.getHits();
				if (searchHit.getHits().length == 0) {
					//break;
				}
				System.out.println("查询数量 ：" + searchHit.getHits().length);
				int j = 0;
				for ( int i=0; i < 1000; i++) {
					String jsonStr = searchHit.getHits()[j*i + i].getSourceAsString();
					JSONObject json = new JSONObject();
					if (JSONUtils.checkJsonObject(jsonStr)) {
						json = JSONObject.parseObject(jsonStr);
						jsonArray.add(json);
					}
				}
			
			//}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}


	
}
