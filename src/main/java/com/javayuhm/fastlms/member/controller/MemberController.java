package com.javayuhm.fastlms.member.controller;

import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.course.dto.TakeCourseDto;
import com.javayuhm.fastlms.course.entity.TakeCourse;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.course.service.TakeCourseService;
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
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

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
    private final TakeCourseService takeCourseService;

    @RequestMapping("/member/login")
    public String login() {
        return "member/login";

    }

    @GetMapping("/member/find/password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findePasswordSubmit(Model model, ResetPasswordInput resetPasswordInput) {

        boolean result = false;
        try {
            result = memberService.sendResetPassword(resetPasswordInput);
        } catch (Exception e) {

        }
        // boolean result =  memberService.sendResetPassword(resetPasswordInput);

        model.addAttribute("result", result);

        return "member/find_password_result";
    }

    //@RequestMapping(value = "/member/register", method = RequestMethod.GET)
    @GetMapping("/member/register")
    public String register() {
        System.out.println("Get run");
        return "member/register";

    }

    //@RequestMapping(value = "/member/register", method = RequestMethod.POST)
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response, MemberInput parameter) {
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
    public String emailAuth(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("id");

        boolean result = memberService.emailAuth(uuid);

        model.addAttribute("result", result);


        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String mememberInfo(Model model, Principal principal) {

        String userId = principal.getName();

        MemberDto detail =memberService.detail(userId);
        model.addAttribute("detail", detail);

        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(Model model, MemberInput parameter ,Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        ServiceResult result =memberService.updateMemberInfo(parameter);

        if(!result.isResult()){
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/password")
    public String memberPassword(Model model, Principal principal) {

        String userId = principal.getName();

        MemberDto detail =memberService.detail(userId);
        model.addAttribute("detail", detail);

        return "member/password";
    }
    @PostMapping("/member/password")
    public String memberPasswordSubmit(Model model, Principal principal,MemberInput parameter) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        ServiceResult result =memberService.updateMemberPassword(parameter);
        if(!result.isResult()){
            model.addAttribute("message", "업데이트에 실패했습니다.");
            return "common/error";
        }

        return "member/password";
    }

    @GetMapping("/member/takecourse")
    public String memberTakeCourse(Model model, Principal principal) {

        String userId = principal.getName();

        List<TakeCourseDto> takeCourseDtoList = takeCourseService.myCouse(userId);

        MemberDto detail =memberService.detail(userId);
        model.addAttribute("detail", detail);

        return "member/takecourse";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        model.addAttribute("uuid", uuid);

        boolean result = memberService.checkResetPassword(uuid);
        model.addAttribute("result", result);
        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter) {

        boolean result = false;

        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());

        } catch (Exception e) {

        }
        model.addAttribute("result", result);
        return "member/reset_password_result";
    }
}


