package com.project.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.demo.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {
int userIdCount(String user_id); //아이디 확인
 
UserVO userGetPassword(String user_id); //패스워드 확인

void userInsert(UserVO vo); //회원 등록
}
