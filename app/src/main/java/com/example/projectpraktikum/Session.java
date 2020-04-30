package com.example.projectpraktikum;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    public static  String PROJECT_APP="ProjectApp";
    public static  String USERNAME="uname";
    public static  String PASSWORD="pass";
    public static  String ID="id";
    public static  String ALREADY_LOGIN="alreadyLogin";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(USERNAME,username);
        editor.apply();
    }
    public static String getUser(Context context){
        return getSharedPreference(context).getString(USERNAME,"");
    }
    public static void setPass(Context context, String password){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(PASSWORD,password);
        editor.apply();
    }
    public static String getPass(Context context){
        return getSharedPreference(context).getString(PASSWORD,"");
    }
    public static void setID(Context context, int id){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(ID, String.valueOf(id));
        editor.apply();
    }
    public static String getID(Context context){
        return getSharedPreference(context).getString(ID,"");
    }
    public static void setStatus(Context context, boolean stats){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(ALREADY_LOGIN,stats);
        editor.apply();
    }
    public static boolean getStatus(Context context){
        return getSharedPreference(context).getBoolean(ALREADY_LOGIN,false);
    }
    public static void clearSession(Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(USERNAME);
        editor.remove(PASSWORD);
        editor.apply();
    }
}
