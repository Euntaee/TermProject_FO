package com.project.demo.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.project.demo.dao.BookDAO;
import com.project.demo.vo.BookVO;
import com.project.demo.vo.BranchVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookDAO bookdao;
	//도서조회
	public List<BookVO> selectBookList(Map<String, Object> map) {
		List<BookVO> blist = bookdao.selectBookList(map);
		
		return blist;
	}
	//도서 검색
	public List<BookVO> findBookList(Map<String, String> map) {
		List<BookVO> flist = bookdao.findBookList(map);

		return flist;
	}
	//도서 상세조회
	public BookVO detailBook(String book_no) {
		BookVO vo = bookdao.detailBook(book_no);

		    String content = null;	
			content = vo.getBookContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
			if(content != null) {
				vo.setBookContent(content);		
		}
		return vo;
	}
	// 도서 카테고리 조회
	public List<BookVO> genreBook() {
		List<BookVO> glist = bookdao.genreBook();
		return glist;
	}
	//도서 조회 (페이징)
	public List<BookVO> selectBookLimit(Map<String, String> map) {
		List<BookVO> list = bookdao.selectBookLimit(map);
		return list;
	}
	// 총페이지
	public int selectTotalPage(Map<String, Object> map) {
		int totalpage = bookdao.selectTotalPage(map);
		return totalpage;
	}
	
	//지점명 조회
	public List<BranchVO> selectBranchData(){
		List<BranchVO> list= bookdao.selectBranchData();
		return list;
	}
	
	public List<BookVO> selectDetailRand(Map<String, Object> map) {
		List<BookVO> list = bookdao.selectDetailRand(map);
		return list;
	}
}
