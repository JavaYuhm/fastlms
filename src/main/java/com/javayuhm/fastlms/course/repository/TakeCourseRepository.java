package com.javayuhm.fastlms.course.repository;

import com.javayuhm.fastlms.course.entity.TakeCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface TakeCourseRepository extends JpaRepository<TakeCourse, Long> {
    long countByCourseIdAndUserIdAndAndStatusIn(long courseId, String userId, Collection<String> statusList);
}
