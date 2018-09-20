package com.demo.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Emp;
import com.demo.service.EmpService;

import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	JestClient jestClient;  //jest通过jestClient操作elasticSearch   
	
	private String indexName = "demo_index";
	private String typeName = "emp_type";
	
	@Override
	public Emp saveOrUpdateEmp(Emp emp) {
		// TODO Auto-generated method stub
		Index build = new Index.Builder(emp).index(indexName).type(typeName).build();
		try {
			DocumentResult execute = jestClient.execute(build);
			String sourceAsString = execute.getJsonString();
			System.out.println(sourceAsString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public Emp findEmpById(Integer id) {
		String searchStr = "{"+
								"\"query\":{"+
									"\"match\":{"+
										"\"id\":"+id+
									"}"+
								"}"+
							"}";
		
		// TODO Auto-generated method stub
		Search build = new Search.Builder(searchStr).addIndex(indexName).addType(typeName).build();
		try {
			SearchResult execute = jestClient.execute(build);
			Hit<Emp,Void> firstHit = execute.getFirstHit(Emp.class);
			Emp emp = firstHit.source;
			return emp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
}
