package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.User;
import com.demo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/save")
	public void save(User user){
		Mono<User> save = userService.save(user);
		user = save.block();
		System.out.println(user.getName());
	}
	
	@RequestMapping(value="/findUserById")
	public User findUserById(Long id){
		Mono<User> findUserById = userService.findUserById(id);
		User user = findUserById.block();
		return user;
	}
	
	@RequestMapping(value="/findAllUser")
	public List<User> findAllUser(){
		Flux<User> findAllUser = userService.findAllUser();
		Mono<List<User>> collectList = findAllUser.collectList();
		List<User> list = collectList.block();
		return list;
	}
	
	@RequestMapping(value="/delete")
	public void delete(Long id){
		Mono<Void> delete = userService.delete(id);
		System.out.println(delete.block());
	}
	
	@RequestMapping(value="/update")
	public void update(User user){
		Mono<User> update = userService.update(user);
		user = update.block();
		System.out.println(user.getName());
	}
}
