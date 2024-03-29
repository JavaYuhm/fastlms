package com.javayuhm.fastlms.course.service.impl;

import com.javayuhm.fastlms.course.dto.CourseDto;
import com.javayuhm.fastlms.course.entity.Course;
import com.javayuhm.fastlms.course.entity.TakeCourse;
import com.javayuhm.fastlms.course.mapper.CourseMapper;
import com.javayuhm.fastlms.course.model.CourseInput;
import com.javayuhm.fastlms.course.model.CourseParam;
import com.javayuhm.fastlms.course.model.ServiceResult;
import com.javayuhm.fastlms.course.model.TakeCourseParam;
import com.javayuhm.fastlms.course.repository.CourseRepository;
import com.javayuhm.fastlms.course.repository.TakeCourseRepository;
import com.javayuhm.fastlms.course.service.CourseService;
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
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TakeCourseRepository takeCourseRepository;
    private final CourseMapper courseMapper;

    private LocalDate getLocalDate(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = null;
        try{
            return LocalDate.parse(value, formatter);
        } catch (Exception e){
            return null;
        }
    }
    @Override
    public boolean add(CourseInput parameter) {

        LocalDate localsaleDt = getLocalDate(parameter.getSaleEndDtText());

        Course course = Course.builder()
                .categoryId(parameter.getCategoryId())
                .keyword(parameter.getKeyword())
                .subject(parameter.getSubject())
                .summary(parameter.getSummary())
                .contents(parameter.getContents())
                .price(parameter.getPrice())
                .salePrice(parameter.getSalePrice())
                .saleEndDt(localsaleDt)
                .regDt(LocalDateTime.now())
                .build();

        courseRepository.save(course);

        return true;
    }

    @Override
    public boolean edit(CourseInput parameter) {
        Optional<Course> optionalCourse =  courseRepository.findById(parameter.getId());



        if(!optionalCourse.isPresent()){
            return false;
        }

        LocalDate localsaleDt = getLocalDate(parameter.getSaleEndDtText());

        Course course = optionalCourse.get();

        course.setCategoryId(parameter.getCategoryId());
        course.setSubject(parameter.getSubject());
        course.setKeyword(parameter.getKeyword());
        course.setSummary(parameter.getSummary());
        course.setContents(parameter.getContents());
        course.setPrice(parameter.getPrice());
        course.setSalePrice(parameter.getSalePrice());
        course.setSaleEndDt(localsaleDt);
        course.setUptDt(LocalDateTime.now());

        courseRepository.save(course);

        return true;
    }

    @Override
    public List<CourseDto> list(CourseParam parameter) {
        long totalCount = courseMapper.selectListCount(parameter);

        List<CourseDto> list = courseMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(CourseDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public CourseDto getById(long id) {

        return courseRepository.findById(id).map(CourseDto::of).orElse(null);

    }

    @Override
    public boolean delete(String idlist) {
       if(idlist != null && idlist.length()>0){

           String [] ids = idlist.split(",");
           for(String x : ids){
               long id = 0L;

               try{
                    id =Long.parseLong(x);
               } catch (Exception e){

               }
               if(id>0){
                   courseRepository.deleteById(id);
               }
           }

       }
       return true;

    }

    @Override
    public List<CourseDto> frontList(CourseParam parameter) {

        if(parameter.getCategoryId() < 1){

            List<Course> courseList = courseRepository.findAll();

            return CourseDto.of(courseList);

        }

        return courseRepository.findByCategoryId(parameter.getCategoryId()).map(CourseDto::of).orElse(null);

    }

    @Override
    public CourseDto frontDetail(long id) {

        Optional<Course> optionalCourse = courseRepository.findById(id);

        if(optionalCourse.isPresent()){
            return CourseDto.of(optionalCourse.get());
        }

        return null;
    }

    @Override
    public ServiceResult req(TakeCourseParam parameter) {

        ServiceResult result = new ServiceResult();

        Optional<Course> optionalCourse = courseRepository.findById(parameter.getCourseId());

        if(!optionalCourse.isPresent()){
            result.setResult(false);
            result.setMessage("강좌정보가 없습니다.");
            return result;
        }
        Course course = optionalCourse.get();

        // 이미 신청정보가 있는 지 확인
        String[] statusList = {TakeCourse.STATUS_REQ, TakeCourse.STATUS_COMPLETE};
        long count = takeCourseRepository.countByCourseIdAndUserIdAndAndStatusIn(course.getId(), parameter.getUserId(), Arrays.asList(statusList));

        if(count>0){
            result.setResult(false);
            result.setMessage("이미 신청한 강좌 정보가 있습니다..");
            return result;
        }

        TakeCourse takeCourse = TakeCourse.builder()
                .courseId(course.getId())
                .userId(parameter.getUserId())
                .payPrice(course.getSalePrice())
                .status(TakeCourse.STATUS_REQ)
                .regDt(LocalDateTime.now())

                .build();

        takeCourseRepository.save(takeCourse);
        result.setResult(true);
        result.setMessage("성공");
        return result;
    }
}
