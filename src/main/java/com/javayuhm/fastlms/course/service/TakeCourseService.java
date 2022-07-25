package com.javayuhm.fastlms.course.service;

import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.dto.TakeCourseDto;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.course.model.TakeCourseParam;

import java.util.List;

public interface TakeCourseService {
    List<TakeCourseDto> list(TakeCourseParam parameter);

    /**
     * 수강 내용 상태 변경
     * @param id
     * @param status
     * @return
     */
    ServiceResult updateStatus(long id, String status);

    /**
     * 내수강 신청 내역
     * @param userId
     * @return
     */
    List<TakeCourseDto> myCouse(String userId);
}
