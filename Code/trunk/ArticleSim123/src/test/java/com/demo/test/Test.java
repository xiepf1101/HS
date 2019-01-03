package com.demo.test;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.entity.ArticleEntity;
import com.demo.repository.ArticleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

	//https://github.com/spring-projects/spring-data-elasticsearch
	@Autowired
	private ArticleRepository articleRepository;
	
	@org.junit.Test
	public void testSave(){
		ArticleEntity article = new ArticleEntity();
		article.setId("78910");
		article.setTitle("天天向上");
		article.setIsShow(1);
		article.setPublishTime("2018-10-09");
		article.setUrl("www.baidu.com");
		article.setSimId(null);
		articleRepository.save(article);
	}
	
	@org.junit.Test
	public void testSearch(){
		List<ArticleEntity> findByTitle = articleRepository.findByTitle("好好学习");
		System.out.println(findByTitle.size());
		for (ArticleEntity articleEntity : findByTitle) {
			System.out.println(articleEntity.getTitle());
		}
	}
	
}
