/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/7 19:25</create-date>
 *
 * <copyright file="DemoChineseNameRecoginiton.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014+ 上海林原信息科技有限公司. All Right Reserved+ http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package com.as.summary;

import com.hankcs.hanlp.HanLP;

import java.util.List;

/**
 * 自动摘要
 * @author hankcs
 */
public class Summary
{
    public static void main(String[] args)
    {
        String document = "[京师法律实务]2018年上半年全国特许经营合同,纠纷大数据报告分析 ";
        document = document.replaceAll("[，,。:：“”？?！!；]", "@");
        System.out.println(document);
        document = document.replaceAll("[丨|#\\[\\]【】]", "。");
        System.out.println(document);
        if(document.contains("--")){
        	document =document.replaceAll("--", "。");
		}
		if(document.contains("―")){
			document = document.replaceAll("―", "。");
		}
		if(document.contains("——")){
			document = document.replaceAll("——", "。");
		}
		if(document.contains("—")){
			document = document.replaceAll("—", "。");
		}
        List<String> sentenceList = HanLP.extractSummary(document, 1);
        System.out.println(sentenceList);
    }
    
    public static String handle(String document){
    	if(document != null && !document.trim().equals("")){
    		document = document.replaceAll("[，,。:：“”？?！!；]", "@");

    		document = document.replaceAll("[丨|#\\[\\]【】]", "。");

    		if(document.contains("--")){
            	document =document.replaceAll("--", "。");
    		}
    		if(document.contains("―")){
    			document = document.replaceAll("―", "。");
    		}
    		if(document.contains("——")){
    			document = document.replaceAll("——", "。");
    		}
    		if(document.contains("—")){
    			document = document.replaceAll("—", "。");
    		}
    		List<String> sentenceList = HanLP.extractSummary(document, 1);
    		if(sentenceList != null && !sentenceList.isEmpty()){
    			String string = sentenceList.get(0);
    			if(string != null && !string.trim().equals("")){
    				return string;
    			}
    			return document;
    		}
    		return document;
    	}
    	return "";
    }
    
}
