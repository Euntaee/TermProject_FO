package com.project.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public List<BookVO> selectBookList(String page){
//	String json="";
//	try {
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start=(rowsize*curpage)-(rowsize);
		//int totalpage=bookservice.selectTotalPage(map);
		//int end=(rowsize*curpage);
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", rowsize);
		
//		List<BookVO> list=bookservice.selectBookList(map);
//		int totalpage=bookservice.selectTotalPage(map);
//		System.out.println("ㅇㅇ"+bookservice.selectTotalPage(map));
//		 System.out.println("bb"+bookservice.selectBookList(map));
//		JSONArray arr=new JSONArray();
//		int i=0;
//		for(BookVO vo:list) {
//			JSONObject obj=new JSONObject();
//			obj.put("book_no", vo.getBook_no());
//	     	obj.put("book_title", vo.getBook_title());
//			obj.put("book_img", vo.getBook_img());
//			obj.put("book_author", vo.getBook_author());
////			if(i==0) {
////				obj.put("curpage", curpage);
////				obj.put("totalpage", totalpage);
////			}
//			arr.add(obj);			
//			i++;
//			System.out.println(start);			
//			System.out.println("page="+ curpage);
//		}
//	}catch (Exception e) {}
//	
//	return json;
	return bookservice.selectBookList(map);
}

@RequestMapping("/find_ok")
public List<BookVO> find_BookList(String searchtext, String st){	
//	String json="";
//	try {
//	Map map= new HashMap();
//	map.put("st", st);
//	map.put("searchtext", searchtext);	
//	List<BookVO> list=bookservice.findBookList(map);	
//	
//	JSONArray arr=new JSONArray();
//	for(BookVO vo:list) {
//		JSONObject obj=new JSONObject();
//		obj.put("book_no", vo.getBook_no());
//		obj.put("book_title", vo.getBook_title());
//		obj.put("book_img", vo.getBook_img());
//		obj.put("book_author", vo.getBook_author());	
//		arr.add(obj);
//	}	
//	json=arr.toJSONString();		
//	}catch (Exception e) {}
//
//	System.out.println("st="+st);
//	System.out.println("searchtext="+searchtext);	
//	return json;
	Map map= new HashMap();
	map.put("st", st);
	map.put("searchtext", searchtext);
	System.out.println("st="+st);
	System.out.println("searchtext="+searchtext);
	return bookservice.findBookList(map);	
}

@RequestMapping("/detail_ok")
public BookVO detail_data(String book_no) {
	BookVO vo=bookservice.detailBook(book_no);	
	System.out.println("book_no="+book_no);
	return vo;
}
}
