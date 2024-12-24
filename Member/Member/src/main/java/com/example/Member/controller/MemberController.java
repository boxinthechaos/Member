package com.example.Member.controller;

import com.example.Member.dto.MemberDto;
import com.example.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService; // 의존성 주입 필드 선언

    @GetMapping("/member/save")
    public String saveForm() {
        return "save"; // 회원 저장 폼을 반환 (save.html)
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDto);
        memberService.save(memberDto); // 회원 저장 서비스 호출
        return "index"; // 회원 저장 후 index 화면으로 이동
    }
}
