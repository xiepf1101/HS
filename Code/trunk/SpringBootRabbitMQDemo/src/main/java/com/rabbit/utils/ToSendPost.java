package com.rabbit.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/*
 * 一切照旧
 * 春夏秋冬还是喜欢晴天
 * 一年四季还是习惯清晨开始傍晚结束
 */
public class ToSendPost {

	private static Logger logger = Logger.getLogger(ToSendPost.class);
	
	public static String callService(String url) 
	{

		try
		{
			String postUrl = "";
			HttpClient responseClient = new HttpClient();
			PostMethod postMethod = null;
			
				postUrl = url.split("\\?")[0];
				String postParam = url.split("\\?")[1];
				String [] paramArray = postParam.split("\\&");
				List<NameValuePair> nvList = new ArrayList<NameValuePair>();
				NameValuePair[] data = new NameValuePair[]{};
				for(int i=0;i<paramArray.length;i++)
				{
					String [] mp = paramArray[i].split("=");
					String name = mp[0];
					String value = "";
					if(mp.length==2)
					{
						value = mp[1];
					}
					else
					{
					}
					
					NameValuePair nv = new NameValuePair(name,value);
					nvList.add(nv);
				}
				postMethod = new PostMethod(postUrl);
				postMethod.setRequestBody(nvList.toArray(data));
			
			responseClient.executeMethod(postMethod);
			InputStream resStream = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"utf-8"));
			StringBuffer resBuffer = new StringBuffer();
			String resTemp = "";
			while((resTemp = br.readLine()) != null){
				resBuffer.append(resTemp);
			}
			String convertResponse = resBuffer.toString(); 
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)responseClient.getHttpConnectionManager()).shutdown();
			return convertResponse;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return"";

	} 
	
	public static String callServiceNoParam(String url) 
	{

		try
		{
			String postUrl = "";
			HttpClient responseClient = new HttpClient();
			PostMethod postMethod = null;
			
				postUrl = url.split("\\?")[0];
				//String postParam = url.split("\\?")[1];
				//String [] paramArray = postParam.split("\\&");
				List<NameValuePair> nvList = new ArrayList<NameValuePair>();
				NameValuePair[] data = new NameValuePair[]{};
				/*for(int i=0;i<paramArray.length;i++)
				{
					String [] mp = paramArray[i].split("=");
					String name = mp[0];
					String value = "";
					if(mp.length==2)
					{
						value = mp[1];
					}
					else
					{
					}
					
					NameValuePair nv = new NameValuePair(name,value);
					nvList.add(nv);
				}*/
				postMethod = new PostMethod(postUrl);
				postMethod.setRequestBody(nvList.toArray(data));
			
			responseClient.executeMethod(postMethod);
			InputStream resStream = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"utf-8"));
			StringBuffer resBuffer = new StringBuffer();
			String resTemp = "";
			while((resTemp = br.readLine()) != null){
				resBuffer.append(resTemp);
			}
			String convertResponse = resBuffer.toString(); 
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)responseClient.getHttpConnectionManager()).shutdown();
			return convertResponse;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return"";

	}
	
	public static void main(String[] args) {
		System.setProperty("wordsUrl", "localhost:8080");
		String url = System.getProperty("wordsUrl");
		String str = callServiceNoParam("http://"+url+"/rabbit/callback"); 
		System.out.println(str);
	}
	
	public static List<String> getCategoryWordList(){
		String url = System.getProperty("wordsUrl");
		logger.debug("http://"+url+"/api_v1_0/findAllSubLinkWordsCP?sig=null&timeStamp=1314&dimensionId=1001");
		String str = callService("http://"+url+"/api_v1_0/findAllSubLinkWordsCP?sig=null&timeStamp=1314&dimensionId=1001"); 
		List<String> list = new ArrayList<String>();
		JSONObject json = JSONObject.parseObject(str);
		JSONObject jsons = (JSONObject) json.get("data");
		JSONArray jsonArray = (JSONArray) jsons.get("result");
		if(jsonArray != null && jsonArray.size() > 0){
			for (int i = 0; i < jsonArray.size(); i++) {
				list.add(jsonArray.getString(i));
			}
		}
		return list;
	}
	
	public static List<String> getWhyCategoryWordList(){
		String url = System.getProperty("wordsUrl");
		List<String> list = new ArrayList<String>();
		for(int j = 1001 ; j<1010 ; j++ ){
			logger.debug("http://"+url+"/api_v1_0/findAllSubLinkWords?sig=null&timeStamp=1314&dimensionId="+j);
			String str = callService("http://"+url+"/api_v1_0/findAllSubLinkWords?sig=null&timeStamp=1314&dimensionId="+j); 
			JSONObject json = JSONObject.parseObject(str);
			JSONObject jsons = (JSONObject) json.get("data");
			JSONArray jsonArray = (JSONArray) jsons.get("result");
			if(jsonArray != null && jsonArray.size() > 0){
				for (int i = 0; i < jsonArray.size(); i++) {
					list.add(jsonArray.getString(i));
				}
			}
		}
		return list;
	}
	
	public static Map<String,Set<String>> getDimensionList(){
		String url = System.getProperty("wordsUrl");
		logger.debug("http://"+url+"/api_v1_0/findAllSubDimension?sig=null&timeStamp=1314&dimensionId=1001");
		String str = callService("http://"+url+"/api_v1_0/findAllSubDimension?sig=null&timeStamp=1314&dimensionId=1001"); 
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		Set<String> dimension = new HashSet<String>();
		Set<String> configure = new HashSet<String>();
		JSONObject json = JSONObject.parseObject(str);
		JSONObject jsons = (JSONObject) json.get("data");
		JSONArray jsonArray = (JSONArray) jsons.get("result");
		if(jsonArray != null && jsonArray.size() > 0){
			for (int i = 0; i < jsonArray.size(); i++) {
				String string = jsonArray.getString(i);
				JSONObject parseObject = JSONObject.parseObject(string);
				String dimensionId = parseObject.getString("dimensionId");
				String dimensionDescribe = parseObject.getString("dimensionDescribe");
				String dimensionName = parseObject.getString("dimensionName");
				if(dimensionId.length() > 10){
					if(dimensionDescribe != null && !dimensionDescribe.trim().equals("") && dimensionDescribe.trim().equals("配置指标")){
						configure.add(dimensionName.trim());
					}else{
						dimension.add(dimensionName.trim());
					}
				}
			}
			map.put("dimension", dimension);
			map.put("configure", configure);
		}
		return map;
	}
	
	public static Map<String,Set<String>> getWhyDimensionList(){
		String url = System.getProperty("wordsUrl");
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		Set<String> dimension = new HashSet<String>();
		for(int j = 1001 ; j<1010 ; j++ ){
			logger.debug("http://"+url+"/api_v1_0/findAllSubDimension?sig=null&timeStamp=1314&dimensionId="+j);
			String str = callService("http://"+url+"/api_v1_0/findAllSubDimension?sig=null&timeStamp=1314&dimensionId="+j); 
			JSONObject json = JSONObject.parseObject(str);
			JSONObject jsons = (JSONObject) json.get("data");
			JSONArray jsonArray = (JSONArray) jsons.get("result");
			if(jsonArray != null && jsonArray.size() > 0){
				for (int i = 0; i < jsonArray.size(); i++) {
					String string = jsonArray.getString(i);
					JSONObject parseObject = JSONObject.parseObject(string);
					String dimensionId = parseObject.getString("dimensionId");
					String dimensionDescribe = parseObject.getString("dimensionDescribe");
					String dimensionName = parseObject.getString("dimensionName");
					if(dimensionId.contains("100100")){
						if(dimensionId.length() > 10){
							if(!dimensionDescribe.trim().equals("配置指标")){
								dimension.add(dimensionName.trim());
							}
						}
					}else{
						if(dimensionId.length() >= 7 ){
							if(dimensionDescribe != null){
								dimension.add(dimensionName.trim());
							}
						}
					}
				}
				map.put("dimension", dimension);
			}
		}
		return map;
	}
}
