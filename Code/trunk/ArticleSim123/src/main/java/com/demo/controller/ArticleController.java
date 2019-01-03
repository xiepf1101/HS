package com.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.as.analysis.Analysis;
import com.as.operate.ExcelOperate;
import com.as.redis.Main;
import com.as.sm.SimHash;
import com.demo.entity.ArticleEntity;
import com.demo.redis.dao.CommonRedisDao;
import com.demo.redis.service.SimHashService;
import com.demo.service.ArticleService;

@Controller
public class ArticleController {

	private static Logger logger = Logger.getLogger(ArticleController.class);
	
	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/save")
	public void save(){
		ArticleEntity article = new ArticleEntity();
		article.setId("4567");
		article.setTitle("天天向上");
		articleService.save(article);
	}
	
	@RequestMapping(value="/handle")
	public String handle(){
		
		try {
			List<ArticleEntity> list = ExcelOperate.readArticle("C:/Users/zkdj/Desktop/舆情数据.xls", 0);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(list != null){
				for (ArticleEntity article : list) {
					logger.info("①①①开始处理标题："+article.getTitle());
					UUID uuid = UUID.randomUUID();
					article.setId(uuid.toString());
					//ArticleEntity article = articleService.saveOrUpdateArticle(articleEntity);
					logger.info("②②②：开始生成simHash");
					String simHash = Analysis.getSimHash(article.getTitle());
					logger.info("②②②：simHash.success");
					if(simHash != null){
						logger.info("③③③③：去redis里面查询是否存在simHash值相同的文章");
						String articleId = Main.searchBySimHash(simHash);
						logger.info("③③③③：查询完成");
						if(articleId != null){
							//simHash相同   不做后续判断
							//根据时间先后更新文章相似文章id集合及展现与否
							logger.info("④④④④：simHash值相同.处理");
							ArticleEntity findArticleById = articleService.findById(articleId);
							if(findArticleById != null && article != null){
								if(format.parse(findArticleById.getPublishTime()).before(format.parse(article.getPublishTime()))){
									List<String> simId = article.getSimId();
									if(simId == null){
										simId = new ArrayList<String>();
									}
									if(findArticleById.getSimId() != null){
										simId.addAll(findArticleById.getSimId());
									}
									simId.add(findArticleById.getId());
									article.setSimId(simId);
									article.setIsShow(1);
									articleService.save(article);
									//过去的相似文章不直接显示
									findArticleById.setIsShow(0);
									articleService.save(findArticleById);
								}else{
									List<String> simId = findArticleById.getSimId();
									if(simId == null){
										simId = new ArrayList<String>();
									}
									simId.add(article.getId());
									findArticleById.setSimId(simId);
									articleService.save(findArticleById);
									article.setIsShow(0);
									articleService.save(article);
								}
							}
							logger.info("④④④④：simHash值相同.处理完成");
						}else{
							logger.info("④④④④：simHash值不相同.分段处理");
							//没有出现相同simHash    继续后续排重判断
							String simHash1 = simHash.substring(0,16);
							String simHash2 = simHash.substring(16, 32);
							String simHash3 = simHash.substring(32, 48);
							String simHash4 = simHash.substring(48);
							logger.info("④④④④五：simHash值不相同.redis中分段查询");
							Set<String> set = new HashSet<String>();
							List<String> searchBySimHashFragment = Main.searchBySimHashFragment("sim1", simHash1);
							if(searchBySimHashFragment != null){
								set.addAll(searchBySimHashFragment);
							}
							searchBySimHashFragment = Main.searchBySimHashFragment("sim2", simHash2);
							if(searchBySimHashFragment != null){
								set.addAll(searchBySimHashFragment);
							}
							searchBySimHashFragment = Main.searchBySimHashFragment("sim3", simHash3);
							if(searchBySimHashFragment != null){
								set.addAll(searchBySimHashFragment);
							}
							searchBySimHashFragment = Main.searchBySimHashFragment("sim4", simHash4);
							if(searchBySimHashFragment != null){
								set.addAll(searchBySimHashFragment);
							}
							logger.info("④④④④五：simHash值不相同.redis中分段查询完成");
							int distance = 0;
							logger.info("④④④④六：simHash值不相同.分段simHash值对比");
							if(set != null && !set.isEmpty()){
								for (String simHashStr : set) {
									distance = SimHash.getDistance(simHash, simHashStr);
									if(distance <= 3){
										//update  相似文章
										articleId = Main.searchBySimHash(simHashStr);
										if(articleId != null){
											ArticleEntity findArticleById = articleService.findByIdAndIsShow(articleId, 1);
											if(findArticleById != null && article != null){
												if(format.parse(findArticleById.getPublishTime()).before(format.parse(article.getPublishTime()))){
													List<String> simId = article.getSimId();
													if(simId == null){
														simId = new ArrayList<String>();
													}
													if(findArticleById.getSimId() != null){
														simId.addAll(findArticleById.getSimId());
													}
													simId.add(findArticleById.getId());
													article.setSimId(simId);
													article.setIsShow(1);
													articleService.save(article);
													//过去的相似文章不直接显示
													findArticleById.setIsShow(0);
													articleService.save(findArticleById);
												}else{
													List<String> simId = findArticleById.getSimId();
													if(simId == null){
														simId = new ArrayList<String>();
													}
													simId.add(article.getId());
													findArticleById.setSimId(simId);
													articleService.save(findArticleById);
													
													article.setIsShow(0);
													articleService.save(article);
												}
											}
										}
									}
								}
							}
						logger.info("④④④④六：simHash值不相同.分段simHash值对比完成");
						Main.saveSimHashFragment(simHash);
						logger.info("④④④④：simHash值不相同.分段处理完成");
						logger.info("⑤⑤⑤：相同的simhash值不存在直接index到es");
						articleService.save(article);
						logger.info("⑤⑤⑤：完成");
						logger.info("⑥⑥⑥：相同的simhash值不存在直接缓存到redis");
						Main.saveOrUpdateSimHashArticle(simHash, article.getId());
						logger.info("⑥⑥⑥：完成");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		 	
	}
	
	@Autowired
	private CommonRedisDao dao;
	
	@Resource
	private SimHashService service;
	
	@RequestMapping("handle1")
	public String handle1(){
		
		try {
			List<ArticleEntity> list = ExcelOperate.readArticle("C:/Users/zkdj/Desktop/舆情数据.xls", 0);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(list != null){
				for (ArticleEntity article : list) {
					logger.info("①①①开始处理标题："+article.getTitle());
					UUID uuid = UUID.randomUUID();
					article.setId(uuid.toString());
					//ArticleEntity article = articleService.saveOrUpdateArticle(articleEntity);
					logger.info("②②②：开始生成simHash");
					String simHash = Analysis.getSimHash(article.getTitle());
					logger.info("②②②：simHash.success");
					if(simHash != null){
						logger.info("③③③③：去redis里面查询是否存在simHash值相同的文章");
						String articleId = dao.getValue(simHash);//Main.searchBySimHash(simHash);
						logger.info("③③③③：查询完成");
						if(articleId != null){
							//simHash相同   不做后续判断
							//根据时间先后更新文章相似文章id集合及展现与否
							logger.info("④④④④：simHash值相同.处理");
							ArticleEntity findArticleById = articleService.findById(articleId);
							if(findArticleById != null && article != null){
								if(format.parse(findArticleById.getPublishTime()).before(format.parse(article.getPublishTime()))){
									List<String> simId = article.getSimId();
									if(simId == null){
										simId = new ArrayList<String>();
									}
									if(findArticleById.getSimId() != null){
										simId.addAll(findArticleById.getSimId());
									}
									simId.add(findArticleById.getId());
									article.setSimId(simId);
									article.setIsShow(1);
									articleService.save(article);
									//过去的相似文章不直接显示
									findArticleById.setIsShow(0);
									articleService.save(findArticleById);
								}else{
									List<String> simId = findArticleById.getSimId();
									if(simId == null){
										simId = new ArrayList<String>();
									}
									simId.add(article.getId());
									findArticleById.setSimId(simId);
									articleService.save(findArticleById);
									article.setIsShow(0);
									articleService.save(article);
								}
							}
							logger.info("④④④④：simHash值相同.处理完成");
						}else{
							logger.info("④④④④：simHash值不相同.分段处理");
							//没有出现相同simHash    继续后续排重判断
							String simHash1 = simHash.substring(0,16);
							String simHash2 = simHash.substring(16, 32);
							String simHash3 = simHash.substring(32, 48);
							String simHash4 = simHash.substring(48);
							logger.info("④④④④五：simHash值不相同.redis中分段查询");
							Set<String> set = new HashSet<String>();
							List<String> searchBySimHashFragment = service.searchBySimHashFragment(1, simHash1);//Main.searchBySimHashFragment("sim1", simHash1);
							if(searchBySimHashFragment != null){
								set.addAll(searchBySimHashFragment);
							}
							searchBySimHashFragment = service.searchBySimHashFragment(2, simHash2);//Main.searchBySimHashFragment("sim2", simHash2);
							if(searchBySimHashFragment != null){
								set.addAll(searchBySimHashFragment);
							}
							searchBySimHashFragment = service.searchBySimHashFragment(3, simHash3);//Main.searchBySimHashFragment("sim3", simHash3);
							if(searchBySimHashFragment != null){
								set.addAll(searchBySimHashFragment);
							}
							searchBySimHashFragment = service.searchBySimHashFragment(4, simHash4);//Main.searchBySimHashFragment("sim4", simHash4);
							if(searchBySimHashFragment != null){
								set.addAll(searchBySimHashFragment);
							}
							logger.info("④④④④五：simHash值不相同.redis中分段查询完成");
							int distance = 0;
							logger.info("④④④④六：simHash值不相同.分段simHash值对比"+set.size());
							if(set != null && !set.isEmpty()){
								for (String simHashStr : set) {
									distance = SimHash.getDistance(simHash, simHashStr);
									if(distance <= 3){
										//update  相似文章
										articleId = dao.getValue(simHashStr);//Main.searchBySimHash(simHashStr);
										if(articleId != null){
											ArticleEntity findArticleById = articleService.findByIdAndIsShow(articleId, 1);
											if(findArticleById != null && article != null){
												if(format.parse(findArticleById.getPublishTime()).before(format.parse(article.getPublishTime()))){
													List<String> simId = article.getSimId();
													if(simId == null){
														simId = new ArrayList<String>();
													}
													if(findArticleById.getSimId() != null){
														simId.addAll(findArticleById.getSimId());
													}
													simId.add(findArticleById.getId());
													article.setSimId(simId);
													article.setIsShow(1);
													articleService.save(article);
													//过去的相似文章不直接显示
													findArticleById.setIsShow(0);
													articleService.save(findArticleById);
												}else{
													List<String> simId = findArticleById.getSimId();
													if(simId == null){
														simId = new ArrayList<String>();
													}
													simId.add(article.getId());
													findArticleById.setSimId(simId);
													articleService.save(findArticleById);
													
													article.setIsShow(0);
													articleService.save(article);
												}
											}
										}
									}
								}
							}
						logger.info("④④④④六：simHash值不相同.分段simHash值对比完成");
						//Main.saveSimHashFragment(simHash);
						service.saveSimHashFragment(simHash);
						logger.info("④④④④：simHash值不相同.分段处理完成");
						logger.info("⑤⑤⑤：相同的simhash值不存在直接index到es");
						articleService.save(article);
						logger.info("⑤⑤⑤：完成");
						logger.info("⑥⑥⑥：相同的simhash值不存在直接缓存到redis");
						//Main.saveOrUpdateSimHashArticle(simHash, article.getId());
						dao.cacheValue(simHash, article.getId());
						logger.info("⑥⑥⑥：完成");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		 	
	}
	
	
}
