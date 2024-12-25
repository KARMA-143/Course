package com.example.mobileapp.Activity;

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

public class RegisterActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView registerText = findViewById(R.id.loginText);
        SpannableString ss = new SpannableString("Already have account? Log in");
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                redirectToLoginActivity(widget);
            }
        };

        ss.setSpan(cs, 22, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerText.setText(ss);
        registerText.setMovementMethod(LinkMovementMethod.getInstance());

        Window window = RegisterActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(RegisterActivity.this, R.color.purple));
    }

    public void redirectToLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendHttpRequest(View view) throws JSONException {
        TextView username = findViewById(R.id.userNameText);
        TextView email = findViewById(R.id.emailText);
        TextView password = findViewById(R.id.passwordText);
        TextView repeatPassword = findViewById(R.id.repeatPasswordText);

        if (!password.getText().toString().equals(repeatPassword.getText().toString())) {
            Toast.makeText(this, "Passwords are not equal!", Toast.LENGTH_SHORT).show();
        } else {
            String url = "http://192.168.0.167:5000/api/auth/register";
            String usernameText = username.getText().toString();
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", usernameText);
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
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Request failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        Log.d("RegisterActivity", "Response: " + responseData);

                        try {
                            JSONObject jsonResponse = new JSONObject(responseData);
                            String jwtToken = jsonResponse.getString("access_token");  // Получаем токен

                            SharedPrefsHelper.saveJwtToken(RegisterActivity.this,jwtToken);

                            Intent intent = new Intent(RegisterActivity.this, CourseListActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("RegisterActivity", "Error parsing JSON response: " + e.getMessage());
                            runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        Log.e("RegisterActivity", "Response failed with code: " + response.code());
                        runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        }
    }
}
