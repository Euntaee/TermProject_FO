package com.project.demo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.demo.vo.BookVO;

@Repository
@Mapper
public interface BookDAO {
	List<BookVO> selectBookList(Map<String, Object> map);

	int selectTotalPage(Map<String, Object> map);

	List<BookVO> findBookList(Map<String, String> map);

	BookVO detailBook(String book_no);

	List<BookVO> genreBook();
}
