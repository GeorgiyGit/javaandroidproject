package com.example.javaandroidproject.catalog;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaandroidproject.BaseActivity;
import com.example.javaandroidproject.DTOs.CategoryDTOs.CategoryDTO;
import com.example.javaandroidproject.R;
import com.example.javaandroidproject.catalog.categorycard.CategoriesAdapter;
import com.example.javaandroidproject.service.CategoryNetwork;
import com.example.javaandroidproject.utils.CommonUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends BaseActivity {

    CategoriesAdapter categoriesAdapter;
    private RecyclerView rcvCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        rcvCategories = findViewById(R.id.rcvCategories);
        rcvCategories.setHasFixedSize(true);
        rcvCategories.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        requestServer();
    }

    private void requestServer() {
        CommonUtils.showLoading();

        CategoryNetwork
                .getInstance()
                .getJsonApi()
                .getCategories()
                .enqueue(new Callback<List<CategoryDTO>>() {
                    @Override
                    public void onResponse(Call<List<CategoryDTO>> call, Response<List<CategoryDTO>> response) {
                        CommonUtils.hideLoading();
                        List<CategoryDTO> data = response.body();
                        //CategoryItemDTO item = data.get(0);
                        categoriesAdapter = new CategoriesAdapter(data,
                                CatalogActivity.this::onClickDelete,
                                CatalogActivity.this::onClickUpdate);
                        rcvCategories.setAdapter(categoriesAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<CategoryDTO>> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });

    }

    void onClickDelete(CategoryDTO category) {
        CommonUtils.showLoading();
        CategoryNetwork.getInstance()
                .getJsonApi()
                .delete(category.getId())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        CommonUtils.hideLoading();
                        Intent intent = new Intent(CatalogActivity.this, CatalogActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });
    }

    void onClickUpdate(CategoryDTO category) {
        Intent intent = new Intent(CatalogActivity.this, CategoryUpdateActivity.class);
        Bundle b = new Bundle();
        b.putInt("id", category.getId());
        intent.putExtras(b);
        startActivity(intent);
        finish();

    }
}