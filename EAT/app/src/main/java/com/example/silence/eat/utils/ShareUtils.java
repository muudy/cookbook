package com.example.silence.eat.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareUtils {
	private static SharedPreferences sp;
	public static void saveBoolleanData(Context context,String key,boolean value) {
		if(sp==null){
			sp=context.getSharedPreferences(key, Context.MODE_PRIVATE);
		}
		sp.edit().putBoolean(key, value).commit();
	}
	
	public static boolean getBoolleanData(Context context,String key,boolean value) {
		if(sp==null){
			sp=context.getSharedPreferences(key, Context.MODE_PRIVATE);
		}
		return sp.getBoolean(key, true);
	}
}
