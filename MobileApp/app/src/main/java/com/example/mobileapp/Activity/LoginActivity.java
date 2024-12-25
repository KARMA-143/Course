package com.example.mobileapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mobileapp.R;
import com.example.mobileapp.Utils.SharedPrefsHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView registerText=findViewById(R.id.registerText);
        SpannableString ss=new SpannableString("Are you new user? Register");
        ClickableSpan cs=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                redirectToRegisterActivity(widget);
            }
        };

        ss.setSpan(cs, 18,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerText.setText(ss);
        registerText.setMovementMethod(LinkMovementMethod.getInstance());

        Window window = LoginActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.purple));
    }

    public void redirectToRegisterActivity(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendHttpRequest(View view) throws JSONException {
        TextView email = findViewById(R.id.emailText);
        TextView password = findViewById(R.id.passwordText);

            String url = "http://192.168.0.167:5000/api/auth/login";
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", emailText);
            jsonObject.put("password", passwordText);
            String jsonString = jsonObject.toString();

            RequestBody requestBody = RequestBody.create(
                    jsonString, MediaType.parse("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Log.e("RegisterActivity", "Request failed: " + e.getMessage());
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Request failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        Log.d("LoginActivity", "Response: " + responseData);

                        try {
                            JSONObject jsonResponse = new JSONObject(responseData);
                            String jwtToken = jsonResponse.getString("access_token");

                            SharedPrefsHelper.saveJwtToken(LoginActivity.this, jwtToken);

                            Intent intent = new Intent(LoginActivity.this, CourseListActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("RegisterActivity", "Error parsing JSON response: " + e.getMessage());
                            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        Log.e("RegisterActivity", "Response failed with code: " + response.code());
                        runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show());
                    }
                }
            });
    }
}