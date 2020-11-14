package com.les.topsession.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenSave {
    final static String FileName = "token";

    public static String readSharedSetting(Context context, String settingName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(settingName, defaultValue);
    }

    public static void saveSharedSetting(Context context, String settingName, String settingValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }
}
