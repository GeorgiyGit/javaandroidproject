package com.example.javaandroidproject.catalog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.javaandroidproject.BaseActivity;
import com.example.javaandroidproject.ChangeImageActivity;
import com.example.javaandroidproject.DTOs.CategoryDTOs.CategoryDTO;
import com.example.javaandroidproject.DTOs.CategoryDTOs.CategoryUpdateDTO;
import com.example.javaandroidproject.R;
import com.example.javaandroidproject.application.HomeApplication;
import com.example.javaandroidproject.contants.Urls;
import com.example.javaandroidproject.service.CategoryNetwork;
import com.example.javaandroidproject.utils.CommonUtils;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryUpdateActivity extends BaseActivity {

    int id=0;
    int SELECT_CROPPER = 300;
    Uri uri = null;
    ImageView IVPreviewImage;
    TextView textImageError;

    TextInputEditText txtCategoryName;
    TextInputEditText txtCategoryDescription;

    TextInputLayout txtFieldCategoryName;
    TextInputLayout txtFieldCategoryDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_update);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        textImageError=findViewById(R.id.textImageError);

        txtCategoryName = findViewById(R.id.txtCategoryName);
        txtCategoryDescription = findViewById(R.id.txtCategoryDescription);

        txtFieldCategoryName = findViewById(R.id.txtFieldCategoryName);
        txtFieldCategoryDescription = findViewById(R.id.txtFieldCategoryDescription);

        Bundle b = getIntent().getExtras();
        if(b!=null)
            id=b.getInt("id");
        //Toast.makeText(this, "Id = "+ id, Toast.LENGTH_SHORT).show();
        initInput();

        setupError();
    }

    private void initInput() {
        CommonUtils.showLoading();
        CategoryNetwork
                .getInstance()
                .getJsonApi()
                .getById(id)
                .enqueue(new Callback<CategoryDTO>() {
                    @Override
                    public void onResponse(Call<CategoryDTO> call, Response<CategoryDTO> response) {
                        CommonUtils.hideLoading();
                        CategoryDTO cat = response.body();
                        txtCategoryName.setText(cat.getName());
                        //txtCategoryPriority.setText(Integer.toString(cat.getPriority()));
                        txtCategoryDescription.setText(cat.getDescription());
                        String url = Urls.BASE+cat.getImage();
                        Glide.with(HomeApplication.getAppContext())
                                .load(url)
                                .apply(new RequestOptions().override(600))
                                .into(IVPreviewImage);
                    }

                    @Override
                    public void onFailure(Call<CategoryDTO> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });
    }

    public void handleUpdateCategoryClick(View view) {
        if(!validationForm()) {
            Toast.makeText(this, "Enter all data!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        CategoryUpdateDTO model = new CategoryUpdateDTO();
        model.setId(id);
        model.setName(txtCategoryName.getText().toString());
        //model.setPriority(Integer.parseInt(txtCategoryPriority.getText().toString()));
        model.setDescription(txtCategoryDescription.getText().toString());
        //if(uri!=null)
           // model.setImageBase64(uriGetBase64(uri));

        CategoryNetwork
                .getInstance()
                .getJsonApi()
                .update(model)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(CategoryUpdateActivity.this, CatalogActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
    }

    private boolean validationForm() {
        boolean isValid=true;
        String name = txtCategoryName.getText().toString();
        if(name.isEmpty() || name.length()<=2){
            txtFieldCategoryName.setError(getString(R.string.category_name_required));
            isValid=false;
        }
        String description = txtCategoryDescription.getText().toString();
        if(description.isEmpty() || description.length()<=2){
            txtFieldCategoryDescription.setError(getString(R.string.category_description_required));
            isValid=false;
        }
        return isValid;
    }

    private void setupError() {
        txtCategoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty() || charSequence.length()<=2){
                    txtFieldCategoryName.setError(getString(R.string.category_name_required));
                    txtFieldCategoryName.setErrorEnabled(true);
                }
                else {
                    txtFieldCategoryName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtCategoryDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty() || charSequence.length()<=2){
                    txtFieldCategoryDescription.setError(getString(R.string.category_description_required));
                    txtFieldCategoryDescription.setErrorEnabled(true);
                }
                else {
                    txtFieldCategoryDescription.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void handleSelectImageClick(View view) {
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivityForResult(intent, SELECT_CROPPER);
    }


    private String uriGetBase64(Uri uri) {
        try {
            Bitmap bitmap=null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch(IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            byte[] byteArr = bytes.toByteArray();
            return Base64.encodeToString(byteArr, Base64.DEFAULT);

        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==SELECT_CROPPER) {
            uri = (Uri) data.getParcelableExtra("croppedUri");

            IVPreviewImage.setImageURI(uri);
        }
    }
}