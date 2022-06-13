package com.javayuhm.fastlms.course.dto;

import com.javayuhm.fastlms.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CourseDto {

    Long id;

    long categoryId;

    String imagePath;
    String keyword;
    String subject;

    String summary;
    String contents;
    long price;
    long salePrice;

    LocalDate saleEndDt;
    LocalDateTime regDt;
    LocalDateTime uptDt;

    long totalCount;
    long seq;

    public static CourseDto of(Course course) {

        return  CourseDto.builder()
                .id(course.getId())
                .categoryId(course.getCategoryId())
                .imagePath(course.getImagePath())
                .keyword(course.getKeyword())
                .subject(course.getSubject())
                .summary(course.getSummary())
                .contents(course.getContents())
                .price(course.getPrice())
                .salePrice(course.getSalePrice())
                .saleEndDt(course.getSaleEndDt())
                .regDt(course.getRegDt())
                .uptDt(course.getUptDt())
                .build();
    }

    public static List<CourseDto> of(List<Course> courselist) {

      if(courselist == null) return null;

      List<CourseDto> courseDtoList = new ArrayList<>();
      for(Course x : courselist){
          courseDtoList.add(CourseDto.of(x));
      }

      return courseDtoList;
    }
}
