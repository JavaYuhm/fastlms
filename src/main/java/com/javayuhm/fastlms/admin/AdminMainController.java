package com.javayuhm.fastlms.admin;

import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.admin.model.MemberParam;
import com.javayuhm.fastlms.member.entity.Member;
import com.javayuhm.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMainController {

    private final MemberService memberService;
    @GetMapping("/admin/main.do")
    public String main() {
        return "admin/main";
    }



}
