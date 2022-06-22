package com.javayuhm.fastlms.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
}
