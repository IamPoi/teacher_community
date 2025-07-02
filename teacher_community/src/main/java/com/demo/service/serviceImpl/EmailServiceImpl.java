package com.demo.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demo.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    private final Map<String, VerificationData> verificationCodes = new ConcurrentHashMap<>();
    
    private static class VerificationData {
        private final String code;
        private final LocalDateTime expiryTime;
        
        public VerificationData(String code, LocalDateTime expiryTime) {
            this.code = code;
            this.expiryTime = expiryTime;
        }
        
        public String getCode() {
            return code;
        }
        
        public LocalDateTime getExpiryTime() {
            return expiryTime;
        }
        
        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expiryTime);
        }
    }
    
    @Override
    public boolean sendVerificationCode(String email) {
        try {
            String verificationCode = generateRandomCode();
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
            
            verificationCodes.put(email, new VerificationData(verificationCode, expiryTime));
            
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("teco.email2025@gmail.com");
            message.setTo(email);
            message.setSubject("[Teacher Community] 이메일 인증 코드");
            message.setText("안녕하세요!\n\n" +
                           "Teacher Community 회원가입을 위한 이메일 인증 코드입니다.\n\n" +
                           "인증 코드: " + verificationCode + "\n\n" +
                           "이 코드는 5분간 유효합니다.\n" +
                           "코드를 입력하여 이메일 주소를 확인해주세요.\n\n" +
                           "감사합니다.");
            
            // 임시: 메일 발송 시뮬레이션
            System.out.println("=== 메일 발송 시뮬레이션 ===");
            System.out.println("받는 사람: " + email);
            System.out.println("인증 코드: " + verificationCode);
            System.out.println("============================");
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean verifyCode(String email, String code) {
        System.out.println("=== 인증 코드 검증 시작 ===");
        System.out.println("이메일: " + email);
        System.out.println("입력된 코드: " + code);
        
        VerificationData data = verificationCodes.get(email);
        
        if (data == null) {
            System.out.println("인증 코드가 없습니다. (발송되지 않았거나 이미 사용됨)");
            return false;
        }
        
        System.out.println("저장된 코드: " + data.getCode());
        System.out.println("만료 시간: " + data.getExpiryTime());
        
        if (data.isExpired()) {
            System.out.println("인증 코드가 만료되었습니다.");
            verificationCodes.remove(email);
            return false;
        }
        
        if (data.getCode().equals(code)) {
            System.out.println("인증 성공!");
            verificationCodes.remove(email);
            return true;
        }
        
        System.out.println("인증 코드가 일치하지 않습니다.");
        return false;
    }
    
    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}