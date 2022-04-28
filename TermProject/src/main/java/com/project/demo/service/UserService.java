package com.project.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.protocol.a.authentication.Sha256PasswordPlugin;
import com.project.demo.dao.UserDAO;
import com.project.demo.util.SHA256Util;
import com.project.demo.vo.UserVO;

@Service
public class UserService {
	private static final String DUPLICATE_ID = "NO";
	private static final String WRONG_PASSWORD = "NOPWD";
	private static final String MSG_OK = "OK";
	@Autowired
	UserDAO userdao; // 필드인젝션을 권장하지 않음

	public UserVO isLogin(String userId, String userPwd) {
		UserVO vo = new UserVO();
		int count = userdao.userIdCount(userId);
		if (count == 0) {
			vo.setMsg("NOID");
		} else {
			UserVO dbVO = userdao.userGetPassword(userId);
			if (userPwd.equals(dbVO.getUserPwd())) {
				vo.setMsg(MSG_OK);
				vo.setUserId(userId);
				vo.setUserName(dbVO.getUserName());
			} else {
				vo.setMsg(WRONG_PASSWORD);
			}
		}
		return vo;
	}

	public void userInsert(UserVO vo) {
		vo.setUserPwd(SHA256Util.encryptSHA256(vo.getUserPwd()));
		userdao.userInsert(vo);
	}

	public UserVO selectUserInfo(String user_id) {
		return userdao.selectUserInfo(user_id);
	}

	public UserVO isPwd(String user_pwd, String user_id) {
		UserVO vo = userdao.userGetPassword(user_id);
		if (user_pwd.equals(vo.getUserPwd())) {
			vo.setMsg(MSG_OK);
			vo.setUserId(user_id);
			vo.setUserName(vo.getUserName());
		} else {
			vo.setMsg(WRONG_PASSWORD);
		}
		return vo;
	}

	public void changPwd(Map map) {
//		System.out.println("service에서 비밀번호 값:"+user_pwd);
		userdao.changPwd(map);
	}

	public UserVO idDuplicationCheck(String userId) {
		UserVO vo = new UserVO();
		int count = userdao.userIdCount(userId);
		if (count == 0) {
			vo.setMsg(MSG_OK);
		} else {
			vo.setMsg(DUPLICATE_ID);
		}
		return vo;
	}
}
