package com.javayuhm.fastlms.course.service;

import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.entity.Course;
import com.javayuhm.fastlms.course.model.CourseInput;
import com.javayuhm.fastlms.course.model.CourseParam;

import java.util.List;

public interface CourseService {

    boolean add(CourseInput parameter);

    List<CourseDto> list(CourseParam parameter);
}
