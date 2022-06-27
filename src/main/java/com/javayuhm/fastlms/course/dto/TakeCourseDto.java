package com.javayuhm.fastlms.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TakeCourseDto {
    Long id;
    long courseId;

    String userId;

    long payPrice;
    String status; // 상태 (1. 수강신청 , 2. 결재완료 , 3. 수강취소)

    LocalDateTime regDt;
    LocalDateTime uptDt;

    long totalCount;
    long seq;

    // Join Col
    String userName;
    String phone;
    String subject;

    public String getRegDtText(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(formatter) : "";
    }
}
