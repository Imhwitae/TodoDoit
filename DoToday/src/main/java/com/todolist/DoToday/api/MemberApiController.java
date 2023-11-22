package com.todolist.DoToday.api;

import com.todolist.DoToday.api.request.ApiMemberJoinDto;
import com.todolist.DoToday.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Long apiSocialMemberJoin(@RequestBody ApiMemberJoinDto joinApi) {
        return memberService.appMemberJoin(joinApi);
    }

//    @PostMapping("/checkid")
//    public ResponseEntity<String> apiCheckMember(@RequestBody String id) {
//
//    }

//    @GetMapping("/view")

}


