package com.javayuhm.fastlms.course.controller;

import com.javayuhm.fastlms.admin.service.CategoryService;
import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.dto.TakeCourseDto;
import com.javayuhm.fastlms.course.model.CourseInput;
import com.javayuhm.fastlms.course.model.CourseParam;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.course.model.TakeCourseParam;
import com.javayuhm.fastlms.course.service.CourseService;
import com.javayuhm.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends  BaseController{

    private final TakeCourseService takeCourseService;
    private final CategoryService categoryService;

    @GetMapping("/admin/takecourse/list.do")
    public String list(Model model, TakeCourseParam parameter){

        parameter.init();

        List<TakeCourseDto> courseList = takeCourseService.list(parameter);

        long totalCount = 0;
        if(!CollectionUtils.isEmpty(courseList)){
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(),  queryString);


        model.addAttribute("pager", pagerHtml);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", courseList);

        return "admin/takecourse/list";
    }

    @PostMapping("/admin/takecourse/status.do")
    public String status(Model model, TakeCourseParam parameter){

        parameter.init();

        ServiceResult serviceResult = takeCourseService.updateStatus(parameter.getId(), parameter.getStatus());
        if(!serviceResult.isResult()){
            model.addAttribute("meesage", serviceResult.getMessage());
            return "common/error";
        }
        return "redirect:/admin/takecourse/list.do";
    }





}
