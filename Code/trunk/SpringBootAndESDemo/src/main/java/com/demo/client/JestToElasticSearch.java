package com.demo.client;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Emp;

import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;

@Service
public class JestToElasticSearch {

	@Autowired
	private JestClient jestClient;
	
	private String indexName = "demo_index";
	private String typeName = "emp_type";
	
	public Emp saveOrUpdateEmp(Emp emp){
		Index index = new Index.Builder(emp).index(indexName).type(typeName).build();
		try {
			DocumentResult result = jestClient.execute(index);
			String sourceAsString = result.getSourceAsString();
			System.out.println(sourceAsString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
	
	public Emp findEmpById(Integer id){
		Emp emp = new Emp();
		String searchStr = "{"+
				"\"query\":{"+
					"\"match\":{"+
						"\"id\":"+id+
					"}"+
				"}"+
			"}";
		Search search = new Search.Builder(searchStr).addIndex(indexName).addType(typeName).build();
		try {
			SearchResult execute = jestClient.execute(search);
			Hit<Emp,Void> firstHit = execute.getFirstHit(Emp.class);
			emp = firstHit.source;
			System.out.println(emp.getId()+"   "+emp.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
}
