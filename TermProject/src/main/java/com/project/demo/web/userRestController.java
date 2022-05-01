package com.project.demo.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.UserService;
import com.project.demo.util.SHA256Util;
import com.project.demo.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class userRestController {	
	private final UserService userService;

	// 로그인
	@PostMapping(value = "/login_ok")
	public String memberLoginOk(String user_id, String user_pwd) {
		user_pwd = SHA256Util.encryptSHA256(user_pwd);
		System.out.println("비밀번호 변환 확인: " + SHA256Util.encryptSHA256(user_pwd));
		String msg = "";
		UserVO vo = userService.isLogin(user_id, user_pwd);
		msg = vo.getMsg();
		return msg;
	}

	// 회원가입
	@PostMapping(value = "/userInsert")
	public void memberJoinOk(UserVO vo) {
		userService.userInsert(vo);
	}

	// 회원정보
	@GetMapping("/userInfo")
	public UserVO memberInfo(String user_id) {
		UserVO vo = userService.selectUserInfo(user_id);
		return vo;
	}

	@PostMapping("/pwd_ok")
	public String pwdOk(String user_id, String user_pwd) {
		user_pwd = SHA256Util.encryptSHA256(user_pwd);
		String msg = "";
		UserVO vo = userService.isPwd(user_pwd, user_id);
		msg=vo.getMsg();
		return msg;
	}

	@PostMapping("/pwd_change")
	public void pwdChange(String user_pwd, String user_id, String user_email, String user_phone, String user_addr1) {
		user_pwd = SHA256Util.encryptSHA256(user_pwd);
		Map<String, String> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("user_pwd", user_pwd);
		map.put("user_email", user_email);
		map.put("user_phone", user_phone);
		map.put("user_addr1", user_addr1);
		System.out.println("비밀번호변경 값:" + user_pwd);
		userService.changPwd(map);
	}

	@PostMapping("/id_check")
	public String idCheck(String userId) {
		String user_id = userId;
		String msg = "";
		UserVO vo = userService.idDuplicationCheck(user_id);
		msg = vo.getMsg();
		return msg;
	}
}
