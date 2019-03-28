package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.demo.dao.UserRepository;
import com.demo.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Mono<User> save(User user){
		return userRepository.save(user);
	}
	
	public Mono<Void> delete(Long id){
		Mono<Void> deleteById = userRepository.deleteById(id);
		return deleteById;
	}
	
	public Mono<Void> delete(User user){
		Mono<Void> delete = userRepository.delete(user);
		return delete;
	}
	
	public Mono<User> update(User user){
		Mono<User> save = userRepository.save(user);
		return save;
	}
	
	public Mono<User> findUserById(Long id){
		Mono<User> findById = userRepository.findById(id);
		return findById;
	}
	
	public Flux<User> findAllUser(){
		Flux<User> findAll = userRepository.findAll();
		return findAll;
	}
	
	public Flux<User> findAllUserByPage(Integer pageSize, Integer pageNo){
		//根据age升序排列
		Sort sort = new Sort(Direction.ASC, "age");
		//分页查询
		Flux<User> findAll = userRepository.findAll(sort).skip(pageSize * (pageNo-1)).limitRate(pageSize);
		return findAll;
	}
	
}
