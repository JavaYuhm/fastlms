package com.javayuhm.fastlms.admin.service.impl;

import com.javayuhm.fastlms.admin.dto.CategoryDto;
import com.javayuhm.fastlms.admin.entity.Category;
import com.javayuhm.fastlms.admin.mapper.CategoryMapper;
import com.javayuhm.fastlms.admin.model.CategoryInput;
import com.javayuhm.fastlms.admin.repository.CategoryRepository;
import com.javayuhm.fastlms.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    private Sort getSortBySortValueDesc(){
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    public List<CategoryDto> list() {

        List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());
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
    public boolean update(CategoryInput parameter) {

        Optional<Category> optionalCategory = categoryRepository.findById(parameter.getId());

        if(optionalCategory.isPresent())
        {
            Category category = optionalCategory.get();

            category.setCategoryName(parameter.getCategoryName());
            category.setSortValue(parameter.getSortValue());
            category.setUseYn(parameter.isUseYn());
            categoryRepository.save(category);
        }

        return false;
    }

    @Override
    public boolean delete(long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CategoryDto> frontList(CategoryDto parameter) {

        return categoryMapper.select(parameter);
    }
}
