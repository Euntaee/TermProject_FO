package com.project.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.demo.vo.UserVO;

@Repository
@Mapper
public interface UserDAO {
Integer userIdCount(String user_id); //���̵� Ȯ��
 
UserVO userGetPassword(String user_id); //��й�ȣ Ȯ��
}
