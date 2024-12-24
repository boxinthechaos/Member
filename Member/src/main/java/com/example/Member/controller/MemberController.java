package com.example.Member.controller;

import com.example.Member.dto.MemberDto;
import com.example.Member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // MemberService에 대한 멤버를 사용 가능
@RequestMapping("/members") // 기본 경로 설정
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/new")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/new") // 회원가입 요청
    public String save(@ModelAttribute MemberDto memberDto) {
        logger.info("MemberController.save");
        logger.info("memberDTO = {}", memberDto);
        memberService.save(memberDto);

        return "login";
    }

    // 로그인 페이지 출력
    @GetMapping("/auth")
    public String loginForm() {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/auth")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session) {
        MemberDto loginResult = memberService.login(memberDto);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // 로그인 실패
            return "login";
        }
    }

    // 회원 전체 조회
    @GetMapping
    public String findAll(Model model) {
        List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("memberList", memberDtoList);
        return "list";
    }

    // 회원 상세 조회
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        MemberDto memberDto = memberService.findById(id);
        model.addAttribute("member", memberDto);
        return "detail";
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        memberService.deleteById(id);
        return "redirect:/members"; // 회원 목록 페이지로 리다이렉트
    }
}
