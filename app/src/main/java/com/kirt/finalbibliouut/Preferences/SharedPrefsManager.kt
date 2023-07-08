package com.kirt.finalbibliouut.Preferences

import android.content.SharedPreferences
import com.kirt.finalbibliouut.Databases.Users
import com.kirt.finalbibliouut.Databases.key
import javax.inject.Inject

class keySharedPreferencesRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun write(key: key) {
        sharedPreferences.edit()
            .putString(KEY_REFRESH, key.refresh)
            .putString(KEY_ACCESS, key.access)
            .apply()
    }

    fun Videwrite() {
        sharedPreferences.edit()
            .putString(KEY_REFRESH, "")
            .putString(KEY_ACCESS, "")
            .apply()
    }

    fun read(): key {
        return key(
            refresh = sharedPreferences.getString(KEY_REFRESH, "") as String,
            access = sharedPreferences.getString(KEY_ACCESS, "") as String
        )
    }

    companion object {
        private const val KEY_REFRESH = "KEY_REFRESH"
        private const val KEY_ACCESS = "KEY_ACCESS"
    }
}

class UsersSharedPreferencesRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
)
{
    fun write(users: Users) {
        sharedPreferences.edit()
            .putInt(USERS_ID, users.id)
            .putString(USERS_EMAIL, users.email)
            .putString(USERS_USERNAME, users.username)
            .putString(USERS_PASSWORD, users.password)
            .putString(USERS_FIRST_NAME, users.first_name)
            .putString(USERS_LAST_NAME, users.last_name)
            .putBoolean(USERS_IS_ACTIVE, users.is_active)
            .apply()
    }


    fun Videwrite() {
        sharedPreferences.edit()
            .putInt(USERS_ID, 0)
            .putString(USERS_EMAIL, "")
            .putString(USERS_USERNAME, "")
            .putString(USERS_PASSWORD, "")
            .putString(USERS_FIRST_NAME, "")
            .putString(USERS_LAST_NAME, "")
            .putBoolean(USERS_IS_ACTIVE, false)
            .apply()
    }



    fun read(): Users {
        return Users(
            id = sharedPreferences.getInt(USERS_ID, 0),
            email = sharedPreferences.getString(USERS_EMAIL, "") as String,
            username = sharedPreferences.getString(USERS_USERNAME, "") as String,
            password = sharedPreferences.getString(USERS_PASSWORD, "") as String,
            first_name = sharedPreferences.getString(USERS_FIRST_NAME, "") as String,
            last_name = sharedPreferences.getString(USERS_LAST_NAME, "") as String,
            is_active = sharedPreferences.getBoolean(USERS_IS_ACTIVE, false)
        )
    }

    companion object {
        private const val USERS_ID = "USERS_ID"
        private const val USERS_EMAIL = "USERS_EMAIL"
        private const val USERS_USERNAME = "USERS_USERNAME"
        private const val USERS_PASSWORD = "USERS_PASSWORD"
        private const val USERS_FIRST_NAME = "USERS_FIRST_NAME"
        private const val USERS_LAST_NAME = "USERS_LAST_NAME"
        private const val USERS_IS_ACTIVE = "USERS_IS_ACTIVE"
    }
}



