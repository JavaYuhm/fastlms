package com.javayuhm.fastlms.member.controller;

import com.javayuhm.fastlms.member.entity.Member;
import com.javayuhm.fastlms.member.model.MemberInput;
import com.javayuhm.fastlms.member.model.ResetPasswordInput;
import com.javayuhm.fastlms.member.repository.MemberRepository;
import com.javayuhm.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class MemberController {

    //@Autowired

    //private final MemberRepository memberRepository;
    /*
    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/

    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String login(){
        return "member/login";

    }

    @GetMapping("/member/find/password")
    public String findPassword(){
        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findePasswordSubmit(Model model,ResetPasswordInput resetPasswordInput){

        boolean result = false;
        try{
            result =  memberService.sendResetPassword(resetPasswordInput);
        } catch (Exception e){

        }
       // boolean result =  memberService.sendResetPassword(resetPasswordInput);

        model.addAttribute("result", result);

         return "member/find_password_result";
    }

    //@RequestMapping(value = "/member/register", method = RequestMethod.GET)
    @GetMapping("/member/register")
    public String register(){
        System.out.println("Get run");
        return "member/register";

    }

    //@RequestMapping(value = "/member/register", method = RequestMethod.POST)
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response, MemberInput parameter)
    {
        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);
/*
        System.out.println(parameter.toString());

        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setPassword(parameter.getPassword());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setRegDt(LocalDateTime.now());

        memberRepository.save(member);
*/

        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model,HttpServletRequest request){
        String uuid = request.getParameter("id");

        boolean result = memberService.emailAuth(uuid);

        model.addAttribute("result", result);


        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String mememberInfo(){

        return "member/info";
    }
}


