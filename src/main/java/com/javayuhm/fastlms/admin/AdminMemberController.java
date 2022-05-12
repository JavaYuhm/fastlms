package com.javayuhm.fastlms.admin;

import org.springframework.web.bind.annotation.GetMapping;

public class AdminMemberController {

    @GetMapping("/admin/member/list.do")
    public String list() {
        return "admin/member/list";
    }

}
