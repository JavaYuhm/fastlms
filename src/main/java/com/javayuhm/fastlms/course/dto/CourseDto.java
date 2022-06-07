package com.javayuhm.fastlms.course.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CourseDto {

    Long id;

    String imagePath;
    String keyword;
    String subject;

    String summary;
    String contents;
    long price;
    long salePrice;

    LocalDateTime saleEndDt;
    LocalDateTime regDt;
    LocalDateTime uptDt;

    long totalCount;
    long seq;
}
