package com.javayuhm.fastlms.admin.mapper;

import com.javayuhm.fastlms.admin.dto.CategoryDto;
import com.javayuhm.fastlms.admin.dto.MemberDto;
import com.javayuhm.fastlms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> select(CategoryDto parameter);
}
