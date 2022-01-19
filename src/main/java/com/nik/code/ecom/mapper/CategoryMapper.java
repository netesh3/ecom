package com.nik.code.ecom.mapper;

import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.model.Category;
import com.nik.code.ecom.utils.DTOMapper;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapper implements DTOMapper<CategoryDTO, Category> {


    @Override
    public Category toModel(CategoryDTO instance) {
        return null;
    }

    @Override
    public CategoryDTO toDTO(Category instance) {
        Integer parentId = instance.getParent() != null? instance.getParent().getId() : null;
        return new CategoryDTO(instance.getName(), instance.getDescription(), instance.getImageURL(), parentId);
    }

    @Override
    public List<Category> toModel(List<CategoryDTO> instance) {
        return null;
    }

    @Override
    public List<CategoryDTO> toDTO(List<Category> instance) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category: instance){
            categoryDTOS.add(toDTO(category));
        }
        return categoryDTOS;
    }
}
