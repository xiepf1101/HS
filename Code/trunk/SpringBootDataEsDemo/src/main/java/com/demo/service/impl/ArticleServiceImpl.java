package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.ArticleEntity;
import com.demo.repository.ArticleRepository;
import com.demo.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public List<ArticleEntity> findByTitle(String title) {
		// TODO Auto-generated method stub
		return articleRepository.findByTitle(title);
	}

	@Override
	public ArticleEntity save(ArticleEntity article) {
		// TODO Auto-generated method stub
		return articleRepository.save(article);
	}

}
