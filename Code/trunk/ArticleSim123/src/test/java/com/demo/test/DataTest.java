package com.demo.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.as.export.Export;
import com.demo.entity.ArticleEntity;
import com.demo.service.ArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataTest {

	@Resource
	private ArticleService articleService;
	
	@org.junit.Test
	public void dataTest(){
		
		List<ArticleEntity> resList = new ArrayList<ArticleEntity>();
		List<ArticleEntity> resList1 = new ArrayList<ArticleEntity>();
		List<ArticleEntity> findAll = new ArrayList<ArticleEntity>();
		List<ArticleEntity> findAll1 = articleService.findAllByPage(1, 10000);
		List<ArticleEntity> findAll2 = articleService.findAllByPage(2, 10000);
		List<ArticleEntity> findAll3 = articleService.findAllByPage(3, 10000);
		findAll.addAll(findAll1);
		findAll.addAll(findAll2);
		findAll.addAll(findAll3);
		
		List<String> str = null;
		List<String> simId = null;
		String[] split = null;
		ArticleEntity entity = null;
		for (ArticleEntity articleEntity : findAll) {
			str = new ArrayList<String>();
			if(articleEntity.getSimId() != null && 1 == articleEntity.getIsShow()){
				simId = articleEntity.getSimId();
				articleEntity.setPublishTime(simId.toString());
				if(simId != null){
					for (int i = 0; i < simId.size(); i++) {
						try {
							entity = articleService.findById(split[i]);
							if(entity != null){
								str.add(entity.getTitle()+"\n");
							}
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(split[i]);
						}
					}
					articleEntity.setSimId(str);
				}
				resList.add(articleEntity);
			}else{
				articleEntity.setPublishTime("");
				resList1.add(articleEntity);
			}
		}
		
		Export.saveResultDataTest("D:/thisme_D/舆情管家/r.xls", resList);
		Export.saveResultDataTest("D:/thisme_D/舆情管家/r1.xls", resList1);
	}
	
	
}
