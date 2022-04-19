package com.project.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.demo.vo.BookVO;

@Repository
@Mapper	
public interface BookDAO {
	List<BookVO> selectBookList();
	List<BookVO> bookFindData(String ss);
}
