package com.project.demo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.BookService;
import com.project.demo.vo.BookVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookRestController {
	private final BookService bookservice;

	@PostMapping("/rest_prac")
	public List<BookVO> selectBookList(String page, String sort, String cate) {
		int curpage = Integer.parseInt(page);
		int rowsize = 12;
		int start = (rowsize * curpage) - (rowsize);
		Map<String, Object> map = new HashMap<>();
		map.put("sort", sort);
		map.put("start", start);
		map.put("end", rowsize);
		map.put("cate", cate);		
		return bookservice.selectBookList(map);
	}

	@PostMapping("/find_ok")
	public List<BookVO> findBookList(String searchtext, String st) {
		Map<String, String> map = new HashMap<>();
		map.put("st", st);
		map.put("searchtext", searchtext);
		return bookservice.findBookList(map);
	}

	@GetMapping("/detail_ok")
	public BookVO detailData(String book_no) {
		BookVO vo = bookservice.detailBook(book_no);
		return vo;
	}

	@GetMapping("/genre")
	public List<BookVO> genreData() {
		return bookservice.genreBook();
	}

	@PostMapping("/bookmain1")
	public List<BookVO> bookMain1(String sort) {
		Map<String, String> map = new HashMap<>();
		return bookservice.selectBookLimit(map);
	}

	@PostMapping("/bookmain2")
	public List<BookVO> bookMain2(String sort) {
		Map<String, String> map = new HashMap<>();
		return bookservice.selectBookLimit(map);
	}

	@PostMapping("/totalpage")
	public int selectTotalPage(String cate) {
		Map<String, Object> map = new HashMap<>();		
		map.put("cate", cate);
		int totalpage=bookservice.selectTotalPage(map);		
		System.out.println("totalpage:"+totalpage);
		return totalpage;
	}

}
