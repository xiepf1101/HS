package com.as.redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(10);
        config.setMaxTotal(100);              
        config.setMaxWaitMillis(1000L);
        config.setTestOnBorrow(true);
        RedisClient redis = new RedisClient();
        Jedis jedis = redis.jedis;
        jedis.set("hello", "hello");
        String string = jedis.get("hello");
        jedis.flushDB();
        jedis.select(1);
        jedis.flushDB();
        jedis.select(2);
        jedis.flushDB();
        jedis.select(3);
        jedis.flushDB();
        jedis.select(4);
        jedis.flushDB();
        System.out.println(string);
        string = jedis.get("hello");
        System.out.println(string);
        String select = jedis.select(3);
        System.out.println(select);
        List<String> lrange = jedis.lrange("1", 0, -1);
        System.out.println(lrange);
        jedis.close();
    }

    /*
     * 整个simHash值检索     查询完全相等的simHash值是否存在
     * 存在   取出对应文章id进行修改
     * 否   进行后续分段相似性判断    入库
     */
    public static String searchBySimHash(String simHash){
    	RedisClient redis = new RedisClient();
        Jedis jedis = redis.jedis;
        String articleId = jedis.get(simHash);
        jedis.close();
		return articleId;
    }
    
    /*
     * 更新整个simHash值与文章id对应表
     * 保存的数据为simHash值相等时的最新数据
     */
    public static boolean saveOrUpdateSimHashArticle(String simHash ,String articleId){
    	RedisClient redis = new RedisClient();
        Jedis jedis = redis.jedis;
        jedis.set(simHash, articleId);
        jedis.close();
    	return false;
    }
    
    /*
     * 查询分段simHash相同的   整条simHash集合
     */
    public static List<String> searchBySimHashFragment(String simKey, String simValue){
    	RedisClient redis = new RedisClient();
        Jedis jedis = redis.jedis;
        if(simKey.contains("1")){
        	jedis.select(1);
        }else if(simKey.contains("2")){
        	jedis.select(2);
        }else if(simKey.contains("3")){
        	jedis.select(3);
        }else if(simKey.contains("4")){
        	jedis.select(4);
        }
        List<String> simHashList = jedis.lrange(simValue, 0, -1);
        jedis.close();
        return simHashList;
    }
    
    /*
     * 新增分段simHash
     */
    public static boolean saveSimHashFragment(String simHash){
    	
    	String simHash1 = simHash.substring(0,16);
		String simHash2 = simHash.substring(16, 32);
		String simHash3 = simHash.substring(32, 48);
		String simHash4 = simHash.substring(48);
		RedisClient redis = new RedisClient();
        Jedis jedis = redis.jedis;
		jedis.select(1);
		jedis.lpush(simHash1, simHash);
    	jedis.select(2);
    	jedis.lpush(simHash2, simHash);
    	jedis.select(3);
    	jedis.lpush(simHash3, simHash);
    	jedis.select(4);
    	jedis.lpush(simHash4, simHash);
    	jedis.close();
    	return true;
    }
    
}