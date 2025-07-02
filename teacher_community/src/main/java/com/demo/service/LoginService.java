package com.demo.service;

import com.demo.domain.MemberVO;

public interface LoginService {

    public MemberVO login(String email, String pw) throws Exception;

}
