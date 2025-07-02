package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.MemberVO;
import com.demo.service.JoinService;
import com.demo.util.APIResponse;

@RestController
@RequestMapping("/v1")
public class JoinController {

	@Autowired
	private JoinService joinService;

	@PostMapping("/join")
	public <T> APIResponse<T> join(MemberVO info) {
		try {
			joinService.join(info);
			return new APIResponse<>(true, null, "회원가입이 완료되었습니다.");
		} catch (Exception e) {
			return new APIResponse<>(false, null, "회원가입 중 오류가 발생했습니다: " + e.getMessage());
		}
	}
	
}