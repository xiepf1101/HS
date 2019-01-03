package com.demo.redis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.stereotype.Service;

import com.demo.redis.dao.CommonRedisDao;
import com.demo.redis.service.SimHashService;

@Service
public class SimHashServiceImpl implements SimHashService{

	@Autowired
	private CommonRedisDao dao;
	
	@Override
	public boolean saveSimHashFragment(String simHash) {
		// TODO Auto-generated method stub
		
		String simHash1 = simHash.substring(0,16);
		String simHash2 = simHash.substring(16, 32);
		String simHash3 = simHash.substring(32, 48);
		String simHash4 = simHash.substring(48);
		DefaultStringRedisConnection redis = dao.select(1);
		redis.lPush(simHash1, simHash);
		redis.select(2);
		redis.lPush(simHash2, simHash);
		redis.select(3);
		redis.lPush(simHash3, simHash);
		redis.select(4);
		redis.lPush(simHash4, simHash);
		redis.close();
		return false;
	}

	@Override
	public List<String> searchBySimHashFragment(Integer dbId, String simHash) {
		// TODO Auto-generated method stub
		try {
			
			DefaultStringRedisConnection redis = dao.select(1);
			List<String> lRange = redis.lRange(simHash, 0, -1);
			redis.close();
			return lRange;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
