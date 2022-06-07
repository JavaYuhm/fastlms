package com.javayuhm.fastlms.course.mapper;

import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.model.CourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseDto> selectList(CourseParam courseParam);
    long selectListCount(CourseParam courseParam);
}
