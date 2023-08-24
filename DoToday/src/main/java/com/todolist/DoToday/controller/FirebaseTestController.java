package com.todolist.DoToday.controller;

import com.todolist.DoToday.entity.Test;
import com.todolist.DoToday.service.FirebaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FirebaseTestController {

    private final FirebaseServiceImpl firebaseService;

    @GetMapping("/insertMember")
    public String insertMember(@RequestBody Test test) throws Exception{
        return firebaseService.insert(test);
    }

    @GetMapping("/getMemberDetail")
    public Test getMemberDetail(@RequestParam String id) throws Exception{
        return firebaseService.getDetail(id);
    }

    @GetMapping("/updateMember")
    public String updateMember(@RequestParam Test test) throws Exception{
        return firebaseService.update(test);
    }

    @GetMapping("/deleteMember")
    public String deleteMember(@RequestParam String id) throws Exception{
        return firebaseService.delete(id);
    }

}
