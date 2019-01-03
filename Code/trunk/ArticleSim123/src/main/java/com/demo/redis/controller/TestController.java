package com.demo.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.redis.dao.CommonRedisDao;

@RestController
@RequestMapping("/redis")
public class TestController {

    private final CommonRedisDao dao;

    @Autowired
    public TestController(CommonRedisDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "get/{key}", method = RequestMethod.GET)
    public void find(@PathVariable("key") String key) {
        String value = dao.getValue(key);
        System.out.println(value);
    }

    @RequestMapping(value = "add/{key}/{value}", method = RequestMethod.GET)
    public void add(@PathVariable("value") String value, @PathVariable("key") String key) {
        System.out.println(dao.cacheValue(key, value));
    }

    @RequestMapping(value = "del/{key}", method = RequestMethod.GET)
    public void del(@PathVariable("key") String key) {
        System.out.println(dao.removeValue(key));
    }

    @RequestMapping(value = "count/{key}", method = RequestMethod.GET)
    public void count(@PathVariable("key") String key) {
        System.out.println(dao.getListSize(key));
    }


}
