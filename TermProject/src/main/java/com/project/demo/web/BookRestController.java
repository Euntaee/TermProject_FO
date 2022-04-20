package com.project.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.BookService;
import com.project.demo.vo.BookVO;
import com.project.demo.vo.UserVO;

@RestController
public class BookRestController {
	@Autowired
	BookService bookservice;
	
//
	
@RequestMapping("/rest_prac")
public List<BookVO> selectBookList(){
	return bookservice.selectBookList();
}

@RequestMapping("/find_ok")
public String find_BookList(String ss, String fs){

	String json="";
	Map map= new HashMap();
	map.put("ss", ss);
	map.put("fs", fs);	
	List<BookVO> list=bookservice.findBookList(map);
	JSONArray arr=new JSONArray();
	for(BookVO vo:list) {
		JSONObject obj=new JSONObject();
		obj.put("book_no", vo.getBook_no());
		obj.put("book_title", vo.getBook_title());
		obj.put("book_img", vo.getBook_img());
		obj.put("book_author", vo.getBook_author());
		arr.add(obj);
	}
	json=arr.toJSONString();	

	System.out.println("ss="+ss);
	System.out.println("fs="+fs);
	
	return json;
}
}
