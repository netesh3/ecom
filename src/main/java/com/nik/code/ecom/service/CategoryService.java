package com.nik.code.ecom.service;

import com.nik.code.ecom.builder.CategoryBuilder;
import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.mapper.CategoryDTOMapper;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.repository.CategoryRepository;
import com.nik.code.ecom.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void createCategory(CategoryDTO categoryDTO) {
        Category parent = null;
        if(Objects.nonNull(categoryDTO.getParentId())){
            parent = categoryRepository.getById(categoryDTO.getParentId());
        }
        Category category = new CategoryBuilder(categoryDTO).withParentCategory(parent).build();
        categoryRepository.save(category);
    }

    public void updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = categoryRepository.getById(categoryId);
        Category parentCategory = null;
        if(Objects.nonNull(categoryDTO.getParentId())){
            parentCategory = categoryRepository.getById(categoryDTO.getParentId());
        }
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setImageURL(categoryDTO.getImageURL());
        category.setParent(parentCategory);
        categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> category = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new CategoryDTOMapper().toDTO(category);
        return categoryDTOS;
    }

    public CategoryDTO getCategoryById(Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            CategoryDTO categoryDTO = new CategoryDTOMapper().toDTO(category.get());
            return categoryDTO;
        }
        return null;
    }
}
