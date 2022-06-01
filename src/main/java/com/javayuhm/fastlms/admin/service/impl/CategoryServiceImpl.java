package com.javayuhm.fastlms.admin.service.impl;

import com.javayuhm.fastlms.admin.dto.CategoryDto;
import com.javayuhm.fastlms.admin.entity.Category;
import com.javayuhm.fastlms.admin.repository.CategoryRepository;
import com.javayuhm.fastlms.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public List<CategoryDto> list() {

        List<Category> categories = categoryRepository.findAll();

        return CategoryDto.of(categories);
    }

    @Override
    public boolean add(String categoryName) {

        //카테고리 중복 체크
        
        Category category = Category.builder()
                .categoryName(categoryName)
                .useYn(true)
                .sortValue(0)
                .build();
        
        categoryRepository.save(category);

        return false;
    }

    @Override
    public boolean update(CategoryDto parameter) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
