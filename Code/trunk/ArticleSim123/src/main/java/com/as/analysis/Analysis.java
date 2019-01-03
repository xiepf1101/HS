package com.as.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.as.sm.SimHash;
import com.as.summary.Summary;

public class Analysis {

	public static String getSimHash(String document){
		
		String handle = Summary.handle(document);
		
		if(handle != null){
			SimHash simHash = new SimHash(document,64);
			return simHash.strSimHash;
		}
			
		return null;
	}
	
	public static void main(String[] args) {
		String simHash = getSimHash("[京师法律实务]2018年上半年全国特许经营合同,纠纷大数据报告分析 ");
		System.out.println(simHash);
		Map<String,List<String>> map1 = new HashMap<String,List<String>>();
		Map<String,List<String>> map2 = new HashMap<String,List<String>>();
		Map<String,List<String>> map3 = new HashMap<String,List<String>>();
		Map<String,List<String>> map4 = new HashMap<String,List<String>>();
		String simHash1 = simHash.substring(0,16);
		System.out.println(simHash1);
		String simHash2 = simHash.substring(16, 32);
		System.out.println(simHash2);
		String simHash3 = simHash.substring(32, 48);
		System.out.println(simHash3);
		String simHash4 = simHash.substring(48);
		System.out.println(simHash4);
		
		List<String> list = new ArrayList<String>();
		list = map1.get(simHash1);
		if(list == null){
			list = new ArrayList<String>();
		}
		list.add(simHash);
		map1.put(simHash1, list);
		
		list = map2.get(simHash2);
		if(list == null){
			list = new ArrayList<String>();
		}
		list.add(simHash);
		map2.put(simHash2, list);
		
		
		list = map3.get(simHash3);
		if(list == null){
			list = new ArrayList<String>();
		}
		list.add(simHash);
		map3.put(simHash3, list);
		
		list = map4.get(simHash4);
		if(list == null){
			list = new ArrayList<String>();
		}
		list.add(simHash);
		map4.put(simHash4, list);
		
	}
	
}
