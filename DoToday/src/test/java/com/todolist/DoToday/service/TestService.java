package com.todolist.DoToday.service;

import com.todolist.DoToday.mapper.MemberMapperRepository;
import com.todolist.DoToday.response.MemberDetailDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private MemberMapperRepository memberMapperRepository;

    @Test
    @DisplayName("멤버 객체가 null이 아니다.")
    void checkMember() {
//        Assertions.assertNotNull(memberMapperRepository.findById("test@gmail.com"));
        Assertions.assertNotNull(memberMapperRepository.findAll());
    }
}
