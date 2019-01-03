package com.demo.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import com.as.analysis.Analysis;
import com.as.operate.ExcelOperate;
import com.as.sm.SimHash;
import com.demo.controller.ArticleController;
import com.demo.entity.ArticleEntity;
import com.demo.redis.dao.CommonRedisDao;
import com.demo.redis.service.SimHashService;
import com.demo.service.ArticleService;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("com.demo")
@EnableCaching
@EnableScheduling
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

	@Autowired
	private CommonRedisDao dao;
	
	@org.junit.Test
	public void save(){
		boolean cacheValue = dao.cacheValue("123", "小平二");
		System.out.println(cacheValue);
	}
	
	@org.junit.Test
	public void find(){
		String value = dao.getValue("123");
		System.out.println(value);
	}
	
	@org.junit.Test
	public void del(){
		boolean removeValue = dao.removeValue("123");
		System.out.println(removeValue);
		String value = dao.getValue("123");
		System.out.println(value);
	}
	
	/*
	 * 切换数据库
	 * select 关键字
	 */
	@org.junit.Test
	public void select(){
		DefaultStringRedisConnection select = dao.select(1);
		select.set("123", "好好学习");
		String value = select.get("123");
		System.out.println(value);
		select = dao.select(3);
		String c = select.get("123");
		System.out.println(c);
	}
	
	/*
	 * 操作集合
	 */
	@org.junit.Test
	public void saveToList(){
		//喜悲相冲，平平淡淡。
		List<String> list = new ArrayList<String>();
		list.add("sunhongping");
		dao.cacheList("list1", list);
		List<String> list2 = dao.getList("list1", 0, -1);
		System.out.println(list2.size());
		dao.cacheList("list1", "汪汪汪");
		list2 = dao.getList("list1", 0, -1);
		System.out.println(list2.size());
	}
	
}
