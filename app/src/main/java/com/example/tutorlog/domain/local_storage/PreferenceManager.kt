package com.example.tutorlog.domain.local_storage

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context. MODE_PRIVATE)

    // Save String
    fun saveString(key:  String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    // Get String
    fun getString(key:  String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    // Save Boolean
    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    // Get Boolean
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    // Save Int
    fun saveInt(key: String, value:  Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    // Get Int
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    // Save Long
    fun saveLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    // Get Long
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    // Clear all data
    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    // Remove specific key
    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}