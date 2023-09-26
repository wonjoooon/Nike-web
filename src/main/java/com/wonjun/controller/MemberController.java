package com.wonjun.controller;

import com.wonjun.model.MemberJoinForm;
import com.wonjun.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/joinPage")
    public String joinPage(MemberJoinForm memberJoinForm) {
        return "joinPage";
    }

    @PostMapping("/join")
    public String join(@Valid MemberJoinForm memberJoinForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "joinPage";
        }

        if (!memberJoinForm.getPassword1().equals(memberJoinForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 비밀번호가 다릅니다.");
            return "joinPage";
        }

        try{
            memberService.join(memberJoinForm.getUsername(), memberJoinForm.getPassword1(), memberJoinForm.getEmail(), memberJoinForm.getAddress(), memberJoinForm.getName());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("joinFailed", "이미 가입한 회원입니다.");
            return "joinPage";
        }
        catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("joinFailed", e.getMessage());
            return "joinPage";
        }

        return "redirect:/member/loginPage";
    }

    @GetMapping("/loginPage")
    public String login() {
        return "loginPage";
    }
}
