package com.javayuhm.fastlms.course.service;

import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.dto.TakeCourseDto;
import com.javayuhm.fastlms.course.model.TakeCourseParam;

import java.util.List;

public interface TakeCourseService {
    List<TakeCourseDto> list(TakeCourseParam parameter);

}
