package com.demo.service;

public interface EmailService {
    
    public boolean sendVerificationCode(String email);
    
    public boolean verifyCode(String email, String code);
    
}