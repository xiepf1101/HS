package com.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.demo.entity.ArticleEntity;

public interface ArticleRepository extends ElasticsearchRepository<ArticleEntity, String>{

	List<ArticleEntity> findByTitle(String title);
	
	ArticleEntity findByIdAndIsShow(String id, Integer isShow);
	
	List<ArticleEntity> findAll();
	
	Page<ArticleEntity> findByTitle(String title, Pageable pageable);
}
