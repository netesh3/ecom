package com.nik.code.ecom.builder;

import com.nik.code.ecom.dto.category.CategoryDTO;
import com.nik.code.ecom.model.Category;

public class CategoryBuilder {
    private String name;

    private String description;

    private String imageURL;

    private Category parent;

    public CategoryBuilder(CategoryDTO category){
        this.name = category.getName();
        this.description = category.getDescription();
        this.imageURL = category.getImageURL();
    }

    public CategoryBuilder withParentCategory(Category category){
        this.parent = category;
        return this;
    }

    public Category build(){
        return new Category(this.name, this.description, this.imageURL, this.parent);
    }
}
