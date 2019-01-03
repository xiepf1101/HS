package com.demo.service;

import java.util.List;

import com.demo.entity.ArticleEntity;

public interface ArticleService {

	List<ArticleEntity> findByTitle(String title);
	
	ArticleEntity save(ArticleEntity article);
	
	ArticleEntity findByIdAndIsShow(String id, Integer isShow);
	
	ArticleEntity findById(String id);
	
	List<ArticleEntity> findAll();
	
	List<ArticleEntity> findAllByPage(Integer pageNum, Integer pageSize);
}
