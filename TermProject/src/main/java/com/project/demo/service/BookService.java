package com.project.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.dao.BookDAO;
import com.project.demo.vo.BookVO;

@Service
public class BookService{
	@Autowired
	BookDAO bookdao;
	public List<BookVO> selectBookList(Map map){
		List<BookVO> blist = bookdao.selectBookList(map);
		return blist;
	}
	
	public int selectTotalPage(Map map) {
		return bookdao.selectTotalPage(map);
	}
	public List<BookVO> findBookList(Map map){
		List<BookVO> flist=bookdao.findBookList(map);
		
		return flist;
	}
	public BookVO detailBook(String book_no) {
		BookVO vo=bookdao.detailBook(book_no);
		return vo;
	}
}
