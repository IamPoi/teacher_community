package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.demo.domain.MemberVO;

@Repository
@Mapper
public interface LoginMapper {
    
    public MemberVO getMemberByEmail(String email);
    
}
