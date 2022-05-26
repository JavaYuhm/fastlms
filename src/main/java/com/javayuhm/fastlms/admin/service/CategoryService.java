package com.javayuhm.fastlms.admin.service;

import com.javayuhm.fastlms.admin.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> list();

    /**
     * 카테고리 신규 추가
     * @param categoryName
     * @return
     */
    boolean add(String categoryName);

    /**
     * 카테고리 수정
     * @param parameter
     * @return
     */
    boolean update(CategoryDto parameter);

    /**
     * 카테고리 삭제
     * @param id
     * @return
     */
    boolean delete(long id);
    
}