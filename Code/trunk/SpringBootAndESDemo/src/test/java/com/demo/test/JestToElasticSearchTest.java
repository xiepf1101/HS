package com.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.entity.Emp;
import com.demo.service.EmpService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JestToElasticSearchTest {

	@Autowired
	private EmpService empService;
	
	@Test
	public void testSaveOrUpdate(){
		Emp emp = new Emp();
		emp.setId(1);
		emp.setAge(30);
		emp.setName("Peter");
		emp.setSex("man");
		emp.setInfo("The sun is handsome");
		empService.saveOrUpdateEmp(emp);
	}
	
	@Test
	public void testSearch(){
		Emp emp = empService.findEmpById(1);
		System.out.println(emp.getId()+"    "+emp.getName());
	}
	
}
