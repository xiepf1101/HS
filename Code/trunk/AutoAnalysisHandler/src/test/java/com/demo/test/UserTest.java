package com.demo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.entity.User;
import com.demo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void save(){
		User user = new User(1L, "张三", 19, "123@qq.com");
		Mono<User> save = userService.save(user);
		user = save.block();
		System.out.println(user.getName());
	}
	
	@Test
	public void findUserById(){
		Long id = 1L;
		Mono<User> findUserById = userService.findUserById(id);
		System.out.println(findUserById);
		User user = findUserById.block();
		System.out.println(user.getName());
	}
	
	@Test
	public void findUserAll(){
		Flux<User> findAllUser = userService.findAllUser();
		Mono<List<User>> collectList = findAllUser.collectList();
		List<User> block = collectList.block();
		for (User user : block) {
			System.out.println(user.getName());
		}
	}
	
	@Test
	public void update(){
		User user = new User(1L, "王五", 18, "123@qq.com");
		Mono<User> update = userService.update(user);
		user = update.block();
		System.out.println(user.getName());
	}
	
	@Test
	public void delete(){
		Long id = 1L;
		Mono<Void> delete = userService.delete(id);
		System.out.println(delete);
	}
	
	@Test
	public void findUserAllByPage(){
		Flux<User> findAllUser = userService.findAllUserByPage(5, 1);
		Mono<List<User>> collectList = findAllUser.collectList();
		List<User> block = collectList.block();
		for (User user : block) {
			System.out.println(user.getName()+"===="+user.getAge());
		}
	}
	
}
