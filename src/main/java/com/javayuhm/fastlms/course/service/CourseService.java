package com.javayuhm.fastlms.course.service;

import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.entity.Course;
import com.javayuhm.fastlms.course.model.CourseInput;
import com.javayuhm.fastlms.course.model.CourseParam;

import java.util.List;

public interface CourseService {

    boolean add(CourseInput parameter);
    boolean edit(CourseInput parameter);


    List<CourseDto> list(CourseParam parameter);

    CourseDto getById(long id);

    boolean delete(String idlist);
}
