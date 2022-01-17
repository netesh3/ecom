package com.nik.code.ecom.service;

import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setImageURL(categoryDTO.getImageURL());
        category.setParentId(categoryDTO.getParentId());
        categoryRepository.save(category);
    }
}
