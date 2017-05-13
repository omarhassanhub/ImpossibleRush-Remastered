package com.game.impossible.rush.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String KEY_SHARED_PREF_BEST_SCORE = "key_shared_pref_best_score";

    private static SharedPreferencesManager mSharedPrefManager;

    private SharedPreferences prefs;

    public void setup(Context context) {

        prefs = context.getSharedPreferences("symbolly_prefs", Context.MODE_PRIVATE);


    }

    private SharedPreferencesManager() {


    }

    public static SharedPreferencesManager getInstance() {
        if (mSharedPrefManager == null) {
            mSharedPrefManager = new SharedPreferencesManager();

        }
        return mSharedPrefManager;
    }

    public int getBestScore() {

        return prefs.getInt(KEY_SHARED_PREF_BEST_SCORE, 0);

    }

    public void setBestScore(int score) {


        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_SHARED_PREF_BEST_SCORE, score);
        editor.commit();


    }

}
