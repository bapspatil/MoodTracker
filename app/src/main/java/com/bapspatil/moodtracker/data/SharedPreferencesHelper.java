package com.bapspatil.moodtracker.data;
/*
 ** Created by Bapusaheb Patil {@link https://bapspatil.com}
 */

import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    public static final String KEY_CURRENT_DAY = "KEY_CURRENT_DAY";

    public static final String KEY_CURRENT_MOOD = "KEY_CURRENT_MOOD";
    public static final String KEY_CURRENT_COMMENT = "KEY_CURRENT_COMMENT";

    public static final String KEY_MOOD0 = "KEY_MOOD0";
    public static final String KEY_MOOD1 = "KEY_MOOD1";
    public static final String KEY_MOOD2 = "KEY_MOOD2";
    public static final String KEY_MOOD3 = "KEY_MOOD3";
    public static final String KEY_MOOD4 = "KEY_MOOD4";
    public static final String KEY_MOOD5 = "KEY_MOOD5";
    public static final String KEY_MOOD6 = "KEY_MOOD6";

    public static final String KEY_COMMENT0 = "KEY_COMMENT0";
    public static final String KEY_COMMENT1 = "KEY_COMMENT1";
    public static final String KEY_COMMENT2 = "KEY_COMMENT2";
    public static final String KEY_COMMENT3 = "KEY_COMMENT3";
    public static final String KEY_COMMENT4 = "KEY_COMMENT4";
    public static final String KEY_COMMENT5 = "KEY_COMMENT5";
    public static final String KEY_COMMENT6 = "KEY_COMMENT6";

    public static void saveMood(int moodIndex, int currentDay, SharedPreferences sp) {
        sp.edit().putInt(KEY_CURRENT_MOOD, moodIndex).apply();
        switch (currentDay) {
            case 1:
                sp.edit().putInt(KEY_MOOD0, moodIndex).apply();
                break;
            case 2:
                sp.edit().putInt(KEY_MOOD1, moodIndex).apply();
                break;
            case 3:
                sp.edit().putInt(KEY_MOOD2, moodIndex).apply();
                break;
            case 4:
                sp.edit().putInt(KEY_MOOD3, moodIndex).apply();
                break;
            case 5:
                sp.edit().putInt(KEY_MOOD4, moodIndex).apply();
                break;
            case 6:
                sp.edit().putInt(KEY_MOOD5, moodIndex).apply();
                break;
            case 7:
                sp.edit().putInt(KEY_MOOD6, moodIndex).apply();
                break;
            default:
                sp.edit().putInt(KEY_MOOD0, sp.getInt(KEY_MOOD1, 3)).apply();
                sp.edit().putInt(KEY_MOOD1, sp.getInt(KEY_MOOD2, 3)).apply();
                sp.edit().putInt(KEY_MOOD2, sp.getInt(KEY_MOOD3, 3)).apply();
                sp.edit().putInt(KEY_MOOD3, sp.getInt(KEY_MOOD4, 3)).apply();
                sp.edit().putInt(KEY_MOOD4, sp.getInt(KEY_MOOD5, 3)).apply();
                sp.edit().putInt(KEY_MOOD5, sp.getInt(KEY_MOOD6, 3)).apply();
                sp.edit().putInt(KEY_MOOD6, moodIndex).apply();
        }
    }

    public static void saveComment(String comment, int currentDay, SharedPreferences sp) {
        sp.edit().putString(KEY_CURRENT_COMMENT, comment).apply();
        switch (currentDay) {
            case 1:
                sp.edit().putString(KEY_COMMENT0, comment).apply();
                break;
            case 2:
                sp.edit().putString(KEY_COMMENT1, comment).apply();
                break;
            case 3:
                sp.edit().putString(KEY_COMMENT2, comment).apply();
                break;
            case 4:
                sp.edit().putString(KEY_COMMENT3, comment).apply();
                break;
            case 5:
                sp.edit().putString(KEY_COMMENT4, comment).apply();
                break;
            case 6:
                sp.edit().putString(KEY_COMMENT5, comment).apply();
                break;
            case 7:
                sp.edit().putString(KEY_COMMENT6, comment).apply();
                break;
            default:
                sp.edit().putString(KEY_COMMENT0, sp.getString(KEY_COMMENT1, "")).apply();
                sp.edit().putString(KEY_COMMENT1, sp.getString(KEY_COMMENT2, "")).apply();
                sp.edit().putString(KEY_COMMENT2, sp.getString(KEY_COMMENT3, "")).apply();
                sp.edit().putString(KEY_COMMENT3, sp.getString(KEY_COMMENT4, "")).apply();
                sp.edit().putString(KEY_COMMENT4, sp.getString(KEY_COMMENT5, "")).apply();
                sp.edit().putString(KEY_COMMENT5, sp.getString(KEY_COMMENT6, "")).apply();
                sp.edit().putString(KEY_COMMENT6, comment).apply();
        }
    }
}
