package com.javayuhm.fastlms.course.model;

import com.javayuhm.fastlms.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeCourseParam extends CommonParam {
    long courseId;
    String userId;
}
