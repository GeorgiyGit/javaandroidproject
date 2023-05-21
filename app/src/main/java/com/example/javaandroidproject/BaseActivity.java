package com.example.javaandroidproject;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javaandroidproject.catalog.CatalogActivity;
import com.example.javaandroidproject.catalog.CategoryCreateActivity;
import com.example.javaandroidproject.utils.CommonUtils;

public class BaseActivity extends AppCompatActivity {
    public BaseActivity() {
        CommonUtils.setContext(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        int id = item.getItemId();

        if (id == R.id.m_home) {
            try {
                intent = new Intent(BaseActivity.this, MainActivity.class);
                startActivity(intent);
                //finish();
            } catch (Exception ex) {
                System.out.println("---Problem " + ex.getMessage());
            }
            return true;
        } else if (id == R.id.m_catalog) {
            try {
                intent = new Intent(BaseActivity.this, CatalogActivity.class);
                startActivity(intent);
                //finish();
                return true;
            } catch (Exception ex) {
                System.out.println("---Problem " + ex.getMessage());
            }
        } else if (id == R.id.m_create) {
            try {
                intent = new Intent(BaseActivity.this, CategoryCreateActivity.class);
                startActivity(intent);
                //finish();
                return true;
            } catch (Exception ex) {
                System.out.println("---Problem " + ex.getMessage());
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
