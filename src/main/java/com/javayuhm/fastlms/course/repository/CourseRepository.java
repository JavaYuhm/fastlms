package com.javayuhm.fastlms.course.repository;

import com.javayuhm.fastlms.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {


}
