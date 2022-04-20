package com.project.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.demo.vo.BookVO;

@Repository
@Mapper	
public interface BookDAO {
	List<BookVO> selectBookList();
	List<BookVO> findBookList(Map map);
}
