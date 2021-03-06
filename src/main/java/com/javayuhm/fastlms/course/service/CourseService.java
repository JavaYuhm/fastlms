package com.javayuhm.fastlms.course.service;

import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.entity.Course;
import com.javayuhm.fastlms.course.model.CourseInput;
import com.javayuhm.fastlms.course.model.CourseParam;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.course.model.TakeCourseParam;

import java.util.List;

public interface CourseService {

    boolean add(CourseInput parameter);
    boolean edit(CourseInput parameter);


    List<CourseDto> list(CourseParam parameter);

    CourseDto getById(long id);

    boolean delete(String idlist);

    /**
     * 프론트 강좌 목록
     */
    List<CourseDto> frontList(CourseParam parameter);


    /**
     * 프론트 강좌 상세 DTo
     * @param id
     * @return
     */
    CourseDto frontDetail(long id);

    ServiceResult req(TakeCourseParam parameter);
}
