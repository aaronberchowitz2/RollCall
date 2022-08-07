package com.example.rollcall;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class DataPersistencyHelper {

    public static android.content.Context Context;

    public static void StoreData(List<Leaders> leaders) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Context);
        SharedPreferences.Editor editor = sp.edit();
        String json = new Gson().toJson(leaders);
        editor.putString("users", json);
        editor.commit();
    }

    public static List<Leaders> LoadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Context);
        String json = sp.getString("users", null);
        if (json != null) {
            Type type = new TypeToken<List<Leaders>>() {}.getType();

            return new Gson().fromJson(json, type);
        } else {
            List<Leaders> leaders = new ArrayList<Leaders>();
            leaders.add(new Leaders(R.drawable.steve, "Steve Jobs", "15"));
            leaders.add(new Leaders(R.drawable.elon, "Elon Musk", "12"));
            leaders.add(new Leaders(R.drawable.bill, "Bill Gates", "11"));
            return leaders;
        }
    }
}
