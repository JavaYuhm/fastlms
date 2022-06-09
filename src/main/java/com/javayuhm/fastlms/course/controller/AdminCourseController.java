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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminCourseController extends  BaseController{

    private final CourseService courseService;
    private final CategoryService categoryService;

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

    @GetMapping(value={"/admin/course/add.do", "/admin/course/edit.do"})
    public String add(Model model, HttpServletRequest request, CourseInput parameter){

        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        CourseDto detail = new CourseDto();
        if(editMode){

            long id = parameter.getId();

            CourseDto courseDto = courseService.getById(id);

            if(courseDto==null){
                model.addAttribute("message", "강좌정보가 존재하지 않음.");
                return "common/error";
            }
            detail = courseDto;
        }
        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/course/add";
    }

    @PostMapping(value={"/admin/course/add.do", "/admin/course/edit.do"})
    public String addSubmit(Model model,HttpServletRequest request, CourseInput parameter){

        boolean editMode = request.getRequestURI().contains("/edit.do");
        if(editMode){

            long id = parameter.getId();

            CourseDto courseDto = courseService.getById(id);

            if(courseDto==null){
                model.addAttribute("message", "강좌정보가 존재하지 않음.");
                return "common/error";
            }
            boolean result = courseService.edit(parameter);
        } else {
            boolean result = courseService.add(parameter);
        }

        return "redirect:/admin/course/list.do";
    }



}
