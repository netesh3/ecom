package com.nik.code.ecom.service;

import com.nik.code.ecom.builder.CategoryBuilder;
import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.mapper.CategoryDTOMapper;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void createCategory(CategoryDTO categoryDTO) {
        Optional<Category> parent = null;
        if(Objects.nonNull(categoryDTO.getParentId())){
            parent = categoryRepository.findById(categoryDTO.getParentId());
        }
        Category category = new CategoryBuilder(categoryDTO).withParentCategory(parent.get()).build();
        categoryRepository.save(category);
    }

    public void updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            Optional<Category> parentCategory = null;
            if(Objects.nonNull(categoryDTO.getParentId())){
                parentCategory = categoryRepository.findById(categoryDTO.getParentId());
            }
            if(category.isPresent()){
                category.get().setName(categoryDTO.getName());
                category.get().setDescription(categoryDTO.getDescription());
                category.get().setImageURL(categoryDTO.getImageURL());
            }
            if(parentCategory.isPresent()){
                category.get().setParent(parentCategory.get());
            }
            categoryRepository.save(category.get());
        }
    }

    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<CategoryDTO> getAllCategories(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Category> categoryPagedResult = categoryRepository.findAll(paging);
        if (categoryPagedResult.hasContent()) {
            List<Category> categories = categoryPagedResult.getContent();
            List<CategoryDTO> categoryDTOS = new CategoryDTOMapper().toDTO(categories);
            return categoryDTOS;
        } else {
            return new ArrayList<CategoryDTO>();
        }
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
