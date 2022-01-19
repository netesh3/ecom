package com.nik.code.ecom.dto.category;

public class CategoryDTO {

    private String name;

    private String description;

    private String imageURL;

    private Integer parentId;

    public CategoryDTO() {
    }

    public CategoryDTO(String name, String description, String imageURL, Integer parentId) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
