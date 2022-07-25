package com.javayuhm.fastlms.course.mapper;

import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.dto.TakeCourseDto;
import com.javayuhm.fastlms.course.entity.TakeCourse;
import com.javayuhm.fastlms.course.model.CourseParam;
import com.javayuhm.fastlms.course.model.TakeCourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeCourseMapper {
    List<TakeCourseDto> selectList(TakeCourseParam courseParam);
    long selectListCount(TakeCourseParam courseParam);

    List<TakeCourseDto> selectMyCourseList(TakeCourseParam courseParam);
}
