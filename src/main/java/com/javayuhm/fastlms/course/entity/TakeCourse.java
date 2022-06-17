package com.javayuhm.fastlms.course.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class TakeCourse implements TakeCourseCode{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    long courseId;

    String userId;

    long payPrice;
    String status; // 상태 (1. 수강신청 , 2. 결재완료 , 3. 수강취소)

    LocalDateTime regDt;
    LocalDateTime uptDt;


}
