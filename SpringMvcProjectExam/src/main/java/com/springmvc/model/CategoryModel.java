package com.springmvc.model;

import lombok.Data;

@Data
public class CategoryModel {
    private int categoryId;
    private String categoryName;
    public CategoryModel() {}
    public CategoryModel(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
