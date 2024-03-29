package com.javayuhm.fastlms.course.service.impl;

import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.dto.TakeCourseDto;
import com.javayuhm.fastlms.course.entity.Course;
import com.javayuhm.fastlms.course.entity.TakeCourse;
import com.javayuhm.fastlms.course.mapper.CourseMapper;
import com.javayuhm.fastlms.course.mapper.TakeCourseMapper;
import com.javayuhm.fastlms.course.model.CourseInput;
import com.javayuhm.fastlms.course.model.CourseParam;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.course.model.TakeCourseParam;
import com.javayuhm.fastlms.course.repository.CourseRepository;
import com.javayuhm.fastlms.course.repository.TakeCourseRepository;
import com.javayuhm.fastlms.course.service.CourseService;
import com.javayuhm.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TakeCourseServiceImpl implements TakeCourseService{

    private final TakeCourseMapper takeCourseMapper;
    private final TakeCourseRepository takeCourseRepository;

    @Override
    public List<TakeCourseDto> list(TakeCourseParam parameter) {
        long totalCount = takeCourseMapper.selectListCount(parameter);

        List<TakeCourseDto> list = takeCourseMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(TakeCourseDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public ServiceResult updateStatus(long id, String status) {

        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if(!optionalTakeCourse.isPresent()){
            return new ServiceResult(false, "수강 정보가 존재하지 않습니다");
        }
        
        TakeCourse takeCourse = optionalTakeCourse.get();
        takeCourse.setStatus(status);
        takeCourseRepository.save(takeCourse);
        
        return new ServiceResult(true, "상태값이 업데이트 되었습니다");
    }

    @Override
    public List<TakeCourseDto> myCouse(String userId) {

        TakeCourseParam takeCourseParam = new TakeCourseParam();
        takeCourseParam.setUserId(userId);
        List<TakeCourseDto> list = takeCourseMapper.selectMyCourseList(takeCourseParam);
        return null;
    }
}
