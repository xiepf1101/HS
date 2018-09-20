package com.demo.service;

import com.demo.entity.Emp;

public interface EmpService {

	Emp saveOrUpdateEmp(Emp emp);
	
	Emp findEmpById(Integer id);

}

