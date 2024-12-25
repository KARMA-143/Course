package com.example.mobileapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

public class SharedPrefsHelper {

    private static final String PREF_NAME = "my_preferences";
    private static final String KEY_JWT_TOKEN = "jwt_token";

    public static void saveJwtToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_JWT_TOKEN, token);
        editor.apply();
    }

    public static String getJwtToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_JWT_TOKEN, null);
    }

    public static void clearJwtToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_JWT_TOKEN);
        editor.apply();
    }

    public static boolean isTokenValid(String token) {
        try {
            JWT jwt = new JWT(token);
            return !jwt.isExpired(10);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
