package com.javayuhm.fastlms.admin.mapper;

import com.javayuhm.fastlms.admin.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDto> selectList(MemberDto parameter);
}
