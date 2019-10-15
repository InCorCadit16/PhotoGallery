package com.incorcadit16.photogallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class QueryPreferences {
    private static final String PREF_SEARCH_QUERY = "searchQuery";
    private static final String PREF_LAST_ID = "lastID";
    private static final String PREF_IS_ALARM_ON = "isAlarmOn";

    static String getStoreQuery(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PREF_SEARCH_QUERY,null);
    }

    static void setStoreQuery(Context context,String query) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(PREF_SEARCH_QUERY,query).apply();
    }

    static String getLastResultId(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_LAST_ID,null);
    }

    static void setLastResultId(Context context, String id) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_LAST_ID,id).apply();
    }

    static boolean isAlarmOn(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_IS_ALARM_ON,false);
    }

    static void setAlarmOn(Context context, boolean isOn) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_IS_ALARM_ON,isOn).apply();
    }
}
