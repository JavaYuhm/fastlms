package com.javayuhm.fastlms.admin;

import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.admin.model.MemberParam;
import com.javayuhm.fastlms.member.service.MemberService;
import com.javayuhm.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter){
        parameter.init();

        List<MemberDto> members = memberService.list(parameter);

        long totalCount = 0;
        if(members != null && members.size()>0){
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);


        model.addAttribute("pager", pageUtil.pager());
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", members);

        return "admin/member/list";
    }
}
