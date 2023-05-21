package com.example.javaandroidproject.DTOs;

public class ImageDTO {
    private int id;
    private String name;
    private String fullName;
    private String path;

    public ImageDTO(int id, String name, String fullName, String path) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.path = path;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}