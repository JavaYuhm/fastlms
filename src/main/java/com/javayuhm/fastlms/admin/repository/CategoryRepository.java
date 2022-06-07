package com.javayuhm.fastlms.admin.repository;

import com.javayuhm.fastlms.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
