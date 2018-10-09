package com.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.demo.entity.ArticleEntity;

public interface ArticleRepository extends ElasticsearchRepository<ArticleEntity, String>{

	List<ArticleEntity> findByTitle(String title);
	
}
