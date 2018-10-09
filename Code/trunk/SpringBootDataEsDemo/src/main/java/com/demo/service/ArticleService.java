package com.demo.service;

import java.util.List;

import com.demo.entity.ArticleEntity;

public interface ArticleService {

	List<ArticleEntity> findByTitle(String title);
	
	ArticleEntity save(ArticleEntity article);
}
