package com.project.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.BookService;
import com.project.demo.vo.BookVO;

@RestController
public class BookRestController {
	@Autowired
	BookService bookservice;
	
//
	
@RequestMapping("/rest_prac")
//public List<BookVO> selectBookList(String page,String sort,String cate){
//		int curpage=Integer.parseInt(page);
//		int rowsize=12;
//		int start=(rowsize*curpage)-(rowsize);		
//		System.out.println(cate);
//		Map map=new HashMap();
//		map.put("sort",sort);
//		map.put("start", start);
//		map.put("end", rowsize);
//		map.put("cate",cate);
//		
//	return bookservice.selectBookList(map);
//}
public String selectBookList(String page,String sort,String cate){

	String json="";
	int curpage=Integer.parseInt(page);
	int rowsize=12;
	int start=(rowsize*curpage)-(rowsize);		
	System.out.println(cate);
	Map map= new HashMap();
	map.put("sort",sort);
	map.put("start", start);
	map.put("end", rowsize);
	map.put("cate",cate);
	List<BookVO> list=bookservice.selectBookList(map);
	int totalpage=bookservice.selectTotalPage(map);
	JSONArray arr=new JSONArray();
	int i=0;
	for(BookVO vo:list) {
		JSONObject obj=new JSONObject();
		obj.put("book_no", vo.getBookNo());
		obj.put("book_title", vo.getBookTitle());
		obj.put("book_img", vo.getBookImg());
		obj.put("book_author", vo.getBookAuthor());
		obj.put("book_publish", vo.getBookPublish());
		if(i==0) {
		obj.put("totalpage", totalpage);
		}
		arr.add(obj);
	}
	json=arr.toJSONString();	

	return json;
}

@RequestMapping("/find_ok")
public List<BookVO> find_BookList(String searchtext, String st){	
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

@RequestMapping("/genre")
public List<BookVO> genre_data() {
	
	return bookservice.genreBook();
	
}

}
