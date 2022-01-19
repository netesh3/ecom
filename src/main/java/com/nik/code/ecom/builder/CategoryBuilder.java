package com.nik.code.ecom.builder;

import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.model.Category;

public class CategoryBuilder {
    private String name;

    private String description;

    private String imageURL;

    private Category parent;

    public CategoryBuilder(CategoryDTO categoryDTO){
        this.name = categoryDTO.getName();
        this.description = categoryDTO.getDescription();
        this.imageURL = categoryDTO.getImageURL();
    }

    public CategoryBuilder withParentCategory(Category category){
        this.parent = category;
        return this;
    }

    public Category build(){
        return new Category(this.name, this.description, this.imageURL, this.parent);
    }
}
