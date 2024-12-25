package com.example.mobileapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.util.Log;

import com.example.mobileapp.Adapter.CategoryAdapter;
import com.example.mobileapp.Adapter.CourseAdapter;
import com.example.mobileapp.Domain.CategoryDomain;
import com.example.mobileapp.Domain.CourseDomain;
import com.example.mobileapp.R;
import com.example.mobileapp.databinding.ActivityCourseListBinding;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {
    ActivityCourseListBinding binding;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecyclerView();

        Window window = CourseListActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CourseListActivity.this, R.color.purple));

        sendHttpRequest();
    }

    private void initRecyclerView() {
        binding.popularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        binding.categoryList.setLayoutManager(layoutManager);
    }

    private void sendHttpRequest() {
        String url = "http://192.168.0.167:5000/api/courses"; // Замените на свой URL
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("CourseListActivity", "Request failed: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Log.d("CourseListActivity", "Response: " + responseData);
                    parseJsonResponse(responseData);
                } else {
                    Log.e("CourseListActivity", "Response failed with code: " + response.code());
                }
            }
        });
    }

    private void parseJsonResponse(String responseData) {
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();

            // Извлекаем массивы по ключам
            JsonArray coursesArray = jsonObject.getAsJsonArray("courses");
            JsonArray categoriesArray = jsonObject.getAsJsonArray("categories");

            // Преобразуем JsonArray в List<CourseDomain>
            Type courseListType = new TypeToken<List<CourseDomain>>() {}.getType();
            ArrayList<CourseDomain> courseList = gson.fromJson(coursesArray, courseListType);

            // Преобразуем JsonArray в List<CategoryDomain>
            Type categoryListType = new TypeToken<List<CategoryDomain>>() {}.getType();
            ArrayList<CategoryDomain> categoryList = gson.fromJson(categoriesArray, categoryListType);

            // Используем данные на UI
            ArrayList<CategoryDomain> finalCategoryList = categoryList;
            runOnUiThread(() -> {
                // Устанавливаем адаптер для курсов
                CourseAdapter courseAdapter = new CourseAdapter(courseList);
                binding.popularView.setAdapter(courseAdapter);

                // Устанавливаем адаптер для категорий
                CategoryAdapter categoryAdapter = new CategoryAdapter(finalCategoryList);
                binding.categoryList.setAdapter(categoryAdapter);

                // Отключаем прокрутку для категории
                binding.categoryList.setNestedScrollingEnabled(false);  // Отключаем прокрутку
            });
        } catch (Exception e) {
            Log.e("CourseListActivity", "Error parsing JSON: " + e.getMessage());
        }
    }


    public void redirectToCategoryListActivity(View view) {
        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivity(intent);
    }
}
