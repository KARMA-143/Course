package com.example.mobileapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.mobileapp.R;
import com.example.mobileapp.Utils.SharedPrefsHelper;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jwtToken = SharedPrefsHelper.getJwtToken(this);

        if (jwtToken != null && SharedPrefsHelper.isTokenValid(jwtToken)) {
            Intent intent = new Intent(this, CourseListActivity.class);
            startActivity(intent);
            finish();
        } else if (jwtToken != null) {
            SharedPrefsHelper.clearJwtToken(this);
        }

        TextView registerText = findViewById(R.id.registerText);
        SpannableString ss = new SpannableString("Are you new user? Register");
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                redirectToRegisterActivity(widget);
            }
        };

        ss.setSpan(cs, 18, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerText.setText(ss);
        registerText.setMovementMethod(LinkMovementMethod.getInstance());

        Window window = EntryActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(EntryActivity.this, R.color.purple));
    }

    public void redirectToLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void redirectToRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
