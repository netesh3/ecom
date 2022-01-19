package com.nik.code.ecom.service;

import com.nik.code.ecom.builder.CategoryBuilder;
import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void createCategory(CategoryDTO categoryDTO) {
        final Optional<Category> parentCategory = categoryRepository.findById(categoryDTO.getParentId());
        Category category = new CategoryBuilder(categoryDTO).withParentCategory(parentCategory.get()).build();
        categoryRepository.save(category);
    }
}
