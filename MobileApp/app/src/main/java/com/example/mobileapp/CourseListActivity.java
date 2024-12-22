package com.example.mobileapp;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import okhttp3.*;

import java.io.IOException;

public class CourseListActivity extends AppCompatActivity {

    private TextView textView;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        textView = findViewById(R.id.textView2);
        sendHttpRequest();
    }

    private void sendHttpRequest() {
        String url = "http://192.168.0.167:5000/api/courses"; // Замените на ваш IP-адрес
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                updateTextView("Ошибка запроса: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    updateTextView(responseData);
                } else {
                    updateTextView("Ошибка: " + response.code());
                }
            }
        });
    }

    private void updateTextView(final String text) {
        new Handler(Looper.getMainLooper()).post(() -> textView.setText(text));
    }
}