package com.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.MemberVO;
import com.demo.service.LoginService;
import com.demo.util.APIResponse;

@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public <T> APIResponse<T> login(@RequestParam String email, @RequestParam String pw) {
        try {
            MemberVO loginResult = loginService.login(email, pw);

            if (loginResult != null) {
                LocalDateTime now = LocalDateTime.now();
                if (loginResult.getNextVerifyDue() == null || now.isAfter(loginResult.getNextVerifyDue())) {
                    return new APIResponse<>(false, null, "인증이 필요합니다.");
                }
                return new APIResponse<>(true, (T) loginResult, "로그인이 완료되었습니다.");
            } else {
                return new APIResponse<>(false, null, "이메일 또는 비밀번호가 올바르지 않습니다");
            }
        } catch (Exception e) {
            return new APIResponse<>(false, null, "로그인 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}
