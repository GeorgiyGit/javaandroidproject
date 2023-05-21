package com.example.javaandroidproject.DTOs.CategoryDTOs;

import com.example.javaandroidproject.DTOs.ImageDTO;

import java.io.File;

public class CategoryUpdateDTO {
    private int id;
    private String name;
    private String description;
    private File image;

    public CategoryUpdateDTO(){}
    public CategoryUpdateDTO(int id, String name, String description, File image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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