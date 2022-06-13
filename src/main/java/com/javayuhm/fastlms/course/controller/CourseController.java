package com.javayuhm.fastlms.course.controller;

import com.javayuhm.fastlms.admin.service.CategoryService;
import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.model.CourseInput;
import com.javayuhm.fastlms.course.model.CourseParam;
import com.javayuhm.fastlms.course.service.CourseService;
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
public class CourseController extends  BaseController{

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/course")
    public String course(Model model, CourseParam parameter){

        List<CourseDto> courseDtoList = courseService.frontList(parameter);
        model.addAttribute("list", courseDtoList);
        return "course/index";
    }


}
