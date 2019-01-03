package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public ArticleEntity findByIdAndIsShow(String id, Integer isShow) {
		// TODO Auto-generated method stub
		return articleRepository.findByIdAndIsShow(id, isShow);
	}

	@Override
	public ArticleEntity findById(String id) {
		// TODO Auto-generated method stub
		return articleRepository.findOne(id);
	}

	@Override
	public List<ArticleEntity> findAll() {
		// TODO Auto-generated method stub
		return articleRepository.findAll();
	}

	@Override
	public List<ArticleEntity> findAllByPage(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(pageNum, pageSize);
		Page<ArticleEntity> page = articleRepository.findByTitle(null, pageable);
		return page.getContent();
	}

}
