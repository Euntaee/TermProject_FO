package com.project.demo.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.service.UserService;
import com.project.demo.util.SHA256Util;
import com.project.demo.vo.UserVO;

@RestController
public class userRestController {
    @Autowired
    UserService userService;

    //로그인
    @RequestMapping(value = "/login_ok")
    public String member_login_ok(String user_id, String user_pwd) {
        user_pwd = SHA256Util.encryptSHA256(user_pwd);
        System.out.println("비밀번호 변환 확인: " + SHA256Util.encryptSHA256(user_pwd));
        String msg = "";
        UserVO vo = userService.isLogin(user_id, user_pwd);
        if (vo.getMsg().equals("OK")) {

//			  req.getSession().getAttribute(user_id());
//			  System.out.println(req.getSession().getAttribute(user_id));
//			   session.getAttribute(user_id);
//			   System.out.println((String)session.getAttribute(user_id));
        }
        msg = vo.getMsg();
        System.out.println(msg);

        return msg;
    }

    //회원가입
    @RequestMapping(value = "/userInsert")
    public void member_join_ok(UserVO vo) {
//		   vo.setUser_phone(vo.getTel1() + "-" + vo.getTel2() + "-" + vo.getTel3());

        userService.userInsert(vo);
    }

    //회원정보
    @RequestMapping("/userInfo")
    public UserVO member_info(String user_id) {
        UserVO vo = userService.selectUserInfo(user_id);
        return vo;
    }

    @RequestMapping("/pwd_ok")
    public String pwd_ok(String user_id, String user_pwd) {
        user_pwd = SHA256Util.encryptSHA256(user_pwd);
        String msg = "";
        UserVO vo = userService.isPwd(user_pwd, user_id);
        return msg;
    }

    @RequestMapping("/pwd_change")
    public void pwd_change(String user_pwd, String user_id) {
        user_pwd = SHA256Util.encryptSHA256(user_pwd);
        Map map = new HashMap();
        map.put("user_id", user_id);
        map.put("user_pwd", user_pwd);
        System.out.println("비밀번호변경 값:" + user_pwd);
        userService.changPwd(map);
    }
}
