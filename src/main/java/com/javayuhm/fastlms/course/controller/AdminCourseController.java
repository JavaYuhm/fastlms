package com.javayuhm.fastlms.course.controller;

import com.javayuhm.fastlms.admin.dto.CategoryDto;
import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.admin.model.CategoryInput;
import com.javayuhm.fastlms.admin.model.MemberParam;
import com.javayuhm.fastlms.admin.service.CategoryService;
import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.model.CourseInput;
import com.javayuhm.fastlms.course.model.CourseParam;
import com.javayuhm.fastlms.course.service.CourseService;
import com.javayuhm.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends  BaseController{

    private final CourseService courseService;

    @GetMapping("/admin/course/list.do")
    public String list(Model model, CourseParam parameter){

        parameter.init();

        List<CourseDto> courseList = courseService.list(parameter);

        long totalCount = 0;
        if(!CollectionUtils.isEmpty(courseList)){
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(),  queryString);


        model.addAttribute("pager", pagerHtml);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", courseList);

        return "admin/course/list";
    }

    @GetMapping("/admin/course/add.do")
    public String add(Model model){

        //  List<CategoryDto> list = courseService.list();

        //  model.addAttribute("list", list);

        return "admin/course/add";
    }

    @PostMapping("/admin/course/add.do")
    public String addSubmit(Model model, CourseInput parameter){

        boolean result = courseService.add(parameter);


        return "redirect:/admin/course/list.do";
    }



}
