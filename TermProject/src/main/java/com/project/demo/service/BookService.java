package com.project.demo.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.project.demo.dao.BookDAO;
import com.project.demo.vo.BookVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookDAO bookdao;

	public List<BookVO> selectBookList(Map<String, Object> map) {
		List<BookVO> blist = bookdao.selectBookList(map);
		return blist;
	}

	public List<BookVO> findBookList(Map<String, String> map) {
		List<BookVO> flist = bookdao.findBookList(map);

		return flist;
	}

	public BookVO detailBook(String book_no) {
		BookVO vo = bookdao.detailBook(book_no);
		return vo;
	}

	public List<BookVO> genreBook() {
		List<BookVO> glist = bookdao.genreBook();
		return glist;
	}

	public List<BookVO> selectBookLimit(Map<String, String> map) {
		List<BookVO> list = bookdao.selectBookLimit(map);
		return list;
	}
	
	public int selectTotalPage(Map<String, Object> map) {
		int totalpage = bookdao.selectTotalPage(map);
		return totalpage;
	}
}
