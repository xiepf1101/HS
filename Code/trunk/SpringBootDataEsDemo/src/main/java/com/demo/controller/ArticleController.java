package com.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.ArticleEntity;
import com.demo.service.ArticleService;

@Controller
public class ArticleController {

	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/save")
	public void save(){
		ArticleEntity article = new ArticleEntity();
		article.setId("4567");
		article.setTitle("天天向上");
		articleService.save(article);
	}
	
}
