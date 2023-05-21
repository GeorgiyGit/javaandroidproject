package com.example.javaandroidproject.network;

import com.example.javaandroidproject.DTOs.CategoryDTOs.CategoryCreateDTO;
import com.example.javaandroidproject.DTOs.CategoryDTOs.CategoryDTO;
import com.example.javaandroidproject.DTOs.CategoryDTOs.CategoryUpdateDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryApi {
    @GET("/api/Category")
    public Call<List<CategoryDTO>> getCategories();

    @GET("/api/Category/{id}")
    public Call<CategoryDTO> getById(@Path("id") int id);

    @DELETE("/api/Category/{id}")
    public Call<Void> delete(@Path("id") int id);

    @POST("/api/Category")
    public Call<Void> create(@Body CategoryCreateDTO categoryCreateDTO);

    @PUT("/api/Category")
    public Call<Void> update(@Body CategoryUpdateDTO category);
}
