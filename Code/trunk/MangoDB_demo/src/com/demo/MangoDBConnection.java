package com.demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

public class MangoDBConnection {

	private MongoClient mongoClient;

	public static void main(String[] args) {
		
		try {
			//链接mongoDB服务
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			
			//连接到database
			//如果指定的数据库不存在，则会自主创建
			MongoDatabase database = mongoClient.getDatabase("helloMongo");
			
			System.out.println(database);
			
			System.out.println("连接成功");
		} catch (Exception e) {
			System.out.println("连接失败");
		}
	}
	
	/*
	 * 通过用户名密码链接
	 */
	@Test
	public void connectionByAuth(){
		try {
			//连接到mongoDB服务
			ServerAddress serverAddress = new ServerAddress("localhost",27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			//MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
			MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
			List<MongoCredential> creds = new ArrayList<MongoCredential>();
			creds.add(mongoCredential);
			//通过连接及用户名密码等验证参数认证获取MongoDB连接
			mongoClient = new MongoClient(addrs, creds);
			//连接到数据库
			MongoDatabase database = mongoClient.getDatabase("databaseName");
			System.out.println(database);
			System.out.println("连接成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("连接失败");
		}
	}
	
	/*
	 * 创建集合
	 * 获取集合
	 */
	@Test
	public void createConnection(){
		try{   
	      // 连接到 mongodb 服务
	      MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	         
	       
	      // 连接到数据库
	      MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");  
	      System.out.println("Connect to database successfully");
	      //创建集合
	      mongoDatabase.createCollection("test");
	      System.out.println("集合创建成功");
	        
	      MongoCollection<Document> collection = mongoDatabase.getCollection("test");
	      System.out.println(collection);
	      System.out.println("获取集合成功");
	      }catch(Exception e){
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	     }
	}
	
	//添加文档
	@Test
	public void insertDocument(){
		try {
			MongoClient mongoClient2 = new MongoClient("localhost", 27017);
			
			MongoDatabase database = mongoClient2.getDatabase("mycol");
			
			MongoCollection<Document> collection = database.getCollection("test");
			
			 //插入文档  
	         /** 
	         * 1. 创建文档 org.bson.Document 参数为key-value的格式 
	         * 2. 创建文档集合List<Document> 
	         * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
	         * */
			Document document = new Document("title", "Hello MangoDB");
			document.append("content", "请多多关照").append("dateTime", "2019-03-20");
			
			collection.insertOne(document);
			
			System.out.println("文档插入成功");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//查询文档
	@Test
	public void findDocument(){
		try {
			MongoClient mongoClient2 = new MongoClient("localhost", 27017);
			
			MongoDatabase database = mongoClient2.getDatabase("mycol");
			
			MongoCollection<Document> collection = database.getCollection("test");
			
			FindIterable<Document> find = collection.find();
			
			MongoCursor<Document> iterator = find.iterator();
			
			while(iterator.hasNext()){
				Document document = iterator.next();
				System.out.println("Title: "+ document.getString("title"));
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//更新文档
	@Test
	public void updateDocument(){
		
		try {
			MongoClient mongoClient2 = new MongoClient("localhost", 27017);
			
			MongoDatabase database = mongoClient2.getDatabase("mycol");
			
			MongoCollection<Document> collection = database.getCollection("test");
			
			collection.updateMany(Filters.eq("title", "Hello MangoDB"), new Document("$set", new Document("title","Hello MangoDB!!!")));
			
			FindIterable<Document> find = collection.find();
			
			MongoCursor<Document> iterator = find.iterator();
			
			while(iterator.hasNext()){
				Document document = iterator.next();
				System.out.println(document.getString("title"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//删除数据
	@Test
	public void deleteDocument(){
		
		try {
			
			MongoClient mongoClient2 = new MongoClient("localhost", 27017);
			
			MongoDatabase database = mongoClient2.getDatabase("mycol");
			
			MongoCollection<Document> collection = database.getCollection("test");
			
			DeleteResult deleteOne = collection.deleteOne(Filters.eq("title","Hello MangoDB!!!"));
			
			System.out.println(deleteOne);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/*
	 * 分頁查詢
	 */
	@Test
	public void test(){
		try {
			MongoClient mongoClient2 = new MongoClient("localhost", 27017);
			
			MongoDatabase database = mongoClient2.getDatabase("mycol");
			
			MongoCollection<Document> collection = database.getCollection("test");
			
			FindIterable<Document> find = collection.find();
			int pageSize = 100;
			int pageNo = 0;
			long countDocuments = collection.countDocuments();
			double d = countDocuments / 100;
			int pageCount = (int)d;
			
			for (int i = 0; i <= pageCount; i++) {
				pageNo = i;
				//分頁查詢
				FindIterable<Document> findIterable = find.limit(10).skip(pageSize * pageNo);
				
				MongoCursor<Document> iterator = findIterable.iterator();
				
				Document document = new Document();
				
				List<Document> list = new ArrayList<Document>();
				while(iterator.hasNext()){
					document = iterator.next();
					list.add(document);
				}
				collection.insertMany(list);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void test1(){
		long countDocuments = 101;
		double d = countDocuments / 100;
		System.out.println(d);
		int pageCount = (int)d;
		System.out.println(pageCount);
	}
	
}
