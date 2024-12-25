package com.example.mobileapp.Domain;

public class CategoryDomain {
    private String name;
    private String categoryImg;
    public CategoryDomain(String name, String categoryImg) {
        this.name = name;
        this.categoryImg = categoryImg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategoryImg() {
        return categoryImg;
    }
    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }
}
