package com.demo.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.MemberVO;
import com.demo.mapper.JoinMapper;
import com.demo.service.JoinService;
import com.demo.util.BCrypt;

@Service
public class JoinServiceImpl implements  JoinService{

    private JoinMapper jm;

    @Autowired
    public JoinServiceImpl(JoinMapper jm){
        this.jm = jm;
    }

    @Override
    public void join(MemberVO info) throws Exception {
        // UUID 생성
        String uuid = UUID.randomUUID().toString();
        info.setId(uuid);
        
        // 비밀번호 암호화
        BCrypt bCrypt = new BCrypt();
        String encodePw = bCrypt.encoding(info.getPw());
        info.setPw(encodePw);
        
        // 기본값 설정
        LocalDateTime now = LocalDateTime.now();
        
        // 역할이 설정되지 않았으면 기본값 설정
        if (info.getRole() == null || info.getRole().isEmpty()) {
            info.setRole("TEACHER");
        }
        
        // 이메일 인증 상태 (모든 사용자 기본 true)
        if ("ADMIN".equals(info.getRole())) {
            info.setEmailVerified(true);
            info.setSecondaryVerified(false);
            info.setLastVerifiedAt(now);
            info.setNextVerifyDue(now.plusYears(100)); // ADMIN은 100년 후
        } else {
            info.setEmailVerified(true);
            info.setSecondaryVerified(false);
            info.setLastVerifiedAt(now);
            info.setNextVerifyDue(now.plusYears(1)); // 일반 사용자는 1년 후
        }
        
        // 생성/수정 시간 설정
        info.setCreate_at(now);
        info.setUpdated_at(now);

        System.out.println(info);
        
        // DB에 저장
        try {
            jm.join(info);
            System.out.println("DB 저장 성공: " + info.getId());
        } catch (Exception dbException) {
            System.out.println("DB 저장 실패: " + dbException.getMessage());
            dbException.printStackTrace();
            throw dbException;
        }
    }

    

}
