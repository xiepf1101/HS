package com.demo.redis.service;

import java.util.List;

public interface SimHashService {

	boolean saveSimHashFragment(String simHash);
	
	List<String> searchBySimHashFragment(Integer dbId, String simHash);
	
}
