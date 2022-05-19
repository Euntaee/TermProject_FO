package com.project.demo.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.BookService;
import com.project.demo.vo.BookVO;
import com.project.demo.vo.BranchVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookRestController {
	private final BookService bookservice;
	
	//도서 목록 조회( 페이징, 카테고리, 지점)
	@PostMapping("/rest_prac")
	public List<BookVO> selectBookList(String page, String sort, String cate, String branchCode) {
		int curpage = Integer.parseInt(page);
		int rowsize = 12;
		int start = (rowsize * curpage) - (rowsize);
		System.out.println("branchcode:" + branchCode);
		Map<String, Object> map = new HashMap<>();
		map.put("sort", sort);
		map.put("start", start);
		map.put("end", rowsize);
		map.put("cate", cate);
		map.put("branchCode", branchCode);

		return bookservice.selectBookList(map);
	}
	
	//업로드 파일명 불러오기
	@GetMapping("/{name}")
	public ResponseEntity<InputStreamResource> getTermsConditions(@PathVariable("name") String name)
			throws FileNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "inline;filename=" + name);
		File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\upload\\" + name);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		return ResponseEntity.ok().headers(headers).contentLength(file.length()).body(resource);
	}
	
	//검색목록
	@PostMapping("/find_ok")
	public List<BookVO> findBookList(String searchtext, String st, String bt) {
		Map<String, String> map = new HashMap<>();
		map.put("st", st);
		map.put("searchtext", searchtext);
		map.put("bt", bt);
		System.out.println("searchtext, st, bt:"+searchtext+" "+st+" "+bt);
		return bookservice.findBookList(map);
	}
	
	//상세보기
	@GetMapping("/detail_ok")
	public BookVO detailData(String book_no) {
		BookVO vo = bookservice.detailBook(book_no);
		return vo;
	}

	//카테고리 리스트
	@GetMapping("/genre")
	public List<BookVO> genreData() {
		return bookservice.genreBook();
	}

	//메인화면 인기순 목록
	@PostMapping("/bookmain1")
	public List<BookVO> bookMain1(String sort) {
		Map<String, String> map = new HashMap<>();
		map.put("sort", sort);
		return bookservice.selectBookLimit(map);
	}

	//메인홤녀 최신순 목록
	@PostMapping("/bookmain2")
	public List<BookVO> bookMain2(String sort) {
		Map<String, String> map = new HashMap<>();
		map.put("sort", sort);
		return bookservice.selectBookLimit(map);
	}

	//총페이지
	@PostMapping("/totalpage")
	public int selectTotalPage(String cate, String branchCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("cate", cate);
		map.put("branchCode", branchCode);
		int totalpage = bookservice.selectTotalPage(map);
		return totalpage;
	}

	// 지점 리스트
	@GetMapping("/branchData")
	public List<BranchVO> selectBranchData() {

		return bookservice.selectBranchData();
	}
	
	//같은 카테고리 추천 목록 
	@PostMapping("/selectRandData")
	public List<BookVO> selectDetailRand(String book_genre, String branch_code) {
		System.out.println("책이랑 지점"+book_genre+branch_code);
		Map<String, Object> map = new HashMap<>();
		map.put("book_genre", book_genre);
		map.put("branch_code", branch_code);
		return bookservice.selectDetailRand(map);
	}

}
