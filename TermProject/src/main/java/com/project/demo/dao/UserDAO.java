package com.project.demo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.demo.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {
	int userIdCount(String user_id); // 아이디 확인

	UserVO userGetPassword(String user_id); // 패스워드 확인

	void userInsert(UserVO vo); // 회원 등록

	UserVO selectUserInfo(String user_id); 

	UserVO isPwd(String user_pwd, String user_id);

	void changPwd(Map<String, String> map);
	
	UserVO idCheck(String user_id);
	
	void deleteUser(Map<String, String> map);
	
	int rentCount(Map<String, String> map);
}
