package com.example.javaandroidproject.DTOs.CategoryDTOs;

import com.example.javaandroidproject.DTOs.ImageDTO;

import java.io.File;

public class CategoryCreateDTO {
    private String name;
    private String description;
    private File image;

    public CategoryCreateDTO(){}
    public CategoryCreateDTO(String name, String description, File image) {
        this.name = name;
        this.description = description;
        this.image = image;
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

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}