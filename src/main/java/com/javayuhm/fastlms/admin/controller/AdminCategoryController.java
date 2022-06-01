package com.javayuhm.fastlms.admin.controller;

import com.javayuhm.fastlms.admin.dto.CategoryDto;
import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.admin.model.CategoryInput;
import com.javayuhm.fastlms.admin.model.MemberInput;
import com.javayuhm.fastlms.admin.model.MemberParam;
import com.javayuhm.fastlms.admin.service.CategoryService;
import com.javayuhm.fastlms.member.service.MemberService;
import com.javayuhm.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/admin/category/list.do")
    public String list(Model model, MemberParam parameter){

        List<CategoryDto> list = categoryService.list();

        model.addAttribute("list", list);

        return "admin/category/list";
    }

    @PostMapping("/admin/category/add.do")
    public String add(Model model, CategoryInput categoryInput){

        boolean res = categoryService.add(categoryInput.getCategoryName());


        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/admin/category/delete.do")
    public String del(Model model, CategoryInput categoryInput){

        boolean result = categoryService.delete(categoryInput.getId());

        return "redirect:/admin/category/list.do";

    }


}
