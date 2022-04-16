package com.project.demo.web;

import java.util.List;

import org.slf4j.Logger;
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
public List<BookVO> selectBookList(){
	return bookservice.selectBookList();
}

//@RequestMapping("/rest_login")
//public void login() {
//    LOG.info("GET successfully called on /login resource");
//}
}
