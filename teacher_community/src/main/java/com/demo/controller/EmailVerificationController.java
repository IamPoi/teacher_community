package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.EmailService;

@RestController
@RequestMapping("/v1")
public class EmailVerificationController {
    
    @Autowired
    private EmailService emailService;
    
    @PostMapping("/email/send-code")
    public ResponseEntity<Map<String, Object>> sendVerificationCode(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        String email = request.get("email");
        if (email == null || email.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "이메일이 필요합니다.");
            return ResponseEntity.badRequest().body(response);
        }
        
        boolean sent = emailService.sendVerificationCode(email);
        if (sent) {
            response.put("success", true);
            response.put("message", "인증 코드가 발송되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "인증 코드 발송에 실패했습니다.");
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    @PostMapping("/email/verify-code")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        String email = request.get("email");
        String code = request.get("code");

        System.out.println(email+" "+code);
        
        if (email == null || email.trim().isEmpty() || code == null || code.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "이메일과 인증 코드가 필요합니다.");
            return ResponseEntity.badRequest().body(response);
        }
        
        boolean verified = emailService.verifyCode(email, code);
        if (verified) {
            response.put("success", true);
            response.put("message", "이메일 인증이 완료되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "인증 코드가 올바르지 않거나 만료되었습니다.");
            return ResponseEntity.badRequest().body(response);
        }
    }
}