package com.example.mobileapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobileapp.Adapter.CategoryListItemAdapter;
import com.example.mobileapp.Domain.CategoryDomain;
import com.example.mobileapp.R;
import com.example.mobileapp.databinding.ActivityCategoryListBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CategoryListActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    ActivityCategoryListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecyclerView();

        Window window = CategoryListActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CategoryListActivity.this, R.color.purple));

        sendHttpRequest();
    }
    private void initRecyclerView(){
        binding.categoryList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }
    private void sendHttpRequest(){
        String url = "http://192.168.0.167:5000/api/categories"; // Замените на свой URL
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.e("CategoryListActivity", "Request failed: "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String responseData = response.body().string();
                    Log.d("CategoryListActivity", "Response: " + responseData);
                    parseJsonResponse(responseData);
                } else {
                    Log.e("CategoryListActivity", "Response failed with code: " + response.code());
                }
            }
        });
    }
    private void parseJsonResponse(String responseData){
        try{
            Gson gson = new Gson();
            Type categoryListType = new TypeToken<List<CategoryDomain>>(){}.getType();
            ArrayList<CategoryDomain> categoryList = gson.fromJson(responseData, categoryListType);
            runOnUiThread(()->{
                CategoryListItemAdapter categoryListItemAdapter=new CategoryListItemAdapter(categoryList);
                binding.categoryList.setAdapter(categoryListItemAdapter);
            });
        }
        catch (Exception e){
            Log.e("CategoryListActivity", "Error parsing JSON: "+e.getMessage());
        }
    }
}