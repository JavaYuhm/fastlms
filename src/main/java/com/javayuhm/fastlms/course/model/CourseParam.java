package com.javayuhm.fastlms.course.model;

import com.javayuhm.fastlms.admin.model.CommonParam;
import lombok.Data;

@Data
public class CourseParam extends CommonParam {
    long categoryId;
    long id;
}
