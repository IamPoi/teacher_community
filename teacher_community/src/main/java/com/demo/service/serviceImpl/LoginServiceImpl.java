package com.demo.service.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.MemberVO;
import com.demo.mapper.LoginMapper;
import com.demo.service.LoginService;
import com.demo.util.BCrypt;

@Service
public class LoginServiceImpl implements LoginService {

    private LoginMapper loginMapper;

    @Autowired
    public LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public MemberVO login(String email, String pw) throws Exception {
        MemberVO member = loginMapper.getMemberByEmail(email);

        System.out.println(member);
        
        if (member == null) {
            return null;
        }
        
        BCrypt bCrypt = new BCrypt();
        boolean passwordMatch = bCrypt.match(pw, member.getPw());
        
        if (!passwordMatch) {
            return null;
        }
        
        LocalDateTime now = LocalDateTime.now();
        if (member.getNextVerifyDue() == null || now.isAfter(member.getNextVerifyDue())) {
            // 재인증이 필요한 경우, member 객체에 특별한 표시를 하여 반환
            member.setPw(null); // 보안상 패스워드는 null로 설정
            return member;
        }
        
        return member;
    }


}
