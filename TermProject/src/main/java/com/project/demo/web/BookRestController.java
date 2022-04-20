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

@RequestMapping(value = "/find_ok",produces="text/plain;charset=UTF-8")
public String find_BookList(String searchtext, String st){	
	String json="";
	try {
	Map map= new HashMap();
	map.put("st", st);
	map.put("searchtext", searchtext);	
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
	}catch (Exception e) {}

	System.out.println("st="+st);
	System.out.println("searchtext="+searchtext);	
	return json;
//	Map map= new HashMap();
//	map.put("st", st);
//	map.put("searchtext", searchtext);
//	System.out.println("ss="+st);
//	System.out.println("fs="+searchtext);	
//	return bookservice.findBookList(map);	
}
}
