package com.javayuhm.fastlms.course.controller;

import com.javayuhm.fastlms.admin.service.CategoryService;
import com.javayuhm.fastlms.common.model.ResponseResult;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.course.model.TakeCourseParam;
import com.javayuhm.fastlms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ApiCourseController extends  BaseController{

    private final CourseService courseService;
    private final CategoryService categoryService;

    @PostMapping("/api/course/req.api")
    public ResponseEntity<?> courseReq(Model model, @RequestBody TakeCourseParam parameter, Principal principal){

        parameter.setUserId(principal.getName());

        ServiceResult result = courseService.req(parameter);

        if(!result.isResult()){
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }
        ResponseResult responseResult = new ResponseResult(true, result.getMessage());

        return ResponseEntity.ok().body(responseResult);

    }
}
