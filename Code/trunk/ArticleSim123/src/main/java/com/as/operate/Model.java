package com.as.operate;

import java.util.HashMap;
import java.util.Map;

public class Model {

	private String id;
	private String srcEvent;
	private String title;
	private String content;
	private String url;
	private String category;
	private String event;
	private String textSummary;
	private String newWord;
	private Map<String,String> itemColls = new HashMap<String,String>();;
	
	public Map<String, String> getItemColls() {
		return itemColls;
	}
	public void setItemColls(Map<String, String> itemColls) {
		this.itemColls = itemColls;
	}
	public String getNewWord() {
		return newWord;
	}
	public void setNewWord(String newWord) {
		this.newWord = newWord;
	}
	public String getSrcEvent() {
		return srcEvent;
	}
	public void setSrcEvent(String srcEvent) {
		this.srcEvent = srcEvent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getTextSummary() {
		return textSummary;
	}
	public void setTextSummary(String textSummary) {
		this.textSummary = textSummary;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
